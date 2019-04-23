package core.state;

import core.Connection;
import core.command.Body;
import core.command.Quit;
import database.Transaction;

public class Composing extends State {
    public Composing(Connection connection, Transaction transaction) {
        super(connection);
        commands.put("QUIT", new Quit(this, connection)); // FIXME Sûr de toi ?
        // Je crois que le serveur n'interprète rien dans cet état (excepté "<CRLF>.<CRLF>")

        commands.put("*", new Body(this, connection, transaction));
    }
}
