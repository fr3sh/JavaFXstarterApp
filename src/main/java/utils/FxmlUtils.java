package utils;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class FxmlUtils {

	public static Pane fxmlLoader (String Path) {
		
		FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(Path));
       // ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(getReasorceBoundle());
        
        try {
        	return loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static ResourceBundle getReasorceBoundle () {
		return ResourceBundle.getBundle("bundles.messages");
	}
	
}
