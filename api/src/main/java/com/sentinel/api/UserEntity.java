package com.sentinel.api;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "API key is required")
    @Column(name = "api_key")
    private String apiKey;

    @Min(value = 1, message = "Max requests must be at least 1")
    @Column(name = "max_requests")
    private int maxRequests;

    @Min(value = 1, message = "Window seconds must be at least 1")
    @Column(name = "window_seconds")
    private int windowSeconds;

    public UserEntity() {}

    public UserEntity(String username, String email, String apiKey, int maxRequests, int windowSeconds) {
        this.username = username;
        this.email = email;
        this.apiKey = apiKey;
        this.maxRequests = maxRequests;
        this.windowSeconds = windowSeconds;
    }

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