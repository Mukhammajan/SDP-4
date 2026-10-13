package subsystem;

public class Transmission {
    private final Engine engine;
    private Gear gear = Gear.PARK;

    public Transmission(Engine engine) {
        this.engine = engine;
    }

    public void shiftTo(Gear target) {
        if (!engine.isRunning() && target != Gear.PARK) {
            throw new IllegalStateException("Cannot shift to " + target + ": engine is off");
        }
        gear = target;
        System.out.println("  [Transmission] Gear -> " + target);
    }

    public Gear getGear() {
        return gear;
    }
}