package loads;

import interfaces.Info;
import trailers.AbstractTrailer;

public class Loads implements Info, Comparable<Loads>{
    private String name;
    private int cargoWeight;
    private int temperature;
    private int distance;
    private boolean taken = false;

    public Loads(String name, int cargoWeight, int temperature, int distance){
        this.name = name;
        this.cargoWeight = cargoWeight;
        this.temperature = temperature;
        this.distance = distance;
    }

    public static Loads makeLoads(String name, int cargoWeight, int temperature, int distance) {
        return new Loads(name, cargoWeight, temperature, distance);
    }

    public void take() {
        taken = true;
    }
    public boolean canTake() {
        return !taken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(int cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String toString(){
      return "CARGO: " + name + "; cargo Weight: " + cargoWeight + " kg; temperature for transport: " + temperature +
              " C; distance: " + distance + " km";
    }

    public int compareTo(Loads o) {
        return this.getCargoWeight() - o.getCargoWeight();
    }
}
