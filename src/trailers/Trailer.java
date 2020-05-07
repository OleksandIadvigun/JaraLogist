package trailers;


import loads.Loads;

import java.util.ArrayList;
import java.util.List;

public class Trailer extends AbstractTrailer {
    public static int AllDistance = 0;
    static int refOliCons = 0;
    static int takenRealCargo;

    private String name;
    private int maxLoading;

    private List<Loads> possibleLoads = new ArrayList<>();
    Loads takenLoad = null;

    public Trailer(String name, int maxLoading) {
        this.name = name;
        this.maxLoading = maxLoading;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxLoading() {
        return maxLoading;
    }

    @Override
    public void drive() {
        if (canDrive()) {
            int idx = findFirstReadyLoad();
            possibleLoads.get(idx).take();
            takenLoad = possibleLoads.get(idx);
            AllDistance += takenLoad.getDistance();
        }
    }

    @Override
    public boolean canDrive() {
        return findFirstReadyLoad() != -1;
    }

    int findFirstReadyLoad() {
        for (int i = 0; i < possibleLoads.size(); ++i) {
            if (possibleLoads.get(i).canTake()) {
                takenRealCargo = i;
                return i;
            }
        }
        return -1;
    }

    public void setMaxLoading(int maxLoading) {
        this.maxLoading = maxLoading;
    }

    public String toString() {
        String loadInfo = "";
        if (takenLoad != null) {
            loadInfo = takenLoad.toString();
        }
        return "TRAILER: " + "name: " + name + "; maxLoading: " + maxLoading + " kg" + " LOADED: " + loadInfo;
    }

    @Override
    protected void addLoad(final Loads load) {
        possibleLoads.add(load);
    }

    @Override
    protected boolean canAddLoad(final Loads load) {
        return getMaxLoading() >= load.getCargoWeight() && load.getTemperature() > 0;
    }

    public static void showStatistics2() {
        System.out.println(" All distance: " + AllDistance + " km, Oil Consumption by Refs: " + refOliCons + " L");
    }

    public int getDistanceFromTakenLoad() {

        return possibleLoads.get(takenRealCargo).getDistance();
    }

    public boolean IsItRef() {
        return false;
    }
}
