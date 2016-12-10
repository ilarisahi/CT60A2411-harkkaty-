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
import java.util.Collections;
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
    ArrayList<Double> size;
    
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
        XMLReader xmlr = XMLReader.getInstance();
        Warehouse wh = Warehouse.getInstance();
        startCityCombo.getItems().addAll(smartPosts.getCities());
        endCityCombo.getItems().addAll(smartPosts.getCities());
        objectsCombo.getItems().addAll(Product.getProductList());
        startCityCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                startAutoCombo.getItems().clear();
                String place = t1.toString();
                for (SmartPost sPost : smartPosts.getCitySmartPosts(place)) {
                    startAutoCombo.getItems().add(sPost.getPostoffice() + " " + sPost.getAddress());    
                }
            }
        });
        endCityCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                endAutoCombo.getItems().clear();
                String place = t1.toString();
                for (SmartPost sPost : smartPosts.getCitySmartPosts(place)) {
                    endAutoCombo.getItems().add(sPost.getPostoffice() + " " + sPost.getAddress());    
                }
            }
        });
        packageClass.getItems().add("1. luokka");
        packageClass.getItems().add("2. luokka");
        packageClass.getItems().add("3. luokka");
        fragile.setSelected(true);
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
        ArrayList<Double> size = new ArrayList<>();
        ArrayList<Double> size2 = new ArrayList<>();
        Product pro = null;
        String productName = objectsCombo.getValue();
        boolean valid = true;
        
        if (!objectsCombo.getSelectionModel().isEmpty()) {
            switch (objectsCombo.getValue()) {
                case "Valkoiset Vansit":
                    pro = new Vans();
                    break;
                case "Haramben luut":
                    pro = new HarambeBones();
                    break;
                case "Muutama risu":
                    pro = new Twigs();
                    break;
                case "Trumpin tupee":
                    pro = new TrumpWig();
                    break;
            }
        } else {
            String[] parts = sizeField.getText().split(",");
            if ((parts.length == 3) && (!massField.getText().isEmpty()) && (!nameField.getText().isEmpty())) {
                size.add(Double.parseDouble(parts[0]));
                size.add(Double.parseDouble(parts[1]));
                size.add(Double.parseDouble(parts[2]));
                Collections.sort(size);                
                pro = new Product(Double.parseDouble(massField.getText()), size.get(0), size.get(1), size.get(2), nameField.getText());
            } else {
                System.out.println("Olet huono ihminen");
                return;
            }
        }
        
        String parcelGrade = packageClass.getValue();
        System.out.println(parcelGrade);
        Parcel parcel;
        
        if (!packageClass.getSelectionModel().isEmpty()) {
            if (parcelGrade.equals("1. luokka")) {
                parcel = new ParcelGrade1();
            } else if (parcelGrade.equals("2. luokka")) {
                parcel = new ParcelGrade2();
            } else {
                parcel = new ParcelGrade3();
            }
            
            if (parcel.limit_map.get("weight") >= pro.dimension.get("weight")) {
                size.clear();
                size.add(pro.dimension.get("depth"));
                size.add(pro.dimension.get("width"));
                size.add(pro.dimension.get("height"));
                Collections.sort(size);
                
                size2.add(parcel.limit_map.get("depth"));
                size2.add(parcel.limit_map.get("width"));
                size2.add(parcel.limit_map.get("height"));
                Collections.sort(size2);
                
                for (int i = 0; i < 3; i++) {
                    if (size2.get(i) >= size.get(i)) {
                    } else {
                        System.out.println("Ei täytä mittoja!");
                        valid = false;
                        break;
                    }
                }

            } else {
                System.out.println("Ei täytä painoa!");
                valid = false;
            }
            
            if (valid == true) {
                Warehouse.wh.addParcel(parcel);
            }
        } else {
            System.out.println("Olet huono ihminen");
        }
        
        
    }

    private void startAutoAction(InputMethodEvent event) {
        String place = startCityCombo.getValue();
        for (SmartPost sPost : smartPosts.getCitySmartPosts(place)) {
            startAutoCombo.getItems().add(sPost.getPostoffice() + " " + sPost.getAddress());    
        }
    }
}
