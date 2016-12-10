/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct60a2411.harkkatyö;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Petri
 */
public class CreateNewController implements Initializable {
    
    private SmartPosts smartPosts = SmartPosts.getInstance();
    
    @FXML
    private ComboBox<String> objectsCombo;
    @FXML
    private TextField nameField;
    @FXML
    private TextField sizeField;
    @FXML
    private TextField massField;
    @FXML
    private CheckBox fragile;
    @FXML
    private ComboBox<String> packageClass;
    @FXML
    private ComboBox<String> startCityCombo;
    @FXML
    private ComboBox<String> startAutoCombo;
    @FXML
    private ComboBox<String> endCityCombo;
    @FXML
    private ComboBox<String> endAutoCombo;
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
        try {
            XMLReader xmlr = new XMLReader();
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(CreateNewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        startCityCombo.getItems().addAll(smartPosts.getCities());
        endCityCombo.getItems().addAll(smartPosts.getCities());
        objectsCombo.getItems().addAll(Product.getProductList());
        startCityCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                
                String place = t1.toString();
                for (SmartPost sPost : smartPosts.getCitySmartPosts(place)) {
                    startAutoCombo.getItems().add(sPost.getPostoffice() + " " + sPost.getAddress());    
                }
            }
        });
        endCityCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                
                String place = t1.toString();
                for (SmartPost sPost : smartPosts.getCitySmartPosts(place)) {
                    endAutoCombo.getItems().add(sPost.getPostoffice() + " " + sPost.getAddress());    
                }
            }
        });
        packageClass.getItems().add("1. luokka");
        packageClass.getItems().add("2. luokka");
        packageClass.getItems().add("3. luokka");
    }    

    @FXML
    private void infoButAction(ActionEvent event) throws IOException {
        Stage newPackage = new Stage();
        
        Parent page = FXMLLoader.load(getClass().getResource("InfoBox.fxml"));
        Scene scene = new Scene(page);
        
        newPackage.setScene(scene);
        newPackage.show();
    }

    @FXML
    private void returnButAction(ActionEvent event) {
        Stage stage = (Stage) returnBut.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void createButAction(ActionEvent event) {
    }

    @FXML
    private void startAutoAction(InputMethodEvent event) {
        String place = startCityCombo.getValue();
        for (SmartPost sPost : smartPosts.getCitySmartPosts(place)) {
            startAutoCombo.getItems().add(sPost.getPostoffice() + " " + sPost.getAddress());    
        }
    }

    @FXML
    private void startAutoAction(ContextMenuEvent event) {
    }
    
}
