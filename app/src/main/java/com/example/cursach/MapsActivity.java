package com.example.cursach;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private List<LatLng> ST = new ArrayList<>();
    Marker[] mSt;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Button button = findViewById(R.id.akk);
        button.setOnClickListener(viewClickListener);
        // координаты остановок
        ST.add(new LatLng(50.578377, 36.591314));
        ST.add(new LatLng(50.578249, 36.592727));
        ST.add(new LatLng(50.578481, 36.586030));
        ST.add(new LatLng(50.578143, 36.587047));
        ST.add(new LatLng(50.575265, 36.582893));
        ST.add(new LatLng(50.575849, 36.583160));
        ST.add(new LatLng(50.579013, 36.580314));
        ST.add(new LatLng(50.578733, 36.581149));
        mSt = new Marker[ST.size()];
        // Получите SupportMapFragment и получите уведомление, когда карта будет готова к использованию.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
                                return true;
                            case R.id.menu2:
                                Intent intent = new Intent(MapsActivity.this, account.class);
                                startActivity(intent);
                                return true;
                            case R.id.menu3:
                                 intent = new Intent(MapsActivity.this, settings.class);
                                startActivity(intent);
                                return true;
                            case R.id.menu4:
                                 intent = new Intent(MapsActivity.this, info.class);
                                startActivity(intent);
                                return true;
                            case R.id.menu5:
                                 intent = new Intent(MapsActivity.this, message.class);
                                startActivity(intent);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
        popupMenu.show();
    }
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
//cавим метки
        for (int i = 0; i < ST.size(); i++) {
            mSt[i] =map.addMarker(new MarkerOptions()
                    .position(ST.get(i))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ost_blue)));
        }
        mMap.setOnMarkerClickListener(this);

/**
 //рисуем граф

 PolylineOptions options = new PolylineOptions().width(10).color(Color.BLUE).geodesic(true);
 for (int z=0;z<ST.size();z++) {
 LatLng point = ST.get(z);
 for (int i = 0; i < ST.size(); i++) {
 options.add(point);
 point = ST.get(i);
 options.add(point);
 point = ST.get(z);

 }
 }
 mMap.addPolyline(options);
 **/

//ставим камеру
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(50.599761, 36.601329))         //координаты города
                .zoom(12)                                       // приближение
                .bearing(45)                                // угол поворота от севера по часовой
                .tilt(5)                                    // уровень наклона камеры
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);
    }
    String s;
    int nklil=0;
    int namberStart=-1;
    boolean Start=false;
    boolean Finish=false;
    int namberFinish=-1;
    int tmp;

    @Override
    public boolean onMarkerClick(final Marker marker) {
        nklil++;
        switch (nklil){
            case 1:
                s = marker.getId();
                s = s.replaceFirst("^m*", "");
                int tmp1=Integer.valueOf(s);
                if (!Start) {
                    if (tmp1==namberFinish){
                        nklil=0;
                        Finish=false;
                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ost_blue));
                        namberFinish=-1;
                        break;
                    }
                    Start=true;
                    namberStart=Integer.valueOf(s);
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ost_red));
                    Toast.makeText(getApplicationContext(), "Автобус прибудет через 15 минут",Toast.LENGTH_SHORT).show();
                }
                if (Finish){
                    nklil++;
                }

                break;
            case 2:
                s = marker.getId();
                s = s.replaceFirst("^m*", "");
                int tmp2=Integer.valueOf(s);
                if (!Finish) {
                    if(tmp2==namberStart){
                        nklil=0;
                        Start=false;
                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ost_blue));
                        namberStart=-1;
                        break;
                    }
                    namberFinish=Integer.valueOf(s);
                    Finish=true;
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ost_green));
                    Toast.makeText(getApplicationContext(), "Время в пути 10 минут",Toast.LENGTH_SHORT).show();
                }
                if (Start){
                    nklil++;
                }
                break;

            default:
                s = marker.getId();
                s = s.replaceFirst("^m*", "");
                tmp=Integer.valueOf(s);
                if(tmp==namberStart) {
                    nklil=0;
                    Start=false;
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ost_blue));
                    namberStart=-1;
                }
                else if (tmp==namberFinish){
                    nklil=1;
                    Finish=false;
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ost_blue));
                    namberFinish=-1;
                }
                break;
        }

        //MyThread myThread = new MyThread();
      //  myThread.start();
        return true;
    }
}


