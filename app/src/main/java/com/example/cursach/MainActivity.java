package com.example.cursach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button registration;
    private TextView loginLocked;
    private TextView attempts;
    private TextView numberOfAttempts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.edit_password);
        password = (EditText) findViewById(R.id.edit_FIO);
        login = (Button) findViewById(R.id.button_login);
        registration = (Button) findViewById(R.id.button_registration);
        numberOfAttempts = (TextView) findViewById(R.id.number_of_attempts);

        try {
            log();         // проверка на вход
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void log_write(String t) throws IOException {
        // запись в файл результата входа
        FileOutputStream foz = openFileOutput(nem, Context.MODE_PRIVATE);
        foz.write(t.getBytes());
        foz.close();
    }
    String nem="0";
    void log () throws IOException {                      // проверка на вход
        try {
            FileInputStream fos = openFileInput(nem) ;
            byte[] bytes = new byte[fos.available()];
            fos.read(bytes);

            String text = new String (bytes);
            fos.close();
            if (text.equals("1")){
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        }
        catch (IOException e ) {
            String  tt =  "0";                    /** при запуске в первый раз записывпем в файл 0 **/
            log_write(tt);
        }
    }
    String key = "0"; // ключ входа
    String s;
    public void registration(View view) {  //крнопка регистрации преход на новую активити
        Intent intent = new Intent(MainActivity.this,registraion.class);
        startActivity(intent);
    }
    public void Login(View view) throws IOException {  // кнопка входа
        s="";
        s=username.getText().toString()+"#"+password.getText().toString();
       // Myclientserver Myclientserver = new Myclientserver(s,key);
       // Myclientserver.start();
        if (username.getText().toString().equals("admin")&& password.getText().toString().equals("1234")) {
            Toast.makeText(getApplicationContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show();
            log_write("1");
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Неправильные данные!", Toast.LENGTH_SHORT).show();
        }
    }
}