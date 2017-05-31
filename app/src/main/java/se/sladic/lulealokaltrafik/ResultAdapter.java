package se.sladic.lulealokaltrafik;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private ArrayList<Result> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView from, to, time, byten, line;
        public ViewHolder(View v){
            super(v);
            from    = (TextView) v.findViewById(R.id.from_text);
            to      = (TextView) v.findViewById(R.id.to_text);
            time    = (TextView) v.findViewById(R.id.time_text);
            byten   = (TextView) v.findViewById(R.id.changes_text);
            line    = (TextView) v.findViewById(R.id.line_text);
        }
    }

    public ResultAdapter(ArrayList<Result> dataSet){
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_cards, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.from.setText("Avg: " + dataSet.get(position).departureTime);
        holder.to.setText("Des: " + dataSet.get(position).arrivalTime);
        holder.time.setText("Rese tid : " + dataSet.get(position).travelTime);
        holder.byten.setText("Byten: " + dataSet.get(position).hops);
        holder.line.setText("Linje: " + dataSet.get(position).line);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
