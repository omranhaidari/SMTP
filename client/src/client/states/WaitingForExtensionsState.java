package client.states;

import client.Client;
import client.Packet;

import java.util.LinkedList;
import java.util.List;

public class WaitingForExtensionsState extends State {
    private List<String> supportedExtensions;

    public WaitingForExtensionsState(Client client) {
        super(client);
        this.supportedExtensions = new LinkedList<>();
    }

    @Override
    public void handleResult(String result) {
        this.supportedExtensions.add(result);
        this.client.setState(new ExtensionsReceivedState(this.client));
        this.client.sendPacket(new Packet(String.format("MAIL FROM:<%s>", this.client.getMessage().getFrom())));
    }
}
