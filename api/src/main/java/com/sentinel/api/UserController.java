package com.sentinel.api;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public List<Map<String, Object>> getAllUsers() {
        List<Map<String, Object>> users = new ArrayList<>();

        Map<String, Object> user1 = new HashMap<>();
        user1.put("id", 1);
        user1.put("username", "ashwin");
        user1.put("apiKey", "abc123");
        user1.put("isActive", true);
        user1.put("requestsUsed", 2);
        user1.put("maxRequests", 4);
        users.add(user1);

        Map<String, Object> user2 = new HashMap<>();
        user2.put("id", 2);
        user2.put("username", "priya");
        user2.put("apiKey", "xyz789");
        user2.put("isActive", false);
        user2.put("requestsUsed", 0);
        user2.put("maxRequests", 2);
        users.add(user2);

        return users;
    }
}