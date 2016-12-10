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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebView;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Ilari
 */
public class PakettiautomaattiController implements Initializable {
    
    XMLReader XMLR;
    private ArrayList<SmartPost> SPList;
    
    @FXML
    private ComboBox<SmartPost> autoCombo;
    @FXML
    private Button addToMap;
    @FXML
    private Button createBut;
    @FXML
    private ComboBox<?> packageCombo;
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
        try {
            SPList = XMLR.XMLReader();
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(PakettiautomaattiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        web.getEngine().load(getClass().getResource("index.html").toExternalForm());
        autoCombo.getItems().addAll(SPList);
            
        
    }    

    @FXML
    private void addToMapAction(ActionEvent event) {
    }

    @FXML
    private void createButAction(ActionEvent event) {
    }

    @FXML
    private void removeRouteAction(ActionEvent event) {
    }

    @FXML
    private void refreshAction(ActionEvent event) {
    }

    @FXML
    private void sendButAction(ActionEvent event) {
    }
    
}
