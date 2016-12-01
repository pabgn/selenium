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
		
		// Paso 1 introducir la cadena de bï¿½squeda
		String searchText="Móviles"+ '\n';
		WebElement searchInputBox=driver.findElement(By.name("query"));
		searchInputBox.sendKeys(searchText);
		
		// Paso 2 esperar a los resultados de bï¿½squeda
		WebDriverWait waiting = new WebDriverWait(driver, 10);
		waiting.until( ExpectedConditions.presenceOfElementLocated(
				By.xpath("//a[@href='#acc-fil-0']") ) );
		
		//Paso 3 buscar el elemento ver mï¿½s
		//WebElement elementoMas = driver.findElement(By.xpath("//span[@class='mas']"));
		//elementoMas.click();
		
		//Marcar radio button Smartphones/Mï¿½viles
		WebElement elementSmartphones = driver.findElement(By.xpath("//a[@data-id='1116']"));
		elementSmartphones.click();
		
		//Pulsa el botï¿½n para ver las marcas de mï¿½viles
		WebElement elementMarcas = driver.findElement(By.xpath("//a[@href='#acc-fil-0']"));
		//elementMarcas.click();
		
		//Pulsar sobre "ver mï¿½s"
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,200)", "");
		
		
		WebElement elementVerMas = driver.findElement( By.className("columna-de-filtros__ver-mas"));
		
		//waiting = new WebDriverWait(driver, 10);
		//waiting.until(ExpectedConditions.visibilityOf((elementVerMas)));
		
		elementVerMas.click();
		 
		
		// Paso 5 esperar a que salga el radio botï¿½n de LG y hacer scroll
		waiting = new WebDriverWait(driver, 10);
		waiting.until( ExpectedConditions.presenceOfElementLocated( By.xpath(marca))
		);
		
		WebElement element = driver.findElement(By.xpath(marca));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,100)", "");
		
		//Paso 6 pulsar sobre el radio botï¿½n de los telï¿½fonos LG
		element.click();
		
		// Paso 7 esperar a que muestre los telefonos LG
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//pulsar el botón "ver mas" para ver todos los resultados 
		WebElement btnMore = driver.findElement(By.id("btnMore"));
		WebElement divBtnMore = driver.findElement(By.id("div-btmMore"));
		
		
		Actions actions1 = new Actions(driver);
		
		while(divBtnMore.isEnabled()) {
			actions1.moveToElement(btnMore).perform();
			waiting = new WebDriverWait(driver, 2);
			try{
			waiting.until(ExpectedConditions.elementToBeClickable(By.id("btnMore"))
			);
			btnMore.click();
			}catch(Exception e){System.out.println("Error en el bucle, falta elemnto ver mas");break;}
		}
		
		// Paso 8 Obtener todos los elementos que aparecen en la primera pï¿½gina
		ArrayList<WebElement> resultados2= (ArrayList<WebElement>)
		driver.findElements(
		By.xpath("//*[contains(@class, 'tarjeta-articulo expandible')]"));
		System.out.println("Resultados " + resultados2.size());
		
		//ObservableList<Movil> moviles;	
		// Paso 9 Iterar sobre la lista para obtener las caracterï¿½sticas de los artï¿½culos
		WebElement actual_Elemento, navegacion2;
		for (int i=0; i< resultados2.size(); i++)
		{
			
			actual_Elemento = resultados2.get(i); // elemento actual de la lista.
			System.out.println("Elemento: " + i);
			navegacion2 =actual_Elemento.findElement(By.xpath("./descendant::a"));
			System.out.println("Por navegaciï¿½n2: " + navegacion2.getAttribute("data-name").toString());
			System.out.println("Por navegaciï¿½n2: " + navegacion2.getAttribute("data-price").toString() );
			System.out.println("Qué nodo :" +navegacion2.toString());
			Movil m = new Movil(navegacion2.getAttribute("data-name").toString(),navegacion2.getAttribute("data-price").toString());
			listaMoviles.add(m);
			
			// si estï¿½ disponible o no, se buscar en tarjeta-articulo__elementos-adicionales
			try{
			navegacion2 = actual_Elemento.findElement(By.className("tarjeta-articulo__elementos-adicionales"));
			System.out.println("Por navegación 2 " + navegacion2.getText()); 
			}
			catch(Exception e){}
			// el texto indica si estï¿½ disponible o no
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
