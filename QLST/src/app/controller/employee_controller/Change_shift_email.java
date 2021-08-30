package app.controller.employee_controller;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Change_shift_email {
	@FXML
    private AnchorPane View_email;

    @FXML
    private TextField email_name;

    @FXML
    private TextField email_email;

    @FXML
    private TextArea email_content;

    @FXML
    void Sent(ActionEvent event) {
    	JOptionPane.showMessageDialog(null, "Send successfully.");    
        ((Stage) View_email.getScene().getWindow()).close();
    }
    private static String a1,a2,a3;
	public void getInformation( String name, String email) {
		// TODO Auto-generated method stub
		email_name.setText(name);    	
		email_email.setText(email);
    	a2=email_name.getText();
    	a3=email_email.getText();
    	System.out.println(a2+" / "+a3+" / "+a1);
	}

}

