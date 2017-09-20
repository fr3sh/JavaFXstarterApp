package FxmlControllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.fr3sh.config.LoaderConfig;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
/**
 *
 * @author mateusz.predki
 */
public class SettingsController implements Initializable {
    
		
	LoaderConfig confFile;
	
    @FXML
    private TextField fxlogin;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	confFile = new LoaderConfig();
    	confFile.read_param(confFile.param);
    	confFile.read_conf(confFile.nazwa_conf);
    	//System.out.println(confFile.getConf().getLogin());
    	fxlogin.textProperty().bindBidirectional(confFile.getConf().loginProperty());
    }    
    
    
    
}
