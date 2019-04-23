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
            transaction.finish();
            connection.getSender().sendPacket(new Packet("250 OK"));
            return new Waiting(connection, new Transaction(connection, transaction.getAddress()));
        }
        if(line.startsWith(".")) { // La ligne a djà été vérifiée, elle n'est pas égale à "."
            // Alors il faut supprimer le 1er '.'
//            line = line.substring(1); // TODO Vérifier que c'est bien implémenté sur le client
        }
        transaction.addData(line);
        return state;
    }
}