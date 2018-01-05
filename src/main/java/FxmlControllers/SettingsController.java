package FxmlControllers;

import java.io.IOException;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utils.FxmlUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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


    @FXML
    void save(MouseEvent event) throws IOException {
    	//System.out.println("TEST PRZYCISKU");;
    	confFile.save_conf("src/main/resources/config/conf2.txt");
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(FxmlUtils.getReasorceBoundle().getString("Plik_save_title"));
    	alert.setHeaderText(null);
    	alert.setContentText(FxmlUtils.getReasorceBoundle().getString("Plik_save"));

    	alert.showAndWait();
    	
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	confFile = new LoaderConfig();
    	confFile.read_param(confFile.param);
    	confFile.read_conf(confFile.nazwa_conf);
    	//System.out.println(confFile.getConf().getLogin());
    	fxlogin.textProperty().bindBidirectional(confFile.getConf().loginProperty());
    	
    	fxpass.textProperty().bindBidirectional(confFile.getConf().passProperty());
    	
    	fxpn.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getPnProp());
    	fxwt.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getWtProp());
    	fxsr.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getSrProp());
    	fxczw.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getCzwProp());
    	fxpt.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getPtProp());
    	
    	//fxsub.selectedProperty().bindBidirectional(confFile.getConf().getSubmit());
    	fxsub.selectedProperty().bindBidirectional(confFile.getConf().getSubmitPropProperty());
    	//fxpn.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getWeekProperty().get(0));
    }    
    
    
    
}
