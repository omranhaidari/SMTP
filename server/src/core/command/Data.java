package core.command;

import core.Connection;
import core.state.Composing;
import core.state.State;
import database.Transaction;
import transport.Packet;

public class Data extends Command {
    private Transaction transaction;

    public Data(State state, Connection connection, Transaction transaction) {
        super(state, connection);
        this.transaction = transaction;
    }

    @Override
    public State execute(String[] args) {
        connection.getSender().sendPacket(new Packet("354 Start mail input; end with <CRLF>.<CRLF>"));
        return new Composing(connection, transaction);
    }
}
