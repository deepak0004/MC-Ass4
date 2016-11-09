package com.todo.deepak.taskmanager;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Deepak on 09-Nov-16.
 */

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.RecylerHolderClass> {

    ArrayList<String> note_title, note_content;

    public RecylerAdapter(ArrayList<String> note_title, ArrayList<String> note_content) {
        this.note_title = note_title;
        this.note_content = note_content;
    }

    @Override
    public RecylerHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_data_row, parent, false);
        return new RecylerHolderClass(view);
    }

    @Override
    public void onBindViewHolder(RecylerHolderClass holder, final int position) {

        holder.tv1.setText(note_title.get(position));
        holder.tv2.setText(note_content.get(position));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ViewPagerClass.class);
                i.putExtra("item_position", position);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return note_title.size();
    }

    public static class RecylerHolderClass extends RecyclerView.ViewHolder{
        TextView tv1, tv2;
        public View view;
        public RecylerHolderClass(final View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.r_title);
            tv2 = (TextView) itemView.findViewById(R.id.r_content);
            view = itemView;
        }
    }

}
