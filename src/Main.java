public class Main {
    public static void main(String[] args) {
        RateLimitPolicy policy = new RateLimitPolicy(4, 30); // 3 requests per 60 sec
        ApiKey key1 = new ApiKey("abc123", policy);
        User user1 = new User(1, "ashwin", "ashwin@mail.com", key1);

        for (int i = 1; i <= 5; i++) {
            boolean allowed = key1.canMakeRequest();
            System.out.println("Request " + i + " allowed: " + allowed);
        }
    }
}