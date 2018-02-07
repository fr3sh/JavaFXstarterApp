package FxmlControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.fr3sh.JiraParam;
import com.fr3sh.config.LoaderConfig;
import com.fr3sh.helper.BindedObjects;

//import FxmlControllers.SettingsScreanParametersController.MyEventHandler;
import FxmlInscanceContolers.CustomSettings;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	private Button fxAddParamSettings;

	@FXML
	private Button fxSaveParam2;

	@FXML
	private VBox fxVboxParam;
	
	@FXML
	private VBox fxVboxStarter;

	@FXML
	private TabPane fxTabpane1;

	Integer numer = 1;
	SettingsScreanParametersController SettController;

	@FXML
	void save(MouseEvent event) throws IOException {
		// System.out.println("TEST PRZYCISKU");;
		confFile.save_conf("src/main/resources/config/conf2.txt");

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(FxmlUtils.getReasorceBoundle().getString("Plik_save_title"));
		alert.setHeaderText(null);
		alert.setContentText(FxmlUtils.getReasorceBoundle().getString("Plik_save"));

		alert.showAndWait();

	}

	@FXML
	void AddMoreParam(MouseEvent event) throws IOException {

/*
 * Ok jeśi samą dormatke ładujemy bez bindowania 
 * 
 * 		fxVboxParam.getChildren().add((FxmlUtils.fxmlLoader("/fxml/SettingsScreanParameters.fxml")));
		fxTabpane1.requestLayout(); // odswierzenie TablePane konieczne!
*/
		
		FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource("/fxml/SettingsScreanParameters.fxml"));
		loader.setResources(ResourceBundle.getBundle("bundles.messages"));
		Pane a = new Pane();
		try {
			a = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SettController = (SettingsScreanParametersController) loader.getController();
		
		JiraParam k = new JiraParam();
		confFile.addToAllParams(k);
		
		SettController.getFxnazwa().textProperty().bindBidirectional(k.nazwaProperty());
		
		SettController.getFxkontenerName().textProperty().bindBidirectional(k.linkProperty());
		SettController.getFxsub().selectedProperty().bindBidirectional(k.getImp1Property());
		ArrayList<Integer> c = new ArrayList<>();
		c.add(0);
		c.add(0);
		
		k.setH(c);
		SettController.getFxOd().getValueFactory().valueProperty().bindBidirectional(k.hProperty().get(0));
		SettController.getFxDo().getValueFactory().valueProperty().bindBidirectional(k.hProperty().get(1));
		
		ArrayList<String> d = new ArrayList<>();
		d.add("");
		k.setOptions(d);
		SettController.getFxFillOptions().textProperty().bindBidirectional(k.OptionsProperty().get(0));
		
		
		fxVboxParam.getChildren().add(a);
		fxTabpane1.requestLayout(); // odswierzenie TablePane konieczne!
		
		
	}

	@FXML
	void SaveParam2(MouseEvent event) throws IOException {

		confFile.save_param("src/main/resources/config/param2.txt");

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(FxmlUtils.getReasorceBoundle().getString("Plik_save_title"));
		alert.setHeaderText(null);
		alert.setContentText(FxmlUtils.getReasorceBoundle().getString("Plik_save"));

		alert.showAndWait();
		
		
	}

	public void initConfFile() {
		confFile.read_conf(confFile.nazwa_conf);
		// System.out.println(confFile.getConf().getLogin());
		fxlogin.textProperty().bindBidirectional(confFile.getConf().loginProperty());

		fxpass.textProperty().bindBidirectional(confFile.getConf().passProperty());

		fxpn.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getPnProp());
		fxwt.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getWtProp());
		fxsr.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getSrProp());
		fxczw.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getCzwProp());
		fxpt.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getPtProp());

		// fxsub.selectedProperty().bindBidirectional(confFile.getConf().getSubmit());
		fxsub.selectedProperty().bindBidirectional(confFile.getConf().getSubmitPropProperty());
		// fxpn.getValueFactory().valueProperty().bindBidirectional(confFile.getConf().getWeekProperty().get(0));
	}

	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		confFile =  LoaderConfig.getInstanceUsingDoubleLocking();/////new LoaderConfig(); GET SINGLETON!!!!
		initConfFile();

		//////// PARAM MODE/////
		confFile.read_param(confFile.param);

		if(confFile.getAllParam().size()>0) {
			fxVboxStarter.getChildren().clear();
			//fxVboxStarter.getChildren().remove(1);
		}
		
		for (int j = 0; j < confFile.getAllParam().size(); j++) {

			numer = 1;

			FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource("/fxml/SettingsScreanParameters.fxml"));
			loader.setResources(ResourceBundle.getBundle("bundles.messages"));
			Pane a = new Pane();
			try {
				a = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SettController = (SettingsScreanParametersController) loader.getController();

			// Poprawic to nie nazwa kontenera tylko link kontener tdodac nowe pole
			// SettController.getFxkontenerName().textProperty().bindBidirectional(confFile.getParams().linkProperty());
			SettController.getFxnazwa().textProperty().bindBidirectional(confFile.getAllParam().get(j).nazwaProperty());
			SettController.getFxkontenerName().textProperty()
					.bindBidirectional(confFile.getAllParam().get(j).linkProperty());
			SettController.getFxsub().selectedProperty().bindBidirectional(confFile.getAllParam().get(j).getImp1Property());

			SettController.getFxOd().getValueFactory().valueProperty()
					.bindBidirectional(confFile.getAllParam().get(j).hProperty().get(0));
			SettController.getFxDo().getValueFactory().valueProperty()
					.bindBidirectional(confFile.getAllParam().get(j).hProperty().get(1));

			for (int i = 0; i < confFile.getAllParam().get(j).getOptions().size(); i++) {

				if (i == 0) {
					SettController.getFxFillOptions().textProperty()
							.bindBidirectional(confFile.getAllParam().get(j).OptionsProperty().get(i));
					BindedObjects k = new BindedObjects();
					k.setA(SettController.getFxFillOptions());
			    	k.setB(confFile.getAllParam().get(j).OptionsProperty().get(i));
					SettController.getAddedOptionParam2().add(k);
				} else {

					TextField temp2 = new TextField();
					HBox hb = new HBox();
					hb.setId("test");

					numer++;
					Label n = new Label(numer.toString() + ".");
					n.setTextFill(Color.web("orange", 0.5));
					Button x = new Button("X");
						
					x.setOnMousePressed(SettController::removeLine);
					
					//x.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
					//x.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

					hb.getChildren().add(n);
					hb.getChildren().add(temp2);
					hb.getChildren().add(x);

					temp2.textProperty().bindBidirectional(confFile.getAllParam().get(j).OptionsProperty().get(i));
					
					BindedObjects k = new BindedObjects();
					k.setA(temp2);
			    	k.setB(confFile.getAllParam().get(j).OptionsProperty().get(i));
					SettController.getAddedOptionParam2().add(k);

					SettController.getFxBox1().getChildren().add(hb);

				}
			}

			SettController.setParamJ(confFile.getAllParam().get(j));
			// SettController.getFxFillOptions().textProperty().bindBidirectional(confFile.getParams().linkProperty());
			fxVboxParam.getChildren().add(a);
			fxTabpane1.requestLayout(); // odswierzenie TablePane konieczne!

		}

	}

	private class MyEventHandler implements EventHandler<Event> {
		@Override
		public void handle(Event evt) {

			SettController.getFxBox1().getChildren().remove(((HBox) ((Button) evt.getSource()).getParent()));
			numer--;

		}
	}

}
