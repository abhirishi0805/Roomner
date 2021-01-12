package com.example.roomner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class matchAdapter extends RecyclerView.Adapter<matchAdapter.ViewHolder>
{
    private ArrayList<matchHelperModel> people;
    itemClicked activity;

    public matchAdapter(Context context, ArrayList<matchHelperModel> people)
    {
        this.people = people;
        activity = (itemClicked) context;
    }

    public interface itemClicked{
        void onItemClicked(int index);
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivImage;
        TextView tvName, tvPercentage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvPercentage = itemView.findViewById(R.id.tvPercentage);

            itemView.setOnClickListener(v -> {
                activity.onItemClicked(people.indexOf(v.getTag()));
            });
        }
    }


    @NonNull
    @Override
    public matchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card_design, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull matchAdapter.ViewHolder holder, int position)
    {
        holder.itemView.setTag(people.get(position));

        holder.tvName.setText(people.get(position).getUser().getPersonal_Data().getName());
        holder.tvPercentage.setText(String.format("%d%%", people.get(position).getPercentageMatch()));
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
