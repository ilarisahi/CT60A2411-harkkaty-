/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct60a2411.harkkatyö;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebView;

/**
 *
 * @author Ilari
 */
public class PakettiautomaattiController implements Initializable {
    
    @FXML
    private ComboBox<?> autoCombo;
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
        web.getEngine().load(getClass().getResource("index.html").toExternalForm());
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
