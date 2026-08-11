public class Main {
    public static void main(String[] args) {
        RateLimitPolicy policy1 = new RateLimitPolicy(4, 30);
        ApiKey key1 = new ApiKey("abc123", policy1);
        User user1 = new User(1, "ashwin", "ashwin@mail.com", key1);

        UserRepository repo = new UserRepository();
        repo.saveUser(user1, key1, policy1);
        repo.printAllUsers();
    }
}