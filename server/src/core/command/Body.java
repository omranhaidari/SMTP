package core.command;

import core.Connection;
import core.state.State;
import core.state.Waiting;
import database.Transaction;
import transport.Packet;

public class Body extends Command {
    private Transaction transaction;

    public Body(State state, Connection connection, Transaction transaction) {
        super(state, connection);
        this.transaction = transaction;
    }

    @Override
    public State execute(String[] args) {
        String line = args[0];
        if (line.equals(".")) {
            connection.getSender().sendPacket(new Packet("250 OK"));
            return new Waiting(connection, new Transaction(connection, transaction.getAddress()));
        }
        transaction.addData(line);
        return state;
    }
}