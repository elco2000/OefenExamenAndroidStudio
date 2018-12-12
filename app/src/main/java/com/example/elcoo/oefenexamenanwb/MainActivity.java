package com.example.elcoo.oefenexamenanwb;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;

public class MainActivity extends AppCompatActivity
{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int photo = 0;


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

        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            Log.i("camera", "device heeft camera");
//            dispatchTakePictureIntent();
        } else {
            Log.i("camera", "er is geen camera");
        }

//        anwbDB.execSQL("INSERT INTO "
//        + "gegevens"
//        + " (Voornaam, Achternaam, Email, Telefoonnummer)"
//        + " VALUES ('Elco', 'Mussert', 'elcomussert@gmail.com', '0614975525');");
//
//        Cursor c = anwbDB.rawQuery("SELECT * FROM " + "gegevens" , null);
//        int Column1 = c.getColumnIndex("Voornaam");
//        int Column2 = c.getColumnIndex("Achternaam");
//        int Column3 = c.getColumnIndex("Email");
//        int Column4 = c.getColumnIndex("Telefoonnummer");
//        c.moveToLast();
//        String Voornaam = c.getString(Column1);
//        String Achternaam = c.getString(Column2);
//        String Email = c.getString(Column3);
//        String Telefoonnummer = c.getString(Column4);
//        Log.i("Persoon: " , Voornaam + " " + Achternaam + " " + Email + " " + Telefoonnummer);



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

    public void onClickMaps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void onPhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        photo = 1;
    }

    public void onPhoto2(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        photo = 2;
    }

//    private void dispatchTakePictureIntent() {
//     Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//     startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//    }

    protected void onActivityResult(int requestcode, int resultCode, Intent data) {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        if (requestcode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (photo == 1) {
                imageView.setImageBitmap(imageBitmap);
            } else if (photo == 2) {
                imageView2.setImageBitmap(imageBitmap);
            }

        }
    }

}
