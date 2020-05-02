import interfaces.Info;
import loads.Loads;
import trailers.AbstractTrailer;
import trailers.ReF;
import trailers.Trailer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Processor {
    List<Loads> allLoads = new ArrayList<>();
    List<AbstractTrailer> trailers = new ArrayList<>();
    List<Truck> trucks = new ArrayList<>();

    List<Connection> result = new ArrayList<>();

    private static class Connection {
        Truck truck;
        AbstractTrailer loadedTrailer;

        public Connection(AbstractTrailer trailer, Truck truck) {
            this.truck = truck;
            this.loadedTrailer = trailer;

        }

        public String toString() {
            return truck.toString() + "  <====> " + loadedTrailer.toString();
        }
    }

    public void fillTrailersWithLoads() {
        sortLoadsByWeight();

        for(AbstractTrailer trailer : trailers) {
            trailer.addLoads(allLoads);
        }
        sortTrailersByWeight();
        sortTrucksByWeight();

        for(AbstractTrailer trailer : trailers) {
            int bestMatch = findMatchingTruck(trailer, trucks);
            if (bestMatch != -1 && trailer.canDrive()) {
                result.add(new Connection(trailer, trucks.get(bestMatch)));
                trailer.drive();
                trucks.remove(bestMatch);
            }
        }
    }

    public void printResult() {
        for(Connection con : result) {
            System.out.println(con.toString());
        }
    }

    private void sortLoadsByWeight() {
        Collections.sort(allLoads, Collections.reverseOrder());
    }

    private void sortTrucksByWeight() {
        trucks.sort(Truck::compareTo);
    }

    private void sortTrailersByWeight() {
        Collections.sort(trailers, Collections.reverseOrder());
    }

    public List<Loads> nonTakenLoads() {
        List<Loads> result = new ArrayList<>();
        for(Loads load : allLoads) {
            if (load.canTake()) {
                result.add(load);
            }
        }
        return result;
    }

    int findMatchingTruck(final AbstractTrailer trailer, final List<Truck> trucks) {
        for(int i = 0; i < trucks.size(); ++i) {
            if (trailer.getMaxLoading() <= trucks.get(i).getMaxWeight()) {
                return i;
            }
        }
        return -1;
    }

    public void appendTrucksFromFile(Scanner truckFile) {
        do {
            String name = truckFile.nextLine();
            int weight = Integer.parseInt(truckFile.nextLine());
            int oilCons = Integer.parseInt(truckFile.nextLine());
            Truck truck = new Truck(name, weight, oilCons);
            trucks.add(truck);
        } while (truckFile.hasNext());
    }

    public void appendTrailersFromFile(Scanner trailerFile) {
        do {
            String name = trailerFile.nextLine();
            String ref = "ref";
            Trailer trailer = null;
            int maxLoading = Integer.parseInt(trailerFile.nextLine());
            if (compareStrings(name, ref) == 1) {
                int consumptionOil = Integer.parseInt(trailerFile.nextLine());
                trailer = new ReF(name, maxLoading, consumptionOil);
            } else {
                trailer = new Trailer(name, maxLoading);
            }
            trailers.add(trailer);
        } while (trailerFile.hasNext());
    }

    public static int compareStrings(final String disk, final String ref) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (disk.charAt(i) == ref.charAt(i)) {
                count++;
            }
        }
        if (count == 3) {
            return 1;
        } else {
            return -1;
        }
    }

    public void appendLoadsFromScanner(Scanner loadsFile) {
        do {
            String name = loadsFile.nextLine();
            int cargoWeight = Integer.parseInt(loadsFile.nextLine());
            int temperature = Integer.parseInt(loadsFile.nextLine());
            int distance = Integer.parseInt(loadsFile.nextLine());
            Loads loads = Loads.makeLoads(name, cargoWeight, temperature, distance);
            allLoads.add(loads);

        } while (loadsFile.hasNext());
    }
}
