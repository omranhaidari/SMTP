package core.command;

import core.Connection;
import core.state.State;
import core.state.Waiting;
import database.Transaction;
import transport.Packet;

public class ExtendedHello extends Command {
    public ExtendedHello(State state, Connection connection) {
        super(state, connection);
    }

    @Override
    public State execute(String[] args) {
        if (args.length == 2) {
            String clientAddress = args[1];
            connection.getSender().sendPacket(new Packet(String.format("250 %s greets %s", connection.getAddress(), clientAddress)));
            return new Waiting(connection, new Transaction(connection, clientAddress));
        }
        connection.getSender().sendPacket(new Packet("550 Please decline your identity"));
        return state;
    }
}