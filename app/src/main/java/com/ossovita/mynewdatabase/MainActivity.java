package com.ossovita.mynewdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase  db = this.openOrCreateDatabase("Rappers",MODE_PRIVATE,null);
            db.execSQL("DROP TABLE IF EXISTS rappers");
            db.execSQL("CREATE TABLE rappers (uniqueId INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,age INT(2))");
            db.execSQL("INSERT INTO rappers(name,age) VALUES ('Tupac',24)");
            db.execSQL("INSERT INTO rappers(name,age) VALUES ('Biggie',34)");

            Cursor c = db.rawQuery("SELECT * FROM rappers",null);
            int nameIx = c.getColumnIndex("name");//name sütununun indexini al
            int ageIx = c.getColumnIndex("age");//age sütununun indexini al
            int idIx = c.getColumnIndex("uniqueId");
            while(c.moveToNext()){
                System.out.println("Name:"+ c.getString(nameIx));
                System.out.println("Age:"+ c.getInt(ageIx));
                System.out.println("ID:"+ c.getInt(idIx) );
            }
            c.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}