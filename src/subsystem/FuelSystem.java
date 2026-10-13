package subsystem;

public class FuelSystem {
    private double fuelLiters;
    private boolean pressurized;

    public FuelSystem(double fuelLiters) {
        this.fuelLiters = fuelLiters;
    }

    public boolean hasFuel() {
        return fuelLiters > 0;
    }

    public void primePump() {
        if (!hasFuel()) {
            throw new IllegalStateException("Fuel tank is empty, pump cannot build pressure");
        }
        pressurized = true;
        System.out.println("  [FuelSystem] Pump primed, fuel line pressurized");
    }

    public void releasePressure() {
        pressurized = false;
        System.out.println("  [FuelSystem] Fuel line pressure released");
    }

    public boolean isPressurized() {
        return pressurized;
    }

    public double getFuelLiters() {
        return fuelLiters;
    }
}