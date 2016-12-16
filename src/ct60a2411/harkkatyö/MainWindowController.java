/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct60a2411.harkkatyö;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Ilari
 */
public class MainWindowController implements Initializable {
    
    private ArrayList<SmartPost> SPList;
    private SmartPostContainer smartPosts = SmartPostContainer.getInstance();
    private Warehouse warehouse = Warehouse.getInstance();
    private LogWriter lw;
    
    @FXML
    private ComboBox<String> autoCombo;
    @FXML
    private Button addToMap;
    @FXML
    private Button createBut;
    @FXML
    private ComboBox<Parcel> packageCombo;
    @FXML
    private Button removeRoute;
    @FXML
    private Button sendBut;
    @FXML
    private WebView web;
    @FXML
    private Tab log;
    @FXML
    private TextArea logArea;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Tab smartpost;
    @FXML
    private Label sessionStarted;
    @FXML
    private Label sentParcelCounter;
    @FXML
    private Label distanceCounter;
    
    /**
     * This class contains all all functions that first window uses. At initialize()
     * it initializes comboboxes, webview and log file.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        
        XMLReader xmlr = XMLReader.getInstance();
        autoCombo.getItems().addAll(smartPosts.getCities());
        autoCombo.getSelectionModel().selectFirst();
        web.getEngine().load(getClass().getResource("index.html").toExternalForm());
        loadParcels();
        try {
            lw = LogWriter.getInstance();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sessionStarted.setText(df.format(date));
        sentParcelCounter.setText("0");
        distanceCounter.setText("0.0");
    }    

    /**
     * This button prints all automatons of selected city.
     * @param event 
     */
    @FXML
    private void addToMapAction(ActionEvent event) {
        String name = autoCombo.getValue();
        for (SmartPost sPost : smartPosts.getCitySmartPosts(name)) {
            String open = "<p>" + sPost.getPostoffice() + "</p><p>Auki: " + sPost.getAvailability() + "</p>";
            web.getEngine().executeScript("document.goToLocation(" + sPost.getLat() + "," + sPost.getLng() + ",'" + open + "', 'blue')");
        }
        
        
    }
    
    /**
     * This button opens up a window where you can make a new package.
     * @param event
     * @throws IOException 
     */
    

    @FXML
    private void createButAction(ActionEvent event) throws IOException {
        // Web-elementin vienti uuteen ikkunaan matkojen laskutoimitusta varten
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateParcelWindow.fxml"));
        Parent root = loader.load();        
        root.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        Stage newPackage = new Stage();
        newPackage.setScene(new Scene(root));
        CreateParcelWindowController controller = loader.getController();
        controller.setWeb(web);
        controller.setParcelBox(packageCombo);
        controller.setSendBut(sendBut);
        newPackage.setTitle("TIMO - luo paketti");
        newPackage.getIcons().add(new Image(getClass().getResourceAsStream("assets/timo_icon.png")));
        newPackage.getIcons().add(new Image(getClass().getResourceAsStream("assets/timo_icon_big.png")));
        newPackage.setResizable(false);
        newPackage.initModality(Modality.APPLICATION_MODAL);
        newPackage.showAndWait();
    }
    
    /**
     * This button all routes and automaton from map.
     * @param event 
     */
    @FXML
    private void removeRouteAction(ActionEvent event) {
        web.getEngine().executeScript("document.deletePaths()");
    }

    private void loadParcels() {
        if (Warehouse.getParcels().isEmpty()) {
            packageCombo.setDisable(true);
            sendBut.setDisable(true);
        } else {
            packageCombo.getItems().addAll(Warehouse.getParcels());
            packageCombo.getSelectionModel().selectFirst();
            
        }
    }

    /**
     * This button sends the package that you have made and selected. When you do this
     * it puts beginning and end automaton to the maps and starts drawing route that it uses
     * to get to these points.
     * @param event
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    @FXML
    private void sendButAction(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        boolean broke;
        
        Parcel parcel = packageCombo.getValue();
        SmartPost startPost = SmartPostContainer.getInstance().getSmartPost(parcel.getStartPost());
        SmartPost endPost = SmartPostContainer.getInstance().getSmartPost(parcel.getEndPost());
        ArrayList<Double> array = new ArrayList();
        array.add(startPost.getLat());
        array.add(startPost.getLng());
        array.add(endPost.getLat());
        array.add(endPost.getLng());
        String stringDistance = String.valueOf(web.getEngine().executeScript("document.pathDist(" + array + ")"));
        String color = "red";
        
        String open = "<p>" + startPost.getPostoffice() + "</p><p>Auki: " + startPost.getAvailability() + "</p>";
        web.getEngine().executeScript("document.goToLocation(" + startPost.getLat() + "," + startPost.getLng() + ",'" + open + "', 'blue')");
        open = "<p>" + endPost.getPostoffice() + "</p><p>Auki: " + endPost.getAvailability() + "</p>";
        web.getEngine().executeScript("document.goToLocation(" + endPost.getLat() + "," + endPost.getLng() + ",'" + open + "', 'blue')");
        
        web.getEngine().executeScript("document.createPath(" + array + ",'" + color + "'," + parcel.getGrade() + ",'" + parcel.getItem().getName() + "')");        
        
        sentParcelCounter.setText(String.valueOf(Integer.parseInt(sentParcelCounter.getText()) + 1));
        distanceCounter.setText(String.valueOf(Double.parseDouble(distanceCounter.getText()) + Double.parseDouble(stringDistance)));
        
        String name = parcel.getItem().getName();
        String start = startPost.getAddress() + ", " + startPost.getCity();
        String end = endPost.getAddress() + ", " + endPost.getCity();
        if (parcel.getItem().isFragile() && (parcel.getItem().getFragile_factor() < parcel.getFragile_factor())) {
            broke = true;
        } else {
            broke = false;
        }
        
        
        lw.writer(name, start, end, broke, stringDistance);
        
        warehouse.deleteParcel(parcel);
        packageCombo.getSelectionModel().clearSelection();
        packageCombo.getItems().clear();
        loadParcels();
        
        if (!logArea.getText().trim().isEmpty()){
            logArea.clear();
        }
            
        try {
            String loggerino = lw.reader();
            logArea.setText(loggerino);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

}
