public class General extends Officer{

    private static final int CODE = 100;
    private static final String NAME = "generał Kowalski";

    @Override
    public void processMessage(Message message) {
        if (message.getRank().equals(Rank.GENERAL) && message.getCode()==CODE){
            System.out.println(NAME+" OTRZYMAŁ WIADOMOŚĆ : "+message.getContent());
        }else{
            System.out.println("brak adresata wiadomości");
        }
    }
    
}
