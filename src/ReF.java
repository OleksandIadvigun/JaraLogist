public class ReF extends Trailer {
    int consumptionOil;


    public ReF(String name, int maxLoading, int consumptionOil){
        super(name, maxLoading);
        this.consumptionOil = consumptionOil;

    }
    public void showInfo(){
        System.out.println("TRAILER REF: " + "name: " + getName() + "; maxLoading: " + getMaxLoading() + " kg; consumptionOil: " +
                consumptionOil + " L/100 km");
    }

    public int getConsumptionOil() {
        return consumptionOil;
    }

    public String toString(){
        return "TRAILER REF: " + "name: " + getName() + "; maxLoading: " + getMaxLoading() + " kg; consumptionOil: " +
                consumptionOil + " L/100 km";
    }
}
