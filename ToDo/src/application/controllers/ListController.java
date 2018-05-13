package application.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import application.database.DataBaseHandler;
import application.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListController {
	
	private int currentUserId;

    @FXML
    private JFXListView<Task> listTask;

    @FXML
    private JFXButton addTaskButton;
    
    private ObservableList<Task> tasks;
    
    private DataBaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {
    	
    	addTaskButton.setOnAction(e -> showAddTask());
    }

  
	private void showAddTask() {
		addTaskButton.getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/views/addItemView.fxml"));

		try {
			loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Parent root = loader.getRoot();
		AddItemController aic = loader.getController();
		aic.setCurrentUserId(currentUserId);
		
		
		
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("To Do");
		stage.show();
	}


	public void setCurentUserId(int curentUserId) {
		this.currentUserId = curentUserId;
	}


	public void loadCells() throws SQLException {
		tasks=FXCollections.observableArrayList();
        databaseHandler = new DataBaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(currentUserId);
        
        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            

            tasks.addAll(task);
            System.out.println(task.getTaskId() + " " + task.getTask());
        }

     
        listTask.setItems(tasks);
        listTask.setCellFactory(CellController -> new ItemTaskController());
	}
	   
}
