package com.example.notesapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Notes.class},version = 1)

public abstract class NotesDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();
    public static NotesDatabase instance;

    public static synchronized NotesDatabase getInstance(Context context){

        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class,"notes_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return  instance;
    }




}


