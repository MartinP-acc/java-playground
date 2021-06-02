public class Leutenant extends Officer{

    private static final int CODE = 10;
    private static final String NAME = "porucznik Kiełek";

    @Override
    public void processMessage(Message message) {
        if (message.getRank().equals(Rank.LEUTENANT) && message.getCode()==CODE){
            System.out.println(NAME+" OTRZYMAŁ WIADOMOŚĆ : "+message.getContent());
        }else{
            getSuperiorOfficer().processMessage(message);
        }
    }
}
