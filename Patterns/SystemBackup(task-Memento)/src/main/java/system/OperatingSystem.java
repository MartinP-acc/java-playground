package system;

import system.backup.OperatingSystemCaretaker;
import system.backup.OperatingSystemMemento;

import java.util.Date;

public class OperatingSystem {

    private int backupNumber=0;
    private Date backupDate;

    public void createBackup(){
        this.backupNumber++;
        this.backupDate = new Date();
        System.out.println("Utworzono backup nr :"+backupNumber+" z datą: "+backupDate);
    }

    public OperatingSystemMemento save(){
        return new OperatingSystemMemento(this.backupNumber,this.backupDate);
    }

    public void load(OperatingSystemMemento memento){
        backupNumber = memento.getBackupNumber();
        backupDate = memento.getBackupDate();
    }
}
