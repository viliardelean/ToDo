package application.model;

public class User {
 	private String firstName;
    private String lastName;
    private String userName;
    private String password;

    
    public User() {
    }

    public User(String firstName, String lastName, String userName, String password) {
        setFirstName(firstName);
        this.lastName = lastName;
        setUserName(userName);
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
    	if (userName.length()<3) {
    		throw new IllegalArgumentException("The user name must be at least 3 characthers long");
    	}
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
