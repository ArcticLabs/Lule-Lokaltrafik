package se.sladic.lulealokaltrafik;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class FormFiller {

    org.jsoup.nodes.Document doc;
    String urlQuery     = "http://193.45.213.123/lulea/v2/querypage_adv.aspx";
    String urlResult    = "http://193.45.213.123/lulea/v2/resultspage.aspx";

    public ArrayList<Result> search(String from, String to, String time, String date, String time2, String date2){
        try {
            doc = Jsoup.connect(urlQuery)
                    .data("inpPointFr", from)
                    .data("inpPointTo", to)
                    .data("inpTime", time)
                    .data("inpDate", date)
                    .data("inpTime2", time2)
                    .data("inpDate2", date2)
                    .data("cmdAction", "search")
                    .data("VerNo", "7.1.1.5.170.5685")
                    .data("Source", "querypage_adv")
                    .post();

            doc = Jsoup.connect(urlResult)
                    .data("selPointFr", doc.select("select[name=selPointFr]").select("select > option").first().val())
                    .data("selPointTo", doc.select("select[name=selPointTo]").select("select > option").first().val())
                    .data("inpTime", time)
                    .data("inpDate", date)
                    .data("inpTime2", time2)
                    .data("inpDate2", date2)
                    .data("cmdAction", "search")
                    .data("VerNo", "7.1.1.5.170.5685")
                    .data("Source", "querypage_adv")
                    .post();

        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Result> resultsArray = new ArrayList<>();
        Elements results = doc.select("tr#result-0");
        for(int i = 0; i < 4; i++) {
            Result bus = new Result();
            bus.departureTime   = results.select("tr > td:nth-of-type(2)").text();
            bus.arrivalTime     = results.select("tr > td:nth-of-type(3)").text();
            bus.travelTime      = results.select("tr > td:nth-of-type(4)").text();
            bus.hops            = results.select("tr > td:nth-of-type(5)").text();

            results = results.next();
            bus.line            = results.select("tr > td:nth-of-type(1) > div:nth-of-type(1) > table > tbody > tr:nth-of-type(2) > td:nth-of-type(2) > a").first().text();
            bus.print();

            resultsArray.add(bus);
            results = results.next();
        }
        return resultsArray;
    }
}
