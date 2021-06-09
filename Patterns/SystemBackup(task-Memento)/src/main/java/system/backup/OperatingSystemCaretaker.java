package system.backup;

import system.OperatingSystem;

public class OperatingSystemCaretaker {

    private OperatingSystemMemento memento;

    public void addMemento(OperatingSystemMemento system) {
        System.out.println("Zapisano backup nr: " + system.getBackupNumber() + " z datą: " + system.getBackupDate());
        this.memento = system;
    }

    public OperatingSystemMemento getMemento(){
        System.out.println("Wczytano backup nr: " + memento.getBackupNumber() + " z datą: " + memento.getBackupDate());
        return memento;
    }
}
