
package com.example.cursach;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class registraion extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private EditText FIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraion);
        login = (EditText) findViewById(R.id.edit_login);
        password = (EditText) findViewById(R.id.edit_password);
        FIO = (EditText) findViewById(R.id.edit_FIO);
    }

    String nem="0";
    void log_write(String t) throws IOException {
        // запись в файл результата входа
        FileOutputStream foz = openFileOutput(nem, Context.MODE_PRIVATE);
        foz.write(t.getBytes());
        foz.close();
    }
    public void registred(View view){
        String  l= (login.getText().toString());
        String P=(password.getText().toString());
        String F = (FIO.getText().toString());
        String key = null;
        String s="";
        s=l+"#"+P+"#"+F;
      //  Myclientserver Myclientserver = new Myclientserver(s,key);
      //  Myclientserver.start();
        if (1==1){
            Toast.makeText(getApplicationContext(), "Регистрация прошла успешно!",Toast.LENGTH_SHORT).show();
            try {
                log_write("1");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(registraion.this,MainActivity.class);
            startActivity(intent);}
        else {
            Toast.makeText(getApplicationContext(), "Ошибка регистрации ",Toast.LENGTH_SHORT).show();
        }
    }
}
