package calc_price;

public class SalePrice implements PricingStrategy {

    public void calculatePrice(int price, boolean isSignedForNewsletter) {
        if (isSignedForNewsletter) System.out.println("cena za produkt to : "+(price/2)+" zł");
        else System.out.println("promocja obejmuje osoby zapisane do newslettera");
    }
}
