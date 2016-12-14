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
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Petri
 */
public class CreateParcelWindowController implements Initializable {
    
    private SmartPostContainer smartPosts = SmartPostContainer.getInstance();
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
    @FXML
    private ComboBox parcelBox;
    @FXML
    private Button sendButton;
    
    PseudoClass errorClass = PseudoClass.getPseudoClass("error");
    PseudoClass focusClass = PseudoClass.getPseudoClass("focused");
    private Product item = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XMLReader xmlr = XMLReader.getInstance();
        Warehouse wh = Warehouse.getInstance();
        startCityCombo.getItems().addAll(smartPosts.getCities());
        endCityCombo.getItems().addAll(smartPosts.getCities());
        startAutoCombo.setDisable(true);
        endAutoCombo.setDisable(true);
        objectsCombo.getItems().addAll(Product.getProductList());
        startCityCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                startAutoCombo.getItems().clear();
                String place = t1.toString();
                for (SmartPost sPost : smartPosts.getCitySmartPosts(place)) {
                    startAutoCombo.getItems().add(sPost);    
                }
                
                if (startAutoCombo.isDisable()) {
                    startAutoCombo.setDisable(false);
                }
                startAutoCombo.getSelectionModel().selectFirst();
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
                
                if (endAutoCombo.isDisable()) {
                    endAutoCombo.setDisable(false);
                }
                endAutoCombo.getSelectionModel().selectFirst();
            }
        });
        objectsCombo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
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
                
                nameField.setText(item.getName());
                nameField.setDisable(true);
                sizeField.setText(item.getDimension().get("height").toString() + "*" + item.getDimension().get("width").toString() + "*" + item.getDimension().get("depth").toString());
                sizeField.setDisable(true);
                massField.setText(item.getDimension().get("height").toString());
                massField.setDisable(true);
            }        
        });
        
        packageClass.getItems().add("1. luokka");
        packageClass.getItems().add("2. luokka");
        packageClass.getItems().add("3. luokka");
        fragile.setSelected(true);
        packageClass.getSelectionModel().selectFirst();
    }
    
    public void setWeb(WebView w) {
        web = w;
    }
    
    public void setParcelBox(ComboBox cb) {
        parcelBox = cb;
    }
    
    void setSendBut(Button sb) {
        sendButton = sb;
    }

    @FXML
    private void infoButAction(ActionEvent event) throws IOException {
        Stage newPackage = new Stage();        
        Parent page = FXMLLoader.load(getClass().getResource("InfoBox.fxml"));        
        page.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        Scene scene = new Scene(page);
        
        newPackage.setScene(scene);
        newPackage.setTitle("TIMO - paketti-info");
        newPackage.getIcons().add(new Image(getClass().getResourceAsStream("assets/timo_icon.png")));
        newPackage.getIcons().add(new Image(getClass().getResourceAsStream("assets/timo_icon_big.png")));
        newPackage.setResizable(false);
        newPackage.initModality(Modality.APPLICATION_MODAL);
        newPackage.showAndWait();
    }

    @FXML
    private void returnButAction(ActionEvent event) {
        Stage stage = (Stage) returnBut.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void createButAction(ActionEvent event) {
        Parcel parcel = null;
        boolean validParcel = true;
        String parcelGrade = packageClass.getValue();
        
        if (testReadyProduct() == null) {
            item = testCustomProduct();
        }
        
        if(item == null) {
            validParcel = false;
        }
        if(testStartPost() == false) {
            validParcel = false;
        }
        if (testEndPost() == false) {
            validParcel = false;
        }
        if (testParcelGrade() == false) {
            validParcel = false;
        }
        
        if (validParcel) {
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
            parcel.setStartPost(start.getId());
            parcel.setEndPost(end.getId());
            parcel.setItem(item);

            if (!testDistance(start, end, parcel)) {
                System.out.println("Paketti hylätty (pitkä matka)");
                validParcel = false;
            }

            if (!testDimension(item, parcel)) {
                System.out.println("Paketti hylätty (väärä koko)");
                validParcel = false;
            }
        }
            
        if (validParcel) {
            System.out.println("Paketti luotu!");
            Warehouse.wh.addParcel(parcel);
            updateParcelBox();
        }
    }
    
    private String testReadyProduct() {
        if (objectsCombo.getSelectionModel().isEmpty()) {
            return null;
        } else {
            changeError(nameField, false);
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
        String[] sizeParts = sizeField.getText().trim().split("\\*");
        String productName = nameField.getText().trim();
        String productMass = massField.getText().trim();
        boolean valid = true;
        
        if (productName.equals("")) {
            changeError(nameField, true);
            valid = false;
        } else {
            changeError(nameField, false);
        }
        
        if (!isNumeric(productMass)) {
            changeError(massField, true);
            valid = false;
        }
        
        if (sizeParts.length == 3) {
            for (String str : sizeParts) {
                if (!isNumeric(str)) {
                    changeError(sizeField, true);
                    valid = false;
                    break;
                }
            }
        } else {
            changeError(sizeField, true);
            valid = false;
        }
        
        if (valid) {
            return new Product(Double.parseDouble(productMass), Double.parseDouble(sizeParts[0]),
                    Double.parseDouble(sizeParts[1]), Double.parseDouble(sizeParts[2]), productName);
        } else {
            return null;
        }
    }
    
    private boolean testStartPost() {
        if (startAutoCombo.getSelectionModel().isEmpty()) {
            changeError(startCityCombo, true);
            return false;
        } else {
            changeError(startCityCombo, false);
            return true;
        }
    }
    
    private boolean testEndPost() {
        if (endAutoCombo.getSelectionModel().isEmpty()) {
            changeError(endCityCombo, true);
            return false;
        } else {
            changeError(endCityCombo, false);
            return true;
        }
    }
    
    private boolean testParcelGrade() {
        if (packageClass.getSelectionModel().isEmpty()) {
            changeError(packageClass, true);
            return false;
        } else {
            changeError(packageClass, false);
            return true;
        }
    }
    
    private boolean testDimension(Product pr, Parcel pa) {
        boolean validDimension = true;
        boolean validWeight;
        double proWeight = pr.getDimension().get("weight");
        double parWeight = pa.getLimit_map().get("weight");
        
        ArrayList<Double> proSize = new ArrayList();
        ArrayList<Double> parSize = new ArrayList();
        
        proSize.add(pr.getDimension().get("depth"));
        proSize.add(pr.getDimension().get("width"));
        proSize.add(pr.getDimension().get("height"));
        Collections.sort(proSize);

        parSize.add(pa.getLimit_map().get("depth"));
        parSize.add(pa.getLimit_map().get("width"));
        parSize.add(pa.getLimit_map().get("height"));
        Collections.sort(parSize);
        
        for (int i = 0; i < 3; i++) {
            if (parSize.get(i) < proSize.get(i)) {
                changeError(sizeField, true);
                validDimension = false;
                break;
            }
        }
        
        if (validDimension) {
            changeError(sizeField, false);
        }
        
        if (proWeight > parWeight) {
            changeError(massField, true);
            validWeight = false;
        } else {
            changeError(massField, false);
            validWeight = true;
        }
        
        if (validDimension && validWeight) {
            return true;
        } else {
            return false;
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

        if ((km > 150.0) && (par.getGrade() == 1)) {
            return false;
        } else {
            return true;
        }
    }
    
    private void updateParcelBox() {
        parcelBox.getItems().clear();
        parcelBox.getItems().addAll(Warehouse.getParcels());
        parcelBox.getSelectionModel().selectFirst();

        if (parcelBox.isDisable()) {
            parcelBox.setDisable(false);
        }
        
        if (sendButton.isDisable()) {
            sendButton.setDisable(false);
        }
    }
    
    private void changeError(Control c, boolean b) {
        c.pseudoClassStateChanged(errorClass, b);
        c.pseudoClassStateChanged(focusClass, b);
    }
}