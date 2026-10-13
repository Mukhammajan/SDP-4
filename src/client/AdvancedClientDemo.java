package client;
import subsystem.Battery;
import subsystem.Engine;
import subsystem.FuelSystem;
import subsystem.StarterMotor;
public class AdvancedClientDemo {
    public static void main(String[] args) {
        FuelSystem fuel = new FuelSystem(5);
        Engine engine = new Engine(fuel);
        fuel.primePump();
        new StarterMotor().crank(new Battery(50));
        engine.ignite();
    }
}