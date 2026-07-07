public class User {
    private int userID;
    private String userName;
    private String userEmail;
    private String userAPI;

    public User(int userID,String userName,String userEmail,String userAPI){
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAPI = userAPI;
    }

    public String getUserName(){
        return this.userName;
    }
    public String getUserAPI(){
        return this.userAPI;
    }
    public void isValidEmail(){
        System.out.println(this.userEmail != null && this.userEmail.contains("@"));
    }

    void userInfo(){
        System.out.println("User Name: "+userName+" | user Email: "+userEmail+" | User API: "+userAPI);
    }
}
