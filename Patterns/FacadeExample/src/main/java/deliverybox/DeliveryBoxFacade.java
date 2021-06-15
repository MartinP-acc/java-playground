package deliverybox;

public class DeliveryBoxFacade {

    private DeliveryBox deliveryBox;
    private DeliveryBoxSystem deliveryBoxSystem;

    public DeliveryBoxFacade() {
        deliveryBox = new DeliveryBox();
        deliveryBoxSystem = new DeliveryBoxSystem();
    }

    public void pickUpPackage(){
        deliveryBox.getUserData();
        if (deliveryBoxSystem.isPaymentSecured() && deliveryBoxSystem.isUserDataValidated()){
            deliveryBox.openBox();
        }
    }
}
