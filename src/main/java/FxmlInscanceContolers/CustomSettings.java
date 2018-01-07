package FxmlInscanceContolers;

import java.io.IOException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBox;


public class CustomSettings extends VBox {
	
	public CustomSettings() {

	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
			 "/fxml/SettingsScreanParameters.fxml"));
			    //     fxmlLoader.setRoot(this);
			      //   fxmlLoader.setController(this);

			         try {
			             fxmlLoader.load();
			         } catch (IOException exception) {
			             throw new RuntimeException(exception);
			         }
			         
	}

}
