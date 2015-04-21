package com.lazaro.bdalumnos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

// se declara los edit text
    EditText et_idusuario,  et_nombre, et_apellidos, et_curp, et_email, et_carrera, et_turno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // se ligan con lo grafico
        et_idusuario = (EditText) findViewById(R.id.et_idusuario);
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellidos = (EditText) findViewById(R.id.et_apellidos);
        et_curp = (EditText) findViewById(R.id.et_curp);
        et_email = (EditText) findViewById(R.id.et_email);
        et_carrera = (EditText) findViewById(R.id.et_carrera);
        et_turno = (EditText) findViewById(R.id.et_turno);
    }

    public void guardar (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "alumnos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idusuario = et_idusuario.getText().toString();
        String nombre = et_nombre.getText().toString();
        String apellidos = et_apellidos.getText().toString();
        String curp = et_curp.getText().toString();
        String email = et_email.getText().toString();
        String carrera = et_carrera.getText().toString();
        String turno = et_turno.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("id_usuario", idusuario);
        registro.put("nombre", nombre);
        registro.put("apellidos", apellidos);
        registro.put("curp",curp);
        registro.put("email", email);
        registro.put("carrera", carrera);
        registro.put("turno", turno);


        bd.insert("alumnos", null, registro);
        bd.close();

        et_idusuario.setText("");
        et_nombre.setText("");
        et_apellidos.setText("");
        et_curp.setText("");
        et_email.setText("");
        et_carrera.setText("");
        et_turno.setText("");

        Toast.makeText(this, "Estudiante Agregado con exito", Toast.LENGTH_SHORT).show();
    }

    public void buscar (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "alumnos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idusuario = et_idusuario.getText().toString();
        Cursor fila = bd.rawQuery("select nombre, apellidos, curp, email, carrera, turno from alumnos where id_usuario=" + idusuario, null);
        if (fila.moveToFirst()) {

            et_nombre.setText(fila.getString(0));
            et_apellidos.setText(fila.getString(1));
            et_curp.setText(fila.getString(2));
            et_email.setText(fila.getString(3));
            et_carrera.setText(fila.getString(4));
            et_turno.setText(fila.getString(5));
        } else {
            Toast.makeText(this,"El alumno no se ha registrado",Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void borrar (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "alumnos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idusuario = et_idusuario.getText().toString();
        int cant = bd.delete("alumnos","id_usuario=" + idusuario, null);
        bd.close();

        et_idusuario.setText("");

        et_nombre.setText("");
        et_apellidos.setText("");
        et_curp.setText("");
        et_email.setText("");
        et_carrera.setText("");
        et_turno.setText("");

        if (cant == 1) {
            Toast.makeText(this, "EL registro ha sido eliminado",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "EL registro no existe",Toast.LENGTH_SHORT).show();
        }
    }

    public void editar (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "alumnos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idusuario = et_idusuario.getText().toString();
        String nombre = et_nombre.getText().toString();
        String apellidos = et_apellidos.getText().toString();
        String curp = et_curp.getText().toString();
        String email = et_email.getText().toString();
        String carrera = et_carrera.getText().toString();
        String turno = et_turno.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("id_usuario", idusuario);
        registro.put("nombre", nombre);
        registro.put("apellidos", apellidos);
        registro.put("curp", curp);
        registro.put("email", email);
        registro.put("carrera", carrera);
        registro.put("turno", turno);

        int cant = bd.update("alumnos", registro, "id_usuario=" + idusuario, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Los datos se han actualizado",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "El registro no existe",Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiar (View v){
        et_idusuario.setText("");
        et_nombre.setText("");
        et_apellidos.setText("");
        et_curp.setText("");
        et_email.setText("");
        et_carrera.setText("");
        et_turno.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
