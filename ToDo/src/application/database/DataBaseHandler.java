package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Task;
import application.model.User;

public class DataBaseHandler {
	Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        
    	String connectionString = "jdbc:mysql://localhost:3306/todo";
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, "root", "1234");

        return dbConnection;
    }
    
    // create user in the DB
    public void signUpUser(User user) {

        String insert =  "INSERT INTO users (firstname, lastname, username, password) values (?,?,?,?)";
               

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet getUser(User user) {
        ResultSet resultSet = null;


        if (!user.getUserName().equals("") || !user.getPassword().equals("")) {
            String query ="SELECT * FROM users where username= ? and password=?";
                    
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());

                resultSet = preparedStatement.executeQuery();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            
        }
        return resultSet;
    }
    
    public ResultSet getTasksByUser(int userId) {

        ResultSet resultTasks = null;

        String query = "SELECT * FROM tasks WHERE userid=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

            preparedStatement.setInt(1, userId);


            resultTasks = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return resultTasks;
    }
    
    // Save task to DB by userId
    public void insertTask(Task task) {
    	String insert =  "INSERT INTO tasks (task, userid) values (?,?)";
    	
    	try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, task.getTask());
            preparedStatement.setInt(2, task.getUserId());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }    	   	
    }
    
    public void deleteTask(int taskId) {
    	String delete = "DELETE FROM tasks WHERE taskid =?";
    	
    	try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);

            preparedStatement.setInt(1, taskId);


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    

}
