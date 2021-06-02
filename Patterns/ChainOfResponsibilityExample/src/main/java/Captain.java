public class Captain extends Officer{

    private static final int CODE = 50;
    private static final String NAME = "kapitan Blondas";

    @Override
    public void processMessage(Message message) {
        if (message.getRank().equals(Rank.CAPTAIN) && message.getCode()==CODE){
            System.out.println(NAME+" OTRZYMAŁ WIADOMOŚĆ : "+message.getContent());
        }else{
            getSuperiorOfficer().processMessage(message);
        }
    }
}
