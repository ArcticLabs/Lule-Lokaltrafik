package se.sladic.lulealokaltrafik;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private ArrayList<Result> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v){
            super(v);
            mTextView = (TextView) itemView.findViewById(R.id.info_text);
        }
    }

    public ResultAdapter(ArrayList<Result> dataSet){
        this.dataSet = dataSet;
    }

    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_cards, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText("Linje: " + dataSet.get(position).line);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
