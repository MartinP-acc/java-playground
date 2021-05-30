import adpater.Adapter;
import continental.ContRadio;
import continental.ContinentalDevice;
import continental.ContinentalSocket;
import uk.UKDevice;
import uk.UKRadio;
import uk.UKSocket;

public class Main {

    public static void main(String[] args) {

        ContinentalDevice euRadio = new ContRadio();
        UKDevice ukRadio = new UKRadio();

        Adapter adapter = new Adapter(ukRadio,euRadio);

        UKSocket ukSocket = new UKSocket();
        ukSocket.plugIn(adapter);

        ContinentalSocket euSocket = new ContinentalSocket();
        euSocket.plugIn(adapter);


    }

}
