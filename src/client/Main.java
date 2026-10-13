package client;

import exception.CarOperationException;
import facade.CarFacade;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Scenario 1: normal trip ===");
        try {
            CarFacade car = CarFacade.createStandardCar(80, 35.0);
            car.startCar();
            car.driveOff();
            car.parkAndShutDown();
        } catch (CarOperationException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\n=== Scenario 2: empty tank ===");
        try {
            CarFacade.createStandardCar(80, 0).startCar();
        } catch (CarOperationException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\n=== Scenario 3: weak battery ===");
        try {
            CarFacade.createStandardCar(10, 30.0).startCar();
        } catch (CarOperationException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\n=== Scenario 4: driving off without starting ===");
        try {
            CarFacade.createStandardCar(80, 30.0).driveOff();
        } catch (CarOperationException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}