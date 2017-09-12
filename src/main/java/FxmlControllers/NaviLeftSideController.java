package FxmlControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
/**
 *
 * @author mateusz.predki
 */
public class NaviLeftSideController implements Initializable {
    
		private static final String ThisweekScrean = "/fxml/ThisWeekScrean.fxml";
		private static final String SettingsScrean = "/fxml/SettingsScrean.fxml";

		private MainController maincontroller;
	
	   public MainController getMaincontroller() {
			return maincontroller;
		}

		public void setMaincontroller(MainController maincontroller) {
			this.maincontroller = maincontroller;
		}

	@FXML
	    private Button thisWeek;

	    @FXML
	    private Button RangeWeek;

	    @FXML
	    private Button Settings;

	    @FXML
	    private Button info;

	    @FXML
	    void goInfo(ActionEvent event) {

	    }

	    @FXML
	    void goRangeOfDates(ActionEvent event) {

	    }

	    @FXML
	    void goSettings(ActionEvent event) {
	    	
	    	maincontroller.ScreanLoadAndSet(SettingsScrean);

	    }

	    @FXML
	    void goThisWeek(ActionEvent event) {
	    	maincontroller.ScreanLoadAndSet(ThisweekScrean);
	    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
