package database;

public class User {
    private String address;

    public String getAddress() {
        return address;
    }

    public User(String raw) {
        this.address = raw.substring(1, raw.length() - 1);
    }

    @Override
    public String toString() {
        return String.format("<%s>", address);
    }
}

