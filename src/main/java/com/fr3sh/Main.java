package com.fr3sh;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mateusz.predki
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    	//Locale.setDefault(new Locale("en"));
    	// Parent root = FXMLLoader.load(getClass().getResource("/fxml/FxmlMain.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FxmlMain.fxml"));
        
        
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        stage.setScene(scene);
        stage.setTitle(bundle.getString("NazwaAplikacji"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
