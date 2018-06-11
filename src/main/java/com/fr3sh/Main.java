package com.fr3sh;

import java.util.Locale;
import java.util.ResourceBundle;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.FxmlUtils;

/**
 *
 * @author mateusz.predki
 */
public class Main extends Application {
    
    private static final String MainFxml = "/fxml/FxmlMain.fxml";

	@Override
    public void start(Stage stage) throws Exception {
    	//Locale.setDefault(new Locale("en"));
    	// Parent root = FXMLLoader.load(getClass().getResource("/fxml/FxmlMain.fxml"));
  
/*		FXMLLoader loader = new FXMLLoader(getClass().getResource(MainFxml));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);
        Parent root = loader.load();*/
        
        Pane pane = FxmlUtils.fxmlLoader(MainFxml);
        
        Scene scene = new Scene(pane);
       
        stage.setScene(scene);
        stage.setTitle(FxmlUtils.getReasorceBoundle().getString("NazwaAplikacji"));
        stage.getIcons().add(new Image("/assets/zd.png"));
        stage.show();
       
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
