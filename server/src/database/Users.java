package database;

import java.io.File;
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

    public static Users loadUsers(String directoryPath, String serverDomain) {
        Users users = new Users();

        File directory = new File(directoryPath);
        File[] mailboxes = directory.listFiles();
        if(mailboxes != null) {
            for (File mailbox : mailboxes) {
//                System.out.println(mailbox.getAbsolutePath());
                String mailboxAddress = "<" + mailbox.getName() + "@" + serverDomain + ">";
                users.addUser(new User(mailboxAddress, mailbox));
            }
        }

        return users;
    }
}