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
        holder.from.setText("Avg:        " + dataSet.get(position).departureTime1 + "  -  " + dataSet.get(position).from1);
        holder.to.setText("Des:        " + dataSet.get(position).via2 + "  -  " + dataSet.get(position).departureTime2);
        //holder.time.setText("Restid:    " + dataSet.get(position).travelTime);
        //setChanges(holder, position);
        holder.line.setBackgroundResource(setLinePicture(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private int setLinePicture(int position){
        if (Objects.equals(dataSet.get(position).line1, "1")){
            return R.drawable.line_one;
        }
        else if (Objects.equals(dataSet.get(position).line1, "2")){
            return R.drawable.line_two;
        }
        else if (Objects.equals(dataSet.get(position).line1, "3")){
            return R.drawable.line_three;
        }
        else if (Objects.equals(dataSet.get(position).line1, "4")){
            return R.drawable.line_four;
        }
        else if (Objects.equals(dataSet.get(position).line1, "5")){
            return R.drawable.line_five;
        }
        else if (Objects.equals(dataSet.get(position).line1, "6")){
            return R.drawable.line_six;
        }
        else if (Objects.equals(dataSet.get(position).line1, "7")){
            return R.drawable.line_seven;
        }
        else if (Objects.equals(dataSet.get(position).line1, "8")){
            return R.drawable.line_eight;
        }
        else if (Objects.equals(dataSet.get(position).line1, "9")){
            return R.drawable.line_nine;
        }
        else if (Objects.equals(dataSet.get(position).line1, "10")){
            return R.drawable.line_ten;
        }
        else if (Objects.equals(dataSet.get(position).line1, "14")){
            return R.drawable.line_fourteen;
        } else return R.drawable.line_error;
    }

    //private void setChanges(ViewHolder holder, int position){
    //    if ((dataSet.get(position).hops).equals("0")){
    //        holder.byten.setVisibility(View.INVISIBLE);
    //    } else holder.byten.setText("Ett byte på " + dataSet.get(position).altbgStation + " kl " + dataSet.get(position).altbgTime);
    //}
}
