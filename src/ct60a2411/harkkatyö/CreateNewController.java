/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct60a2411.harkkatyö;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javafx.scene.web.WebView;

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
    private ComboBox<SmartPost> startAutoCombo;
    @FXML
    private ComboBox<String> endCityCombo;
    @FXML
    private ComboBox<SmartPost> endAutoCombo;
    @FXML
    private Button infoBut;
    @FXML
    private Button returnBut;
    @FXML
    private Button createBut;
    @FXML
    private WebView web;

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
                    startAutoCombo.getItems().add(sPost);    
                }
            }
        });
        endCityCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                endAutoCombo.getItems().clear();
                String place = t1.toString();
                for (SmartPost sPost : smartPosts.getCitySmartPosts(place)) {
                    endAutoCombo.getItems().add(sPost);    
                }
            }
        });
        packageClass.getItems().add("1. luokka");
        packageClass.getItems().add("2. luokka");
        packageClass.getItems().add("3. luokka");
        fragile.setSelected(true);
    }
    
    public void setWeb(WebView w) {
        web = w;
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
        Product item;
        Parcel parcel;
        String parcelGrade = packageClass.getValue();
        
        if (testReadyProduct() == null) {
            item = testCustomProduct();
        } else {
            switch (objectsCombo.getValue()) {
                case "Valkoiset Vansit":
                    item = new Vans();
                    break;
                case "Haramben luut":
                    item = new HarambeBones();
                    break;
                case "Muutama risu":
                    item = new Twigs();
                    break;
                case "Trumpin tupee":
                    item = new TrumpWig();
                    break;
                default:
                    item = null;
                    break;
            }
        }
        
        if ((item == null) || (testStartPost() == false) || (testEndPost() == false) || (testParcelGrade() == false)) {
            return;
        }
        
        switch (parcelGrade) {
            case "1. luokka":
                parcel = new ParcelGrade1();
                break;
            case "2. luokka":
                parcel = new ParcelGrade2();
                break;
            case "3. luokka":
                parcel = new ParcelGrade3();
                break;
            default:
                parcel = null;
                break;
        }
        
        SmartPost start = startAutoCombo.getValue();
        SmartPost end = endAutoCombo.getValue();
        parcel.startPost = start.getId();
        parcel.endPost = end.getId();
        
        if (!testDistance(start, end, parcel)) {
            System.out.println("Paketti hylätty (pitkä matka)");
            return;
        }
        
        if (!testDimension(item, parcel)) {
            System.out.println("Paketti hylätty (väärä koko)");
        } else {
            System.out.println("Paketti luotu!");
            Warehouse.wh.addParcel(parcel);
        }
    }
    
    private String testReadyProduct() {
        if (objectsCombo.getSelectionModel().isEmpty()) {
            return null;
        } else {
            return objectsCombo.getValue();
        }
    }
    
    // Katsotaan, onko String numero vai ei
    // http://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    private boolean isNumeric(String s) {
        try {
            double d = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    private Product testCustomProduct() {
        String[] sizeParts = sizeField.getText().trim().split(",");
        String productName = nameField.getText().trim();
        String productMass = massField.getText().trim();
        boolean valid = true;
        
        if (!isNumeric(productMass)) {
            valid = false;
        }
        
        if (sizeParts.length == 3) {
            for (String str : sizeParts) {
                if (!isNumeric(str)) {
                    valid = false;
                    break;
                }
            }
        } else {
            valid = false;
        }
        
        if (valid == true) {
            return new Product(Double.parseDouble(productMass), Double.parseDouble(sizeParts[0]),
                    Double.parseDouble(sizeParts[1]), Double.parseDouble(sizeParts[2]), productName);
        } else {
            return null;
        }
    }
    
    private boolean testStartPost() {
        if (startAutoCombo.getSelectionModel().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean testEndPost() {
        if (endAutoCombo.getSelectionModel().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean testParcelGrade() {
        if (packageClass.getSelectionModel().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean testDimension(Product pr, Parcel pa) {
        double proWeight = pr.dimension.get("weight");
        double parWeight = pa.limit_map.get("weight");
        
        ArrayList<Double> proSize = new ArrayList();
        ArrayList<Double> parSize = new ArrayList();
        
        proSize.add(pr.dimension.get("depth"));
        proSize.add(pr.dimension.get("width"));
        proSize.add(pr.dimension.get("height"));
        Collections.sort(proSize);

        parSize.add(pa.limit_map.get("depth"));
        parSize.add(pa.limit_map.get("width"));
        parSize.add(pa.limit_map.get("height"));
        Collections.sort(parSize);
        
        for (int i = 0; i < 3; i++) {
            if (parSize.get(i) < proSize.get(i)) {
                return false;
            }
        }
        
        if (proWeight > parWeight) {
            return false;
        } else {
            return true;
        }        
    }
    
    private boolean testDistance(SmartPost start, SmartPost end, Parcel par) {
        ArrayList<Double> s = new ArrayList<>();
        s.add(start.getLat());
        s.add(start.getLng());
        s.add(end.getLat());
        s.add(end.getLng());

        String dist = web.getEngine().executeScript("document.pathDist(" + s + ")").toString();        
        Double km = Double.parseDouble(dist);

        if ((km > 150.0) && (par.grade == 1)) {
            return false;
        } else {
            return true;
        }
    }
}
