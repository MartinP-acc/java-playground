package calc_price;

public class RegularPrice implements PricingStrategy {

    public void calculatePrice(int price, boolean isSignedForNewsletter) {
        if (!isSignedForNewsletter) System.out.println("cena za produkt to : "+price+" zł");
        else System.out.println("osoby zapisane do newslettera powinny uzyc ceny promocyjnej");
    }
}
