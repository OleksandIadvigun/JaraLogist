package trailers;

import loads.Loads;

public class HotTrailer extends Trailer {
    int electricity;

    public int getElectricity() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }

    public HotTrailer(String name, int maxLoading, int electricity) {
        super(name, maxLoading);
        this.electricity = electricity;
    }

    @Override
    protected boolean canAddLoad(final Loads load) {
        return getMaxLoading() >= load.getCargoWeight() && load.getTemperature() > 30;
    }
}