package client;

public class AddressFactory {
    public static String getAddress(String domain) {
        if (domain.equals("quatrea.uk")) {
            return "127.0.0.1"; // adresse IP du client
        } else if (domain.equals("informatique.fr")) {
            return "127.0.0.1"; // adresse IP du client
        } else if (domain.equals("polytech.com")) {
            return "127.0.0.1"; // adresse IP du client
        }
        return "";
    }
}
