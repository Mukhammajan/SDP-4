package subsystem;

public class Engine {
    private final FuelSystem fuelSystem;
    private boolean running;

    public Engine(FuelSystem fuelSystem) {
        this.fuelSystem = fuelSystem;
    }

    public void ignite() {
        if (!fuelSystem.isPressurized()) {
            throw new IllegalStateException("Cannot ignite: fuel line is not pressurized");
        }
        running = true;
        System.out.println("  [Engine] Running");
    }

    public void shutOff() {
        running = false;
        System.out.println("  [Engine] Stopped");
    }

    public boolean isRunning() {
        return running;
    }
}