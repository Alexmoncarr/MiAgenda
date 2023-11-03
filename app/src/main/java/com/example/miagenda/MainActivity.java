package com.example.miagenda;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText editText1, editText2;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);

        dbHelper = new DatabaseHelper(this);
    }

    //Método para guardar los datos:
    public void guardar(View view) {
        String nombre = editText1.getText().toString();
        String datos = editText2.getText().toString();

        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Crea un nuevo mapa de valores, donde las columnas son los nombres de las columnas
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("datos", datos);

        // Inserta la nueva fila, retorna el valor de clave primaria de la nueva fila
        long newRowId = db.insert("Contactos", null, values);

        Toast.makeText(this, "Contacto guardado con ID: " + newRowId, Toast.LENGTH_SHORT).show();
    }

    //Método para recuperar los datos:
    public void buscar(View view) {
        String nombre = editText1.getText().toString();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define una proyección que especifica qué columnas del resultado quieres obtener
        String[] projection = {"datos"};

        // Filtro para la cláusula WHERE
        String selection = "nombre = ?";
        String[] selectionArgs = {nombre};

        Cursor cursor = db.query(
                "Contactos",   // La tabla para consultar
                projection,    // Las columnas para retornar
                selection,     // Las columnas para la cláusula WHERE
                selectionArgs, // Los valores para la cláusula WHERE
                null,          // Agrupar las filas
                null,          // Filtrar por filas que agrupan
                null           // El orden de las filas
        );

        if (cursor.moveToFirst()) {
            String datos = cursor.getString(cursor.getColumnIndexOrThrow("datos"));
            editText2.setText(datos);
        } else {
            Toast.makeText(this, "No se ha encontrado ningún dato", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }
}