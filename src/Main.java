public class Main {
    public static void main(String[] args) {
        ApiKey key1 = new ApiKey("abc0122dasgyyu3323453t6l");
        User user1 = new User(1, "ashwin", "ashwin@mail.com", key1);
        User user2 = new User(2, "priya", "priya@mail.com"); // overloaded constructor, no key

        user1.printUserInfo();
        user2.printUserInfo();

        System.out.println("Ashwin can access API: " + user1.canAccessApi());
        System.out.println("Priya can access API: " + user2.canAccessApi());

        key1.deactivate();
        System.out.println("After deactivation — Ashwin can access API: " + user1.canAccessApi());
    }
}