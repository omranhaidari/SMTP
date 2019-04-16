package transport;

import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Sender extends Observable implements Runnable {
    private Socket socket;
    private Queue<Packet> packets;
    private boolean run;
    private boolean toQuit;

    public Sender(Socket socket) {
        this.socket = socket;
        this.packets = new ConcurrentLinkedQueue<>();
        this.run = true;
        this.toQuit = false;
    }

    public synchronized void sendPacket(Packet packet) {
        packets.add(packet);
        notify();
    }

    public synchronized void toQuit() {
        toQuit = true;
        notify();
    }

    public synchronized void stop() {
        this.run = false;
        notify();
    }

    @Override
    public synchronized void run() {
        try {
            while (this.run) {
                while (packets.size() > 0) {
                    try {
                        packets.poll().send(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (toQuit) {
                    run = false;
                    setChanged();
                    notifyObservers("SENDER_CLOSED");
                } else
                    wait();
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        } finally {
            System.out.println("Sender closed.");
        }
    }
}

