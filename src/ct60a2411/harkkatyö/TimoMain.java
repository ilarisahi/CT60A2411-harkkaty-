/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct60a2411.harkkatyö;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Ilari
 */
public class TimoMain extends Application {
    
    MainWindowController controller;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = loader.load();
        root.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        Scene scene = new Scene(root);
        controller = loader.getController();
        
        stage.setTitle("TIMO");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("assets/timo_icon.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("assets/timo_icon_big.png")));
        stage.setMinHeight(500.0);
        stage.setMinWidth(760.0);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() {
        controller.closeAction();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
