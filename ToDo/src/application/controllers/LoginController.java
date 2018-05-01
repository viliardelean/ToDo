package application.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.database.DataBaseHandler;
import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginController {

	private int userId;

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

	private DataBaseHandler databaseHandler = new DataBaseHandler();

	@FXML
	void initialize() {

		// A new user must first create un account by clicking this button
		signupButton.setOnAction(e -> {
			showSignUpPage();
		});
		
		// An existing user provides it's credentials and clicks Login
		loginButton.setOnAction(e -> {
			loginUser();
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
	

	private void loginUser() {
		String username = loginUsername.getText();
		String password = loginPassword.getText();

		User user = new User(username, password);

		ResultSet userRow = databaseHandler.getUser(user);
		int counter = 0;
		try {
			while (userRow.next()) {
				counter++;
				userId = userRow.getInt("userid");
			}

			if (counter == 1)  {
				
				showListView();
			}
			else
				wrongLoginLabel.setText("Incorrect username or password");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	// If the user is found in DB, show the list view
	private void showListView() {
		loginButton.getScene().getWindow().hide();
		
		try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/views/listView.fxml"));

		
		Parent root = (Parent) loader.load();
		
		
		
		ListController listController= loader.getController();
		listController.loadCells(userId);
			 
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("To Do");
		
		stage.show();
		
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
