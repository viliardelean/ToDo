package application.controllers;

import java.io.IOException;

import com.jfoenix.controls.JFXListCell;

import application.database.DataBaseHandler;
import application.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;



public class ItemTaskController extends JFXListCell<Task> {
	
	 	@FXML
	    private AnchorPane rootAnchorPane;

	    @FXML
	    private Label taskLabel;

	    @FXML
	    private ImageView editButton;

	    @FXML
	    private ImageView deleteButton;
	    
	    private FXMLLoader fxmlLoader;
	    
	    private DataBaseHandler databaseHandler;

	    @FXML
	    void initialize() {
	       

	    }
	    
	    @Override
	    public void updateItem(Task myTask, boolean empty) {

	        databaseHandler = new DataBaseHandler(); 

	        super.updateItem(myTask, empty);

	        if (empty || myTask == null) {
	            setText(null);
	            setGraphic(null);
	        }else {

	             if (fxmlLoader == null ) {
	                  fxmlLoader = new FXMLLoader(getClass()
	                          .getResource("/application/views/itemTaskView.fxml"));
	                  fxmlLoader.setController(this);

	                 try {
	                     fxmlLoader.load();
	                 } catch (IOException e) {
	                     e.printStackTrace();
	                 }
	             }

	             taskLabel.setText(myTask.getTask());

	             int taskId = myTask.getTaskId();
	             
	             setText(null);
	             setGraphic(rootAnchorPane);
	        }
	    }

}
