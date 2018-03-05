package com.example.diegocisneros.proyecto;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class RegistroActivity extends AppCompatActivity {
    EditText user,password;
    Button registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        user = (EditText)findViewById(R.id.userregistro);
        password = (EditText)findViewById(R.id.passregistro);
        registro = (Button)findViewById(R.id.crear);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarusuario();
            }
        });
    }
    public Connection connexionBD(){
        Connection conexion = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //conexion = DriverManager.getConnection("jdbc:mysql://192.168.32.46:3306/fitmacro",
            conexion = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/fitmacro",
                    "root",
                    "root");
            Log.d("dgo","Se ha conectado a la base de datos");
        }catch (Exception e){
            Log.d("dgo",e.getMessage());

        }
        return conexion;

    }
    public void registrarusuario(){
        try{
            Connection c = connexionBD();
            PreparedStatement pst=c.prepareStatement("insert into usuarios (nombre_usuario,contraseña)values(?,?)");
            pst.setString(1,user.getText().toString());
            pst.setString(2,password.getText().toString());
            pst.executeUpdate();
            Log.d("dgo","Ha funcionado");
        }catch(Exception e){
            Log.d("dgo",e.getMessage());
        }
    }
}
