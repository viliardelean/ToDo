package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
	    private JFXButton loginSignupButton;
	    
	    @FXML
	    private Label wrongLoginLabel;

	    
//	    private DbHandler dbhandler;


	    @FXML
	    void initialize() {
	    	
	    	
	    	
	    }

}
