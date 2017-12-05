package com.example.foster.parkthis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private String parkingLots[];

    private List<ParkingLotSample> newParkLots = new ArrayList<>();
    private void readParkingData(){

        //raw resources returns an input stream
        InputStream is = getResources().openRawResource(R.raw.plotfac);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while((line  = reader.readLine())!=null){
                //split by comma
                String[] tokens = line.split(",");

                //read the data
                ParkingLotSample pls= new ParkingLotSample();

                pls.setpAssetId(Integer.parseInt(tokens[0]));
                pls.setParkName(tokens[1]);
                pls.setNumSpaces(Integer.parseInt(tokens[2]));
                pls.setNumHSpaces(Integer.parseInt(tokens[3]));
                pls.setGisCdStr(tokens[4]);
                pls.setAccess(tokens[5]);
                pls.setParkCoords(tokens[4]);
                newParkLots.add(pls);

              //  LatLng parkLatLng = new LatLng(pls.getParkLat(), pls.getParkLong());
                //Ipsum.latLngList.add(parkLatLng);
                Ipsum.ParkingLotNameList.add(pls.getParkName());


                Log.d("MainActivity", "Just created: "+ pls);
            }
        } catch (IOException e) {
            Log.wtf("MainActivity", "Error reading data file on line " + line, e);
        }
    }




    public void nameHandler(){
        Intent nameIntent = new Intent(MainActivity.this, NameActivity.class);
        startActivity(nameIntent);

    }

    public void locationHandler(){
        Intent locationsIntent = new Intent(MainActivity.this, LocationsActivity.class);
        startActivity(locationsIntent);
    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.help_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readParkingData();

        Button nameButton = (Button) findViewById(R.id.location);
        Button addressButton = (Button)findViewById(R.id.address);

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationHandler();
            }
        });

        addressButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                nameHandler();
            }
        });

    }
}
