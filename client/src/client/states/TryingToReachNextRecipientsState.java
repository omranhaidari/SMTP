package client.states;

import client.Client;
import client.Packet;

public class TryingToReachNextRecipientsState extends State {
    public TryingToReachNextRecipientsState(Client client) {
        super(client);
    }

    @Override
    public void handleResult(String result) {
        String recipient = this.client.getMessage().popRecipient(this.client.getDomain());
        if (recipient != null) {
            this.client.setLastRecipientTried(recipient);
            this.client.sendPacket(new Packet(String.format("RCPT TO:<%s>", recipient)));
        } else {
            this.client.setState(new WaitingBeforeSendingEmailContentState(this.client));
            this.client.setLastRecipientTried(recipient);
            this.client.sendPacket(new Packet("DATA"));
        }
    }
}
