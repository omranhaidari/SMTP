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
            send(new Packet("250 OK"));
            return new Waiting(connection, new Transaction(connection, transaction.getAddress()));
        }
        // Gestion de la transparence
        if(line.startsWith(".")) { // La ligne a djà été vérifiée, elle n'est pas égale à "."
            // Alors il faut supprimer le 1er '.'
//            line = line.substring(1); // TODO Vérifier que c'est bien implémenté sur le client
        }

        // ============ Pour les tests =============
        if(line.toLowerCase().equals("/test")) {
            // TODO On crée un mail bidon avec l'adresse de l'envoyeur = transaction.getSender
            // TODO Puis on l'envoie au client (pour l'affichage en console)
        }
        // =========================================

        transaction.addData(line);
        return state;
    }
}