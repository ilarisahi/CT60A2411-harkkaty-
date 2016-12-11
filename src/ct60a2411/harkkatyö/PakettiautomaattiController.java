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
import java.util.ArrayList;
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
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Ilari
 */
public class PakettiautomaattiController implements Initializable {
    
    private ArrayList<SmartPost> SPList;
    private SmartPosts smartPosts = SmartPosts.getInstance();
    private Warehouse warehouse = Warehouse.getInstance();
    private LokiWriter lw;
    
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XMLReader xmlr = XMLReader.getInstance();
        autoCombo.getItems().addAll(smartPosts.getCities());
        autoCombo.getSelectionModel().selectFirst();
        web.getEngine().load(getClass().getResource("index.html").toExternalForm());
        loadParcels();
        try {
            lw = LokiWriter.getInstance();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(PakettiautomaattiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void addToMapAction(ActionEvent event) {
        String name = autoCombo.getValue();
        for (SmartPost sPost : smartPosts.getCitySmartPosts(name)) {
            String open = sPost.getPostoffice() + "Auki: " + sPost.getAvailability();
            web.getEngine().executeScript("document.goToLocation(" + sPost.getLat() + "," + sPost.getLng() + ",'" + open + "', 'blue')");
        }
        
        
    }

    @FXML
    private void createButAction(ActionEvent event) throws IOException {
        // Web-elementin vienti uuteen ikkunaan matkojen laskutoimitusta varten
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateNew.fxml"));
        Parent root = loader.load();        
        root.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        Stage newPackage = new Stage();
        newPackage.setScene(new Scene(root));
        CreateNewController controller = loader.<CreateNewController>getController();
        controller.setWeb(web);
        controller.setParcelBox(packageCombo);
        
        newPackage.show();
    }

    @FXML
    private void removeRouteAction(ActionEvent event) {
        web.getEngine().executeScript("document.deletePaths()");
    }

    private void loadParcels() {
        if (Warehouse.getParcels().isEmpty()) {
            packageCombo.setDisable(true);
        } else {
            packageCombo.getItems().addAll(Warehouse.getParcels());
            packageCombo.getSelectionModel().selectFirst();
            
        }
    }

    @FXML
    private void sendButAction(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        Parcel parcel = packageCombo.getValue();
        SmartPost startPost = SmartPosts.getInstance().getSmartPost(parcel.startPost);
        SmartPost endPost = SmartPosts.getInstance().getSmartPost(parcel.endPost);
        ArrayList<Double> array = new ArrayList();
        array.add(startPost.getLat());
        array.add(startPost.getLng());
        array.add(endPost.getLat());
        array.add(endPost.getLng());
        String color = "red";
        
        String open = startPost.getPostoffice() + "Auki: " + startPost.getAvailability();
        web.getEngine().executeScript("document.goToLocation(" + startPost.getLat() + "," + startPost.getLng() + ",'" + open + "', 'blue')");
        open = endPost.getPostoffice() + "Auki: " + endPost.getAvailability();
        web.getEngine().executeScript("document.goToLocation(" + endPost.getLat() + "," + endPost.getLng() + ",'" + open + "', 'blue')");
        
        web.getEngine().executeScript("document.createPath(" + array + ",'" + color + "'," + parcel.grade + ")");
        
        String name = parcel.item.name;
        String start = startPost.getAddress() + ", " + startPost.getCity();
        String end = endPost.getAddress() + ", " + endPost.getCity();
        String broke = "lul";
        
        lw.writer(name, start, end, broke);
        
    }    
}