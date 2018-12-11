package FxmlControllers;


import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
////import org.openqa.selenium.Proxy;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.firefox.FirefoxBinary;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fr3sh.config.LoaderConfig;
import com.gargoylesoftware.htmlunit.javascript.host.event.MouseEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utils.FxmlUtils;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import javafx.scene.control.DateCell;
import javafx.geometry.HPos;

import javafx.util.StringConverter;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author mateusz.predki
 */
public class RangeOfDatesController implements Initializable {

	FirefoxProfile profile;
	private WebDriver driver;
	private String baseUrl;

	ArrayList<Integer> weekH;
	ArrayList<String> RangeOfDaysToFill;
	String Gadzet1;
	String Gadzet2;
	int pocztyg;
	String pocztygS;
	private String miech;
	private String rok;
	 Map hm = new TreeMap<>();
	 Map<Calendar, List<Integer>> map = new TreeMap<Calendar, List<Integer>>();
	
	  private DatePicker checkInDatePicker;
	    private DatePicker checkOutDatePicker;
	    private final String pattern = "dd/MM/yyyy";
	    
	@FXML
	private Button fxRangeOfDays;
	
    @FXML
    private VBox VboxRangeOfDates;
    
    @FXML
    private TextArea fxLogger;
	
    
    //func add for multiple key value pair for Hash map that not allow duplicates
    public void add( Calendar key,  Integer val) {
        List<Integer> list = map.get(key);
        if (list == null) {
            list = new ArrayList<Integer>();
            map.put(key, list);
        }
        list.add(val);
    }
    
	 private void initUI() {
	        VBox vbox = new VBox(20);
	        vbox.setStyle("-fx-padding: 10;");
	     //   Scene scene = new Scene(vbox, 400, 400);
	     //   stage.setScene(scene);
	        checkInDatePicker = new DatePicker();
	        checkOutDatePicker = new DatePicker();
	        checkInDatePicker.setValue(LocalDate.now());
	        final Callback<DatePicker, DateCell> dayCellFactory = 
	            new Callback<DatePicker, DateCell>() {
	                @Override
	                public DateCell call(final DatePicker datePicker) {
	                    return new DateCell() {
	                        @Override
	                        public void updateItem(LocalDate item, boolean empty) {
	                            super.updateItem(item, empty);
	                           
	                            if (item.isBefore(
	                                    checkInDatePicker.getValue().plusDays(1))
	                                ) {
	                                    setDisable(true);
	                                    setStyle("-fx-background-color: #ffc0cb;");
	                            }   
	                    }
	                };
	            }
	        };
	        checkOutDatePicker.setDayCellFactory(dayCellFactory);
	        checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(1));
	        
	        
	        
	        StringConverter converter = new StringConverter<LocalDate>() {
	            DateTimeFormatter dateFormatter = 
	                DateTimeFormatter.ofPattern(pattern);
	            @Override
	            public String toString(LocalDate date) {
	                if (date != null) {
	                    return dateFormatter.format(date);
	                } else {
	                    return "";
	                }
	            }
	            @Override
	            public LocalDate fromString(String string) {
	                if (string != null && !string.isEmpty()) {
	                    return LocalDate.parse(string, dateFormatter);
	                } else {
	                    return null;
	                }
	            }
	        };             
	        checkInDatePicker.setConverter(converter);
	        checkInDatePicker.setPromptText(pattern.toLowerCase());
	        
	        checkOutDatePicker.setConverter(converter);
	        checkOutDatePicker.setPromptText(pattern.toLowerCase());
	        
	        
	        
	        GridPane gridPane = new GridPane();
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        Label checkInlabel = new Label("Data wypełniania OD:");
	        checkInlabel.setStyle("-fx-text-fill: white;");
	        gridPane.add(checkInlabel, 0, 0);
	        GridPane.setHalignment(checkInlabel, HPos.LEFT);
	        gridPane.add(checkInDatePicker, 0, 1);
	        Label checkOutlabel = new Label("Data wypełniania DO:");
	        checkOutlabel.setStyle("-fx-text-fill: white;");
	        gridPane.add(checkOutlabel, 0, 2);
	        GridPane.setHalignment(checkOutlabel, HPos.LEFT);
	        gridPane.add(checkOutDatePicker, 0, 3);
	        vbox.getChildren().add(gridPane);
	        VboxRangeOfDates.getChildren().add(vbox);
	        
	      //  String date = checkInDatePicker.getValue().format(DateTimeFormatter.ofPattern(pattern));
	      //  System.out.println(date);
	      //  System.out.println(checkInDatePicker.getValue().toString());
	      //  System.out.println(checkOutDatePicker.getValue().toString());
	        //checkInDatePicker.getValue().toString();
	       
	        
	        
	    }


	private void DaysToFill() {
		String startdate = checkInDatePicker.getValue().format(DateTimeFormatter.ofPattern(pattern));;
		String enddate = checkOutDatePicker.getValue().format(DateTimeFormatter.ofPattern(pattern));;
		RangeOfDaysToFill = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try
		{
		  //Date start = sdf.parse(startdate);
		  Calendar start = Calendar.getInstance();
		  start.setTime(sdf.parse(startdate));
		  //Date end = sdf.parse(enddate);
		  Calendar end = Calendar.getInstance();
		  end.setTime(sdf.parse(enddate));
		  int workingDays = 0;
		  while(!start.after(end))//removed ; (semi-colon)
		  {
		    //int day = start.getDay();
		    int day = start.get(Calendar.DAY_OF_WEEK);
		    //if ((day != Calendar.SATURDAY) || (day != Calendar.SUNDAY)) if it's sunday, != to Saturday is true
		    if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)) {
		    	String formatted = sdf.format(start.getTime());
		    	RangeOfDaysToFill.add(formatted);
		    	System.out.println(formatted);
		    	
		    workingDays++;
		    }
		    //System.out.println(workingDays);//moved
		    start.add(Calendar.DATE, 1);//removed comment tags
		  }
		  System.out.println(workingDays);
		}
		catch(Exception e)
		{
		  e.printStackTrace();
		}
	}
	
	@FXML
	void goThisRange() throws Exception {
		 DaysToFill();
		 fxLogger.clear();
		 
			LoaderConfig laderConf = LoaderConfig.getInstanceUsingDoubleLocking();
			laderConf.getConf();
			laderConf.getParams();
		 
		 System.setProperty("webdriver.gecko.driver", laderConf.geckoDriver);
			profile = new FirefoxProfile();

			profile.setPreference("network.proxy.type", 0);

			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);
			driver = new FirefoxDriver(options);
			baseUrl = "https://jira/login.jsp";
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("https://jira/secure/Dashboard.jspa");

		
			
			weekH = new ArrayList<>();
			
			for (int i = 0; i < RangeOfDaysToFill.size(); i++) {
				weekH.add(8);
			}
			
			WebElement input = (new WebDriverWait(driver, 15))
					.until(ExpectedConditions.presenceOfElementLocated(By.id("login-form-username")));

			driver.findElement(By.id("login-form-username")).clear();
			driver.findElement(By.id("login-form-username")).sendKeys(laderConf.getConf().getLogin());
			driver.findElement(By.id("login-form-password")).clear();
			driver.findElement(By.id("login-form-password")).sendKeys(laderConf.getConf().getPass());
			driver.findElement(By.id("login")).click();
			
			input = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("add-gadget")));
			
		
			
			List<WebElement> t = driver.findElements(By.className("dashboard-item-title"));

			
			// pobranie id Tabeli Time Sheet i uciecie ostatnich znaków gadget-43689
			for (int i = 0; i < t.size(); i++) {
				String temp1 = t.get(i).getText();
				if (temp1.contains("Time Sheet")) {

					Gadzet1 = t.get(i).getAttribute("id");
					Gadzet1 = (String) Gadzet1.subSequence(0, Gadzet1.length() - 6);
					

				}

			}

			// pobranie id Przydzielone do mnie i uciecie ostatnich znaków gadget-43689
			for (int i = 0; i < t.size(); i++) {
				String temp1 = t.get(i).getText();
				if (temp1.contains("Przydzielone do mnie") || temp1.contains("Assigned to Me")) {

					Gadzet2 = t.get(i).getAttribute("id");
					Gadzet2 = (String) Gadzet2.subSequence(0, Gadzet2.length() - 6);
					

				}

			}
			
			System.out.println("two" );
			WebElement a = driver.findElement(By.id(Gadzet1));
			driver.switchTo().frame(a);

			System.out.println("two" );
			//pobranie poczatka tygodnia z timeshit po atrybucie title "07/05/2018" zamiast temp3 
			//String temp33 = t.get(1).getAttribute("title");
		//	System.out.println("To jest cała data!!!:" + temp33);
			
		
				driver.switchTo().defaultContent();

				///////
				input = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id(Gadzet2)));
				a = driver.findElement(By.id(Gadzet2));
				// driver.switchTo().frame(a);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}

				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				// WebDriverWait wait = (new WebDriverWait(driver, 15));
				t = driver.findElements(By.className("issue-link"));
				
				
				///// dla wszystkich element�w w tabeli time shhet
				for (int j = 0; j < laderConf.getAllParam().size(); j++) {

					// System.out.println(temp1);

					// dla wszystkich zebranych w param
					for (int i = 0; i < t.size(); i++) {
						String temp1 = t.get(i).getText();
						// System.out.println("Co jest: "+ temp1);
						if (temp1.contains(laderConf.getAllParam().get(j).getLink())) {

							t.get(i).click();
							try {
								Thread.sleep(3000);
							} catch (InterruptedException ex) {
								Thread.currentThread().interrupt();
							}
							int temp6 = 0;

							
							for (int f = 0; f < RangeOfDaysToFill.size(); f++) {
								
								if (laderConf.getAllParam().get(j).getImp1()) {
									wypelnij(laderConf, RangeOfDaysToFill.get(f),  j, f);

								}

								else {
									//Szansa na wypełnienie pół na pół losuje liczbe jedną z dwóch i jaśli wylosował 1 to wypełnia dla four.important:n
									Random rnd = new Random();
									int rando = rnd.nextInt(2);
									if (rando == 1) {
										wypelnij(laderConf, RangeOfDaysToFill.get(f),  j, f);
									}

								}
							}
							
							
					
							// driver.navigate().back();
							driver.get("https://jira/secure/MyJiraHome.jspa");
							input = (new WebDriverWait(driver, 15))
									.until(ExpectedConditions.presenceOfElementLocated(By.id(Gadzet2)));
							try {
								Thread.sleep(5000);
							} catch (InterruptedException ex) {
								Thread.currentThread().interrupt();
							}

							a = driver.findElement(By.id(Gadzet2));
			

							t = driver.findElements(By.className("issue-link"));


						}
					}

				}

				////////// UZUPE�NIENie JAKBY ZOSTAŁO/////
				for (int i = 0; i < weekH.size(); i++) {
					if (weekH.get(i) > 0) {
						Random rnd = new Random();
						do {

							int n = rnd.nextInt(laderConf.getAllParam().size());
							while (laderConf.getAllParam().get(n).getImp1()) {
								n = rnd.nextInt(laderConf.getAllParam().size());
							}

							if (!laderConf.getAllParam().get(n).getImp1()) {

								int lin = -1;
								for (int g = 0; g < t.size(); g++) {
									String temp1 = t.get(g).getText();
									if (temp1.contains(laderConf.getAllParam().get(n).getLink())) {
										lin = g;
									}
								}

								if (lin != -1) {

									t.get(lin).click();
									try {
										Thread.sleep(3000);
									} catch (InterruptedException ex) {
										Thread.currentThread().interrupt();
									}
									
									int rr = rnd
											.nextInt(laderConf.getAllParam().get(n).getH().get(1)
													- laderConf.getAllParam().get(n).getH().get(0))
											+ laderConf.getAllParam().get(n).getH().get(0);
									if (weekH.get(i) > rr) {

										weekH.set(i, weekH.get(i) - rr);
									} else {
										rr = weekH.get(i);
										weekH.set(i, weekH.get(i) - rr);
									}

									if (n != 0) {

										jiraWypelnij(laderConf, RangeOfDaysToFill.get(i), i, rnd, n, rr);

										driver.get("https://jira/secure/MyJiraHome.jspa");
										input = (new WebDriverWait(driver, 15))
												.until(ExpectedConditions.presenceOfElementLocated(By.id(Gadzet2)));
										try {
											Thread.sleep(5000);
										} catch (InterruptedException ex) {
											Thread.currentThread().interrupt();
										}

										a = driver.findElement(By.id(Gadzet2));
										// driver.switchTo().frame(a);

										t = driver.findElements(By.className("issue-link"));

									}

								}
							}

							//
						} while (weekH.get(i) > 0);

					}

				}
				
				
				System.out.println(hm);
				
				for(Map.Entry<Calendar, List<Integer>> entry: map.entrySet()) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String data = sdf.format(entry.getKey().getTime());
					Integer iloscgodzin = 0;
					for (int i = 0; i<entry.getValue().size(); i++) {
						iloscgodzin = iloscgodzin +	entry.getValue().get(i);
					}
				
					fxLogger.appendText(" Data Uzupełnienia: " +data + " Kolejnośc godzin wypełnienia: " + entry.getValue() + " Suma wypełnionych godzin dla dnia:" + iloscgodzin + "\n");
				    System.out.println(" Data Uzupełnienia: " +data + " Kolejnośc godzin wypełnienia: " + entry.getValue() + " Suma wypełnionych godzin dla dnia:" + iloscgodzin);
				}
				
				fxLogger.appendText("Koniec wykonywania! \n");
				 System.out.println("Koniec wykonywania!");
				driver.close();
				
				
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle(FxmlUtils.getReasorceBoundle().getString("Jira_fill_title"));
				alert.setHeaderText(null);
				alert.setContentText(FxmlUtils.getReasorceBoundle().getString("Jira_fill"));

				alert.showAndWait();
			
	}
	
	

	private void jiraWypelnij(LoaderConfig laderConf, String temp33, int i, Random rnd, int n, int rr)
			throws ParseException {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		driver.findElement(By.id("opsbar-operations_more")).click();
		driver.findElement(By.id("log-work")).click();
		driver.findElement(By.id("log-work-time-logged")).clear();

		driver.findElement(By.id("log-work-time-logged")).sendKeys(Integer.toString(rr) + "h");

		driver.findElement(By.id("log-work-date-logged-date-picker")).clear();

		/////// MIECHA IMPLEMENTACJA
		//String dt = pocztygS + "/" + miech + "/" + rok;
		String dt = temp33;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(dt));
		//c.add(Calendar.DATE, i);

		String DataWpisu = sdf.format(c.getTime());
		System.out.println("UZUPEŁNIAM: " + DataWpisu + " ILE H: " + Integer.toString(rr));
		
		add(c, rr);
		
		 if (hm.containsKey(DataWpisu)) {
			
		       int l = (int) (hm.get(DataWpisu));
		       int wynik = l + rr;
		       hm.replace(DataWpisu, wynik);
		 }
		      else
		        hm.put(DataWpisu, rr);

		driver.findElement(By.id("log-work-date-logged-date-picker"))
				.sendKeys(DataWpisu + " 08:30");

		driver.findElement(By.cssSelector("div.field-group > #comment")).clear();
		
		int opt = rnd.nextInt(laderConf.getAllParam().get(n).getOptions().size());

		driver.findElement(By.cssSelector("div.field-group > #comment"))
				.sendKeys(laderConf.getAllParam().get(n).getOptions().get(opt));

		if (laderConf.getConf().getSubmit()) {
			driver.findElement(By.id("log-work-submit")).click();
			try {
				Thread.sleep(12000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		} else {
			driver.findElement(By.id("log-work-cancel")).click();
		}
	}

	private void wypelnij(LoaderConfig laderConf, String temp33, int j, int temp6) throws ParseException {
		Random rnd = new Random();
		int n = rnd
				.nextInt(laderConf.getAllParam().get(j).getH().get(1)
						- laderConf.getAllParam().get(j).getH().get(0))
				+ laderConf.getAllParam().get(j).getH().get(0);
		
		// zeby nie przekroczyło wiecej godzin niz w weekH
		if (weekH.get(temp6) > n) {

			weekH.set(temp6, weekH.get(temp6) - n);
		} else {
			n = weekH.get(temp6);
			weekH.set(temp6, weekH.get(temp6) - n);
		}

		if (n != 0) {
	
			jiraWypelnij(laderConf, temp33, temp6, rnd, n, n);
			
		}
	}

	private void checkHour(ArrayList<Integer> weekH2, ArrayList<Integer> weekHodj2) {

		for (int i = 0; i < weekH2.size(); i++) {
			int a = weekH2.get(i) - weekHodj2.get(i);
			weekH2.set(i, a);
			System.out.println("Do uzupe�nienia zostało: " + a);

		}

	}

	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		initUI();
	}

}
