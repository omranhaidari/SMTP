package database;

/*import core.Utils.Mail;
import core.Utils.Mailbox;

import java.io.File;*/

public class User {
    private String address;
    //private Mailbox mailbox;

    public User(String raw) { this.address = raw.substring(1, raw.length() - 1); }

    /*public User(String raw, File mailbox) {
        this.address = raw.substring(1, raw.length() - 1);
        this.mailbox = new Mailbox(mailbox);
    }*/

    public String getAddress() { return address; }

    /*public Mailbox getMailbox() {
        return mailbox;
    }

    public boolean writeMail(Transaction transaction) {
        if(!mailbox.isLocked()) { // Si la mailbox n'est pas lock
            // Lock la mailbox
            mailbox.lock();

            // Ecrit le mail à la fin de la mailbox
            // FIXME Ne pas oublier la syntaxe !!
            Mail mail = Mail.createFromTransaction(this, transaction);
            mailbox.appendData(String.join("\r" + "\n", transaction.getData()));

            // Délocke la mailbox
            mailbox.unlock(); // FIXME Si le fichier n'est pas correctement supprimé, que faire ?

            return true;
        }
        return false;
    }*/

    @Override
    public String toString() {
        return String.format("<%s>", address);
    }
}

