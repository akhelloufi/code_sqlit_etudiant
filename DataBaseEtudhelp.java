package com.example.projet2022_212052022;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseEtudhelp  extends SQLiteOpenHelper {



    public DataBaseEtudhelp(@Nullable Context context){
        super(context,"gestionEtudiant",null,1);//creation database gestionEtudiant
    }
    @Override
    public void onCreate(SQLiteDatabase x) {
        String sql="create table etudiant(code INTEGER primary key,nom TEXT ,note REAL) ";
        x.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase x, int i, int i1) {
        String sql="drop table etudiant";
        x.execSQL(sql);
        onCreate(x);
    }
}
