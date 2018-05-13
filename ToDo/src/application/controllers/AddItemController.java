package application.controllers;

import java.io.IOException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.database.DataBaseHandler;
import application.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddItemController {
	
	private int currentUserId;
	
    @FXML
    private JFXTextField taskField;

    @FXML
    private JFXButton saveTaskButton;
    
    private DataBaseHandler databaseHandler;

    @FXML
    void initialize() {
        saveTaskButton.setOnAction(e -> saveTask());
    }

	private void saveTask() {
		String text = taskField.getText();
		System.out.println(currentUserId);
		Task task = new Task(text, currentUserId);
		
		databaseHandler = new DataBaseHandler();
		databaseHandler.insertTask(task);
		
		saveTaskButton.getScene().getWindow().hide();
		showListControlle();
		
	}

	private void showListControlle() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/views/listView.fxml"));
			
			Parent root = (Parent) loader.load();
			// Load the task cells of this user	
			ListController listController= loader.getController();
			listController.setCurentUserId(currentUserId);
			listController.loadCells();
				 
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

	public void setCurrentUserId(int currentUserId) {
		this.currentUserId = currentUserId;
	}
}
