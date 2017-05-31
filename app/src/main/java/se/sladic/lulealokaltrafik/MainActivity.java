package se.sladic.lulealokaltrafik;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements FormFiller.OnTaskCompleted {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter reyclerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;

    EditText fromEdit;
    EditText toEdit;
    Button searchButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton    = (Button) findViewById(R.id.button);
        fromEdit        = (EditText) findViewById(R.id.editText);
        toEdit          = (EditText) findViewById(R.id.editText2);
        progressBar     = (ProgressBar) findViewById(R.id.progressBar);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask formFillerTask = new FormFiller(MainActivity.this, progressBar);
                View view = getCurrentFocus();
                if (view != null){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                formFillerTask.execute(fromEdit.getText().toString(),
                        toEdit.getText().toString(),
                        buildTime(),
                        buildDate());
            }
        });
    }

    private String buildDate(){
        Calendar c  = Calendar.getInstance();
        int day     = c.get(Calendar.DATE);
        int month   = c.get(Calendar.MONTH);
        int year    = c.get(Calendar.YEAR);
        String date;
        if (month < 10) {
            date = year + "-0" + month + "-" + day;
        } else date = year + "-" + month + "-" + day;
        return date;
    }

    private String buildTime(){
        Calendar c  = Calendar.getInstance();
        int hour    = c.get(Calendar.HOUR_OF_DAY);
        int minute  = c.get(Calendar.MINUTE);
        String time = hour + ":" + minute;
        return time;
    }

    @Override
    public void onTaskCompleted(Object response) {
        ArrayList<Result> results;
        results = (ArrayList<Result>) response;
        recyclerView = (RecyclerView) findViewById(R.id.resultRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        reyclerAdapter = new ResultAdapter(results);
        recyclerView.setAdapter(reyclerAdapter);
    }
}
