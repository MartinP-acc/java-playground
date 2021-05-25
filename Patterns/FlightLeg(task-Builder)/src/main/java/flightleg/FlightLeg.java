package flightleg;

public class FlightLeg {

    private String from;
    private String to;
    private boolean delayed;
    private int price;

    public FlightLeg(FlightLegBuilder flightLegBuilder) {
        this.from = flightLegBuilder.from;
        this.to = flightLegBuilder.to;
        this.delayed = flightLegBuilder.delayed;
        this.price = flightLegBuilder.price;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public boolean isDelayed() {
        return delayed;
    }

    public int getPrice() {
        return price;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FlightLeg{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", delayed=" + delayed +
                ", price=" + price +
                '}';
    }

    public static class FlightLegBuilder{

        private String from;
        private String to;
        private int price;
        private boolean delayed = false;

        public FlightLegBuilder buildFrom(String from){
            this.from = from;
            return this;
        }

        public FlightLegBuilder buildTo(String to){
            this.to = to;
            return this;
        }

        public FlightLegBuilder buildPrice(int price){
            this.price = price;
            return this;
        }

        public FlightLeg build(){
            if (price==0) throw new IllegalStateException("Brak wymaganego pola - cena");
            return new FlightLeg(this);
        }

    }
}
