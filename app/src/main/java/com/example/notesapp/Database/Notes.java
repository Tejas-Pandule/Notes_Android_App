package com.example.notesapp.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_database")
public class Notes {

    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_Title")
    public String notes_Title;

    @ColumnInfo(name = "notes_Subtitle")
    public String notes_Subtitle;

    @ColumnInfo(name = "notes_date")
    public String notes_date;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "notes_priority")
    public String notes_priority;
}
