import javax.net.ssl.SSLHandshakeException;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;

public class Receiver extends Observable implements Runnable {

    private boolean run;
    private Socket socket;

    public Receiver(Socket socket) {
        this.run = true;
        this.socket = socket;
    }

    public synchronized void stop() {
        this.run = false;
        notify();
    }

    @Override
    public void run() {
        try {
            BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (this.run) {
                String input = buf.readLine();
                if (input != null) {
                    setChanged();
                    notifyObservers(input);
                }
            }
        } catch (IOException e) {
            if (e instanceof SSLHandshakeException) {
                JOptionPane.showMessageDialog(null, "Pas de cipher suites en commun.", "Erreur", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            } else {
                //e.printStackTrace();
            }
        } finally {
            Thread.currentThread().interrupt();
        }
    }
}
