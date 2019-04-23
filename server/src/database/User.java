package database;

import java.io.File;
import java.util.List;

public class User {
    private String address;
    private File mailbox;

    public User(String raw) {
        this.address = raw.substring(1, raw.length() - 1);
    }

    public User(String raw, File mailbox) {
        this.address = raw.substring(1, raw.length() - 1);
        this.mailbox = mailbox;
    }

    public String getAddress() {
        return address;
    }

    public File getMailbox() {
        return mailbox;
    }

    public boolean writeMail(List<String> mail) {
        return false; // TODO Vérifie si la boîte mail n'est pas lock, la locke, puis écrit le mail, puis délocke
    }

    @Override
    public String toString() {
        return String.format("<%s>", address);
    }
}

