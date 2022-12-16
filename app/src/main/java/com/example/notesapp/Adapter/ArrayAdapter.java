package com.example.notesapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.Activity.UpdateNoteActivity;
import com.example.notesapp.Database.Notes;
import com.example.notesapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArrayAdapter extends RecyclerView.Adapter<ArrayAdapter.ViewHolder> {


    Context context ;
    List<Notes> notesList;


    public ArrayAdapter(Context context,List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_layout_model,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.txttitle.setText(notesList.get(position).notes_Title);
        holder.txtsubtitle.setText(notesList.get(position).notes_Subtitle);
        holder.txtdate.setText(notesList.get(position).notes_date);

        if (Objects.equals(notesList.get(position).notes_priority, "1"))
        holder.showPriority.setBackgroundResource(R.drawable.greenshape);

       else if (Objects.equals(notesList.get(position).notes_priority, "2"))
        holder.showPriority.setBackgroundResource(R.drawable.yellowshape);

       else if (Objects.equals(notesList.get(position).notes_priority, "3"))
            holder.showPriority.setBackgroundResource(R.drawable.redshape);


       holder.itemView.setOnClickListener(v -> {
           Intent intent = new Intent(context, UpdateNoteActivity.class);

           Notes note = notesList.get(position);

           intent.putExtra("id",note.id);
           intent.putExtra("title",note.notes_Title);
           intent.putExtra("subtitle",note.notes_Subtitle);
           intent.putExtra("notes",note.notes);
           intent.putExtra("priority",note.notes_priority);

           context.startActivity(intent);

       });




    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttitle,txtsubtitle,txtdate;
        View showPriority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txttitle = itemView.findViewById(R.id.txttitle);
            txtsubtitle = itemView.findViewById(R.id.txtsubtitle);
            txtdate = itemView.findViewById(R.id.txtdate);
            showPriority = itemView.findViewById(R.id.showPriority);

        }
    }
}
