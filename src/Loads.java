public class Loads implements Info{
    private String name;
    private int cargoWeight;
    private int temperature;
    private int distance;

    public Loads(String name, int cargoWeight, int temperature, int distance){
        this.name = name;
        this.cargoWeight = cargoWeight;
        this.temperature = temperature;
        this.distance = distance;
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

    @Override
    public void showInfo() {
        System.out.println("CARGO: " + name + "; cargo Weight: " + cargoWeight + " kg; temperature for transport: " + temperature +
                " C; distance: " + distance + " km");
    }

    public String toString(){
      return "CARGO: " + name + "; cargo Weight: " + cargoWeight + " kg; temperature for transport: " + temperature +
              " C; distance: " + distance + " km";
    }
}
