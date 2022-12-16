package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.notesapp.Activity.InsertNoteActivity;
import com.example.notesapp.Adapter.ArrayAdapter;
import com.example.notesapp.Database.Notes;
import com.example.notesapp.Mvvm.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
 FloatingActionButton floatingActionButton;
 ViewModel viewModel;
 RecyclerView recyclerView;
 TextView txtnofilter, txthighfilter, txtlowfilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle("My Notes");

        floatingActionButton = findViewById(R.id.floatingActionButtonAdd);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);

        txtnofilter = findViewById(R.id.txtNofilter);
        txthighfilter = findViewById(R.id.txthightolow);
        txtlowfilter = findViewById(R.id.txtlowtohigh);

        txtnofilter.setOnClickListener(v -> {
            loadData(0);
            txtnofilter.setBackgroundResource(R.drawable.filter_selected_layout);
            txthighfilter.setBackgroundResource(R.drawable.filter_unselected_layout);
            txtlowfilter.setBackgroundResource(R.drawable.filter_unselected_layout);

        });

        txthighfilter.setOnClickListener(v -> {
            loadData(1);
            txthighfilter.setBackgroundResource(R.drawable.filter_selected_layout);
            txtlowfilter.setBackgroundResource(R.drawable.filter_unselected_layout);
            txtnofilter.setBackgroundResource(R.drawable.filter_unselected_layout);

        });

        txtlowfilter.setOnClickListener(v -> {
            loadData(2);
            txthighfilter.setBackgroundResource(R.drawable.filter_unselected_layout);
            txtlowfilter.setBackgroundResource(R.drawable.filter_selected_layout);
            txtnofilter.setBackgroundResource(R.drawable.filter_unselected_layout);

        });


        // Action Perform when click on add floating action button :
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, InsertNoteActivity.class);
                startActivity(intent);
            }
        });

        //showing data on recycler view:
        viewModel.getallNotes.observe(this, Notes ->{

           Setadapter(Notes);


        });





    }

    private void loadData(int i) {

        if (i==0){
            viewModel.getallNotes.observe(this, Notes ->{

                Setadapter(Notes);
            });
        }else if (i==1){
            viewModel.hightolow.observe(this,Notes ->{

                Setadapter(Notes);

            });
        }else if (i==2){
            viewModel.lowtohigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    Setadapter(notes);

                }
            });
        }
    }

    public void Setadapter(List<Notes> notes){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,notes);
        recyclerView.setAdapter(arrayAdapter);
    }


}