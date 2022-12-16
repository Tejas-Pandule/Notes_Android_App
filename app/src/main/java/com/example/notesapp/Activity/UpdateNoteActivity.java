package com.example.notesapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.Database.Notes;
import com.example.notesapp.Mvvm.ViewModel;
import com.example.notesapp.R;
import com.example.notesapp.databinding.ActivityUpdateNoteBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;
import java.util.Objects;

public class UpdateNoteActivity extends AppCompatActivity {
ActivityUpdateNoteBinding binding;
ViewModel viewModel;
String stitle,ssubtitle,sdate,snotes,spriority;
int iid;
String priority = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //changing text on toolbar:
        Objects.requireNonNull(getSupportActionBar()).setTitle("Update Notes");

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        iid = getIntent().getIntExtra("id",0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        snotes = getIntent().getStringExtra("notes");
        spriority = getIntent().getStringExtra("priority");

        binding.btnUtitle.setText(stitle);
        binding.btnUSubtitle.setText(ssubtitle);
        binding.btnUNotes.setText(snotes);

        if(Objects.equals(spriority, "1"))
            binding.priorityGreen.setImageResource(R.drawable.ic_baseline_done_24);


        else if(Objects.equals(spriority, "2"))
            binding.priorityYellow.setImageResource(R.drawable.ic_baseline_done_24);

        else if(Objects.equals(spriority, "3"))
            binding.priorityRed.setImageResource(R.drawable.ic_baseline_done_24);



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


        binding.floatingActionButtonUpdate.setOnClickListener(v -> {

           String  title = binding.btnUtitle.getText().toString();
           String subtitle = binding.btnUSubtitle.getText().toString();
           String notes = binding.btnUNotes.getText().toString();

            UpdateNotes(title,subtitle,notes);

        });

    }

    private void UpdateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes notes2 = new Notes();
        notes2.id = iid;
        notes2.notes_Title = title;
        notes2.notes_Subtitle = subtitle;
        notes2.notes_date = sequence.toString();       // type casting of sequence from char to string.
        notes2.notes = notes;
        notes2.notes_priority = priority;

        viewModel.updateNote(notes2);


        Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.deletenote,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.icdelete){

            BottomSheetDialog sheetDialog = new BottomSheetDialog(this);

            View view = LayoutInflater.from(this).inflate(R.layout.delete_layout,
                    (LinearLayout)findViewById(R.id.delete_shit_structure));

            sheetDialog.setContentView(view);

            TextView yes = view.findViewById(R.id.delete_yes);
            TextView no = view.findViewById(R.id.delete_no);


            yes.setOnClickListener(v -> {

                viewModel.deleteNote(iid);

                finish();
            });

            no.setOnClickListener(v -> {

                sheetDialog.dismiss();


            });
            

            sheetDialog.show();

        }

        return true;
    }
}