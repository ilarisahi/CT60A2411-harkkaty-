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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Petri Rämö
 * opiskelijanro:0438578
 * 16.12.2016
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
    private WebView web;
    private ComboBox parcelBox;
    private Button sendButton;
    
    PseudoClass errorClass = PseudoClass.getPseudoClass("error");
    PseudoClass focusClass = PseudoClass.getPseudoClass("focused");
    private Product item = null;
    @FXML
    private Label errorLabel;

    /**
     * Tämä luokka sisältää, mitä kaikki näppäimet ja comboboksit tekevät uuden
     * paketin luonnissa.
     * 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XMLReader xmlr = XMLReader.getInstance();
        Warehouse wh = Warehouse.getInstance();
        nameField.setDisable(true);
        sizeField.setDisable(true);
        massField.setDisable(true);
        fragile.setDisable(true);
        startCityCombo.getItems().addAll(smartPosts.getCities());
        endCityCombo.getItems().addAll(smartPosts.getCities());
        startAutoCombo.setDisable(true);
        endAutoCombo.setDisable(true);
        objectsCombo.getItems().addAll(Product.getProductList());        
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
    private void objectsComboAction(ActionEvent event) {
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

        if (item == null) {
            nameField.clear();
            nameField.setDisable(false);
            sizeField.clear();
            sizeField.setDisable(false);
            massField.clear();
            massField.setDisable(false);
            fragile.setSelected(false);
            fragile.setDisable(false);
        } else {
            nameField.setText(item.getName());
            nameField.setDisable(true);
            sizeField.setText(item.getDimension().get("height").toString() + "*" + item.getDimension().get("width").toString() + "*" + item.getDimension().get("depth").toString());
            sizeField.setDisable(true);
            massField.setText(item.getDimension().get("height").toString());
            massField.setDisable(true);
            fragile.setSelected(item.isFragile());
            fragile.setDisable(true);
        }
    }

    
    @FXML
    private void startCityComboAction(ActionEvent event) {
        startAutoCombo.getItems().clear();
        String place = startCityCombo.getValue();
        for (SmartPost sPost : smartPosts.getCitySmartPosts(place)) {
            startAutoCombo.getItems().add(sPost);    
        }

        if (startAutoCombo.isDisable()) {
            startAutoCombo.setDisable(false);
        }
        startAutoCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void endCityComboAction(ActionEvent event) {
        endAutoCombo.getItems().clear();
        String place = endCityCombo.getValue();
        for (SmartPost sPost : smartPosts.getCitySmartPosts(place)) {
            endAutoCombo.getItems().add(sPost);    
        }

        if (endAutoCombo.isDisable()) {
            endAutoCombo.setDisable(false);
        }
        endAutoCombo.getSelectionModel().selectFirst();
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
        
        errorLabel.setText("");
        
        if (objectsCombo.getSelectionModel().isEmpty()) {
            errorLabel.setText("Valitse tuote");
            changeError(objectsCombo, true);
            return;
        } else {
            changeError(objectsCombo, false);
        }
        
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
                validParcel = false;
            }

            if (!testDimension(item, parcel)) {
                validParcel = false;
            }
        }
            
        if (validParcel) {
            Warehouse.wh.addParcel(parcel);
            updateParcelBox();
            errorLabel.setText(errorLabel.getText() + "Tuote on luotu\n");
        }
    }
    
    private String testReadyProduct() {
        if (objectsCombo.getSelectionModel().isEmpty() || objectsCombo.getValue().equals("Tee oma tuote")) {
            return null;
        } else {
            changeError(nameField, false);
            changeError(massField, false);
            changeError(sizeField, false);
            return objectsCombo.getValue();
        }
    }
    
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
            errorLabel.setText(errorLabel.getText() + "Anna tuotteelle nimi\n");
            valid = false;
        } else {
            changeError(nameField, false);
        }
        
        if (!isNumeric(productMass)) {
            changeError(massField, true);
            errorLabel.setText(errorLabel.getText() + "Virheellinen massa\n");
            valid = false;
        } else {
            changeError(massField, false);
        }
        
        if (sizeParts.length == 3) {
            for (String str : sizeParts) {
                if (!isNumeric(str)) {
                    changeError(sizeField, true);
                    errorLabel.setText(errorLabel.getText() + "Virheelliset mitat\n");
                    valid = false;
                    break;
                }
                changeError(sizeField, false);
            }
        } else {
            changeError(sizeField, true);
            errorLabel.setText(errorLabel.getText() + "Virheelliset mitat\n");
            valid = false;
        }
        
        if (valid) {
            return new Product(Double.parseDouble(productMass), Double.parseDouble(sizeParts[0]),
                    Double.parseDouble(sizeParts[1]), Double.parseDouble(sizeParts[2]), productName, fragile.isSelected());
        } else {
            return null;
        }
    }
    
    private boolean testStartPost() {
        if (startAutoCombo.getSelectionModel().isEmpty()) {
            changeError(startCityCombo, true);
            errorLabel.setText(errorLabel.getText() + "Valitse lähetyspaikka\n");
            return false;
        } else {
            changeError(startCityCombo, false);
            return true;
        }
    }
    
    private boolean testEndPost() {
        if (endAutoCombo.getSelectionModel().isEmpty()) {
            changeError(endCityCombo, true);
            errorLabel.setText(errorLabel.getText() + "Valitse vastaanottopaikka\n");
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
                errorLabel.setText(errorLabel.getText() + "Tuote on liian iso (vaihda luokkaa)\n");
                validDimension = false;
                break;
            }
        }
        
        if (validDimension) {
            changeError(sizeField, false);
        }
        
        if (proWeight > parWeight) {
            changeError(massField, true);
            errorLabel.setText(errorLabel.getText() + "Tuote on liian painava (vaihda luokkaa)\n");
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
            errorLabel.setText(errorLabel.getText() + "Liian pitkä matka (vaihda luokkaa)\n");
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