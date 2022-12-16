package com.example.notesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.example.notesapp.Database.Notes;
import com.example.notesapp.Mvvm.ViewModel;
import com.example.notesapp.R;
import com.example.notesapp.databinding.ActivityInsertNoteBinding;

import java.util.Date;

public class InsertNoteActivity extends AppCompatActivity {
ActivityInsertNoteBinding binding;
String title,subtitle,notes;
ViewModel viewModel;
String priority = "1";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Add Notes");

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        binding.priorityGreen.setOnClickListener(v -> {
            priority = "1";
            binding.priorityGreen.setImageResource(R.drawable.ic_baseline_done_24);
            binding.priorityYellow.setImageResource(0);
            binding.priorityRed.setImageResource(0);
        });

        binding.priorityYellow.setOnClickListener(v -> {
            priority = "2";
            binding.priorityYellow.setImageResource(R.drawable.ic_baseline_done_24);
            binding.priorityGreen.setImageResource(0);
            binding.priorityRed.setImageResource(0);
        });

        binding.priorityRed.setOnClickListener(v -> {
            priority = "3";
            binding.priorityRed.setImageResource(R.drawable.ic_baseline_done_24);
            binding.priorityYellow.setImageResource(0);
            binding.priorityGreen.setImageResource(0);
        });





        binding.floatingActionButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = binding.btnTitle.getText().toString();
                subtitle = binding.btnSubtitle.getText().toString();
                notes = binding.btnNotes.getText().toString();


                if(title.equals("") && notes.equals("")){

                    Toast.makeText(InsertNoteActivity.this, "Title & Notes Must...", Toast.LENGTH_SHORT).show();
                }else {
                    CreateNotes(title,subtitle,notes);
                }




            }
        });
    }

    private void CreateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes notes1 = new Notes();
        notes1.notes_Title = title;
        notes1.notes_Subtitle = subtitle;
        notes1.notes_date = sequence.toString();       // type casting of sequence from char to string.
        notes1.notes = notes;
        notes1.notes_priority = priority;

        viewModel.insertNote(notes1);

        Toast.makeText(this, "Note Added Successfully", Toast.LENGTH_SHORT).show();

        finish();
    }
}