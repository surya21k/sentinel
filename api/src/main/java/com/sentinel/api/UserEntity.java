package com.sentinel.api;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "max_requests")
    private int maxRequests;

    @Column(name = "window_seconds")
    private int windowSeconds;

    // JPA REQUIRES a no-argument constructor — it creates objects internally before filling fields
    public UserEntity() {}

    public UserEntity(String username, String email, String apiKey, int maxRequests, int windowSeconds) {
        this.username = username;
        this.email = email;
        this.apiKey = apiKey;
        this.maxRequests = maxRequests;
        this.windowSeconds = windowSeconds;
    }

    // Getters and setters — JPA needs these to read/write field values
    public int getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }
    public int getMaxRequests() { return maxRequests; }
    public void setMaxRequests(int maxRequests) { this.maxRequests = maxRequests; }
    public int getWindowSeconds() { return windowSeconds; }
    public void setWindowSeconds(int windowSeconds) { this.windowSeconds = windowSeconds; }
}