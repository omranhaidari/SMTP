package database;

import java.util.HashMap;
import java.util.Map;

public class Users {
    private Map<String, User> users;

    public Users() {
        this.users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getAddress(), user);
    }

    public User getUser(String address) {
        return users.get(address);
    }

    public boolean hasUser(String address) { // Les adresses mails ne sont pas sensibles à la casse
        return users.containsKey(address.toLowerCase());
    }

    public static Users loadUsers(String directoryPath) {
        Users users = new Users();



        return users;
    }
}