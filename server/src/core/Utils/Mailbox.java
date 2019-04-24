package core.Utils;

import core.Utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Mailbox {
    private File mailboxFile;
    private File lock;

    public Mailbox(File mailboxFile) {
        this.mailboxFile = mailboxFile;
        File parentDirectory = mailboxFile.getParentFile();
        // Get parent/lock/mailboxName.lock
        File lockDirectory = new File(parentDirectory, Utils.LOCK_PATH);
        if(!lockDirectory.exists()) {
            lockDirectory.mkdir();
        }
        this.lock = new File(lockDirectory, mailboxFile.getName());
    }

    public boolean lock() {
        if(isLocked()) {
            return false;
        }

        try {
            return lock.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unlock() {
        if(!isLocked()) {
            return false;
        }

        return lock.delete();
    }

    public boolean isLocked() {
        // Get parent directory
        File parentDirectory = mailboxFile.getParentFile();
        // Get parent/lock/mailboxName.lock
        File lockDirectory = new File(parentDirectory, Utils.LOCK_PATH);
        File lock = new File(lockDirectory, mailboxFile.getName());

        return lock.exists();
    }

    public void appendData(String data) {
        try {
            // FIXME Ajouter un <CRLF> au début ?
            FileWriter writer = new FileWriter(mailboxFile, true);
            writer.write(data);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
