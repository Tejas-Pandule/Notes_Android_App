package com.example.notesapp.Mvvm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesapp.Database.Notes;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    public Repository repository;
    public LiveData<List<Notes>> getallNotes;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;

    public ViewModel(Application application) {
        super(application);

        repository = new Repository(application);
        getallNotes = repository.getAllNotes;
        hightolow = repository.hightolow;
        lowtohigh = repository.lowtohigh;
    }

    public void insertNote(Notes notes){
        repository.insertNotes(notes);
    }

    public void updateNote(Notes notes){
        repository.updateNotes(notes);
    }
    public void deleteNote(int id){
        repository.deleteNotes(id);
    }
}
