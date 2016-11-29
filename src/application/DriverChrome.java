package application;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.internal.runners.statements.ExpectException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import controlador.VentanaPrincipalController;
import javafx.collections.ObservableList;

public class DriverChrome {

	public void PCComponentes(VentanaPrincipalController ventanaPrincipalController,ObservableList<Movil> listaMoviles,String marca){
		String os = System.getProperty("os.name");
		WebDriver driver;
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
		driver.get("http://www.pccomponentes.com");
		
		//Cerrar la ventana de cookies
		waitForPageLoad(driver);
		driver.findElement(By.xpath("//button[@class='btn btn-block btn-secondary m-t-1 accept-cookie']")).click();
		
		// Paso 1 introducir la cadena de b�squeda
		String searchText="Móviles"+ '\n';
		WebElement searchInputBox=driver.findElement(By.name("query"));
		searchInputBox.sendKeys(searchText);
		
		// Paso 2 esperar a los resultados de b�squeda
		WebDriverWait waiting = new WebDriverWait(driver, 10);
		waiting.until( ExpectedConditions.presenceOfElementLocated(
				By.xpath("//a[@href='#acc-fil-0']") ) );
		
		//Paso 3 buscar el elemento ver m�s
		//WebElement elementoMas = driver.findElement(By.xpath("//span[@class='mas']"));
		//elementoMas.click();
		
		//Marcar radio button Smartphones/M�viles
		//WebElement elementSmartphones = driver.findElement(By.xpath("//a[@data-id='1116']"));
		//elementSmartphones.click();
		
		//Pulsa el bot�n para ver las marcas de m�viles
		WebElement elementMarcas = driver.findElement(By.xpath("//a[@href='#acc-fil-0']"));
		//elementMarcas.click();
		
		//Pulsar sobre "ver m�s"
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,200)", "");
		
		
		WebElement elementVerMas = driver.findElement( By.className("columna-de-filtros__ver-mas"));
		
		//waiting = new WebDriverWait(driver, 10);
		//waiting.until(ExpectedConditions.visibilityOf((elementVerMas)));
		
		elementVerMas.click();
		 
		
		// Paso 5 esperar a que salga el radio bot�n de LG y hacer scroll
		waiting = new WebDriverWait(driver, 10);
		waiting.until( ExpectedConditions.presenceOfElementLocated( By.xpath(marca))
		);
		
		WebElement element = driver.findElement(By.xpath(marca));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,100)", "");
		
		//Paso 6 pulsar sobre el radio bot�n de los tel�fonos LG
		element.click();
		
		// Paso 7 esperar a que muestre los telefonos LG
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//pulsar el bot�n "ver mas" para ver todos los resultados 
		WebElement btnMore = driver.findElement(By.id("btnMore"));
		
		while(btnMore.isDisplayed()) {
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnMore);
			jse.executeScript("window.scrollBy(0,400)", "");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			btnMore.click();
		}
		
		
		// Paso 8 Obtener todos los elementos que aparecen en la primera p�gina
		ArrayList<WebElement> resultados2= (ArrayList<WebElement>)
		driver.findElements(
		By.xpath("//*[contains(@class, 'tarjeta-articulo expandible')]"));
		System.out.println("Resultados " + resultados2.size());
		
		ObservableList<Movil> moviles;	
		// Paso 9 Iterar sobre la lista para obtener las caracter�sticas de los art�culos
		WebElement actual_Elemento, navegacion2;
		for (int i=0; i< resultados2.size(); i++)
		{
			
			actual_Elemento = resultados2.get(i); // elemento actual de la lista.
			System.out.println("Elemento: " + i);
			navegacion2 =actual_Elemento.findElement(By.xpath("./descendant::a"));
			System.out.println("Por navegaci�n2: " + navegacion2.getAttribute("data-name").toString());
			System.out.println("Por navegaci�n2: " + navegacion2.getAttribute("data-price").toString() );
			System.out.println("Qu� nodo :" +navegacion2.toString());
			Movil m = new Movil(navegacion2.getAttribute("data-name").toString(),navegacion2.getAttribute("data-price").toString());
			listaMoviles.add(m);
			
			// si est� disponible o no, se buscar en tarjeta-articulo__elementos-adicionales
			try{
			navegacion2 = actual_Elemento.findElement(By.className("tarjeta-articulo__elementos-adicionales"));
			System.out.println("Por navegaci�n 2 " + navegacion2.getText()); 
			}
			catch(Exception e){}
			// el texto indica si est� disponible o no
			System.out.println("-------------------------------------------");
			
		}
		
		ventanaPrincipalController.rellenarTabla();
	
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
