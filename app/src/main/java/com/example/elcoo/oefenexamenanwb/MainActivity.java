package com.example.elcoo.oefenexamenanwb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase anwbDB = this.openOrCreateDatabase("anwbDB", MODE_PRIVATE, null);

        File dbFile = getDatabasePath("anwbDB");
        if(dbFile.exists()) {
            Log.i("Database anwbDB", "bestaat");
        } else {
            Log.i("Database anwbDB", "bestaat niet");
        }

        anwbDB.execSQL("CREATE TABLE IF NOT EXISTS "
        + "gegevens"
        + " (Voornaam VARCHAR, Achternaam VARCHAR, Email VARCHAR, Telefoonnummer VARCHAR );");

//        anwbDB.execSQL("INSERT INTO "
//        + "gegevens"
//        + " (Voornaam, Achternaam, Email, Telefoonnummer)"
//        + " VALUES ('Elco', 'Mussert', 'elcomussert@gmail.com', '0614975525');");

        Cursor c = anwbDB.rawQuery("SELECT * FROM " + "gegevens" , null);
        int Column1 = c.getColumnIndex("Voornaam");
        int Column2 = c.getColumnIndex("Achternaam");
        int Column3 = c.getColumnIndex("Email");
        int Column4 = c.getColumnIndex("Telefoonnummer");
        c.moveToLast();
        String Voornaam = c.getString(Column1);
        String Achternaam = c.getString(Column2);
        String Email = c.getString(Column3);
        String Telefoonnummer = c.getString(Column4);
        Log.i("Persoon: " , Voornaam + " " + Achternaam + " " + Email + " " + Telefoonnummer);
    }

    public void onClick(View view) {
        SQLiteDatabase anwbDB = this.openOrCreateDatabase("anwbDB", MODE_PRIVATE, null);


        EditText Voornaam = (EditText) findViewById(R.id.editText1);
        String voornaamInfo = Voornaam.getText().toString();
        EditText Achternaam = (EditText) findViewById(R.id.editText3);
        String achternaamInfo = Achternaam.getText().toString();
        EditText Email = (EditText) findViewById(R.id.editText2);
        String emailInfo = Email.getText().toString();
        EditText Telefoonnummer = (EditText) findViewById(R.id.editText4);
        String telefoonInfo = Telefoonnummer.getText().toString();

        Log.d("test", "registeren button geklikt");
        String sql = "INSERT or replace INTO gegevens (Voornaam, Achternaam, Email, Telefoonnummer) VALUES('" + voornaamInfo + "' , '" + achternaamInfo + "' , '" + emailInfo + "' , '" + telefoonInfo + "')";
        anwbDB.execSQL(sql);


    }
}
