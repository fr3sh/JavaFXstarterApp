package FxmlControllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import utils.FxmlUtils;

/**
 *
 * @author mateusz.predki
 */
public class MainController implements Initializable {
    

	
	@FXML
	private AnchorPane MainPane;
	
	@FXML
	private BorderPane OptionsScrean;
	
    @FXML
    private Label label;

    @FXML
    private NaviLeftSideController NaviLeftSideButtonsController;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World to you !");
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	NaviLeftSideButtonsController.setMaincontroller(this);
    	
    }    
    
    public void ScreanLoadAndSet (String fxml) {
 /*   	
    	 FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
         ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
         loader.setResources(bundle);
         Parent root = null;
         try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
         OptionsScrean.setCenter(FxmlUtils.fxmlLoader(fxml));
    }
  
    
    
    
    
    
}
