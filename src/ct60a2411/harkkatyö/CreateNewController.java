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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Petri
 */
public class CreateNewController implements Initializable {

    @FXML
    private ComboBox<?> objectsCombo;
    @FXML
    private TextField nameField;
    @FXML
    private TextField sizeField;
    @FXML
    private TextField massField;
    @FXML
    private CheckBox fragile;
    @FXML
    private ComboBox<?> packageClass;
    @FXML
    private ComboBox<?> startCityCombo;
    @FXML
    private ComboBox<?> startAutoCombo;
    @FXML
    private ComboBox<?> endCityCombo;
    @FXML
    private ComboBox<?> endAutoCombo;
    @FXML
    private Button infoBut;
    @FXML
    private Button returnBut;
    @FXML
    private Button createBut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void infoButAction(ActionEvent event) {
    }

    @FXML
    private void returnButAction(ActionEvent event) {
    }

    @FXML
    private void createButAction(ActionEvent event) {
    }
    
}
