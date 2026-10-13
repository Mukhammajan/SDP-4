package subsystem;

public class StarterMotor {
    private static final int CRANKING_COST_PERCENT = 5;

    public void crank(Battery battery) {
        if (!battery.canCrankEngine()) {
            throw new IllegalStateException(
                    "Battery too weak to crank engine (" + battery.getChargePercent() + "%)");
        }
        battery.consume(CRANKING_COST_PERCENT);
        System.out.println("  [StarterMotor] Cranking engine");
    }
}