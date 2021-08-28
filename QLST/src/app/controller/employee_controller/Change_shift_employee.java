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
import app.model.ChangeShift;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Change_shift_employee implements Initializable{
	
	  @FXML
	    private TableColumn<ChangeShift,String> col_emp_name;

	    @FXML
	    private TableColumn<ChangeShift,String> col_emp_email;

	    @FXML
	    private TableColumn<ChangeShift,String> col_main_shift;

	    @FXML
	    private TableColumn<ChangeShift,String> col_switch_shift;

	    @FXML
	    private TableColumn<ChangeShift,String> col_date_switch;

	    @FXML
	    private TableColumn<ChangeShift,String> col_shift_change_date;

	    @FXML
	    private TableView<ChangeShift> table_changeshift;
	    
	    @FXML
	    private TableColumn<ChangeShift, Integer> col_no_shift_id;
	    
	    @FXML
	    private Label ltotal;
	    
	    int index = -1;
	    
	    Connection conn =null;
	    ResultSet rs = null;
	    PreparedStatement pst = null;
	    ObservableList<ChangeShift> listM;
	    ObservableList<ChangeShift> dataList;
	    
	    public void UpdateTable_changeshift(){


	    	col_no_shift_id.setCellValueFactory(new PropertyValueFactory<ChangeShift,Integer>("shift_id"));
	    	col_emp_name.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("emp_name"));
	    	col_emp_email.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("emp_email"));
	    	col_main_shift.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("main_shift"));
	    	col_switch_shift.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("switch_shift"));
	    	col_date_switch.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("date_switch"));
	    	col_shift_change_date.setCellValueFactory(new PropertyValueFactory<ChangeShift,String>("shift_change_date"));
	    	
	    	
	    	


	        listM = connectDB.getDataChangeShift();
	        table_changeshift.setItems(listM);
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
		UpdateTable_changeshift();
	}
	
	
}
