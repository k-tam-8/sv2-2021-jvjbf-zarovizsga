package shipping;

public class InternationalPackage implements Transportable {

    private int weight;
    private boolean isBreakable;
    private String destinationCountry;
    private int distance;
    private static final int SHIPPING_PRICE = 1200;
    private static final int PRICE_PER_KM = 10;

    public InternationalPackage(int weight, boolean isBreakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.isBreakable = isBreakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public boolean isBreakable() {
        return this.isBreakable;
    }

    @Override
    public int calculateShippingPrice() {
        return (isBreakable ? SHIPPING_PRICE *2 : SHIPPING_PRICE) + distance * PRICE_PER_KM;
    }

}
