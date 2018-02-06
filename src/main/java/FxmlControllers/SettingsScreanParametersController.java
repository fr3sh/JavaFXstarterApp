package FxmlControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.fr3sh.JiraParam;
import com.fr3sh.config.LoaderConfig;
import com.fr3sh.helper.BindedObjects;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.paint.Color;
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
	JiraParam paramJ;
	
	ArrayList<SimpleStringProperty> addedOptionParam = new  ArrayList<SimpleStringProperty>();;
	
	ArrayList<BindedObjects> addedOptionParam2 = new  ArrayList<BindedObjects>();
	
    public JiraParam getParamJ() {
		return paramJ;
	}

	public void setParamJ(JiraParam paramJ) {
		this.paramJ = paramJ;
	}

	@FXML
    private Button fxAddMoreOptions;

    @FXML
    private GridPane fxmainGridPane;
    
    @FXML
    private VBox fxBox1;
    
    public Spinner<Integer> getFxOd() {
		return fxOd;
	}

	public void setFxOd(Spinner<Integer> fxOd) {
		this.fxOd = fxOd;
	}

	public Spinner<Integer> getFxDo() {
		return fxDo;
	}

	public void setFxDo(Spinner<Integer> fxDo) {
		this.fxDo = fxDo;
	}

	@FXML
    private Spinner<Integer> fxOd;
    
    @FXML
    private Spinner<Integer> fxDo;
    
    @FXML
    private CheckBox fxsub;
    
    public CheckBox getFxsub() {
		return fxsub;
	}

	public void setFxsub(CheckBox fxsub) {
		this.fxsub = fxsub;
	}

	public VBox getFxBox1() {
		return fxBox1;
	}

	public void setFxBox1(VBox fxBox1) {
		this.fxBox1 = fxBox1;
	}

	@FXML
    private Button fxRemoveLine;
  
    @FXML
    private TextField fxkontenerName;
    
    @FXML
    private TextField fxFillOptions;
    
    
    @FXML
    private TextField  fxnazwa;

    public TextField getFxnazwa() {
		return fxnazwa;
	}

	public void setFxnazwa(TextField fxnazwa) {
		this.fxnazwa = fxnazwa;
	}

	public TextField getFxFillOptions() {
		return fxFillOptions;
	}

	public void setFxFillOptions(TextField fxFillOptions) {
		this.fxFillOptions = fxFillOptions;
	}

	public TextField getFxkontenerName() {
		return fxkontenerName;
	}

	public void setFxkontenerName(TextField fxkontenerName) {
		this.fxkontenerName = fxkontenerName;
	}

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
        	
        			
        			
        	
        	
        	
    /*    	for (Iterator<SimpleStringProperty> it = getParamJ().OptionsProperty().iterator(); it.hasNext();) {
        		
        		for (Iterator<SimpleStringProperty> it2 = addedOptionParam.iterator(); it2.hasNext();) {
        			if (it.next() == it2.next() ) {
        			//	https://stackoverflow.com/questions/10738634/delete-data-from-arraylist-with-a-for-loop
        			//	https://stackoverflow.com/questions/1196586/calling-remove-in-foreach-loop-in-java/1196612
        				System.out.println(it2.next().get());
        			}
        			
        		}
        		
        		
        		
        	}*/
/*        	for (int i = 0; i < getParamJ().OptionsProperty().size(); i++) {
        		
        		for (int j = 0; j < addedOptionParam.size(); j++) {
					if (getParamJ().OptionsProperty().get(i) == addedOptionParam.get(j) ) {
						
						SimpleStringProperty ik = getParamJ().OptionsProperty().get(i);
						SimpleStringProperty jk =addedOptionParam.get(j);
						//System.out.println("HYBAAAAAAAAAAAAA ZNALAZEM ZOBACZYMY");
						getParamJ().OptionsProperty().remove(i);
						addedOptionParam.get(j).unbind();
						addedOptionParam.remove(j);
						//temp1param = null;
					}
        		}
			}*/
        	
        			
        			HBox a = (HBox) ((Button)evt.getSource()).getParent();
        			SimpleStringProperty temp = new SimpleStringProperty();
        			
        			for (int i = 0; i < a.getChildren().size(); i++) {
						if (a.getChildren().get(i) instanceof TextField) {
							System.out.println("Cos mamy");
							TextField f =(TextField) a.getChildren().get(i);
							
							Iterator<BindedObjects> it2 =addedOptionParam2.iterator();
	        				while (it2.hasNext()) {
	        					BindedObjects s2 = it2.next();
	        					
	        					if(f == s2.getA()) {
	        						
	        						temp= s2.getB();
	        					}
						}
					}}
						
						
						Iterator<SimpleStringProperty> it1 = getParamJ().OptionsProperty().iterator();
	        			
	        			while (it1.hasNext()) {
	        				SimpleStringProperty s1 = it1.next(); // must be called before you can call i.remove()
	        				if(temp == s1) {
	        					it1.remove();
        					}
	       
	        			   // Do something
	        			  // i.remove();
	        			}
	        			
	        			Iterator<SimpleStringProperty> it2 =addedOptionParam.iterator();
	     				while (it2.hasNext()) {
        					SimpleStringProperty s2 = it2.next();
        					if(temp == s2) {
        						it2.remove();
        					}
        					
        				}
        			
        	fxBox1.getChildren().remove(((HBox) ((Button)evt.getSource()).getParent()));
        	i--;
        	//((HBox) ((Button)evt.getSource()).getParent()).getChildren();
        }
    }
    
    Integer i = 1;
    
    @FXML
    void addMoreInputs(MouseEvent event) throws IOException {
    	
    	confFile =  LoaderConfig.getInstanceUsingDoubleLocking();
    	//confFile.getAllParam().
    	TextField a = new TextField();
    	HBox hb = new HBox();
    	hb.setId("test");
    	//Scene scene = source.getScene();
    	//Node nodeToFind = scene.lookup("#nodeToFindId");
    	
    	i++;
    	Label n = new Label(i.toString()+ ".");
    	n.setTextFill(Color.web("orange", 0.5));
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
    	
   
    	//if (getParamJ() != null) {
    	SimpleStringProperty temp1param = getParamJ().addOptions2("");
    	
     	BindedObjects k = new BindedObjects();
    	k.setA(a);
    	k.setB(temp1param);
    	addedOptionParam.add(temp1param);
    	addedOptionParam2.add(k);
    	a.textProperty().bindBidirectional(temp1param);
    //	}
    	
    	//SettController.getFxFillOptions().textProperty().bindBidirectional(confFile.getAllParam().get(j).OptionsProperty().get(i));
    	//fxmainGridPane.add(a, 1, 1);
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    
    
    
    
}
