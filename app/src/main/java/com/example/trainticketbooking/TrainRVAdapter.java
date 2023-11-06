package com.example.trainticketbooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.busticketbooking.R;


import java.util.ArrayList;

public class TrainRVAdapter extends RecyclerView.Adapter<TrainRVAdapter.ViewHolder> {
    public ArrayList<TrainRVModel> TrainRVModelArrayList;
    public Context context;
    int lastPos=-1;
    public TrainClickInterface trainClickInterface;

    public TrainRVAdapter(ArrayList<TrainRVModel> TrainRVModelArrayList, Context context, TrainClickInterface trainClickInterface) {
        this.TrainRVModelArrayList = TrainRVModelArrayList;
        this.context = context;
        this.trainClickInterface = trainClickInterface;
    }

    @NonNull
    @Override
    public TrainRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.train_rv_item,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainRVAdapter.ViewHolder holder, int position) {
        TrainRVModel trainRVModel=TrainRVModelArrayList.get(position);
        holder.srcTV.setText("From: "+trainRVModel.getSrc());
        holder.destTV.setText("To: "+trainRVModel.getDest());
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainClickInterface.onTrainClick(holder.getAdapterPosition());
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if(position>lastPos){
            Animation animation= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos=position;
        }
    }

    public interface TrainClickInterface {
        void onTrainClick(int position);
    }
    @Override
    public int getItemCount() {
        return TrainRVModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView srcTV,destTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            srcTV=itemView.findViewById(R.id.idTrainSrc);
            destTV=itemView.findViewById(R.id.idTrainDest);
        }
    }

}
