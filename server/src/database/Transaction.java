package database;

import core.Connection;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private String address;
    private User sender;
    private Users receptors;
    private List<String> data;
    private Connection connection;

    public Transaction(Connection connection, String address) {
        this.connection = connection;
        this.address = address;
        this.receptors = new Users();
        this.data = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public boolean addUser(User user) {
        if (connection.getUsers().hasUser(user.getAddress())) {
            receptors.addUser(user);
            return true;
        }
        return false;
    }

    public void addData(String data) {
        this.data.add(data);
    }

    public List<String> getData() {
        return this.data;
    }

    /*public void finish() {
        // Ecrit les mails dans les boites mails
        for (User user : this.receptors.getUsers()) {
            user.writeMail(this);
        }
    }*/
}
