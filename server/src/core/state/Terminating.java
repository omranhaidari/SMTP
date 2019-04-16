package core.state;

import core.Connection;
import transport.Packet;

public class Terminating extends State {
    public Terminating(Connection connection) {
        super(connection);
        connection.getSender().sendPacket(new Packet(String.format("221 %s Service closing transmission channel",
                connection.getAddress())));
        connection.stop();
    }
}
