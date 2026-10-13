package subsystem;

public class Battery {
    private static final int MIN_CRANKING_CHARGE = 20;
    private int chargePercent;

    public Battery(int chargePercent) {
        this.chargePercent = chargePercent;
    }

    public boolean canCrankEngine() {
        return chargePercent >= MIN_CRANKING_CHARGE;
    }

    public void consume(int percent) {
        chargePercent = Math.max(0, chargePercent - percent);
    }

    public int getChargePercent() {
        return chargePercent;
    }
}