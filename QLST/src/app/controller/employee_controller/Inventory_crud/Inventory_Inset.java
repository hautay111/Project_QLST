package app.controller.employee_controller.Inventory_crud;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.JComponent;
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
import javafx.scene.control.Labeled;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Inventory_Inset implements Initializable{
	 	


	    
	    @FXML
	    private AnchorPane root;
	    
	    @FXML
	    private TextField text_input_detail_amount;

	    @FXML
	    private TextField text_input_detail_price_input;

	    @FXML
	    private DatePicker date_input;

	    @FXML
	    private TextField text_product_id;
	    
	    @FXML
	    private TextField emp_id;

	    @FXML
	    private TextField id_input;

	    @FXML
	    private TextField input_id1;
	    
	    @FXML
	    private Label label_notification;
	    
	    @FXML
	    private DatePicker expiry;

	    @FXML
	    private TextField text_product_barcode;

	    @FXML
	    private TextField text_product_name;

	    @FXML
	    private TextField text_product_price;
	    
	    private static Integer input_id;
	    
	    @FXML
	    private Label ltotal;
	    
	    
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
	        private ObservableList<String> stationsList3 = FXCollections.observableArrayList();
	        private ObservableList<String> stationsList4 = FXCollections.observableArrayList();
	        
	        @FXML
	        private ComboBox<String> combobox_product;
	        
	        @FXML
	        private ComboBox<String> combobox_product_category;

	        @FXML
	        private ComboBox<String> combobox_product_brand;
	        
	        @FXML
	        private ComboBox<String> combobox_product_unit;
	        
	        @FXML
	        private ComboBox<String> combobox_sup_id;

			private int pro_id;
			
			 public void getEmp_id(String id_emp) {
			    	emp_id.setText(id_emp);
			    	System.out.println("emp_id bill employee: "+ emp_id.getText());
			    }
			 
		      public void combobox_sup_id() {
//			      ObservableList<String> list1 = FXCollections.observableArrayList("cat_name");
//			      combobox_product.setItems(list1);
			    	
			        String sql = " select * from supplier ";
			
			        try {
			            conn = (Connection) connectDB.ConnectDb();
			            PreparedStatement pstStn = conn.prepareStatement(sql);
			            ResultSet stnRS = pstStn.executeQuery(sql);
			            
			            while (stnRS.next()) {
			
//			            	combobox_product.getItems().add(stnRS.getString("cat_name"));

			                stationsList4.add(stnRS.getString("sup_name"));
			                
			                combobox_sup_id.setItems(stationsList4);
			                
			                
			                
			            }
			            	
			            } catch (SQLException ex) {
			                System.err.println("ERR" + ex);
			            }
			  }    
		      
		      
			  	private static Integer sup_id;
			      @FXML
			      void box_sup_id(ActionEvent event) {
			  		try {
						Connection con = connectDB.ConnectDb();
						String title_name = combobox_sup_id.getValue();
						String sql = "select * from supplier where sup_name='" + title_name + "' ";
						PreparedStatement statement;
						statement = con.prepareStatement(sql);
						ResultSet set = statement.executeQuery();
						if (set.next()) {
							sup_id = set.getInt("sup_id");
							System.out.println(sup_id);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      }
		      
	        
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

		      public void product_combobox_unit() {
//			      ObservableList<String> list1 = FXCollections.observableArrayList("unit_name");
//			      combobox_product.setItems(list1);
			    	
			        String sql = " select * from unit ";
			
			        try {
			            conn = (Connection) connectDB.ConnectDb();
			            PreparedStatement pstStn = conn.prepareStatement(sql);
			            ResultSet stnRS = pstStn.executeQuery(sql);
			
			            while (stnRS.next()) {
	
//			            	combobox_product.getItems().add(stnRS.getString("unit_name"));
			
			                stationsList3.add(stnRS.getString("unit_name"));
			                combobox_product_unit.setItems(stationsList3);

			            }
			            	
			            } catch (SQLException ex) {
			                System.err.println("ERR" + ex);
			            }
			  } 
		      
		      
			  	private static Integer unit_id;
			      @FXML
			      void box_unit(ActionEvent event) {
			  		try {
						Connection con = connectDB.ConnectDb();
						String unit_name = combobox_product_unit.getValue();
						String sql = "select * from unit where unit_name='" + unit_name + "' ";
						PreparedStatement statement;
						statement = con.prepareStatement(sql);
						ResultSet set = statement.executeQuery();
						if (set.next()) {
							unit_id = set.getInt("unit_id");
							System.out.println(unit_id);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      }
		      
	    
//			      public void supplier_combobox_sup_id() {
////				      ObservableList<String> list1 = FXCollections.observableArrayList("unit_name");
////				      combobox_product.setItems(list1);
//				    	
//				        String sql = " select * from supplier																																					 ";
//				
//				        try {
//				            conn = (Connection) connectDB.ConnectDb();
//				            PreparedStatement pstStn = conn.prepareStatement(sql);
//				            ResultSet stnRS = pstStn.executeQuery(sql);
//				
//				            while (stnRS.next()) {
//				
////				            	combobox_product.getItems().add(stnRS.getString("unit_name"));
//				
//				                stationsList4.add(stnRS.getString("sup_id"));
//				                combobox_sup_id.setItems(stationsList4);
//
//				            }
//				            	
//				            } catch (SQLException ex) {
//				                System.err.println("ERR" + ex);
//				            }
//				  } 
//			      
//			      
//				  	private static Integer sup_id;
//				      @FXML
//				      void box_sup_id(ActionEvent event) {
//				  		try {
//							Connection con = connectDB.ConnectDb();
//							String sup_id = combobox_sup_id.getValue();
//							String sql = "select * from supplier where sup_id='" + sup_id + "' ";
//							PreparedStatement statement;
//							statement = con.prepareStatement(sql);
//							ResultSet set = statement.executeQuery();
//							if (set.next()) {
////								sup_id = set.getInt("sup_id");
//								System.out.println(sup_id);
//							}
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//				      }
				      
				      
				      
				      
	    @FXML
	    void Inset_inventory(ActionEvent event) throws SQLException {
	    	
	    	
	        conn = connectDB.ConnectDb();
	        String sql = "insert into product (barcode,pro_name,pro_sale_price,pro_expiry,pro_unit,brand_id,unit_id,pro_category,pro_brand,cat_id)values(?,?,?,?,?,?,?,?,?,?)";
	        try {
	        	
	            pst = conn.prepareStatement(sql);
	            pst.setString(1, text_product_barcode.getText());
	            pst.setString(2, text_product_name.getText());
	            pst.setString(3, text_product_price.getText());
	            pst.setString(4, expiry.getValue().toString());	           
	            String value1 = combobox_product_brand.getSelectionModel().getSelectedItem().toString();
	            String value2 = combobox_product_category.getSelectionModel().getSelectedItem().toString();
	            String value3 = combobox_product_unit.getSelectionModel().getSelectedItem().toString();
	            String value4 = combobox_sup_id.getSelectionModel().getSelectedItem().toString();
	            

	            pst.setString(5, value3);
	            pst.setString(9, value1);
	            pst.setString(8, value2);	           
	            pst.setInt(6, brand_id);
	            pst.setInt(7, unit_id);
//	            pst.setInt(10, sup_id);
	            pst.setInt(10, category_id);
//	            pst.setInt(12, sup_id);
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
	            JOptionPane.showMessageDialog(null, e);
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
				JOptionPane.showMessageDialog(null, "Zo 1");
				System.out.println(e);
			}	
	    
//	    	conn = connectDB.ConnectDb();
//			String sql1 = "insert into ware_house (date_input) values (?)";
//			try {
//	            pst = conn.prepareStatement(sql1);
////	            pst.setString(1, text_product_id.getText());
////	            pst.setString(2, text_product_amount.getText());
////	            pst.setString(3, text_product_price_input.getText());
//	            pst.setString(1, date_input.getValue().toString());
//	            pst.execute();
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, "Zo 2");
//				// TODO: handle exception
//			}
//		    
//	    }

	    
  	    	conn=connectDB.ConnectDb();
  	    	String query= "insert into input (emp_id) VALUES (?)";
  			pst = conn.prepareStatement(query);
  			pst.setString(1, emp_id.getText());
  			pst.execute();
  			System.out.println("new input success");
  		    try {
  		        conn=connectDB.ConnectDb();
  		        String query3="SELECT input.* FROM input,employee WHERE input.emp_id=employee.emp_id ORDER BY input.input_id DESC LIMIT 1 ";
  				pst= conn.prepareStatement(query3);
  			    rs=pst.executeQuery();
  			    if(rs.next()) {
  			    	 id_input.setText(rs.getString("input_id"));
  			    	 input_id1.setText(rs.getString("input_id"));
  			    	 label_notification.setText("Create Input Succeed !!!");
 	    
			    }
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Zo 2");
				e.printStackTrace();
			}
				
}

	    {
		    
			conn = connectDB.ConnectDb();
			String sql2 = "insert into input_detail(input_id,sup_id,pro_id,expiry,amount,input_price) values (?,?,?,?,?,?)";
//			sql2="SELECT input_detail.*,input.input_id,supplier.sup_id,product.pro_id FROM input_detail,input,supplier,product WHERE input_detail.input_id=input.input_id,input_detail.sup_id=supplier.sup_id,input_detail.pro_id=product.pro_id ORDER BY input_detail.input_id,input_detail.sup_id,input_detail.pro_id DESC LIMIT 1";
			try {
	            pst = conn.prepareStatement(sql2);
	            pst.setInt(1, input_id);
	            pst.setInt(2, sup_id);
	            pst.setInt(3, pro_id);
	            pst.setString(4, expiry.getValue().toString());	
	            pst.setString(5, text_input_detail_amount.getText());
	            pst.setString(6, text_input_detail_price_input.getText());
//	            pst.setString(3, text_product_id.getText());
	            pst.execute();
	            System.out.println("Success new input detail");
	            Stage stage = (Stage) root.getScene().getWindow();
	            // do what you have to do
	            stage.close();
	            try {
	  		        conn=connectDB.ConnectDb();
//	  		        String query1="SELECT input_detail.*,input.input_id,supplier.sup_id,product.pro_id FROM input_detail,input,supplier,product WHERE input_detail.input_id=input.input_id,input_detail.sup_id=supplier.sup_id,input_detail.pro_id=product.pro_id ORDER BY input_detail.input_id,input_detail.sup_id,input_detail.pro_id DESC LIMIT 1";
	  		        String query2="SELECT input_detail.* FROM input_detail";
	  		        pst= conn.prepareStatement(query2);
	  			    rs=pst.executeQuery();
	  			  while(rs.next()) {
				    	text_product_id.setText(rs.getString("input_id"));
				    	System.out.println(text_product_id.getText());
				    	 
				    }	
	  			    
			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, "Zo 3");
				// TODO: handle exception
			}
			    
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Zo 4");
				// TODO: handle exception
			}
   
			   
	    }
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
	        product_combobox_brand();
	        product_combobox_category();
	        product_combobox_unit();
	        combobox_sup_id();
	}
	    
}

