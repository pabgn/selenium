package application;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class DriverChrome {

	public static void Chrome(){
		String exePath = "C:\\Users\\Alvaro\\Desktop\\selenium\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.pccomponentes.com");
		
		//Cerrar la ventana de cookies
		waitForPageLoad(driver);
		driver.findElement(By.xpath("//button[@class='btn btn-block btn-secondary m-t-1 accept-cookie']")).click();
		
		// Paso 1 introducir la cadena de búsqueda
		String searchText="Móviles"+ '\n';
		WebElement searchInputBox=driver.findElement(By.name("query"));
		searchInputBox.sendKeys(searchText);
		
		// Paso 2 esperar a los resultados de búsqueda
		WebDriverWait waiting = new WebDriverWait(driver, 10);
		waiting.until( ExpectedConditions.presenceOfElementLocated(
		By.id("resultados-busqueda") ) );
		
		//Paso 3 buscar el elemento ver más
		//WebElement elementoMas = driver.findElement(By.xpath("//span[@class='mas']"));
		//elementoMas.click();
		
		WebElement elementMarcas = driver.findElement(By.xpath("//a[@href='#acc-fil-0']"));
		elementMarcas.click();
		
		// Paso 5 esperar a que salga el radio botón de LG y hacer scroll
		waiting = new WebDriverWait(driver, 10);
		waiting.until( ExpectedConditions.presenceOfElementLocated( By.xpath("//a[@data-id='3']"))
		);
		
		WebElement element = driver.findElement(By.xpath("//a[@data-id='3']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,100)", "");
		
		//Paso 6 pulsar sobre el radio botón de los teléfonos LG
		element.click();
		waitForPageLoad(driver);
		// Paso 7 esperar a que muestre los telefonos LG
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// Paso 8 Obtener todos los elementos que aparecen en la primera página
		ArrayList<WebElement> resultados2= (ArrayList<WebElement>)
		driver.findElements(
		By.xpath("//*[contains(@class, 'tarjeta-articulo expandible')]"));
		System.out.println("Resultados " + resultados2.size());
		
		// Paso 9 Iterar sobre la lista para obtener las características de los artículos
		WebElement actual_Elemento, navegacion2;
		for (int i=0; i< resultados2.size(); i++)
		{
			actual_Elemento = resultados2.get(i); // elemento actual de la lista.
			System.out.println("Elemento: " + i);
			navegacion2 =actual_Elemento.findElement(By.xpath("./descendant::a"));
			System.out.println("Por navegación2: " + navegacion2.getAttribute("data-name").toString());
			System.out.println("Por navegación2: " + navegacion2.getAttribute("data-price").toString() );
			System.out.println("Qué nodo :" +navegacion2.toString());
		
			// si está disponible o no, se buscar en tarjeta-articulo__elementos-adicionales
			try{
			navegacion2 = actual_Elemento.findElement(By.className("tarjeta-articulo__elementos-adicionales"));
			System.out.println("Por navegación 2 " + navegacion2.getText()); 
			}
			catch(Exception e){}
			// el texto indica si está disponible o no
			System.out.println("-------------------------------------------");
		}
	}
	
	static void waitForPageLoad(WebDriver wdriver) {
		 WebDriverWait wait = new WebDriverWait(wdriver, 60);
		 Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {
			 
			 @Override
			 public boolean apply(WebDriver input) {
				 return ((JavascriptExecutor)
						 input).executeScript("return document.readyState").equals("complete");
			 	}
			 };
			 wait.until(pageLoaded);
		}
}
