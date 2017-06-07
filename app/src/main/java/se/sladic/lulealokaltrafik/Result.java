package se.sladic.lulealokaltrafik;

public class Result {

    String line1, from1, via1, positionFrom1, departureTime1,
            line2, from2, via2, positionFrom2, departureTime2,
            line3, from3, via3, positionFrom3, departureTime3;

    public void print(){
        System.out.println("Line1:      " + line1);
        System.out.println("From1:      " + from1);
        System.out.println("Via1:       " + via1);
        System.out.println("PosFrom:    " + positionFrom1);
        System.out.println("DepTime1:   " + departureTime1);

        System.out.println("Line2:      " + line2);
        System.out.println("From2:      " + from2);
        System.out.println("Via2:       " + via2);
        System.out.println("PosFrom2:   " + positionFrom2);
        System.out.println("DepTime2:   " + departureTime2);

        System.out.println("Line3:      " + line3);
        System.out.println("From3:      " + from3);
        System.out.println("Via3:       " + via3);
        System.out.println("PosFrom3:   " + positionFrom3);
        System.out.println("DepTime3:   " + departureTime3);
    }
}
