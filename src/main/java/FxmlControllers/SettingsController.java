package FxmlControllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.fr3sh.config.LoaderConfig;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
/**
 *
 * @author mateusz.predki
 */
public class SettingsController implements Initializable {
    
		
	LoaderConfig confFile;
	
    @FXML
    private TextField fxlogin;
    
    @FXML
    private Spinner<Integer> fxpn;

    @FXML
    private Spinner<Integer> fxwt;

    @FXML
    private Spinner<Integer> fxsr;

    @FXML
    private Spinner<Integer> fxczw;

    @FXML
    private Spinner<Integer> fxpt;

    @FXML
    private PasswordField fxpass;

    @FXML
    private CheckBox fxsub;

    @FXML
    private Button fxsaveButton;

    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	confFile = new LoaderConfig();
    	confFile.read_param(confFile.param);
    	confFile.read_conf(confFile.nazwa_conf);
    	//System.out.println(confFile.getConf().getLogin());
    	fxlogin.textProperty().bindBidirectional(confFile.getConf().loginProperty());
    	
    	fxpass.textProperty().bindBidirectional(confFile.getConf().passProperty());
    	
    	fxpn.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getPnProp());
    	//fxpn.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getWeekProperty().get(0));
    }    
    
    
    
}
