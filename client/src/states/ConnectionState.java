package states;

import Client;
import Packet;

public class ConnectionState extends State {
    public ConnectionState(Client client) {
        super(client);
    }

    @Override
    public void handleResult(String result) {
        if (result.startsWith("220")) {
            this.client.sendPacket(new Packet("EHLO " + this.client.getMessage().getFromDomain()));
        } else {
            this.client.setState(new WritingState(this.client));
        }
    }
}
