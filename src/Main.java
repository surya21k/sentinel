import java.util.*;
public class Main {
    static void simulateRequest(Map<String,User> registry,String incomingKey){
        User user = registry.get(incomingKey);
        if(user == null){
            System.out.println("Request with key '" + incomingKey + "' -> REJECTED (unknown key)");
            return;
        }
        System.out.println("Request From " + user.getUsername() + " -> allowed: " + user.canAccessApi());
    }
    public static void main(String[] args) {
        Map<String,User> userRegistry = new HashMap<>();

        RateLimitPolicy policy1 = new RateLimitPolicy(4, 30); // 3 requests per 60 sec
        ApiKey key1 = new ApiKey("abc123", policy1);
        User user1 = new User(1, "ashwin", "ashwin@mail.com", key1);

        RateLimitPolicy policy2 = new RateLimitPolicy(3, 30); // 3 requests per 60 sec
        ApiKey key2 = new ApiKey("abc123abc", policy2);
        User user2 = new User(1, "shristi", "shristi@mail.com", key2);

        userRegistry.put(key1.getKeyValue(), user1);
        userRegistry.put(key2.getKeyValue(), user2);

        simulateRequest(userRegistry,"abc123");
        simulateRequest(userRegistry,"abc123abc");
        simulateRequest(userRegistry,"xyzfv2445");

    }
}