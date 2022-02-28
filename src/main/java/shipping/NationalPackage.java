package shipping;

public class NationalPackage implements Transportable {
    private int weight;
    private boolean isBreakable;
    private static final int SHIPPING_PRICE = 1000;

    public NationalPackage(int weight, boolean isBreakable) {
        this.weight = weight;
        this.isBreakable = isBreakable;
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
        return (isBreakable ? SHIPPING_PRICE *2 : SHIPPING_PRICE);
    }
}
