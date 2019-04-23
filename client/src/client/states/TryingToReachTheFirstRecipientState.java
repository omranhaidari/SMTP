package client.states;

import client.Client;
import client.Notification;
import client.NotificationType;
import client.Packet;

public class TryingToReachTheFirstRecipientState extends State {
    public TryingToReachTheFirstRecipientState(Client client) {
        super(client);
    }

    @Override
    public void handleResult(String result) {
        if (result.startsWith("250")) {
            String recipient = this.client.getMessage().popRecipient(this.client.getDomain());
            if (recipient != null) {
                this.client.setState(new TryingToReachNextRecipientsState(this.client));
                this.client.setLastRecipientTried(recipient);
                this.client.sendPacket(new Packet(String.format("RCPT TO:<%s>", recipient)));
            } else {
                this.client.setState(new WaitingBeforeSendingEmailContentState(this.client));
                this.client.sendPacket(new Packet("DATA"));
            }
        } else {
            this.client.notifyGUI(
                    new Notification(
                            NotificationType.UNREACHBLE_RECIPIENT,
                            this.client.getLastRecipientTried()
                    )
            );

            String recipient = this.client.getMessage().popRecipient(this.client.getDomain());
            if (recipient == null) {
                this.client.setState(new WritingState(this.client));
                this.client.notifyGUI(
                        new Notification(
                                NotificationType.ENDED,
                                null
                        )
                );
            } else {
                this.client.setLastRecipientTried(recipient);
                this.client.sendPacket(new Packet(String.format("RCPT TO:<%s>", recipient)));
            }
        }
    }
}
