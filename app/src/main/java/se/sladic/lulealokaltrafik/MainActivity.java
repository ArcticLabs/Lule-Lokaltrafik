package se.sladic.lulealokaltrafik;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

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
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker      = (TimePicker) findViewById(R.id.timeSelector);
        timePicker.setIs24HourView(true);
        timePicker.setVisibility(View.GONE);
        recyclerView    = (RecyclerView) findViewById(R.id.resultRecycler);
        searchButton    = (Button) findViewById(R.id.button);
        fromEdit        = (EditText) findViewById(R.id.editText);
        toEdit          = (EditText) findViewById(R.id.editText2);
        progressBar     = (ProgressBar) findViewById(R.id.progressBar);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.GONE);
                recyclerView.setVisibility(View.INVISIBLE);
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

        toEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    switch (keyCode){
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            searchButton.performClick();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_favorite:
                View view = getCurrentFocus();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                selectTime();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void selectTime(){
        recyclerView.setVisibility(View.INVISIBLE);
        if (timePicker.getVisibility() == View.VISIBLE){
            timePicker.setVisibility(View.INVISIBLE);
            return;
        } else timePicker.setVisibility(View.VISIBLE);
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
        int hour    = timePicker.getHour();
        int minute  = timePicker.getMinute();
        String time = hour + ":" + minute;
        if (time.length() < 5){
            time = time + "0";
        }
        System.out.println("Tiden att söka på: " + time);
        return time;
    }

    @Override
    public void onTaskCompleted(Object response) {
        ArrayList<Result> results;
        results = (ArrayList<Result>) response;
        recyclerView.setHasFixedSize(true);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        reyclerAdapter = new ResultAdapter(results);
        recyclerView.setAdapter(reyclerAdapter);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
