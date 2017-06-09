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
        public TextView from, line1from, line1to, line2from, line2to, line3from, line3to;
        public ImageView line1, line2, line3;
        public ViewHolder(View v){
            super(v);
            line1   = (ImageView) v.findViewById(R.id.imageView1);
            line2   = (ImageView) v.findViewById(R.id.imageView2);
            line3   = (ImageView) v.findViewById(R.id.imageView3);
            line1from = (TextView) v.findViewById(R.id.textView1);
            line1to = (TextView) v.findViewById(R.id.textView1under);
            line2from = (TextView) v.findViewById(R.id.textView2);
            line2to = (TextView) v.findViewById(R.id.textView2under);
            line3from = (TextView) v.findViewById(R.id.textView3);
            line3to = (TextView) v.findViewById(R.id.textView3under);
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
        if (dataSet.get(position).line3 != null){
            setLinePicturesVisibility(3, holder);
            holder.line1.setBackgroundResource(setLinePicture(position, "line1"));
            holder.line2.setBackgroundResource(setLinePicture(position, "line2"));
            holder.line3.setBackgroundResource(setLinePicture(position, "line3"));
            holder.line1from.setText(dataSet.get(position).avg1 + "     " + dataSet.get(position).from1);
            holder.line1to.setText(dataSet.get(position).ank1 +   "     " + dataSet.get(position).via1);
            holder.line2from.setText(dataSet.get(position).avg2 + "     " + dataSet.get(position).from2);
            holder.line2to.setText(dataSet.get(position).ank2 +   "     " + dataSet.get(position).via2);
            holder.line3from.setText(dataSet.get(position).avg3 + "     " + dataSet.get(position).from3);
            holder.line3to.setText(dataSet.get(position).ank3 +   "     " + dataSet.get(position).via3);
        } else if (dataSet.get(position).line2 != null){
            setLinePicturesVisibility(2, holder);
            holder.line1.setBackgroundResource(setLinePicture(position, "line1"));
            holder.line2.setBackgroundResource(setLinePicture(position, "line2"));
            holder.line1from.setText(dataSet.get(position).avg1 + "     " + dataSet.get(position).from1);
            holder.line1to.setText(dataSet.get(position).ank1 +   "     " + dataSet.get(position).via1);
            holder.line2from.setText(dataSet.get(position).avg2 + "     " + dataSet.get(position).from2);
            holder.line2to.setText(dataSet.get(position).ank2 +   "     " + dataSet.get(position).via2);
        } else {
            setLinePicturesVisibility(1, holder);
            holder.line1.setBackgroundResource(setLinePicture(position, "line1"));
            holder.line1from.setText(dataSet.get(position).avg1 + "     " + dataSet.get(position).from1);
            holder.line1to.setText(dataSet.get(position).ank1 +   "     " + dataSet.get(position).via1);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private void setLinePicturesVisibility(int lines, ViewHolder holder){
        switch (lines){
            case 1:
                holder.line1.setVisibility(View.VISIBLE);
                holder.line2.setVisibility(View.GONE);
                holder.line3.setVisibility(View.GONE);
                holder.line2from.setVisibility(View.GONE);
                holder.line2to.setVisibility(View.GONE);
                holder.line3from.setVisibility(View.GONE);
                holder.line3to.setVisibility(View.GONE);
                break;
            case 2:
                holder.line1.setVisibility(View.VISIBLE);
                holder.line2.setVisibility(View.VISIBLE);
                holder.line3.setVisibility(View.GONE);
                holder.line3from.setVisibility(View.GONE);
                holder.line3to.setVisibility(View.GONE);
                break;
            case 3:
                holder.line1.setVisibility(View.VISIBLE);
                holder.line2.setVisibility(View.VISIBLE);
                holder.line3.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private int setLinePicture(int position, String line){
        switch (line){
            case "line1":
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
            case "line2":
                if (Objects.equals(dataSet.get(position).line2, "1")){
                    return R.drawable.line_one;
                }
                else if (Objects.equals(dataSet.get(position).line2, "2")){
                    return R.drawable.line_two;
                }
                else if (Objects.equals(dataSet.get(position).line2, "3")){
                    return R.drawable.line_three;
                }
                else if (Objects.equals(dataSet.get(position).line2, "4")){
                    return R.drawable.line_four;
                }
                else if (Objects.equals(dataSet.get(position).line2, "5")){
                    return R.drawable.line_five;
                }
                else if (Objects.equals(dataSet.get(position).line2, "6")){
                    return R.drawable.line_six;
                }
                else if (Objects.equals(dataSet.get(position).line2, "7")){
                    return R.drawable.line_seven;
                }
                else if (Objects.equals(dataSet.get(position).line2, "8")){
                    return R.drawable.line_eight;
                }
                else if (Objects.equals(dataSet.get(position).line2, "9")){
                    return R.drawable.line_nine;
                }
                else if (Objects.equals(dataSet.get(position).line2, "10")){
                    return R.drawable.line_ten;
                }
                else if (Objects.equals(dataSet.get(position).line2, "14")){
                    return R.drawable.line_fourteen;
                } else return R.drawable.line_error;
            case "line3":
                if (Objects.equals(dataSet.get(position).line3, "1")){
                    return R.drawable.line_one;
                }
                else if (Objects.equals(dataSet.get(position).line3, "2")){
                    return R.drawable.line_two;
                }
                else if (Objects.equals(dataSet.get(position).line3, "3")){
                    return R.drawable.line_three;
                }
                else if (Objects.equals(dataSet.get(position).line3, "4")){
                    return R.drawable.line_four;
                }
                else if (Objects.equals(dataSet.get(position).line3, "5")){
                    return R.drawable.line_five;
                }
                else if (Objects.equals(dataSet.get(position).line3, "6")){
                    return R.drawable.line_six;
                }
                else if (Objects.equals(dataSet.get(position).line3, "7")){
                    return R.drawable.line_seven;
                }
                else if (Objects.equals(dataSet.get(position).line3, "8")){
                    return R.drawable.line_eight;
                }
                else if (Objects.equals(dataSet.get(position).line3, "9")){
                    return R.drawable.line_nine;
                }
                else if (Objects.equals(dataSet.get(position).line3, "10")){
                    return R.drawable.line_ten;
                }
                else if (Objects.equals(dataSet.get(position).line3, "14")){
                    return R.drawable.line_fourteen;
                } else return R.drawable.line_error;
            default:
                break;
        }
        return R.drawable.line_error;
    }

    //private void setChanges(ViewHolder holder, int position){
    //    if ((dataSet.get(position).hops).equals("0")){
    //        holder.byten.setVisibility(View.INVISIBLE);
    //    } else holder.byten.setText("Ett byte på " + dataSet.get(position).altbgStation + " kl " + dataSet.get(position).altbgTime);
    //}
}
