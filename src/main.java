import interfaces.Info;
import loads.Loads;
import trailers.ReF;
import trailers.Trailer;
/*
By Alex Iad
22 May 2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Processor proc = new Processor();

        Scanner truckFile = new Scanner(new File("trucks.txt"));
        Scanner trailerFile = new Scanner(new File("trailers.txt"));
        Scanner loadsFile = new Scanner(new File("loads.txt"));

        proc.appendTrucksFromFile(truckFile);
        proc.appendTrailersFromFile(trailerFile);
        proc.appendLoadsFromScanner(loadsFile);

        proc.fillTrailersWithLoads();
        proc.printResult();

        List<Loads> notTaken = proc.nonTakenLoads();
        System.out.println("\nList of loads not takes to delivery: ");
        for(Loads l : notTaken) {
            System.out.println(l.toString());
        }
        proc.showStatistics();

    }
}
