package application.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import application.database.DataBaseHandler;
import application.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class ListController {

	
	
	    @FXML
	    private JFXListView<Task> listTask;

	    @FXML
	    private JFXButton addTaskButton;
	    
	    private ObservableList<Task> tasks;
	    
	    private DataBaseHandler databaseHandler;

	    @FXML
	    void initialize() throws SQLException {
	    	/*getUserId();*/
	    /*	System.out.println();*/
	    	loadCells();
	    	
	    	/*getUserId();*/
	    }

	  
	 

/*		public int getUserId() {
			System.out.println(this.userId);
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}
*/
		private void loadCells() throws SQLException {
			tasks=FXCollections.observableArrayList();
	        databaseHandler = new DataBaseHandler();
	        ResultSet resultSet = databaseHandler.getTasksByUser(11);
	        
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
