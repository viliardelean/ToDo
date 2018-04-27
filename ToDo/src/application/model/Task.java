package application.model;

public class Task {

	private int userId;
	private int taskId;
	private String task;
	
    public Task(String task, int userId) {
        this.task = task;
        this.userId = userId;
    }	
	
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getUserId() {
        return this.userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }	

}
