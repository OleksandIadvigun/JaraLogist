package trailers;

import loads.Loads;

public class ReF extends Trailer {
    int consumptionOil;

    public ReF(String name, int maxLoading, int consumptionOil){
        super(name, maxLoading);
        this.consumptionOil = consumptionOil;
    }

    public int getConsumptionOil() {
        return consumptionOil;
    }

    @Override
    protected boolean canAddLoad(final Loads load) {
        return getMaxLoading() >= load.getCargoWeight() && load.getTemperature() <= 0;
    }
}
