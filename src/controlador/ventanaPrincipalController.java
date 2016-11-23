package controlador;

import java.util.ArrayList;

import application.DriverChrome;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;

public class ventanaPrincipalController {
	
	@FXML
	private Button boton_buscar;
	@FXML
	private MenuButton menu_marcas;
	@FXML
	private RadioButton pcComponentes,fnac,amazon;
	@FXML
	private TableView table_resultados;
	@FXML
	private MenuItem lg,samsung,huawei,motorola,apple,one_plus,lenovo;
	
	private String marca;
	
	public void initialize(){
		lg.setOnAction(event -> {
		    marca = "LG";
		    menu_marcas.setText(marca);
		});
		
		samsung.setOnAction(event -> {
			  marca = "Samsung";
		});
		
		huawei.setOnAction(event -> {
			 marca = "Huawei";
			 menu_marcas.setText(marca);
		});
		
		motorola.setOnAction(event -> {
			 marca = "Motorola";
			 menu_marcas.setText(marca);
		});
		
		apple.setOnAction(event -> {
			 marca = "Apple";
			 menu_marcas.setText(marca);
		});
		
		one_plus.setOnAction(event -> {
			 marca = "One Plus";
			 menu_marcas.setText(marca);
		});
		
		lenovo.setOnAction(event -> {
			marca = "Lenovo";
			menu_marcas.setText(marca);
		});
	}
	
	@FXML
	public void iniciarBusqueda(){
		
		System.out.println(marca);
		//DriverChrome dc = new DriverChrome();
		//dc.Chrome();
	}
	
	public void rellenarTable(ArrayList lista){
		
	}
	

}
