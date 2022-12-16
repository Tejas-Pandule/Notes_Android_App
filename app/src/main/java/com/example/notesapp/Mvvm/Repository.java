package com.example.notesapp.Mvvm;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesapp.Database.NoteDao;
import com.example.notesapp.Database.NotesDatabase;
import com.example.notesapp.Database.Notes;

import java.util.List;

public class Repository {

    public NoteDao noteDao;

    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;

    public Repository(Application application){

        NotesDatabase notesDatabase = NotesDatabase.getInstance(application);
        noteDao = notesDatabase.noteDao();
        getAllNotes = noteDao.getallNotes();
        hightolow = noteDao.hightolow();
        lowtohigh = noteDao.lowtohigh();
    }


   public void insertNotes (Notes notes){
       noteDao.insertData(notes);
    }


    public void deleteNotes (int id){
        noteDao.deleteData(id);
    }

    public void updateNotes (Notes notes){
        noteDao.updateData(notes);
    }
}
