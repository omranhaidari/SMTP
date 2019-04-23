package client;

public class PortFactory {
    public static int getPort(String domain) {
        if (domain.equals("quatrea.uk")) {
            return 1339;
        } else if (domain.equals("informatique.fr")) {
            return 1338;
        } else if (domain.equals("polytech.com")) {
            return 1337;
        }
        return -1;
    }
}
