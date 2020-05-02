package trailers;

import interfaces.Info;
import loads.Loads;

import java.util.List;


public abstract class AbstractTrailer implements Info, Comparable<AbstractTrailer> {

    @Override
    public String toString() {
        return "";
    }

    public void addLoads(final List<Loads> loadsArray) {
        for(Loads load : loadsArray) {
            if(canAddLoad(load)) {
                addLoad(load);
            }
        }
    }

    public abstract int getMaxLoading();

    public int compareTo(AbstractTrailer o) {
        return this.getMaxLoading() - o.getMaxLoading();
    }

    public abstract void drive();
    public abstract boolean canDrive();

    protected abstract void addLoad(final Loads load);

    protected abstract boolean canAddLoad(final Loads load);
}
