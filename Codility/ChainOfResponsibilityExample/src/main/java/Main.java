public class Main {

    public static void main(String[] args) {

        General gen = new General();

        Captain cpt = new Captain();
        cpt.setSuperiorOfficer(gen);

        Officer lut = new Leutenant();
        lut.setSuperiorOfficer(cpt);

        Message message = new Message(" test msg 1",100,Rank.GENERAL);

        lut.processMessage(message);

        Message message2 = new Message(" test msg 2",50,Rank.CAPTAIN);

        lut.processMessage(message2);

        Message message3 = new Message(" test msg 3",10,Rank.LEUTENANT);

        cpt.processMessage(message3);
    }
}
