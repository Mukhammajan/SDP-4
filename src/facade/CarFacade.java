package facade;
import exception.CarOperationException;
import subsystem.Battery;
import subsystem.Engine;
import subsystem.FuelSystem;
import subsystem.Gear;
import subsystem.StarterMotor;
import subsystem.Transmission;
public class CarFacade {
    private final Battery battery;
    private final FuelSystem fuelSystem;
    private final StarterMotor starterMotor;
    private final Engine engine;
    private final Transmission transmission;

    public CarFacade(Battery battery, FuelSystem fuelSystem, StarterMotor starterMotor,
                     Engine engine, Transmission transmission) {
        this.battery = battery;
        this.fuelSystem = fuelSystem;
        this.starterMotor = starterMotor;
        this.engine = engine;
        this.transmission = transmission;
    }
    public static CarFacade createStandardCar(int batteryPercent, double fuelLiters) {
        Battery battery = new Battery(batteryPercent);
        FuelSystem fuelSystem = new FuelSystem(fuelLiters);
        Engine engine = new Engine(fuelSystem);
        return new CarFacade(battery, fuelSystem, new StarterMotor(), engine, new Transmission(engine));
    }

    public void startCar() throws CarOperationException {
        System.out.println(">> Starting car");
        ensureReadyToStart();
        try {
            fuelSystem.primePump();
            starterMotor.crank(battery);
            engine.ignite();
        } catch (IllegalStateException e) {
            rollbackStart();
            throw new CarOperationException("Car failed to start: " + e.getMessage(), e);
        }
    }

    public void driveOff() throws CarOperationException {
        System.out.println(">> Driving off");
        if (!engine.isRunning()) {
            throw new CarOperationException("Start the car before driving off");
        }
        try {
            transmission.shiftTo(Gear.DRIVE);
        } catch (IllegalStateException e) {
            throw new CarOperationException("Cannot drive off: " + e.getMessage(), e);
        }
    }

    public void parkAndShutDown() throws CarOperationException {
        System.out.println(">> Parking and shutting down");
        try {
            transmission.shiftTo(Gear.PARK);
            engine.shutOff();
            fuelSystem.releasePressure();
        } catch (IllegalStateException e) {
            throw new CarOperationException("Shutdown failed: " + e.getMessage(), e);
        }
    }

    private void ensureReadyToStart() throws CarOperationException {
        if (engine.isRunning()) {
            throw new CarOperationException("Car is already running");
        }
        if (!fuelSystem.hasFuel()) {
            throw new CarOperationException("Cannot start: fuel tank is empty");
        }
        if (!battery.canCrankEngine()) {
            throw new CarOperationException("Cannot start: battery is too weak");
        }
    }

    private void rollbackStart() {
        fuelSystem.releasePressure();
        engine.shutOff();
    }
}