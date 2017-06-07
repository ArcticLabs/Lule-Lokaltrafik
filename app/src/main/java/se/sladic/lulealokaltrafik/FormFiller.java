package se.sladic.lulealokaltrafik;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class FormFiller extends AsyncTask {

    org.jsoup.nodes.Document doc;
    String urlQuery     = "http://193.45.213.123/lulea/v2/querypage_adv.aspx";
    String urlResult    = "http://193.45.213.123/lulea/v2/resultspage.aspx";

    private OnTaskCompleted taskCompleted;
    ProgressBar progressBar;

    public FormFiller(OnTaskCompleted taskCompleted, ProgressBar pBar){
        this.taskCompleted = taskCompleted;
        progressBar = pBar;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        // Send input data to server
        try {
            doc = Jsoup.connect(urlQuery)
                    .data("inpPointFr", params[0].toString())
                    .data("inpPointTo", params[1].toString())
                    .data("inpTime", params[2].toString())
                    .data("inpDate", params[3].toString())
                    .data("inpTime2", "")
                    .data("inpDate2", "")
                    .data("cmdAction", "search")
                    .data("VerNo", "7.1.1.5.170.5685")
                    .data("Source", "querypage_adv")
                    .post();

            doc = Jsoup.connect(urlResult)
                    .data("selPointFr", doc.select("select[name=selPointFr]").select("select > option").first().val())
                    .data("selPointTo", doc.select("select[name=selPointTo]").select("select > option").first().val())
                    .data("inpTime", params[2].toString())
                    .data("inpDate", params[3].toString())
                    .data("inpTime2", "")
                    .data("inpDate2", "")
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

            // Jump to details of 1:st line
            results = results.select("tr").next();
            bus.line1           = results.select("tr > td > div > table > tbody > tr:nth-of-type(2) > td:nth-of-type(2) > a").first().text();
            bus.from1            = results.select("tr > td > div > table > tbody > tr:nth-of-type(2) > td:nth-of-type(3) > a:nth-of-type(1)").first().text();
            bus.via1             = results.select("tr > td > div > table > tbody > tr:nth-of-type(2) > td:nth-of-type(3) > a:nth-of-type(2)").first().text();
            bus.positionFrom1    = results.select("tr > td > div > table > tbody > tr:nth-of-type(2) > td:nth-of-type(4) > strong").first().text();
            bus.departureTime1   = results.select("tr > td > div > table > tbody > tr:nth-of-type(2) > td:nth-of-type(5)").first().text();

            // Jump to 2:nd line if available
            if (results.select("tr > td > div > table > tbody > tr:nth-of-type(3)").first() != null){
                bus.line2            = results.select("tr > td > div > table > tbody > tr:nth-of-type(3) > td:nth-of-type(2) > a").first().text();
                bus.from2            = results.select("tr > td > div > table > tbody > tr:nth-of-type(3) > td:nth-of-type(3) > a:nth-of-type(1)").first().text();
                bus.via2             = results.select("tr > td > div > table > tbody > tr:nth-of-type(3) > td:nth-of-type(3) > a:nth-of-type(2)").first().text();
                bus.positionFrom2    = results.select("tr > td > div > table > tbody > tr:nth-of-type(3) > td:nth-of-type(4)").first().text();
                bus.departureTime2   = results.select("tr > td > div > table > tbody > tr:nth-of-type(3) > td:nth-of-type(5)").first().text();
            }

            // Jump to 3:nd line if available
            if (results.select("tr > td > div > table > tbody > tr:nth-of-type(4)").first() != null){
                bus.line3            = results.select("tr > td > div > table > tbody > tr:nth-of-type(3) > td:nth-of-type(2) > a").first().text();
                bus.from3            = results.select("tr > td > div > table > tbody > tr:nth-of-type(3) > td:nth-of-type(3) > a:nth-of-type(1)").first().text();
                bus.via3             = results.select("tr > td > div > table > tbody > tr:nth-of-type(3) > td:nth-of-type(3) > a:nth-of-type(2)").first().text();
                bus.positionFrom3    = results.select("tr > td > div > table > tbody > tr:nth-of-type(3) > td:nth-of-type(4)").first().text();
                bus.departureTime3   = results.select("tr > td > div > table > tbody > tr:nth-of-type(3) > td:nth-of-type(5)").first().text();
            }
            bus.print();
            resultsArray.add(bus);

            //Check if new day
            if (results.next().hasClass("dark-grey")){
                results = results.next();
                results = results.next();
            } else results = results.next();
        }

        return resultsArray;
    }

    @Override
    protected void onPreExecute(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Object o) {
        progressBar.setVisibility(View.INVISIBLE);
        taskCompleted.onTaskCompleted(o);
        //super.onPostExecute(o);
    }

    public interface OnTaskCompleted {
        void onTaskCompleted(Object response);
    }
}
