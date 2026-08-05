public class User {
    private int id;
    private String username;
    private String email;
    private ApiKey apiKey;

    public User(int id, String username, String email, ApiKey apiKey) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.apiKey = apiKey;
    }

    public User(int id, String username, String email) {
        this(id, username, email, null); // constructor chaining
    }

    public String getUsername() {
        return username;
    }

    public boolean canAccessApi() {
        return apiKey != null && apiKey.isActive();
    }

    public void printUserInfo() {
        String keyStatus = (apiKey == null) ? "No API Key" : "Key: " + apiKey.getKeyValue() + " | Active: " + apiKey.isActive();
        System.out.println(username + " | " + email + " | " + keyStatus);
    }
}