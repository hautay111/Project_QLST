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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

public class Bill_employee implements Initializable{

	
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
    private TableColumn<Bill,String> col_name;

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
    private TextField search_bill;
    

    @FXML
    private Label ltotal;
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ObservableList<Bill> listM;
    ObservableList<Bill> dataList;

    private static String code_bar;
    public void initialize(URL url, ResourceBundle rb) {
    UpdateTable_bill();
    search_user_bill();
    num=0;
    bill.setText("---------------------------SuperMarket------------------------"+"\n"+"\n"+"Nhân viên: Hậu Kar"+"\n"+
    "Name                                Price               Amount                       Total"+"\n"+"\n");

         

    int a = 2;
    double b = 200.2000;
    double c = a * b;
    System.out.println(c);

    
    
    // Code Source in description
    } 
    
    
    

    
    
    
    int p = 0;
    @FXML
    void pay(ActionEvent event) {    
        DecimalFormat formatter = new DecimalFormat("###,###,###");           
//        
//        String moneyString = formatter.format(text_price.getText());
//        double money =	Double.parseDouble(moneyString); 
//        System.out.println(moneyString);
    	
    	
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
        
        ltotal.setText(""+p);
    	    	
        String s=bill.getText();
        System.out.println(p);
        c++;
        
        bill.setText(s+String.valueOf(c)+"  "+text_name.getText()+"                              "+text_price.getText()+"              "+
        text_amount.getText()+"                    "+moneyString+" vnđ"+"\n"+"------------------------------------------------------------------------------\n"
        		
       
        );
        


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
    @FXML
    private Button print;
    @FXML
    private TextArea bill;
    
    @FXML
    private void Print() {
    	print.setVisible(false);
        print(bill);
        
		      try {
		      num++;
		       PrintWriter f = new PrintWriter("bill "+String.valueOf(num)+".txt");
		       f.println(bill.getText());
		      f.close();
			  } catch (FileNotFoundException ex) {
			      Logger.getLogger(Bill_employee.class.getName()).log(Level.SEVERE, null, ex);
			      System.out.println(ex);
			  }
    }
         
    
    private void print(Node node) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(node.getScene().getWindow())){
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }
        print.setVisible(true);
    }
    
    
    public void UpdateTable_bill(){


    	col_no.setCellValueFactory(new PropertyValueFactory<Bill,Integer>("no"));
    	col_name.setCellValueFactory(new PropertyValueFactory<Bill,String>("name"));
    	col_type.setCellValueFactory(new PropertyValueFactory<Bill,String>("brand"));
    	col_price.setCellValueFactory(new PropertyValueFactory<Bill,String>("price"));
    	col_barcode.setCellValueFactory(new PropertyValueFactory<Bill,String>("code"));
    	col_category.setCellValueFactory(new PropertyValueFactory<Bill,String>("category"));
    	col_no.setCellValueFactory(new PropertyValueFactory<Bill,Integer>("no"));
    	
    	


        listM = connectDB.getDatausers_bill();
        table_bill.setItems(listM);
    }
    
    
    @FXML
    void getSelected_bill(MouseEvent event) {
        index = table_bill.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
            return;
        }
        
        
         
        text_name.setText(col_name.getCellData(index).toString());
        text_code.setText(col_barcode.getCellData(index).toString());
        text_price.setText(col_price.getCellData(index).toString());
 
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
	    void quet(ActionEvent event) {
	    	Barcode_Scanner code = new Barcode_Scanner();	
	    	code.setVisible(true);
	    	search_bill.setText("");
	    	
	    	
	    }
	    
	    
	    
	    
	    
		
}
