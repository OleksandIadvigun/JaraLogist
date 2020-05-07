package trailers;

import loads.Loads;

import java.util.ArrayList;
import java.util.List;

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
    public boolean IsItRef() {
        return true;
    }
 }
