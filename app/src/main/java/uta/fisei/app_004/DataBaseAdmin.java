package uta.fisei.app_004;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseAdmin extends SQLiteOpenHelper {

    private static final String TABLE_CLIENTES="CREATE TABLE Clients " +
            " (Code INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Name TEXT," +
            "LastName TEXT," +
            "Phone TEXT, " +
            "Email TEXT)";

    private static final String DROP_TABLE="DROP TABLE IF EXISTS Clients";

    public DataBaseAdmin(@Nullable Context context,
                         @Nullable String name,
                         @Nullable SQLiteDatabase.CursorFactory factory,
                         int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creacion de la estructura de las tablas

        sqLiteDatabase.execSQL(TABLE_CLIENTES);
        //INSERT INTO CLIENTES

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // los comandos requeridos cuando hay cambios en la estructura de la tabla


        sqLiteDatabase.execSQL(DROP_TABLE);

        sqLiteDatabase.execSQL(TABLE_CLIENTES);
    }
}
