package FxmlControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fr3sh.config.LoaderConfig;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.FxmlUtils;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
/**
 *
 * @author mateusz.predki
 */
public class SettingsScreanParametersController implements Initializable {
    
		
	LoaderConfig confFile;
	
    @FXML
    private Button fxAddMoreOptions;

    @FXML
    private GridPane fxmainGridPane;
    
    @FXML
    private VBox fxBox1;
    
    @FXML
    private Button fxRemoveLine;

    

    @FXML
    void removeLine(MouseEvent event) {

    }
    
    private class MyEventHandler implements EventHandler<Event>{
        @Override
        public void handle(Event evt) {
          // System.out.println(((Control)evt.getSource()).getId());
        //	Scene scene = source.getScene();
        	//HBox tb = (HBox) scene.lookup("#test");
        	//fxBox1.getChildren().remove(fxBox1.lookup("#test"));
        	fxBox1.getChildren().remove(((HBox) ((Button)evt.getSource()).getParent()));
        	
        	//((HBox) ((Button)evt.getSource()).getParent()).getChildren();
        }
    }
    
    
    @FXML
    void addMoreInputs(MouseEvent event) throws IOException {
    	TextField a = new TextField();
    	HBox hb = new HBox();
    	hb.setId("test");
    	//Scene scene = source.getScene();
    	//Node nodeToFind = scene.lookup("#nodeToFindId");
    	
    	
    	Label n = new Label("2.");
    	Button x = new Button("X");
    	
    	x.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
    	
/*    	x.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 
          
            }
        });*/
    	
    	hb.getChildren().add(n);
    	hb.getChildren().add(a);
    	hb.getChildren().add(x);
    	
    	fxBox1.getChildren().add(hb);
    	
    	
    	//fxmainGridPane.add(a, 1, 1);
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    
    
    
    
}