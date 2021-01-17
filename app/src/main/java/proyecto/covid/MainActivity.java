package proyecto.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.UniversalTimeScale;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UTFDataFormatException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import proyecto.covid.Utilidades.Utilidades;

public class MainActivity extends AppCompatActivity {

    private EditText campoUsuarioLogin;
    private EditText campoContrasenaLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this,"bd_usuarios", null,1);

        campoUsuarioLogin = (EditText) findViewById(R.id.txtfieldUsuarioLogin);
        campoContrasenaLogin = (EditText) findViewById(R.id.txtfieldContrasenaLogin);

    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.btnIngresar:
                login(campoContrasenaLogin);
                viewAll();
                break;
            case R.id.btnRegistrate:
                Intent siguiente = new Intent(this, Registro.class);
                startActivity(siguiente);
                break;
        }
    }

    public void login(EditText contrasena){


        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this,"bd_usuarios", null,1);

        SQLiteDatabase db = conexion.getReadableDatabase();

        String[] parametros = {campoUsuarioLogin.getText().toString()};

        String[] campos = {Utilidades.CAMPO_USUARIOREGISTRADO,Utilidades.CAMPO_CONTRASENA};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_USUARIOREGISTRADO + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            if(cursor.getString(1).equals(contrasena.getText().toString())){
                Intent siguiente = new Intent(this, PaginaPrincipal.class);
                startActivity(siguiente);
            }else{
                Toast.makeText(getApplicationContext(),"Contraseña incorrecta", Toast.LENGTH_LONG).show();
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_LONG).show();
        }
    }

    public void viewAll(){

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this,"bd_usuarios", null,1);

        SQLiteDatabase db = conexion.getReadableDatabase();

        String[] campos = {Utilidades.CAMPO_NOMBRECOMPLETO,Utilidades.CAMPO_USUARIOREGISTRADO,Utilidades.CAMPO_CORREO,Utilidades.CAMPO_CONTRASENA, Utilidades.CAMPO_CONFIRMARCONTRASENA, Utilidades.CAMPO_SEXO};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, null, null, null, null, null);
            if(cursor.moveToFirst()){
                do {
                    System.out.println(cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)
                    +" " + cursor.getString(4)+" "+cursor.getString(5));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_LONG).show();
        }
    }
}
