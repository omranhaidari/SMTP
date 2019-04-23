package client.states;

import client.Client;
import client.Packet;

public class WaitingBeforeSendingEmailContentState extends State {
    public WaitingBeforeSendingEmailContentState(Client client) {
        super(client);
    }

    @Override
    public void handleResult(String result) {
        this.client.setState(new EmailContentSentState(this.client));
        this.client.sendPacket(new Packet(this.client.getMessage().getBody()));
    }
}
