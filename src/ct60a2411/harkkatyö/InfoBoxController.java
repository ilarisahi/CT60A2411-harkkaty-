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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Petri
 */
public class InfoBoxController implements Initializable {

    @FXML
    private Button returnBut;
    @FXML
    private Label class1info1;
    @FXML
    private Label class2info1;
    @FXML
    private Label class3info1;
    @FXML
    private Label class1info2;
    @FXML
    private Label class2info2;
    @FXML
    private Label class3info2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        class1info1.setText("Nopea toimitus\n"
                + "rikkoutumisvaara\n"
                + "toimitus max. 150km päähän");
        class1info2.setText("Max. paino 20kg\n"
                + "mitat 60 x 50 x 30 (cm)");
        class2info1.setText("Särkymätön kuljetus\n"
                + "toimitus mihin vaan\n");
        class2info2.setText("Max. paino 10kg\n"
                + "mitat 20 x 20 x 30 (cm)");
        class3info1.setText("Hidas kuljetus\n"
                + "rikkoutumisvaara\n");
        class3info2.setText("Max. paino 200kg\n"
                + "mitat 200 x 200 x 200 (cm)");
    }    

    @FXML
    private void returnButAction(ActionEvent event) {
        Stage stage = (Stage) returnBut.getScene().getWindow();
        stage.close();
    }
    
}
