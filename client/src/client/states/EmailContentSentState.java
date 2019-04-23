package client.states;

import client.Client;
import client.Notification;
import client.NotificationType;

public class EmailContentSentState extends State {
    public EmailContentSentState(Client client) {
        super(client);
    }

    @Override
    public void handleResult(String result) {
        this.client.setState(new WritingState(this.client));
        this.client.notifyGUI(
                new Notification(
                        NotificationType.ENDED,
                        null
                )
        );
    }
}
