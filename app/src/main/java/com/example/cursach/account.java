package com.example.cursach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import java.io.FileOutputStream;
import java.io.IOException;

public class account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Button button = findViewById(R.id.akk);
        button.setOnClickListener(viewClickListener);
    }
    String nem="0";
    void log_write(String t) throws IOException {
        // запись в файл результата входа
        FileOutputStream foz = openFileOutput(nem, Context.MODE_PRIVATE);
        foz.write(t.getBytes());
        foz.close();
    }
    //// Меню
    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }
    };
    public void sm(View view){
        String t="0";
        try {
            log_write(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(account.this,MainActivity.class);
        startActivity(intent);
    }
    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_main);

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                Intent intent = new Intent(account.this, MapsActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.menu2:
                               intent = new Intent(account.this, account.class);
                                startActivity(intent);
                                return true;
                            case R.id.menu3:
                                intent = new Intent(account.this, settings.class);
                                startActivity(intent);
                                return true;
                            case R.id.menu4:
                                intent = new Intent(account.this, info.class);
                                startActivity(intent);
                                return true;
                            case R.id.menu5:
                                intent = new Intent(account.this, message.class);
                                startActivity(intent);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
        popupMenu.show();
    }
}
