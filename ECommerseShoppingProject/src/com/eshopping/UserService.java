package com.eshopping;
import java.util.ArrayList;
import java.util.List;

public class UserService {
	    private List<User> users;

	    public UserService() {
	        users = new ArrayList<>();
	    }

	    public void register(String username, String password, boolean isAdmin) {
	        users.add(new User(username, password, isAdmin));
	    }

	    public User login(String username, String password) {
	        for (User user : users) {
	            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
	                return user;
	            }
	        }
	        return null;
	    }
}
