package com.uilover.project1932.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.uilover.project1932.Activity.WorkoutActivity;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.databinding.ViewholderWorktoutBinding;


import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.Viewholder> {
    private final ArrayList<Workout> list;
    private Context context;

    public WorkoutAdapter(ArrayList<Workout> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WorkoutAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderWorktoutBinding binding = ViewholderWorktoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.Viewholder holder, int position) {

        holder.binding.titleTxt.setText(list.get(position).getTitle());
        int resId = context.getResources().getIdentifier(list.get(position).getPicPath(), "drawable", context.getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(resId)
                .into(holder.binding.pic);

        holder.binding.excerciseTxt.setText(list.get(position).getLessions().size() + " Exercise");
        holder.binding.kcalTxt.setText(list.get(position).getKcal() + " Kcal");
        holder.binding.durationTxt.setText(list.get(position).getDurationAll());

        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, WorkoutActivity.class);
            intent.putExtra("object", list.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderWorktoutBinding binding;

        public Viewholder(ViewholderWorktoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
