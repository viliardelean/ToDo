package application.controllers;

import java.io.IOException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.database.DataBaseHandler;

public class SignupController {
 
    @FXML
    private JFXButton signupButton;

    @FXML
    private JFXTextField signupFirstName;

    @FXML
    private JFXTextField signupLastName;

    @FXML
    private JFXTextField signupUsername;

    @FXML
    private JFXPasswordField signupPassword;


    @FXML
    void initialize() {
    	signupButton.setOnAction(event -> {
    		createUser();
    	});
    }


    private void createUser() {
    	DataBaseHandler databaseHandler = new DataBaseHandler();
    	
		// Get the fields from view 
    	String name = signupFirstName.getText();
        String lastName = signupLastName.getText();
        String userName = signupUsername.getText();
        String password = signupPassword.getText();
        // Create user
        try {
        	User user = new User(name, lastName, userName, password);
        	showLoginPage();
        	databaseHandler.signUpUser(user);
        	
        } catch (IllegalArgumentException iae) {
        	System.out.println(iae.getMessage());
        }
        	
        
	}
	
	private void showLoginPage() {
		signupButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/application/views/LoginView.fxml"));


        try {
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("To Do");
        stage.setResizable(false);
        stage.show();		
	}
}
