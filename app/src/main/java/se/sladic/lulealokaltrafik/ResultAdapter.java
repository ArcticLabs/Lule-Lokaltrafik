package se.sladic.lulealokaltrafik;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private ArrayList<Result> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView from, to, time, byten;
        public ImageView line;
        public ViewHolder(View v){
            super(v);
            from    = (TextView) v.findViewById(R.id.from_text);
            to      = (TextView) v.findViewById(R.id.to_text);
            time    = (TextView) v.findViewById(R.id.time_text);
            byten   = (TextView) v.findViewById(R.id.changes_text);
            line    = (ImageView) v.findViewById(R.id.imageView);
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
        holder.from.setText("Avg:        " + dataSet.get(position).departureTime + "  -  " + dataSet.get(position).from);
        holder.to.setText("Des:        " + dataSet.get(position).arrivalTime + "  -  " + dataSet.get(position).to);
        holder.time.setText("Restid:    " + dataSet.get(position).travelTime);
        setChanges(holder, position);
        holder.line.setBackgroundResource(setLinePicture(holder, position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private int setLinePicture(ViewHolder holder, int position){
        if (Objects.equals(dataSet.get(position).line, "1")){
            return R.drawable.line_one;
        }
        else if (Objects.equals(dataSet.get(position).line, "2")){
            return R.drawable.line_two;
        }
        else if (Objects.equals(dataSet.get(position).line, "3")){
            return R.drawable.line_three;
        }
        else if (Objects.equals(dataSet.get(position).line, "4")){
            return R.drawable.line_four;
        }
        else if (Objects.equals(dataSet.get(position).line, "5")){
            return R.drawable.line_five;
        }
        else if (Objects.equals(dataSet.get(position).line, "6")){
            return R.drawable.line_six;
        }
        else if (Objects.equals(dataSet.get(position).line, "7")){
            return R.drawable.line_seven;
        }
        else if (Objects.equals(dataSet.get(position).line, "8")){
            return R.drawable.line_eight;
        }
        else if (Objects.equals(dataSet.get(position).line, "9")){
            return R.drawable.line_nine;
        }
        else if (Objects.equals(dataSet.get(position).line, "10")){
            return R.drawable.line_ten;
        }
        else if (Objects.equals(dataSet.get(position).line, "14")){
            return R.drawable.line_fourteen;
        } else return R.drawable.line_error;
    }

    private void setChanges(ViewHolder holder, int position){
        if ((dataSet.get(position).hops) == "0"){
            holder.byten.setVisibility(View.INVISIBLE);
        } else holder.byten.setText("Ett byte på " + dataSet.get(position).altbgStation + " kl " + dataSet.get(position).altbgTime);
    }
}
