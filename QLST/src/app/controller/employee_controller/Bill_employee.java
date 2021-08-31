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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
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
import app.model.Order_Detail;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Bill_employee implements Runnable, ThreadFactory , Initializable{

	
    @FXML
    private Label text_code;

    @FXML
    private Label text_name;

 
    @FXML
    private TextField text_amount;

    @FXML
    private Label text_type;

    @FXML
    private Label text_price;

    @FXML
    private Label text_total;

    @FXML
    private Label label_cus_poit;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private Label label_cus_name;
    
    @FXML
    private Label label_error_cus;
    
    @FXML
    private Label label_error_cus1;
    
    @FXML
    private Label total_bill_pay;
    
    @FXML
    private Label label_cus_point_end;
    
    @FXML
    private Label lable_message_sale;
    
    @FXML
    private Label label_show;
    
    @FXML
    private Label label_point_bill;
    

    @FXML
    private Button btn_create_order;
    
    @FXML
    private Button btn_add_product;
    
    @FXML
    private Button btn_cong_point;
    
    @FXML
    private TableColumn<Bill,String> col_name;

    @FXML
    private TableColumn<Bill,Integer> col_id;
    
    @FXML
    private TableColumn<Bill,String> col_barcode;
    @FXML
    private TableColumn<Bill,String> col_type;

    @FXML
    private TableColumn<Bill,String> col_price;

    @FXML
    private TableView<Bill> table_bill;
    
    @FXML
    private TableColumn<Bill,String> col_category;
    @FXML
    private TableColumn<Bill, Integer> col_no;
    
    
    @FXML
    private TableView<Order_Detail> table_order;

    @FXML
    private TableColumn<Order_Detail, Integer> col_bill_no;
    
    @FXML
    private TableColumn<Order_Detail, String> col_bill_name;

    @FXML
    private TableColumn<Order_Detail, Integer> col_bill_price;

    @FXML
    private TableColumn<Order_Detail, Integer> col_bill_amount;

    @FXML
    private TableColumn<Order_Detail, String> col_bill_total;
    
    
    @FXML
    private TextField search_bill;

    
    @FXML
    private TextField id_order;


    @FXML
    private TextField text_id;
    
    @FXML
    private Label ltotal;
    
    @FXML
    private Label order_id1;

	@FXML
    private Label emp_id;
    
    @FXML
    private Label total_bill_order;
    
    @FXML
    private TextField text_cus_code;

    @FXML
    private TextField text_cus_id;

    @FXML
    private Label text_discount;
	
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ObservableList<Bill> listM;
    ObservableList<Bill> dataList;

    ObservableList<Order_Detail> listM1;
    ObservableList<Order_Detail> dataList1;    
    private static String code_bar;
    public void initialize(URL url, ResourceBundle rb) {
//    UpdateTable_bill();
//    search_user_bill();
//    UpdateTable_Order_detail();
//    search_user_bill_order();
    showdate();
//    text_cus_code.setText("0");
//    print.setDisable(true);
//           
//    text_discount.setText("0");
//    id_order.setText("  ");
    

    
    
    num=0;
    int a = 2;
    double b = 200.2000;
    double c = a * b;
    System.out.println(c);

    
    
    // Code Source in description
    } 
    

    @FXML
    private Label date_text;
    
    public static  String DateFormat = "yyyy-MM-dd";
    public void showdate()
    {
      Calendar cal= Calendar.getInstance();
      SimpleDateFormat format = new SimpleDateFormat(DateFormat);
      date_text.setText(format.format(cal.getTime()));

        
    }
    
    @FXML
    void add_cus(ActionEvent event) {
	    try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/employee/Customer.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	          }
    }
    
    @FXML
    void new_orders(ActionEvent event) {
    	try {
	    	conn=connectDB.ConnectDb();
	    	String query= "insert into orders (emp_id) VALUES (?)";
			pst = conn.prepareStatement(query);
			pst.setString(1, emp_id.getText());
			pst.execute();
			System.out.println("tao new orders thanh cong");
			search_user_bill_order();
		    try {
		        conn=connectDB.ConnectDb();
		        String query1="SELECT orders.*,employee.emp_name FROM orders,employee WHERE orders.emp_id=employee.emp_id ORDER BY orders.order_id DESC LIMIT 1 ";
				pst= conn.prepareStatement(query1);
			    rs=pst.executeQuery();
			    if(rs.next()) {
			    	 id_order.setText(rs.getString("order_id"));
			    	 order_id1.setText(rs.getString("order_id"));
			    	 label_show.setVisible(true);
			    	 total_bill_pay.setText("");
			    	 total_bill_order.setText("");
			    	 label_cus_point_end.setText("");
			    	 label_cus_poit.setText("");
			    	 label_cus_name.setText("");
			    	 text_cus_code.setText("");
			    	 btn_create_order.setDisable(true);
			    	 text_discount.setText("");
			    	    print.setDisable(false);
			    	    UpdateTable_bill();
			    	    btn_cong_point.setDisable(false);
			    	    print.setDisable(false);
			    	    
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void totalCalculation (){

    	   Integer TotalPrice = 0;
    	    TotalPrice = table_order.getItems().stream().map(
    	            (item) -> item.getTotal()).reduce(TotalPrice, (accumulator, _item) -> accumulator + _item);

    	    total_bill_order.setText(String.valueOf(TotalPrice));
    	}
    
    
    int p = 0;
    @FXML
    void pay(ActionEvent event) {    
        DecimalFormat formatter = new DecimalFormat("###,###,###");           

        	
    	if (text_amount.getText().trim().equals("")||text_name.getText().trim().equals("")) {
        	JOptionPane.showMessageDialog(null, "Please choose a product or amount");
    	}
    	
    	
    	
    	p++;
    	int c=0;
    	double money =	Double.parseDouble(text_price.getText());
        int y = Integer.parseInt(text_amount.getText());
        double total=(money*y);      
        String moneyString = formatter.format(total);
        System.out.println(total);      

       double z = 0;
       double q = 0;
       double discount = 0;
       
       	z = Integer.parseInt(text_discount.getText());
		q = 100 - z;      
		discount = (total*(q/100));
		System.out.println(discount);        
        c++;
        search_bill.setText("");
        
        //--------------------		
        conn = connectDB.ConnectDb();
        String sql = "insert into orders_detail (name,quantity,price,total,order_id,pro_id)values(?,?,?,?,?,?)";
        try {
        	id_order.setText("");
            pst = conn.prepareStatement(sql);
            pst.setString(1, text_name.getText());
            pst.setString(2, text_amount.getText());
//            pst.setString(4, date_text.getText());           
            pst.setString(3, text_price.getText());
            pst.setDouble(4, total);      
            pst.setString(5, order_id1.getText());
            pst.setString(6, text_id.getText());
            pst.execute();
            label_show.setVisible(true);
            label_show.setText("Add "+ "'' "+ text_name.getText() +" ''" + " Successfully");
            search_user_bill_order();

		    try {
		        conn=connectDB.ConnectDb();
		        String query1="SELECT orders.*,employee.emp_name FROM orders,employee WHERE orders.emp_id=employee.emp_id ORDER BY orders.order_id DESC LIMIT 1 ";
				pst= conn.prepareStatement(query1);
			    rs=pst.executeQuery();
			    while(rs.next()) {
			    	 id_order.setText(rs.getString("order_id"));
			    	 
			    }		   
			    
			    try {
			        conn=connectDB.ConnectDb();
			        String query112="select * from ware_house where pro_id = ? ";
			        pst.setString(1, text_id_product.getText());
					pst= conn.prepareStatement(query112);
				    rs=pst.executeQuery();

				    if (rs.next()) {
					
				    		int id_product,amount_stock,id_wh,amount,id_pro;
				    		id_wh = rs.getInt(1);
				    		id_product = rs.getInt(2);
				    		amount_stock = rs.getInt(3);
				    		amount = Integer.parseInt(text_amount.getText());
				    		id_pro = Integer.parseInt(text_id_product.getText());
				    		
				    		System.out.println(id_product+"//"+amount_stock+"//"+id_wh+"//"+amount+"//"+id_pro);
				    		
//						    if(ID1 == ID2)
//						    {	
//						          conn = connectDB.ConnectDb();;
//						          String sql111 = "update ware_house set amount_stock= '"+amount_kho+"' where wh_id = '"+ID_ware+"' ";
//						          pst= conn.prepareStatement(sql111);
//						          pst.execute();
//						          
//						    }
//						    else
//						    {
//						      //ID do not match...
//						      // Throw error message...
//						    }
				    }
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            
	        } catch (Exception e) {
	        	System.out.println(e);
	        	
	            JOptionPane.showMessageDialog(null,e);
	        }
        } catch (Exception e) {
        	System.out.println(e);
        	
            JOptionPane.showMessageDialog(null,e);
        }

        String s=bill_Area.getText();
        bill_Area.setText(s+"Name:  "+text_name.getText()+"               Price:  "+text_price.getText()+"               Amount:  "
        +text_amount.getText()+"\n--  --  --  --  --  --  --  --  --  - -  --  --  --  --  --  --  --  --  --  --  --\n"
        );
        text_price.setText("");
        text_amount.setText("");
        text_code.setText("");
        text_name.setText("");
    }

    public void getEmp_id(String id_emp) {
    	emp_id.setText(id_emp);
    	emp_id.getText();
    	System.out.println("emp_id bill employee: "+ emp_id.getText());
    }
    
    public void UpdateTable_Order_detail(){

        
    	
    	
    	col_bill_no.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("no"));
    	col_bill_name.setCellValueFactory(new PropertyValueFactory<Order_Detail,String>("name"));
    	col_bill_amount.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("quantity"));
    	col_bill_price.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("price"));
    	col_bill_total.setCellValueFactory(new PropertyValueFactory<Order_Detail,String>("total"));
    	
    	


        listM1 = connectDB.getDataOrder_detail();
        table_order.setItems(listM1);
    }
    
    
    
    int num =0;
//    public void Print() {
//        
//        try {
//            num++;
//             PrintWriter f = new PrintWriter("bill "+String.valueOf(num)+".txt");
//             f.println(bill.getText());
//            f.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Bill_employee.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex);
//        }
//                 
//        bill.setText("----------------------SuperMarket------------------------"+"\n"+
//        	    "Name                           Price             Amount              Total"+"\n"+"\n");
//        
//  
//  }
    
    public void load_clear() {
        text_id.setText("");
        text_name.setText("");
        text_code.setText("");
        text_price.setText("");
    	order_id1.setText("");
    	id_order.setText("");
    	search_bill.setText("");
    	text_cus_code.setText("");
    	ltotal.setText("");
    	total_bill_order.setText("");
    	text_cus_id.setText("");
    	text_code.setText("");
    	text_discount.setText("0");
    	text_name.setText("");
    	text_type.setText("");
    	text_amount.setText("");
    	text_price.setText("");
    	text_total.setText("");
    }
    
    
    @FXML
    private Button print;
    @FXML
    private TextArea bill;
    
    @FXML
    private Label label_tien;
    
    @FXML
    private Label label_show1;
    
    @FXML
    private void Print() {
    	
    	
        try {
        	
        	label_show1.setText("Let's add points for customers !!!");
//          DecimalFormat formatter = new DecimalFormat("###,###,###");           
//          double money = Double.parseDouble(text_product_price.getText());            
//          String moneyString = formatter.format(money);
//          System.out.println(moneyString);
     	   Integer TotalPrice = 0;
     	   TotalPrice = table_order.getItems().stream().map(
   	        (item) -> item.getTotal()).reduce(TotalPrice, (accumulator, _item) -> accumulator + _item);
     	   total_bill_order.setText((String.valueOf(TotalPrice)));
     	   
     	  	double z = 0;
     	  	double q = 0;	     	  	
     	  	z = Integer.parseInt(text_discount.getText());	
     	  	q = 100 - z;   
   
     	  	double discount = (TotalPrice*(q/100));
     	  	total_bill_pay.setText(String.valueOf(discount));
          conn = connectDB.ConnectDb();
          String value1 = order_id1.getText();
          String value2 = total_bill_order.getText();
          String value3 = text_cus_id.getText();
          String sql = "update orders set total_price= '"+discount+"',cus_id= '"+value3+"' where order_id = '"+value1+"' ";
          pst= conn.prepareStatement(sql);
          pst.execute();
	      } catch (Exception e) {
	          JOptionPane.showMessageDialog(null, e);
	      }
    	
        
        try {
        	
     	   Integer TotalPrice = 0;
     	   TotalPrice = table_order.getItems().stream().map(
   	        (item) -> item.getTotal()).reduce(TotalPrice, (accumulator, _item) -> accumulator + _item);
     	   total_bill_order.setText((String.valueOf(TotalPrice)));
     	   
     	  	double z = 0;
     	  	double q = 0;	     	  	
     	  	z = Integer.parseInt(text_discount.getText());	
     	  	q = 100 - z;   
   
     	  	double discount = (TotalPrice*(q/100));
     	  	total_bill_pay.setText(String.valueOf(discount));
     	  	double point;
     	  	double point_bill;
     	  	
     	  double a = 100000;
          double b = 200000;
          double c = 300000;
          double d = 500000;
          double e = 1000000;
          double f = 2000000;
          

          
          p = Integer.parseInt(label_cus_point_end.getText());	

          if (discount < a) {
        	   point = p + 50;
        	   point_bill = 50;
        	   System.out.println(point);
        	   label_tien.setText((String.valueOf(point)));
        	   label_point_bill.setText((String.valueOf(point_bill)));
			}else if(discount >= a && discount <= b ) {
	      	   		point = (p + 100);
	      	   	point_bill = 100;
	      	   		System.out.println(point);
				label_tien.setText((String.valueOf(point)));
				label_point_bill.setText((String.valueOf(point_bill)));
	          }else if (discount > b && discount <= c) {
	          	   point = (p + 200);
	          	 point_bill = 200;
	          	 label_tien.setText((String.valueOf(point)));
	          	label_point_bill.setText((String.valueOf(point_bill)));
	  			System.out.println(point);
			}else if (discount > c && discount <= d) {
		      	   point = (p + 300);
		      	 point_bill = 300;
		      	 label_tien.setText((String.valueOf(point)));
		      	label_point_bill.setText((String.valueOf(point_bill)));
					System.out.println(point);
			}else if (discount > d && discount <= e) {
		      	   point = (p + 500);
		      	 point_bill = 500;
		      	 label_tien.setText((String.valueOf(point)));
		      	label_point_bill.setText((String.valueOf(point_bill)));
					System.out.println(point);
			}else if (discount > e && discount <= f) {
		      	   point = (p + 1000);
		      	   point_bill = 1000;
		      	 label_tien.setText((String.valueOf(point)));
		      	label_point_bill.setText((String.valueOf(point_bill)));
					System.out.println(point);
			}    
	          conn = connectDB.ConnectDb();
	          String sql = "update orders set point= '"+label_point_bill.getText()+"', cus_id = '"+text_cus_id.getText()+"', discount = '"+text_discount.getText()+"' where order_id = '"+order_id1.getText()+"' ";
	          pst= conn.prepareStatement(sql);
	          pst.execute();
          
	      } catch (Exception e) {
	          JOptionPane.showMessageDialog(null, e);
	      }
        
        
        try {
     	  	int z = 0;
     	  	z = Integer.parseInt(label_cus_poit.getText());	
            int t = Integer.parseInt(label_cus_poit.getText());
            int id_cus = Integer.parseInt(text_cus_id.getText());
            int point = t - z;
            
            
            
          conn = connectDB.ConnectDb();
          String sql = "update customer set cus_point= '"+point+"', last_purchase_date = '"+date_text.getText()+"' where cus_id = '"+id_cus+"' ";
          pst= conn.prepareStatement(sql);
          pst.execute();
	      } catch (Exception e) {
	          JOptionPane.showMessageDialog(null, e);
	      }
        
        String s=bill_Area.getText();
        bill_Area.setText(s+"Total Bill : "+total_bill_order.getText()+"\n"+" "+lable_message_sale.getText()+"\n"
        +"Payment : "+total_bill_pay.getText()+"\n--------------------------------------\n"
        );
        in.setDisable(false);
        
    }

    @FXML
    private Button in;   
    
    @FXML
    private TextArea bill_Area;
    
      @FXML
    void inbill(ActionEvent event) {
    	  
          try {
              num++;
              
               PrintWriter f = new PrintWriter("bill "+String.valueOf(num)+".txt");
               f.println(bill_Area.getText());
              f.close();
          } catch (FileNotFoundException ex) {
              Logger.getLogger(Bill_employee.class.getName()).log(Level.SEVERE, null, ex);
          }

    	  
    	in.setVisible(false);
        in(bill_Area);
    }  
    private void in(Node node) {
    	
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(node.getScene().getWindow())){
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }
        in.setVisible(true); 
        
        
    }
    
    
    
    
    @FXML
    private TextField tienthoi;
    
    @FXML
    private Label tienphaitrakhach;
    
    @FXML
    void chaythoitien(KeyEvent event) {
    		
    	double tienbill = Double.parseDouble(total_bill_pay.getText());	
    	double tienkhachdua = Double.parseDouble(tienthoi.getText());	
    	
    	double tienphaitra = tienkhachdua - tienbill;
    	
    	tienphaitrakhach.setText((String.valueOf(tienphaitra)));

    }
    

    
    
    
    public void UpdateTable_bill(){

    	
   
    	

    	col_no.setCellValueFactory(new PropertyValueFactory<Bill,Integer>("no"));
    	col_name.setCellValueFactory(new PropertyValueFactory<Bill,String>("name"));
    	col_type.setCellValueFactory(new PropertyValueFactory<Bill,String>("brand"));
    	col_price.setCellValueFactory(new PropertyValueFactory<Bill,String>("price"));
    	col_barcode.setCellValueFactory(new PropertyValueFactory<Bill,String>("code"));
    	col_category.setCellValueFactory(new PropertyValueFactory<Bill,String>("category"));
    	col_id.setCellValueFactory(new PropertyValueFactory<Bill,Integer>("pro_id"));
    	
    	


        listM = connectDB.getDatausers_bill();
        table_bill.setItems(listM);
    }
    

    @FXML
    private Label text_id_product;
    @FXML
    void getSelected_bill(MouseEvent event) {
        index = table_bill.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
            return;
        }
        
        text_id_product.setText(col_id.getCellData(index).toString());
        text_id.setText(col_id.getCellData(index).toString());
        text_name.setText(col_name.getCellData(index).toString());
        text_code.setText(col_barcode.getCellData(index).toString());
        text_price.setText(col_price.getCellData(index).toString());
 
    }
    
    
    @FXML
   public void search_user_bill_order(){
    	col_bill_no.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("no"));
    	col_bill_name.setCellValueFactory(new PropertyValueFactory<Order_Detail,String>("name"));
    	col_bill_amount.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("quantity"));
    	col_bill_price.setCellValueFactory(new PropertyValueFactory<Order_Detail,Integer>("price"));
    	col_bill_total.setCellValueFactory(new PropertyValueFactory<Order_Detail,String>("total"));
    			
	           dataList1 = connectDB.getDataOrder_detail();
	           table_order.setItems(dataList1);
	           FilteredList<Order_Detail> filteredData = new FilteredList<>(dataList1, b -> true);  
	           id_order.textProperty().addListener((observable, oldValue, newValue) -> {
	    filteredData.setPredicate(person -> {
	       if (newValue == null || newValue.isEmpty()) {
	        return true;
	       }    
	       String lowerCaseFilter = newValue.toLowerCase();
	       
	       if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	        return true; // Filter matches name
	       }else if (String.valueOf(person.getId()).indexOf(lowerCaseFilter)!=-1)
	            return true;// Filter matches username
	                                   
	            else  
	             return false; // Does not match.
	      });
	     });  
	     SortedList<Order_Detail> sortedData = new SortedList<>(filteredData);  
	     sortedData.comparatorProperty().bind(table_order.comparatorProperty());  
	     table_order.setItems(sortedData);      
	       
    }
    
    
    

    @FXML
    void search_user_bill(){
    	col_no.setCellValueFactory(new PropertyValueFactory<Bill,Integer>("no"));
    	col_name.setCellValueFactory(new PropertyValueFactory<Bill,String>("name"));
    	col_price.setCellValueFactory(new PropertyValueFactory<Bill,String>("price"));
    	col_barcode.setCellValueFactory(new PropertyValueFactory<Bill,String>("code"));
    	col_type.setCellValueFactory(new PropertyValueFactory<Bill,String>("brand"));
    	col_category.setCellValueFactory(new PropertyValueFactory<Bill,String>("category"));
    	col_no.setCellValueFactory(new PropertyValueFactory<Bill,Integer>("no"));

	           dataList = connectDB.getDatausers_bill();
	           table_bill.setItems(dataList);
	           FilteredList<Bill> filteredData = new FilteredList<>(dataList, b -> true);  
	           search_bill.textProperty().addListener((observable, oldValue, newValue) -> {
	    filteredData.setPredicate(person -> {
	       if (newValue == null || newValue.isEmpty()) {
	        return true;
	       }    
	       String lowerCaseFilter = newValue.toLowerCase();
	       
	       if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	        return true; // Filter matches name
	       } else if (person.getPrice().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	        return true; // Filter matches email
	       }else if (person.getCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	        return true; // Filter matches phone
	       }
//	       else if (String.valueOf(person.getBusiness()).indexOf(lowerCaseFilter)!=-1)
//	            return true;// Filter matches username
	                                   
	            else  
	             return false; // Does not match.
	      });
	     });  
	     SortedList<Bill> sortedData = new SortedList<>(filteredData);  
	     sortedData.comparatorProperty().bind(table_bill.comparatorProperty());  
	     table_bill.setItems(sortedData);      
	       
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
	
	
		
	private static String a;
	void showcode(String text) {
				
			search_bill.setText(text);
			a=search_bill.getText();
			System.out.println(a);
	}
		//---------------------------------------------
		
    @FXML
    private void handleScanCustomer(KeyEvent event) throws SQLException {
    	scannBarcode();
    	
        
    }
	
    private void scannBarcode(){
        

    	
        try {
                            
	    	conn=connectDB.ConnectDb();
	    	String query= "Select * from customer where cus_code = ? ";
			pst = conn.prepareStatement(query);
			pst.setString(1, text_cus_code.getText());
			pst.execute();
			rs=pst.executeQuery();
            
            if(rs.next()){
            	
                int cus_id,cus_code,cus_point;
                String cus_name;
                cus_id = rs.getInt(1);
                cus_name = rs.getString(2); 
                cus_code = rs.getInt(4);
                cus_point = rs.getInt(8);

                text_cus_id.setText(String.valueOf(cus_id));
                label_cus_poit.setText(String.valueOf(cus_point));
                label_cus_name.setText(cus_name);
                label_error_cus.setVisible(false);

                if (cus_point>=1000 && cus_point<=30000) {
                	
                  int diem = (cus_point/1000);
                  text_discount.setText(Integer.toString(diem));
                  label_cus_point_end.setText("0");
                  lable_message_sale.setText("You get "+ diem +" discount!!!");
                }else if (cus_point > 30000 ) {
                	int x = 0;
                               	
                	int point = (30000/1000);            	
                    int diem = (cus_point-30000);
                    
                    text_discount.setText(Integer.toString(point));
                    label_cus_point_end.setText(String.valueOf(diem));
					System.out.println(point);
					System.out.println(diem);
					lable_message_sale.setText("You get 30% discount!!!");
					
				}else if (cus_point < 1000) {
					
					
					
					label_cus_point_end.setText(String.valueOf(cus_point));
					label_cus_poit.setText(String.valueOf(cus_point));
					lable_message_sale.setText("You get "+ text_discount.getText() + " discount!!!");
					text_discount.setText("0");
				}
                
//                double diem = (cus_point/10);
//                text_discount.setText(String.valueOf(diem));
//                System.out.println(cus_point);
//                System.out.println(diem);
                
                
            }
             if (text_cus_code.getText().trim().equals("")) {
            	 	label_error_cus.setVisible(true);
                   label_cus_poit.setText(".  .  .");
                   label_cus_name.setText(".  .  .");
                   lable_message_sale.setText("");
        	}
             
             
             if (label_cus_poit.getText().trim().equals("") || label_cus_name.getText().trim().equals("")) {
         	 	label_error_cus.setVisible(true);

             }
             bill_Area.setText("Welcome " +label_cus_name.getText()+ " to SuperMarket"+"\n");
            rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Bill_employee.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
    }
    
    @FXML
    void btn_add_point(ActionEvent event) {
    	try {
    	conn = connectDB.ConnectDb();
        String value1 = text_cus_id.getText();
        String value2 = label_tien.getText();
        System.out.println(value2);
        String sql = "update customer set cus_point= '"+value2+"' where cus_id = '"+value1+"' ";
        pst= conn.prepareStatement(sql);
        pst.execute();
        
        label_cus_point_end.setText("");
        label_cus_poit.setText("");
        label_cus_name.setText("");
        label_error_cus1.setVisible(true);
        btn_create_order.setDisable(false);
        label_show1.setText("");
        text_cus_code.setText("");
        btn_cong_point.setDisable(true);
		} catch (Exception e) {
			// TODO: handle exception
		}

    	
    	
    }
	
	    @FXML
	    void quet(ActionEvent event) {
	    	
	    	WebcamQRCodeExample code = new WebcamQRCodeExample();	
	    	code.setVisible(true);	    	
	    	System.out.println(code.getStr());
	    	String ab = code.getStr();
	    	search_bill.setText(ab);
	    	
	    	
	    	
	    }
		private Webcam webcam = null;
		private WebcamPanel panel = null;
	    
		public void run() {

			do {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Result result = null;
				BufferedImage image = null;

				if (webcam.isOpen()) {

					if ((image = webcam.getImage()) == null) {
						continue;
					}

					LuminanceSource source = new BufferedImageLuminanceSource(image);
					BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

					try {
						
						result = new MultiFormatReader().decode(bitmap);
					} catch (NotFoundException e) {
						// fall thru, it means there is no QR code in image
					}
				}

				if (result != null) {
					search_bill.setText(result.getText());	
					 a = result.getText();
					System.out.println(a);
				}

			} while (true);
		}
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r, "example-runner");
			t.setDaemon(true);
			return t;
		}
	    
	    
		
}
