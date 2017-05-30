package se.sladic.lulealokaltrafik;

import android.content.Intent;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements FormFiller.OnTaskCompleted {

    EditText fromEdit;
    EditText toEdit;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton    = (Button) findViewById(R.id.button);
        fromEdit        = (EditText) findViewById(R.id.editText);
        toEdit          = (EditText) findViewById(R.id.editText2);

        Calendar c  = Calendar.getInstance();
        final String date = buildDate(c);
        final ArrayList<String> times = buildTime(c);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask formFillerTask = new FormFiller(MainActivity.this);
                formFillerTask.execute(fromEdit.getText().toString(),
                        toEdit.getText().toString(),
                        times.get(0),
                        date,
                        times.get(1),
                        date);

                //Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                //startActivity(intent);
            }
        });
    }

    private String buildDate(Calendar c){
        int day     = c.get(Calendar.DATE);
        int month   = c.get(Calendar.MONTH);
        int year    = c.get(Calendar.YEAR);
        String date;
        if (month < 10) {
            date = year + "-0" + month + "-" + day;
        } else date = year + "-" + month + "-" + day;
        System.out.println("Date: " + date);
        return date;
    }

    private ArrayList<String> buildTime(Calendar c){
        int hour    = c.get(Calendar.HOUR_OF_DAY);
        int minute  = c.get(Calendar.MINUTE);
        String time = hour + ":" + minute;
        String time2 = hour+2 + ":" + minute;
        ArrayList<String> times = new ArrayList<>();
        times.add(0, time);
        times.add(1, time2);
        System.out.println("Time1: " + time + "\nTime2: " + time2);
        return times;
    }

    @Override
    public void onTaskCompleted(Object response) {
        //System.out.println("HÄR KOMMER DET FRÅN UI-TRÅDEN!\n");
        //((ArrayList<Result>) response).get(0).print();
        fromEdit.setVisibility(View.INVISIBLE);
        toEdit.setVisibility(View.INVISIBLE);
        searchButton.setVisibility(View.INVISIBLE);
    }
}
