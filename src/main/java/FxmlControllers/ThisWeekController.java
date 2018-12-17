package FxmlControllers;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utils.FxmlUtils;
import javafx.scene.control.Button;

/**
 *
 * @author mateusz.predki
 */
public class ThisWeekController implements Initializable {

	FirefoxProfile profile;
	private WebDriver driver;
	private String baseUrl;

	ArrayList<Integer> weekH;
	String Gadzet1;
	String Gadzet2;
	int pocztyg;
	String pocztygS;
	private String miech;
	private String rok;

	@FXML
	private Button fxThisWeek;

	@FXML
	void goThisWeek() throws Exception {

		
		LoaderConfig laderConf = LoaderConfig.getInstanceUsingDoubleLocking();
		laderConf.getConf();
		laderConf.getParams();
		/*
		 * Alert alert = new Alert(AlertType.INFORMATION); alert.setTitle("Info");
		 * alert.setHeaderText(null); alert.setContentText("Działa");
		 * 
		 * alert.showAndWait();
		 */

		// System.setProperty("webdriver.gecko.driver","D:\\skrypty\\java\\Selenium\\4\\geckodriver-v0.19.1-win64\\geckodriver.exe");

		// 18 działa na firefox 52.x
		// 19 już potrzebuje minimum Firefox 55.0 (and greater) ,Selenium 3.5 (and
		// greater)
		//System.setProperty("webdriver.gecko.driver", "gecoDriver/geckodriver18.exe");
		//System.setProperty("webdriver.gecko.driver","D:\\geckodriver18.exe");
		System.setProperty("webdriver.gecko.driver", laderConf.geckoDriver);
		
		profile = new FirefoxProfile();

		profile.setPreference("network.proxy.type", 0);

		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);
		driver = new FirefoxDriver(options);

		//////////////////////////////////////////////////////////////
		/*
		 * // 18 działa na firefox 52.x conf dla selenium 3.4 // 19 już potrzebuje
		 * minimum Firefox 55.0 (and greater) ,Selenium 3.5 (and greater)
		 * System.setProperty("webdriver.gecko.driver","gecoDriver/geckodriver18.exe");
		 * profile = new FirefoxProfile(); profile.setPreference("network.proxy.type",
		 * 0); driver = new FirefoxDriver(profile);
		 */
		//////////////////////////////////////////////////////////////

		// driver = new FirefoxDriver();
		baseUrl = "https://jira/login.jsp";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://jira/secure/Dashboard.jspa");

	

		weekH = new ArrayList<>();
		weekH.add(8);
		weekH.add(8);
		weekH.add(8);
		weekH.add(8);
		weekH.add(8);

		checkHour(weekH, laderConf.getConf().getWeekHodj());

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
				System.out.println(Gadzet1);

			}

		}

		// pobranie id Przydzielone do mnie i uciecie ostatnich znaków gadget-43689
		for (int i = 0; i < t.size(); i++) {
			String temp1 = t.get(i).getText();
			if (temp1.contains("Przydzielone do mnie") || temp1.contains("Assigned to Me")) {

				Gadzet2 = t.get(i).getAttribute("id");
				Gadzet2 = (String) Gadzet2.subSequence(0, Gadzet2.length() - 6);
				System.out.println("PRZYDZIELONE G::" + Gadzet2);

			}

		}

		WebElement a = driver.findElement(By.id(Gadzet1));
		driver.switchTo().frame(a);

		//pobranie poczatka tygodnia z tabeli Timeshit
		t = driver.findElements(By.className("colHeaderLink"));
		String temp3 = t.get(1).getText();   // text pobrany Pn	7/maj
		//System.out.println("TO JEST TEMP 3 :" + temp3);
		String[] temps1 = temp3.split("/", 2);
		String[] temps2 = temps1[0].split("\n");
		temp3 = temps2[1];  // dzien poczatku biezacego tygodnia z tabeli timeshit  7
		
		//pobranie poczatka tygodnia z timeshit po atrybucie title "07/05/2018" zamiast temp3 
		String temp33 = t.get(1).getAttribute("title");
		System.out.println("To jest cała data!!!:" + temp33);
		
		SimpleDateFormat temp4 = new SimpleDateFormat("MM");

		Calendar now = Calendar.getInstance();

		int terazTydzien = now.get(Calendar.WEEK_OF_YEAR);

		miech = temp4.format(new Date());
		temp4 = new SimpleDateFormat("YYYY");
		rok = temp4.format(new Date());

		System.out.println("miesiac :" + miech);
		System.out.println("rok :" + rok);

		System.out.println("week1 :" + terazTydzien);
		// temp3 = (String) temp3.subSequence(3, 5);

		

		// System.out.println(temp3+ now.get(Calendar.MONTH)+now.get(Calendar.YEAR));
		pocztyg = Integer.parseInt(temp3);
		pocztygS = String.format("%02d", pocztyg);

		temp4 = new SimpleDateFormat("ddMMyyyy");

		
		
////// jaki tydzien 01 jest w tym miesiacu chyba nie wykozystywane ////		
		Date temp5 = temp4.parse("01" + "" + miech + "" + rok);
		Calendar cal = Calendar.getInstance();
		cal.setTime(temp5);
		int week2 = cal.get(Calendar.WEEK_OF_YEAR);
		System.out.println("week2 :" + week2);
/////////////////////////////////////////////////////////////////////////
		//// ok

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

					while (temp6 < 5) {
							//sprawdza czy dany parametr z pliku param ma ustawiona flage important na y wtedy losuje przedział ile godzin ma ustawic z przedziału godzinowego one.h:2-4
						if (laderConf.getAllParam().get(j).getImp1()) {
							wypelnij(laderConf, temp33,  j, temp6);

						}

						else {
							//Szansa na wypełnienie pół na pół losuje liczbe jedną z dwóch i jaśli wylosował 1 to wypełnia dla four.important:n
							Random rnd = new Random();
							int rando = rnd.nextInt(2);
							if (rando == 1) {
								wypelnij(laderConf, temp33,  j, temp6);
							}

						}

						temp6++;
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

								jiraWypelnij(laderConf, temp33, i, rnd, n, rr);

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
		c.add(Calendar.DATE, i);

		String DataWpisu = sdf.format(c.getTime());
		System.out.println("UZUPE�NIAM: " + DataWpisu + " ILE H: " + Integer.toString(rr));

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
		int n;
		if ((laderConf.getAllParam().get(j).getH().get(1)- laderConf.getAllParam().get(j).getH().get(0))==0) {
			n = laderConf.getAllParam().get(j).getH().get(1);
		}
		else {
		 n = rnd
				.nextInt(laderConf.getAllParam().get(j).getH().get(1)
						- laderConf.getAllParam().get(j).getH().get(0))
				+ laderConf.getAllParam().get(j).getH().get(0);
		}
		
		
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
	}

}
