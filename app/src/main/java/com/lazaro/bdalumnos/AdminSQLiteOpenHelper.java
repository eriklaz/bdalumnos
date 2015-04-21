package com.lazaro.bdalumnos;

/**
 * Created by lazaro on 17/04/2015.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminSQLiteOpenHelper  extends  SQLiteOpenHelper{


    // Creamos el constructor
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // creamosla tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alumnos (id_usuario integer primary key unique, nombre text, apellidos text, curp text, email text, carrera text, turno text) ");
    }

    // borrar la tabla (drop)  y crear la nueva tabla (create)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists alumnos");
        db.execSQL("create table alumnos (id_usuario integer primary key unique, nombre text, apellidos text, curp text, email text, carrera text, turno text) ");
    }

}
