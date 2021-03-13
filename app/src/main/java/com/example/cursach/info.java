package com.example.cursach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Button button = findViewById(R.id.akk);
        button.setOnClickListener(viewClickListener);
    }
    //// Меню
    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }
    };
    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_main);

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                Intent intent = new Intent(info.this, MapsActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.menu2:
                                intent = new Intent(info.this, account.class);
                                startActivity(intent);
                                return true;
                            case R.id.menu3:
                                intent = new Intent(info.this, settings.class);
                                startActivity(intent);
                                return true;
                            case R.id.menu4:
                                intent = new Intent(info.this, info.class);
                                startActivity(intent);
                                return true;
                            case R.id.menu5:
                                intent = new Intent(info.this, message.class);
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
