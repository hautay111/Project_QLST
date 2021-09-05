package app.controller.employee_controller.Inventory_crud;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import app.controller.manage_controller.Product_controller.product;
import app.dao.connectDB;
import app.model.Category1;
import app.model.Inventory;
import app.model.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Inventory_Inset implements Initializable{
	 	


	    
	    @FXML
	    private AnchorPane root;
	    
	    @FXML
	    private TextField text_product_amount_stock;

	    @FXML
	    private TextField text_product_amount_input;

	    @FXML
	    private TextField text_product_price_input;

	    @FXML
	    private DatePicker date_input;

	    @FXML
	    private TextField text_product_id;
	    
	    @FXML
	    private DatePicker date;

	    @FXML
	    private TextField text_product_barcode;

	    @FXML
	    private TextField text_product_name;

	    @FXML
	    private TextField text_product_price;
	    
	    @FXML
	    private Label ltotal;
	    

	    @FXML
	    private TextField text_product_unit;
	    
	    int index = -1;
	    
	    Connection conn =null;
	    ResultSet rs = null;
	    PreparedStatement pst = null;
	    ObservableList<Inventory> listM11;
	    ObservableList<Inventory> dataList11;

	    
	    ObservableList<Product> listM;
	    ObservableList<Product> dataList;
	    
	    
	        ObservableList<Category1> listM1;
	        ObservableList<Category1> dataList1;
//	        @FXML
	        void Select_combobox(ActionEvent event) {
//	            String s = combobox_product.getSelectionModel().getSelectedItem().toString();
//	            label.setText(s);
	        } 
	        @FXML
	        private TextField test;
	        private ObservableList<String> stationsList1 = FXCollections.observableArrayList();
	        private ObservableList<String> stationsList2 = FXCollections.observableArrayList();
	        
	        @FXML
	        private ComboBox<String> combobox_product;
	        
	        @FXML
	        private ComboBox<String> combobox_product_category;

	        @FXML
	        private ComboBox<String> combobox_product_brand;
	        
	        
	        
	        
	      public void product_combobox_brand() {
//	      ObservableList<String> list1 = FXCollections.observableArrayList("cat_name");
//	      combobox_product.setItems(list1);
	    	
	        String sql = " select * from brand ";
	
	        try {
	            conn = (Connection) connectDB.ConnectDb();
	            PreparedStatement pstStn = conn.prepareStatement(sql);
	            ResultSet stnRS = pstStn.executeQuery(sql);
	
	            while (stnRS.next()) {
	
//	            	combobox_product.getItems().add(stnRS.getString("cat_name"));

	                stationsList1.add(stnRS.getString("brand_name"));
	                
	                combobox_product_brand.setItems(stationsList1);
	                
	                
	                
	            }
	            	
	            } catch (SQLException ex) {
	                System.err.println("ERR" + ex);
	            }
	  } 
	      

	  	private static Integer brand_id;
	      @FXML
	      void box_brand(ActionEvent event) {
	  		try {
				Connection con = connectDB.ConnectDb();
				String title_name = combobox_product_brand.getValue();
				String sql = "select * from brand where brand_name='" + title_name + "' ";
				PreparedStatement statement;
				statement = con.prepareStatement(sql);
				ResultSet set = statement.executeQuery();
				if (set.next()) {
					brand_id = set.getInt("brand_id");
					System.out.println(brand_id);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	      
	    
	      public void product_combobox_category() {
//		      ObservableList<String> list1 = FXCollections.observableArrayList("cat_name");
//		      combobox_product.setItems(list1);
		    	
		        String sql = " select * from category ";
		
		        try {
		            conn = (Connection) connectDB.ConnectDb();
		            PreparedStatement pstStn = conn.prepareStatement(sql);
		            ResultSet stnRS = pstStn.executeQuery(sql);
		
		            while (stnRS.next()) {
		
//		            	combobox_product.getItems().add(stnRS.getString("cat_name"));
		
		                stationsList2.add(stnRS.getString("cat_name"));
		                combobox_product_category.setItems(stationsList2);

		            }
		            	
		            } catch (SQLException ex) {
		                System.err.println("ERR" + ex);
		            }
		  } 
	      
	      
		  	private static Integer category_id;
		      @FXML
		      void box_category(ActionEvent event) {
		  		try {
					Connection con = connectDB.ConnectDb();
					String category_name = combobox_product_category.getValue();
					String sql = "select * from category where cat_name='" + category_name + "' ";
					PreparedStatement statement;
					statement = con.prepareStatement(sql);
					ResultSet set = statement.executeQuery();
					if (set.next()) {
						category_id = set.getInt("cat_id");
						System.out.println(category_id);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
	      
	    
	    
	    
	    @FXML
	    void Inset_inventory(ActionEvent event) {
	    	
	    	
	        conn = connectDB.ConnectDb();
	        String sql = "insert into product (barcode,pro_name,pro_sale_price,pro_expiry,pro_unit,brand_id,pro_category,pro_brand,cat_id)values(?,?,?,?,?,?,?,?,?)";
	        try {
	        	
	            pst = conn.prepareStatement(sql);
	            pst.setString(1, text_product_barcode.getText());
	            pst.setString(2, text_product_name.getText());
	            pst.setString(3, text_product_price.getText());
	            pst.setString(4, date.getValue().toString());	           
	            String value1 = combobox_product_brand.getSelectionModel().getSelectedItem().toString();
	            String value2 = combobox_product_category.getSelectionModel().getSelectedItem().toString();
	            
	            pst.setString(5, text_product_unit.getText());
	            pst.setInt(6, brand_id);
	            pst.setString(7, value2);
	            pst.setString(8, value1);
	            pst.setInt(9, category_id);
	            pst.execute();
	            JOptionPane.showMessageDialog(null, "Users Add succes");
	            Stage stage = (Stage) root.getScene().getWindow();
	            // do what you have to do
	            stage.close();
	            
	        } catch (Exception e) {
	        	System.out.println(e);
	            Stage stage = (Stage) root.getScene().getWindow();
	            // do what you have to do
	            stage.close();
	            JOptionPane.showMessageDialog(null, "Please double check, you are entering missing Data or Barcode already exists!");
	        }
	        
		    try {
		        conn=connectDB.ConnectDb();
		        String query1="SELECT * FROM product ORDER BY pro_id DESC LIMIT 1;";
				pst= conn.prepareStatement(query1);
			    rs=pst.executeQuery();
			    while(rs.next()) {
			    	text_product_id.setText(rs.getString("pro_id"));
			    	System.out.println(text_product_id.getText());
			    	 
			    }	
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "No2");
				System.out.println(e);
			}	
		    
	    	conn = connectDB.ConnectDb();
			String sql1 = "insert into ware_house (pro_id,amount_stock,amount_input,price_input,date_input) values (?,?,?,?,?)";
			try {
	            pst = conn.prepareStatement(sql1);
	            pst.setString(1, text_product_id.getText());
	            pst.setString(2, text_product_amount_stock.getText());
	            pst.setString(3, text_product_amount_input.getText());
	            pst.setString(4, text_product_price_input.getText());
	            pst.setString(5, date_input.getValue().toString());
	            pst.execute();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No3");
				// TODO: handle exception
			}
		    

		    
	    }
	    
	    

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
	        product_combobox_brand();
	        product_combobox_category();
		}
	    
}
