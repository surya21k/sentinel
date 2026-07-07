public class Main{
    public static void main(String[] args){
        User user1 = new User(1,"Surya","surya@gmail","abcx1234");
        User user2 = new User(2,"shristi","shristi@gmail","efgh0987");
        user1.userInfo();
        user2.userInfo();
        user1.isValidEmail();
        user2.isValidEmail();

    }
}