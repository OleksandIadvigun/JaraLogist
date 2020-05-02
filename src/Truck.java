import interfaces.Info;
import trailers.AbstractTrailer;
import trailers.Trailer;

public class Truck implements Info {
   private String name;
   private int maxWeight;
   private int oilConsumptionLoad;

   public Truck(String name, int maxWeight, int oilConsumptionLoad) {
       this.name = name;
       this.maxWeight = maxWeight;
       this.oilConsumptionLoad = oilConsumptionLoad;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getOilConsumptionLoad() {
        return oilConsumptionLoad;
    }

    public void setOilConsumptionLoad(int oilConsumptionLoad) {
        this.oilConsumptionLoad = oilConsumptionLoad;
    }

    public void showInfo(){
        System.out.println("TRUCK: " + "name: " + name + "; maxWeight: " + maxWeight + " kg; oilConsumptionLoad: " + oilConsumptionLoad + " L/100 km" );
    }

    public String toString(){
       return "TRUCK: " + "name: " + name + "; maxWeight: " + maxWeight + " kg; oilConsumptionLoad: " + oilConsumptionLoad + " L/100 km";
    }

    public int compareTo(Truck o) {
        return this.getMaxWeight() - o.getMaxWeight();
    }
}
