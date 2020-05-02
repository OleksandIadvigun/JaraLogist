import org.w3c.dom.ls.LSOutput;
/*
By Alex Iad
22 May 2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class main {
    static int countOilForRef = 0;
    static int countOilForTrucks = 0;
    static int AllDistance = 0;
    static int numberOfTakenCargo = 0;
    static int numberOfEmptyTrucks = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner disk = new Scanner(new File("trucks.txt"));
        Scanner disk2 = new Scanner(new File("trailers.txt"));
        Scanner disk3 = new Scanner(new File("loads.txt"));
        // Lists from File:
        ArrayList<Truck> trucks = new ArrayList<>();
        ArrayList<Trailer> trailers = new ArrayList<>();
        ArrayList<ReF> RefTrailers = new ArrayList<>();
        ArrayList<Loads> loads = new ArrayList<>();
        ArrayList<Loads> loadsForRef = new ArrayList<>();
        //  New lists after loadings:
        ArrayList<Loads> takenLoads = new ArrayList<>();
        ArrayList<Trailer> doneTrailersForLoads = new ArrayList<>();
        ArrayList<ReF> doneRefForLoads = new ArrayList<>();

        AdditionScannedTruck(disk, trucks);
        numberOfEmptyTrucks = trucks.size();
        AdditionScannedTrailers(disk2, trailers, RefTrailers);
        AdditionScannedLoads(disk3, loads, loadsForRef);
        SortingTrucksByWeights(trucks);
        SortingTrucksByWeights(trucks);  //      ?????????
        System.out.println();
        System.out.println("Sorted Trucks by weights: ");
        for (Info o : trucks) {
            o.showInfo();
        }

        SortingRefTrailersByWeights(RefTrailers);
        System.out.println();
        System.out.println("Sorted trailers Ref by weights: ");
        for (Info o : RefTrailers) {
            o.showInfo();
        }

        SortingTrailersByWeights(trailers);
        System.out.println();
        System.out.println("Sorted  normal trailers by weights: ");
        for (Info o : trailers) {
            o.showInfo();
        }

        SortingCargoByWeights(loadsForRef);
        System.out.println();
        System.out.println("Sorted loads for REF by weights: ");
        for (Info o : loadsForRef) {
            o.showInfo();
        }

        SortingCargoByWeights(loads);
        System.out.println();
        System.out.println("Sorted loads for normal trailers by weights: ");
        for (Info o : loads) {
            o.showInfo();
        }

        CheckingForRef(loadsForRef, RefTrailers, doneRefForLoads, takenLoads, trucks);
        System.out.println();
        System.out.println("Used Refs for cargo: ");
        for (Info o : doneRefForLoads) {
            o.showInfo();
        }

        CheckingForTrailers(loads, trailers, doneTrailersForLoads, takenLoads, trucks);
        System.out.println();
        System.out.println("Used Trailers for cargo: ");
        for (Info o : doneTrailersForLoads) {
            o.showInfo();
        }

        System.out.println();
        System.out.println(" The rest trailers witch was not used: ");
        for (Info o : trailers) {
            o.showInfo();
        }
        System.out.println();
        System.out.println(" The rest refs witch was not used: ");
        for (Info o : RefTrailers) {
            o.showInfo();
        }
        System.out.println();
        System.out.println(" The rest trucks witch was not used: ");
        for (Info o : trucks) {
            o.showInfo();
        }
        System.out.println();
        System.out.println(" The rest cargo witch was not loaded: ");
        for (Info o : loadsForRef) {
            o.showInfo();
        }
        for (Info o : loads) {
            o.showInfo();
        }
        System.out.println();
        System.out.println(" Taken loads: ");
        for (Info o : takenLoads) {
            o.showInfo();
        }

        int numberOfEmptyTrailers = trailers.size();
        int numberOfEmptyRefs = RefTrailers.size();
        System.out.println();
        System.out.println(" TOTAL RESULTS: ");
        System.out.println(" Number of empty REFs: " + numberOfEmptyRefs);
        System.out.println(" Number of empty trailers: " + numberOfEmptyTrailers);
        System.out.println(" Number of empty trucks: " + numberOfEmptyTrucks);
        System.out.println(" Number of taken loads: " + numberOfTakenCargo);
        System.out.println(" Consumption oil for all Refs: " + countOilForRef + " L");
        System.out.println(" Consumption oil for all trucks: " + countOilForTrucks + " L");
        int total = countOilForTrucks + countOilForRef;
        System.out.println(" Total oil consumption: " + total + " L");
        System.out.println(" All distance: " + AllDistance + " km");
    }

    public static void AdditionScannedTruck(Scanner disk, ArrayList<Truck> trucks) {
        do {
            String name = disk.nextLine();
            int weight = Integer.parseInt(disk.nextLine());
            int oilCons = Integer.parseInt(disk.nextLine());
            Truck truck = new Truck(name, weight, oilCons);
            trucks.add(truck);
        } while (disk.hasNext());

        System.out.println("List of TRUCKS:");
        for (Info o : trucks) {
            o.showInfo();
        }
    }

    public static void AdditionScannedTrailers(Scanner disk2, ArrayList<Trailer> trailers, ArrayList<ReF> RefTrailers) {
        do {
            String name = disk2.nextLine();
            String ref = "ref";
            if (compareStrings(name, ref) == 1) {
                int maxLoading = Integer.parseInt(disk2.nextLine());
                int consumptionOil = Integer.parseInt(disk2.nextLine());
                ReF trailer = new ReF(name, maxLoading, consumptionOil);
                RefTrailers.add(trailer);
            } else {
                int maxLoading = Integer.parseInt(disk2.nextLine());
                Trailer trailer = new Trailer(name, maxLoading);
                trailers.add(trailer);
            }
        } while (disk2.hasNext());
        System.out.println();
        System.out.println("List of TRAILERS: ");

        for (Info o : trailers) {
            o.showInfo();
        }
        System.out.println();
        for (Info o : RefTrailers) {
            o.showInfo();
        }
    }

    public static int compareStrings(String disk, String ref) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (disk.charAt(i) == ref.charAt(i)) {
                count++;
            }
        }
        if (count == 3) {
            return 1;
        } else return -1;
    }

    public static void AdditionScannedLoads(Scanner disk3, ArrayList<Loads> loads, ArrayList<Loads> loadsForRef) {
        do {
            String name = disk3.nextLine();
            int cargoWeight = Integer.parseInt(disk3.nextLine());
            int temperature = Integer.parseInt(disk3.nextLine());
            if (temperature < 0) {
                int distance = Integer.parseInt(disk3.nextLine());
                Loads loads1 = new Loads(name, cargoWeight, temperature, distance);
                loadsForRef.add(loads1);
            } else {
                int distance = Integer.parseInt(disk3.nextLine());
                Loads loads1 = new Loads(name, cargoWeight, temperature, distance);
                loads.add(loads1);
            }

        } while (disk3.hasNext());
        System.out.println();
        System.out.println(" Cargo for normal trailers:");
        for (Info o : loads) {
            o.showInfo();

        }
        System.out.println();
        System.out.println("Cargo for Refs:");
        for (Info o : loadsForRef) {
            o.showInfo();
        }
    }

    public static void SortingTrucksByWeights(ArrayList<Truck> trucks) {    //  wtf ????????????????????
        boolean b = false;

        while (!b) {
                b = true;
            for (int i = 0; i < trucks.size() - 1; i++) {
                if (trucks.get(i).getMaxWeight() >= trucks.get(i + 1).getMaxWeight()) {
                    Collections.swap(trucks, i, i + 1);
                    b = false;
                }
            }
        }
    }

    public static void SortingRefTrailersByWeights(ArrayList<ReF> refs) {    //  wtf ????????????????????
        boolean b = false;

        while (!b) {
            for (int i = 0; i < refs.size() - 1; i++) {
                if (refs.get(i).getMaxLoading() > refs.get(i + 1).getMaxLoading()) {

                    Collections.swap(refs, i, i + 1);
                    b = false;

                } else b = true;
            }
        }
    }

    public static void SortingTrailersByWeights(ArrayList<Trailer> refs) {
        boolean b = false;

        while (!b) {
            for (int i = 0; i < refs.size() - 1; i++) {
                if (refs.get(i).getMaxLoading() > refs.get(i + 1).getMaxLoading()) {

                    Collections.swap(refs, i, i + 1);
                    b = false;

                } else b = true;
            }
        }
    }

    /*
Sorting Cargo is from MAX to MIN weight!
 */
    public static void SortingCargoByWeights(ArrayList<Loads> loadsForRef) {
        boolean b = false;

        while (!b) {
            for (int i = 0; i < loadsForRef.size() - 1; i++) {
                if (loadsForRef.get(i).getCargoWeight() < loadsForRef.get(i + 1).getCargoWeight()) {

                    Collections.swap(loadsForRef, i, i + 1);
                    b = false;

                } else b = true;
            }
        }
    }

    public static void CheckingForRef(ArrayList<Loads> loadsForRef, ArrayList<ReF> RefForLoads, ArrayList<ReF> doneRefForLoads,
                                      ArrayList<Loads> takenLoads, ArrayList<Truck> trucks) {
        int numberForCircle = Math.min(loadsForRef.size(), RefForLoads.size());
        int i = 0;
        int sizeCircle = 0;
        for (i = 0; i < numberForCircle - sizeCircle; i++) {
            int indexTheSmallestGoodRef = theSmallestGoodRef(loadsForRef.get(i).getCargoWeight(), RefForLoads);
            // index of the first good for loading  REF by weight
            int indexTheSmallestGoodTruck = theSmallestGoodTruck(RefForLoads.get(indexTheSmallestGoodRef).getMaxLoading(), trucks);
            // index of the first good for loading  TRUCK by weight

            if (loadsForRef.get(i).getCargoWeight() <= RefForLoads.get(indexTheSmallestGoodRef).getMaxLoading()
                    && 0 < numberOfEmptyTrucks &&
                    trucks.get(indexTheSmallestGoodTruck).getMaxWeight() >= RefForLoads.get(indexTheSmallestGoodRef).getMaxLoading()) {
                /*
                counting:
                 */
                countOilForRef += loadsForRef.get(i).getDistance() * RefForLoads.get(indexTheSmallestGoodRef).getConsumptionOil() / 100;
                AllDistance += loadsForRef.get(i).getDistance();
                numberOfTakenCargo++;
                numberOfEmptyTrucks--;
                /*
                additions:
                 */
                doneRefForLoads.add(RefForLoads.get(indexTheSmallestGoodRef));
                takenLoads.add(loadsForRef.get(i));
                countOilForTrucks += trucks.get(indexTheSmallestGoodTruck).getOilConsumptionLoad()
                        * loadsForRef.get(i).getDistance() / 100;
                /*
                removes:
                 */
                trucks.remove(indexTheSmallestGoodTruck);
                RefForLoads.remove(i);
                loadsForRef.remove(i);
                /*
                After removing one object from loads we should start from position i -- and the size
                smallest than before by 1 element
                 */
                i--;
                sizeCircle++;
            }
        }
    }

    public static void CheckingForTrailers(ArrayList<Loads> loadsForTrailers, ArrayList<Trailer> TrailersForLoads, ArrayList<Trailer> doneTrailersForLoads,
                                           ArrayList<Loads> takenLoads, ArrayList<Truck> trucks) {
        int numberForCircle = Math.min(loadsForTrailers.size(), TrailersForLoads.size());
        int i = 0;
        int sizeCircle = 0;
        for (i = 0; i < numberForCircle - sizeCircle; i++) {
            int indexTheSmallestGoodTrailer = theSmallestGoodTrailer(loadsForTrailers.get(i).getCargoWeight(), TrailersForLoads);
            // index of the first good for loading  TRAILER by weight
            int indexTheSmallestGoodTruck = theSmallestGoodTruck(TrailersForLoads.get(indexTheSmallestGoodTrailer).getMaxLoading(), trucks);
            // index of the first good for loading  TRUCK by weight

            if (loadsForTrailers.get(i).getCargoWeight() < TrailersForLoads.get(indexTheSmallestGoodTrailer).getMaxLoading()
                    && 0 < numberOfEmptyTrucks &&
                    trucks.get(indexTheSmallestGoodTruck).getMaxWeight() >= TrailersForLoads.get(indexTheSmallestGoodTrailer).getMaxLoading()) {
                /*
                counting:
                 */
                AllDistance += loadsForTrailers.get(i).getDistance();
                countOilForTrucks += trucks.get(indexTheSmallestGoodTruck).getOilConsumptionLoad()
                        * loadsForTrailers.get(i).getDistance() / 100;
                numberOfTakenCargo++;
                numberOfEmptyTrucks--;
                /*
                additions
                 */
                doneTrailersForLoads.add(TrailersForLoads.get(indexTheSmallestGoodTrailer));
                takenLoads.add(loadsForTrailers.get(i));
                /*
                removes:
                 */
                trucks.remove(indexTheSmallestGoodTruck);
                TrailersForLoads.remove(indexTheSmallestGoodTrailer);
                loadsForTrailers.remove(i);
                /*
                After removing one object from loads we should start from position i -- and the size
                  smallest than before by 1 element
                 */
                i--;
                sizeCircle++;
            }
        }
    }
// Sorting from MIN to MAX for finding first smallest element witch can transport the current cargo
    public static int theSmallestGoodRef(int weight, ArrayList<ReF> refs) {
        int index = 0;
        for (int i = 0; i < refs.size(); i++)
            if (weight <= refs.get(i).getMaxLoading()) {
                index = i;
                break;
            }
        return index;
    }

    public static int theSmallestGoodTruck(int weight, ArrayList<Truck> trucks) {
        int index = 0;
        for (int i = 0; i < trucks.size(); i++)
            if (weight <= trucks.get(i).getMaxWeight()) {
                index = i;
                break;
            }
        return index;
    }

    public static int theSmallestGoodTrailer(int weight, ArrayList<Trailer> refs) {
        int indexRef = 0;
        for (int i = 0; i < refs.size(); i++)
            if (weight <= refs.get(i).getMaxLoading()) {
                indexRef = i;
                break;
            }
        return indexRef;
    }
}
