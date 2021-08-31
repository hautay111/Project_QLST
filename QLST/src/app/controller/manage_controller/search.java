package app.controller.manage_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class search {
	
	@FXML
    private BorderPane main;

    @FXML
    private DatePicker date;

    @FXML
    private DatePicker from;

    @FXML
    private DatePicker to;

    @FXML
    private Button search;
    
    @FXML
    void back(MouseEvent event) {
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void Quarter_1(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/quarter_1.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void Quarter_2(ActionEvent event) {

    }

    @FXML
    void Quarter_3(ActionEvent event) {
    	try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/manage/search_dashboard/quarter_3.fxml"));
	                Parent root = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));  
	                stage.show();             	  
	               
	        } catch(Exception e) {
	        	
	           e.printStackTrace();
	         }
    	Stage stage = (Stage) main.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void Quarter_4(ActionEvent event) {

    }

    @FXML
    void T1(ActionEvent event) {

    }

    @FXML
    void T10(ActionEvent event) {

    }

    @FXML
    void T11(ActionEvent event) {

    }

    @FXML
    void T12(ActionEvent event) {

    }

    @FXML
    void T2(ActionEvent event) {

    }

    @FXML
    void T3(ActionEvent event) {

    }

    @FXML
    void T4(ActionEvent event) {

    }

    @FXML
    void T5(ActionEvent event) {

    }

    @FXML
    void T6(ActionEvent event) {

    }

    @FXML
    void T7(ActionEvent event) {

    }

    @FXML
    void T8(ActionEvent event) {

    }

    @FXML
    void T9(ActionEvent event) {

    }

    @FXML
    void search_date(ActionEvent event) {

    }

}
