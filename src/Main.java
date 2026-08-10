import java.util.*;

public class Main {
    static void simulateRequest(Map<String, User> registry, String incomingKey) {
        User user = registry.get(incomingKey);
        if (user == null) {
            System.out.println("Request with key '" + incomingKey + "' -> REJECTED (unknown key)");
            return;
        }
        System.out.println("Request From " + user.getUsername() + " -> allowed: " + user.canAccessApi());
    }

    public static void main(String[] args) {
        Map<String, User> userRegistry = new HashMap<>();

        RateLimitPolicy policy1 = new RateLimitPolicy(4, 30);
        ApiKey key1 = new ApiKey("abc123", policy1);
        User user1 = new User(1, "ashwin", "ashwin@mail.com", key1);
        userRegistry.put(key1.getKeyValue(), user1);

        simulateRequest(userRegistry, "abc123");

        // Now try creating an invalid policy — should NOT crash the whole program
        try {
            RateLimitPolicy badPolicy = new RateLimitPolicy(-5, 30);
        } catch (InvalidPolicyException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        System.out.println("Program continues running normally after the error was handled.");
    }
}