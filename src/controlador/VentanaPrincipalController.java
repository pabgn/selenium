package controlador;

import java.util.ArrayList;

import application.DriverChromePCComponentes;
import application.Movil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VentanaPrincipalController {
	
	@FXML
	private Button boton_buscar;
	@FXML
	private MenuButton menu_marcas;
	@FXML
	private RadioButton pcComponentes,fnac,amazon;
	@FXML
	private TableView tabla_resultados;
	@FXML
	private MenuItem lg,samsung,huawei,motorola,apple,one_plus,lenovo;
	@FXML
	private TableColumn<Movil, String> columnaPrecio;
	@FXML
	private TableColumn<Movil, String> columnaModelo;

	private ObservableList<Movil> listaMoviles = FXCollections.observableArrayList();
	private String marca;
	
	public void initialize(){
		lg.setOnAction(event -> {
		    marca = "LG";
		    menu_marcas.setText(marca);
		});
		
		samsung.setOnAction(event -> {
			  marca = "Samsung";
			  menu_marcas.setText(marca);
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
		
		listaMoviles.clear();
		
		switch(menu_marcas.getText()){
		case "LG":
			marca = "//a[@data-id='3']";
			break;
			
		case "Samsung":
			marca = "//a[@data-id='27']";
			break;
			
		case "Huawei":
			marca = "//a[@data-id='644']";
			break;
			
		case "Motorola":
			marca = "//a[@data-id='21']";
			break;
			
		case "Apple":
			marca = "//a[@data-id='161']";
			break;
			
		case "One Plus":
			marca = "//a[@data-id='887']";
			break;
			
		case "Lenovo":
			marca = "//a[@data-id='178']";
			break;
			
		 default:
			System.out.println("Seleccione una marca");
		}
		
		DriverChromePCComponentes dc = new DriverChromePCComponentes();
		dc.Chrome(this,listaMoviles,marca);
	}
	
	public void rellenarTabla(){
		System.out.println(listaMoviles.size());
		columnaModelo.setCellValueFactory(cellData ->cellData.getValue().getNombre());
		columnaPrecio.setCellValueFactory(cellData ->cellData.getValue().getPrecio());
		tabla_resultados.setItems(listaMoviles);
	}
	
	public Button getBoton_buscar() {
		return boton_buscar;
	}

	public void setBoton_buscar(Button boton_buscar) {
		this.boton_buscar = boton_buscar;
	}

	public MenuButton getMenu_marcas() {
		return menu_marcas;
	}

	public void setMenu_marcas(MenuButton menu_marcas) {
		this.menu_marcas = menu_marcas;
	}

	public RadioButton getPcComponentes() {
		return pcComponentes;
	}

	public void setPcComponentes(RadioButton pcComponentes) {
		this.pcComponentes = pcComponentes;
	}

	public RadioButton getFnac() {
		return fnac;
	}

	public void setFnac(RadioButton fnac) {
		this.fnac = fnac;
	}

	public RadioButton getAmazon() {
		return amazon;
	}

	public void setAmazon(RadioButton amazon) {
		this.amazon = amazon;
	}

	public TableView getTabla_resultados() {
		return tabla_resultados;
	}

	public void setTabla_resultados(TableView table_resultados) {
		this.tabla_resultados = table_resultados;
	}

	public MenuItem getLg() {
		return lg;
	}

	public void setLg(MenuItem lg) {
		this.lg = lg;
	}

	public MenuItem getSamsung() {
		return samsung;
	}

	public void setSamsung(MenuItem samsung) {
		this.samsung = samsung;
	}

	public MenuItem getHuawei() {
		return huawei;
	}

	public void setHuawei(MenuItem huawei) {
		this.huawei = huawei;
	}

	public MenuItem getMotorola() {
		return motorola;
	}

	public void setMotorola(MenuItem motorola) {
		this.motorola = motorola;
	}

	public MenuItem getApple() {
		return apple;
	}

	public void setApple(MenuItem apple) {
		this.apple = apple;
	}

	public MenuItem getOne_plus() {
		return one_plus;
	}

	public void setOne_plus(MenuItem one_plus) {
		this.one_plus = one_plus;
	}

	public MenuItem getLenovo() {
		return lenovo;
	}

	public void setLenovo(MenuItem lenovo) {
		this.lenovo = lenovo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public ObservableList<Movil> getListaMoviles() {
		return listaMoviles;
	}

	public void setListaMoviles(ObservableList<Movil> listaMoviles) {
		this.listaMoviles = listaMoviles;
	}
	
}
