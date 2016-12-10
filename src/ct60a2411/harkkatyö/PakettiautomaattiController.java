/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct60a2411.harkkatyö;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
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
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Ilari
 */
public class PakettiautomaattiController implements Initializable {
    
    private ArrayList<SmartPost> SPList;
    private SmartPosts smartPosts = SmartPosts.getInstance();
    private Warehouse warehouse = Warehouse.getInstance(); 
    
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
    private Button refreshBut;
    @FXML
    private Button sendBut;
    @FXML
    private WebView web;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XMLReader xmlr = XMLReader.getInstance();
        autoCombo.getItems().addAll(smartPosts.getCities());
        web.getEngine().load(getClass().getResource("index.html").toExternalForm());
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
        Stage newPackage = new Stage();
        newPackage.setScene(new Scene(loader.load()));
        CreateNewController controller = loader.<CreateNewController>getController();
        controller.setWeb(web);
        
        newPackage.show();
    }

    @FXML
    private void removeRouteAction(ActionEvent event) {
        web.getEngine().executeScript("document.deletePaths()");
    }

    @FXML
    private void refreshAction(ActionEvent event) {
        ArrayList<String> lel = new ArrayList<>();
        lel.add("60.96122");
        lel.add("25.6582995");
        lel.add("60.867886");
        lel.add("26.7041295");
        Object s = web.getEngine().executeScript("document.pathDist(" + lel + ")");
        System.out.println(s);
        packageCombo.getItems().clear();
        Parcel parcel = new ParcelGrade1();
        parcel.item = new Twigs();
        parcel.startPost = SmartPosts.getInstance().getCitySmartPosts("ESPOO").get(0);
        parcel.endPost = SmartPosts.getInstance().getCitySmartPosts("TORNIO").get(0);
        warehouse.addParcel(parcel);
        packageCombo.getItems().addAll(warehouse.getParcels());
        
    }

    @FXML
    private void sendButAction(ActionEvent event) {
        Parcel parcel = packageCombo.getValue();
        ArrayList<Double> array = new ArrayList();
        array.add(parcel.startPost.getLat());
        array.add(parcel.startPost.getLng());
        array.add(parcel.endPost.getLat());
        array.add(parcel.endPost.getLng());
        String color = "blue";
        
        web.getEngine().executeScript("document.createPath(" + array + ",'" + color + "'," + parcel.grade + ")");
    }
    
}
