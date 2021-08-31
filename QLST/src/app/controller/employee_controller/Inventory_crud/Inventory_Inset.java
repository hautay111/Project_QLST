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

import app.model.Inventory;
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
	    private TextField text_wh_id;
	    
	    @FXML
	    private TextField text_pro_id;
	    
	    @FXML
	    private TextField text_pro_name;
	    
	    @FXML
	    private TextField text_amount_stock;
	    
	    @FXML
	    private TextField text_amount_input;
	    
	    @FXML
	    private TextField text_price_input;
	    
	    @FXML
	    private TextField text_date_input;
	    
	    @FXML
	    private Button Inventory_import;
	    
	    @FXML
	    private AnchorPane root;
	    
	    @FXML
	    private Label ltotal;
	    
	    int index = -1;
	    
	    Connection conn =null;
	    ResultSet rs = null;
	    PreparedStatement pst = null;
	    ObservableList<Inventory> listM;
	    ObservableList<Inventory> dataList;
	    
	    
	    
	    
	    
	    @FXML
	    void Inset_inventory(ActionEvent event) {
	    	conn = connectDB.ConnectDb();
			String sql1 = "insert into ware_house (wh_id,pro_id,amount_stock,amount_input,price_input,date_input) values (?,?,?,?,?,?)";
			try {
//				String value_pro_name=pro_name.getText();
				  pst = conn.prepareStatement(sql1);
		            pst.setString(1, text_wh_id.getText());
		            pst.setString(2, text_pro_id.getText());
//		            pst.setString(3, text_pro_name.getText());
		            pst.setString(3, text_amount_stock.getText());
		            pst.setString(4, text_amount_input.getText());
		            pst.setString(5, text_price_input.getText());
		            pst.setString(6, text_date_input.getText());
		            
		            Stage stage = (Stage) root.getScene().getWindow();
		            stage.close();
						
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Users Add succes");
				System.out.println(e);
			}	
	    	conn = connectDB.ConnectDb();
			String sql11 = "insert into product (pro_id,pro_name) values (?,?)";
			try {
//				String value_pro_name=pro_name.getText();
				  pst = conn.prepareStatement(sql11);
		            pst.setString(1, text_pro_id.getText());
		            pst.setString(2, text_pro_name.getText());
		            Stage stage = (Stage) root.getScene().getWindow();
		            stage.close();
						
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Users Add succes");
			}	
			
			
			
	    }



//					if (value_pro_name.equals("")) {
//						JOptionPane.showMessageDialog(null, "Enter Fill Please!!");
//					} else {
//						String query1="Select * from ware_house where pro_name like '%"+value_pro_name+"%'";
//						pst = conn.prepareStatement(query1);
//						rs=pst.executeQuery();
//						if(rs.next()) {
//							JOptionPane.showMessageDialog(null, "This Pro Name Exist.");
//						}else {
//							pst = conn.prepareStatement(sql1);
//							pst.setString(1, pro_name.getText());
//							pst.execute();
//							JOptionPane.showMessageDialog(null, "ADD new successfully!!");
//						}
//						
//					}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
	    
}
