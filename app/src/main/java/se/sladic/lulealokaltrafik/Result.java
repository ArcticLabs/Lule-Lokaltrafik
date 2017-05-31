package se.sladic.lulealokaltrafik;

public class Result {

    String departureTime, arrivalTime, travelTime, hops, line, from, to, altbgLine, altbgStation, altbgTime;

    public void print(){
        System.out.println("Departure time: " + departureTime);
        System.out.println("Arrival time:   " + arrivalTime);
        System.out.println("Travel time:    " + travelTime);
        System.out.println("Changes:        " + hops);
        System.out.println("Line:           " + line);
        System.out.println("From:           " + from);
        System.out.println("To:             " + to);
        System.out.println("Via:            " + altbgLine);
        System.out.println("At:             " + altbgStation);
        System.out.println("Change time:    " + altbgTime);
    }
}
