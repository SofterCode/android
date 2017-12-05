package com.example.foster.parkthis;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class LocationsActivity extends FragmentActivity implements OnMapReadyCallback {

    /*
    * need
    * 2 string arrays to hold 10 landmark locations
    * and their addresses
    * */
    private GoogleMap mMap;
    private String parkLotNames[];
    private static int index =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Geocoder x = new Geocoder(this);
        List<Address> adressList = null;
        try
        {
            adressList = x.getFromLocationName("Toronto Ontario", 1);
        }
        catch (Exception e)
        {

        }
        Address a = adressList.get(0);
        // set focus to Toronto
        LatLng toronto = new LatLng(a.getLatitude(), a.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toronto,9.6f));

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        parkLotNames = new String[Ipsum.ParkingLotNameList.toArray().length];

        Object[] tempLocName = Ipsum.ParkingLotNameList.toArray();

        for(int i=0;i<tempLocName.length;i++){
            //get the parking lot names from the data set loaded
            parkLotNames[i]=tempLocName[i].toString();

        }

        //put markers for the lots on the map
        for(LatLng neighborhood : Ipsum.latLngList){
            mMap.addMarker(new MarkerOptions().position(neighborhood).title(parkLotNames[index])).setTag(neighborhood);
            index++;



            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker arg0) {
                    Intent send = new Intent(getApplicationContext(), NameActivity.class);
                    for(int i =0;i<parkLotNames.length; i++){
                        if(parkLotNames[i].contains(arg0.getTitle())){
                            index = i;
                        }
                        send.putExtra("Name", parkLotNames[index]);
                        index =0;
                        startActivity(send);
                    }
                }
            });


        }
    }
}
