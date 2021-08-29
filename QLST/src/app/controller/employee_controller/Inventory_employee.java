package app.controller.employee_controller;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import app.controller.homepage.Info_Employee;
import app.dao.connectDB;
import app.model.Bill;
import app.model.Inventory;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Inventory_employee implements Initializable{
	
	   @FXML
	    private TableColumn<Inventory,String> col_pro_name;

	    @FXML
	    private TableColumn<Inventory,String> col_date_input;

	    @FXML
	    private TableView<Inventory> table_inventory;
	    
	    @FXML
	    private TableColumn<Inventory, Integer> col_wh_id;
	    
	    @FXML
	    private TableColumn<Inventory, Integer> col_pro_id;
	    
	    @FXML
	    private TableColumn<Inventory, Integer> col_amount_stock;
	    
	    @FXML
	    private TableColumn<Inventory, Integer> col_amount_input;
	    
	    @FXML
	    private TableColumn<Inventory, Integer> col_price_input;
	    
	    
	    @FXML
	    private TextField search_inventory;
	    
	    @FXML
	    private Button Inventory_import;
	    
	    @FXML
	    private Label ltotal;
	    
	    int index = -1;
	    
	    Connection conn =null;
	    ResultSet rs = null;
	    PreparedStatement pst = null;
	    ObservableList<Inventory> listM;
	    ObservableList<Inventory> dataList;

		private Result id;
	
	    
	    public void UpdateTable_inventory(){


	    	col_wh_id.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("wh_id"));
	    	col_pro_id.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("pro_id"));
	    	col_amount_stock.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("amount_stock"));
	    	col_amount_input.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("amount_input"));
	    	col_price_input.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("price_input"));
	    	col_pro_name.setCellValueFactory(new PropertyValueFactory<Inventory,String>("pro_name"));
	    	col_date_input.setCellValueFactory(new PropertyValueFactory<Inventory,String>("date_input"));
	    	
	    	


	        listM = connectDB.getDataInventory();
	        table_inventory.setItems(listM);
	    }
	    

	    @FXML
	    void search_user_inventory(){
	    	col_wh_id.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("wh_id"));
	    	col_pro_id.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("pro_id"));
	    	col_amount_stock.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("amount_stock"));
	    	col_amount_input.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("amount_input"));
	    	col_price_input.setCellValueFactory(new PropertyValueFactory<Inventory,Integer>("price_input"));
	    	col_pro_name.setCellValueFactory(new PropertyValueFactory<Inventory,String>("pro_name"));
	    	col_date_input.setCellValueFactory(new PropertyValueFactory<Inventory,String>("date_input"));

		           dataList = connectDB.getDataInventory();
		           table_inventory.setItems(dataList);
		           FilteredList<Inventory> filteredData = new FilteredList<>(dataList, b -> true);  
		           search_inventory.textProperty().addListener((observable, oldValue, newValue) -> {
		    filteredData.setPredicate(person -> {
		       if (newValue == null || newValue.isEmpty()) {
		        return true;
		       }    
		       String lowerCaseFilter = newValue.toLowerCase();
		       
		       if (person.getPro_name().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
		        return true; // Filter matches name
		       } else if (person.getDate_input().toLowerCase().indexOf(lowerCaseFilter) != -1) {
		        return true; // Filter matches email
		       }
//		       else if (String.valueOf(person.getBusiness()).indexOf(lowerCaseFilter)!=-1)
//		            return true;// Filter matches username
		                                   
		            else  
		             return false; // Does not match.
		      });
		     });  
		     SortedList<Inventory> sortedData = new SortedList<>(filteredData);  
		     sortedData.comparatorProperty().bind(table_inventory.comparatorProperty());  
		     table_inventory.setItems(sortedData);      
		       
	    }
	    
	    
	    @FXML
	    void Inventory_import(MouseEvent event) {
		    try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/employee/Inventory_import.fxml"));
		                Parent root = (Parent) fxmlLoader.load();
		                Stage stage = new Stage();
		                stage.setScene(new Scene(root));  
		                stage.show();             
		                UpdateTable_inventory();
//		                showamount();
		                search_user_inventory();
		                
		        } catch(Exception e) {
		        	
		           e.printStackTrace();
		          }
	    }

	    
	    
	@FXML
	void exit(MouseEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../../ui/homepage/Home_Employee.fxml"));
			Parent parent;
			
			parent = loader.load();
			
			Scene scene = new Scene(parent);
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		UpdateTable_inventory();
		search_user_inventory();
	}
	
	
}
