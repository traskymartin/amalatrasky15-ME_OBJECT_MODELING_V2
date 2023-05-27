package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.crio.jukebox.entities.User;

public class UserRepository {
    private Map<Integer, User> users;

    public UserRepository() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
            users.put(user.getId(),user);
        
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    public Integer getUserCount() {
        return users.size();
    }    
}
