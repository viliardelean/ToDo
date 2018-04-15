package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton signupButton;
    
    @FXML
    private Label wrongLoginLabel;



    @FXML
    void initialize() {
    	signupButton.setOnAction(e->  {
    		showSignUpPage();	    	
    	});
    }



    // Shows the signup Page when the respective button is pushed
	private void showSignUpPage() {
		loginButton.getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/views/SignupView.fxml"));
		
		try {
			loader.load();
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
		
		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("To Do");
		stage.show();		
	}
}
