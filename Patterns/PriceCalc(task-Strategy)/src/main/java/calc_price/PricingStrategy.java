package calc_price;

public interface PricingStrategy {

    void calculatePrice(int price, boolean isSignedForNewsletter);
}
