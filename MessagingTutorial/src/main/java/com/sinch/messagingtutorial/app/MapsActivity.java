package com.sinch.messagingtutorial.app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Context context;
    Intent intent;
    Bundle bundle;
    Button b;
    Double longitude,latitude;
    LatLng tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        //setUpMapIfNeeded();

        b=(Button)findViewById(R.id.button);

        intent=this.getIntent();
        bundle=intent.getExtras();
        latitude=bundle.getDouble("latitude");
        longitude=bundle.getDouble("longitude");

        tts = new LatLng(latitude,longitude);

        //String location=bundle.getString("location");

        context=this;
        mMap=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        //設定地圖種類
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);    //道路地圖
        //設定目前所在位置
        mMap.setMyLocationEnabled(true);
        UiSettings ui = mMap.getUiSettings();
        ui.setZoomControlsEnabled(true);

        //tts=GeoUtil.getLatLngByAddress(location);

        //Toast.makeText(MapsActivity.this,location,Toast.LENGTH_SHORT).show();
        Toast.makeText(MapsActivity.this,"map lat: "+latitude,Toast.LENGTH_SHORT).show();
        Toast.makeText(MapsActivity.this,"map long: "+longitude,Toast.LENGTH_SHORT).show();

        MarkerOptions mo=new MarkerOptions();
        mo.position(tts);
       // mo.title(location);
        mo.snippet("經緯度:" + tts);
        mo.anchor(0.5f, 0.5f);
        mo.draggable(true);
        Marker marker=mMap.addMarker(mo);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tts, 16));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18), 2000, null);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
