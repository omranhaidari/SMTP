package client;

public class AddressFactory {
    public static String getAddress(String domain) {
        if (domain.equals("quatrea.uk")) {
            return ""; // adresse IP du client
        } else if (domain.equals("informatique.fr")) {
            return ""; // adresse IP du client
        } else if (domain.equals("polytech.com")) {
            return ""; // adresse IP du client
        }
        return "";
    }
}
