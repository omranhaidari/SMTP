package client.states;

import client.Client;
import client.Packet;

public class ExtensionsReceivedState extends State {
    public ExtensionsReceivedState(Client client) {
        super(client);
    }

    @Override
    public void handleResult(String result) {
        if (result.startsWith("250")) {
            this.client.setState(new TryingToReachTheFirstRecipientState(this.client));
            String client = this.client.getMessage().popRecipient(this.client.getDomain());
            this.client.setLastRecipientTried(client);
            this.client.sendPacket(new Packet(String.format("RCPT TO:<%s>", client)));
        }
    }
}
