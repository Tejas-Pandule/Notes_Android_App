package com.example.notesapp.Database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public  interface NoteDao {

    @Query("SELECT * FROM notes_database")
    LiveData<List<Notes>> getallNotes();

    @Query("SELECT * FROM notes_database order by notes_priority Desc")
    LiveData<List<Notes>> hightolow();

    @Query("SELECT * FROM notes_database order by notes_priority Asc")
    LiveData<List<Notes>> lowtohigh();



    @Insert
    void insertData(Notes notes);

    @Query("delete from notes_database where id =:id")
    void deleteData(int id);

    @Update
    void updateData (Notes notes);




}
