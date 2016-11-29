package application;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import controlador.VentanaPrincipalController;
import javafx.collections.ObservableList;

public class DriverFnac {

	public void Fnac(VentanaPrincipalController ventanaPrincipalController,ObservableList<Movil> listaMoviles,String marca){
		WebDriver driver;
		String os = System.getProperty("os.name");
		if(os.contains("Mac")){
		driver = new SafariDriver();
		 driver.manage().window().maximize();
		}else{
			String exePath = "C:\\Users\\Alvaro\\Desktop\\selenium\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", exePath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
		}
		driver.get("http://www.fnac.es/telefono-MP3-GPS/Smartphones-Libres/s39887");
		
		ArrayList<WebElement> res = (ArrayList<WebElement>) driver.findElements(
					By.xpath("//a[@class='categoryMenu-link']"));
			
			String hrefGoto = "";
			for(WebElement el:res){
				String t =el.getText();
				if(t.contains(marca)){
					hrefGoto= el.getAttribute("href");
				}
				System.out.println(t);
			}
			driver.get(hrefGoto);
			WebDriverWait waiting = new WebDriverWait(driver, 10);
			waiting.until( ExpectedConditions.presenceOfElementLocated( By.xpath("//li[@class='clearfix Article-item']"))
					);
			
			WebElement div = driver.findElements(By.xpath("//div[@class='fake-box']")).get(1);
			div.click();
			WebElement li = driver.findElement(By.xpath("//li[@data-value='100']"));
			li.click();
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                int elementCount =  driver.findElements(
	    					By.xpath("//li[contains(@class, 'Article-item')]")).size();
	                if (elementCount > 20)
	                    return true;
	                else
	                    return false;
	            }
	        });
	        

			ArrayList<WebElement> res2 = (ArrayList<WebElement>) driver.findElements(
					By.xpath("//li[contains(@class, 'Article-item')]"));
			for(WebElement phone : res2){	
				String name = phone.findElement(By.xpath(".//a[@class='js-minifa-title']")).getText();
				String price = phone.findElement(By.xpath(".//*[@class='userPrice']")).getText();
				
				Movil m = new Movil(name,price);
				listaMoviles.add(m);
				
			}
			ventanaPrincipalController.rellenarTabla();

			
	}
}
