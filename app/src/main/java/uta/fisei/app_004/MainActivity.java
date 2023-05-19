package uta.fisei.app_004;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCode;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextPhone;
    private EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCode=findViewById(R.id.editTextCode);
        editTextName=findViewById(R.id.editTextName);
        editTextLastName=findViewById(R.id.editTextLastName);
        editTextPhone=findViewById(R.id.editTextPhone);
        editTextEmail=findViewById(R.id.editTextEmail);
    }

    public void onClick_buttonInsert(View view){
        DataBaseAdmin dataBaseAdmin=new DataBaseAdmin(this,"SEXTODB",null,1);
        SQLiteDatabase sql=dataBaseAdmin.getWritableDatabase();

        String name=editTextName.getText().toString();
        String lastName=editTextLastName.getText().toString();
        String phone=editTextPhone.getText().toString();
        String email=editTextEmail.getText().toString();

        //sql.execSQL("INSERT INTO Clientes(Name,LastName,Phone,Email)" +
        // "VALUES('"+name+"','"+lastName+"','"+phone+"','"+mail+"')");

        ContentValues values =new ContentValues();
        values.put("Name",name);
        values.put("LastName",lastName);
        values.put("Phone",phone);
        values.put("Email",email);

        long count=sql.insert("Clients",null,values);

        sql.close();
        Toast.makeText(this,"El contacto fue insertado correctamente",
                Toast.LENGTH_SHORT).show();

        clearTexts();

    }

    private void clearTexts() {
        editTextName.setText("");
        editTextLastName.setText("");
        editTextPhone.setText("");
        editTextEmail.setText("");
    }

    public void onClick_buttonSearch(View view){
        DataBaseAdmin dataBaseAdmin=new DataBaseAdmin(this,"SEXTODB",null,1);
        SQLiteDatabase sql=dataBaseAdmin.getReadableDatabase();

        String code=editTextCode.getText().toString();
        if (!code.matches("")) {

            final String SELECT = "SELECT Name, LastName, Phone, Email " +
                    "FROM Clients " +
                    "WHERE Code = " + code;


            Cursor cursor = sql.rawQuery(SELECT, null);


            if (cursor.moveToFirst()) {
                editTextName.setText(cursor.getString(0));
                editTextLastName.setText(cursor.getString(1));
                editTextPhone.setText(cursor.getString(2));
                editTextEmail.setText(cursor.getString(3));
            } else {
                clearTexts();
                Toast.makeText(this, "Contacto no encontrado",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Debe ingresar el código",
                    Toast.LENGTH_SHORT).show();
        }
        sql.close();
    }

    public void onClick_buttonDelete(View view){
        DataBaseAdmin dataBaseAdmin=new DataBaseAdmin(this,"SEXTODB",null,1);
        SQLiteDatabase sql=dataBaseAdmin.getWritableDatabase();

        String code=editTextCode.getText().toString();


        final String DELETE = "DELETE " +
                "FROM clients " +
                "WHERE Code = " + code;

        sql.execSQL(DELETE);
        editTextCode.setText("");
        clearTexts();

        Toast.makeText(this, "El contacto ha sido eliminado correctamente",
                Toast.LENGTH_SHORT).show();

            sql.close();
    }

    public void onClick_buttonUpdate(View view){
        DataBaseAdmin dataBaseAdmin=new DataBaseAdmin(this,"SEXTODB",null,1);
        SQLiteDatabase sql=dataBaseAdmin.getWritableDatabase();



    }

}