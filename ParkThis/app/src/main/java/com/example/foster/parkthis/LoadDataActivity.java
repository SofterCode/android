package com.example.foster.parkthis;

import android.content.Intent;
import android.content.res.AssetManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import static java.lang.Double.parseDouble;

public class LoadDataActivity extends AppCompatActivity {

    public String pLots[];


    private static String splitLots = new String();

    protected void startup(){

        getAllLots();
        Geocoder x = new Geocoder(this);
        List<Address> addList = null;

        String lotsP = "";
        String latLong = "";

        String name = "";
        String name2 = "";

        String[] temp;
        String[] temp2;


        //get the parking Lot details
        for(int i=0;i<pLots.length;i++){
            lotsP = pLots[i].toString();

       //regex to get name of parking lot
       Pattern patt2 = Pattern.compile("/[A-Z]+[ ]+[A-Z]+./g");
       Matcher mat2 = patt2.matcher(lotsP);


       if(mat2.find()){
           name = mat2.group(1);
                if(name.contains("-")){
                    //if there is a - in the name, it is not a part of the name
                    //so remove it by splitting and taking the first part
                    temp2 = name.split("-");

                     name2 = temp2[0];
                }
        //add the parking lot name to the names vector
        Ipsum.ParkingLotNameList.add(name2);
       }

        //use the regex pattern for the lat long
        Pattern patt = Pattern.compile("(?:\\d{2}\\.)?\\d{13},(?:\\d{2}\\.)?\\d{13}");
        Matcher matcher = patt.matcher(lotsP);

        Double tempLat =0.0;
        Double tempLong = 0.0;
        //put the negative back in for the correct long (-79 not +79)
        if(matcher.find()){
                latLong =  matcher.group(1).toString();
                //split lat and long on comma
                temp = latLong.split(",");
                 tempLat = parseDouble(temp[0]);
                 tempLat = -1*tempLat;
                 tempLong = parseDouble(temp[1]);
        }


        Address a = new Address(Locale.CANADA);

        a.setLatitude(tempLat);
        a.setLongitude(tempLong);
            addList.add(a);

        LatLng neighB = new LatLng(a.getLatitude(), a.getLongitude());
        //figure out where to store the
        Ipsum.latLngList.add(neighB);
        }

Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);




        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);
        startup();
    }

    public void getAllLots(){
        try{
            AssetManager assMan = getAssets();
            InputStream in = assMan.open("plotfac.xls");
            Workbook wb = Workbook.getWorkbook(in);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            int col = s.getColumns();

            //make a new string array for the lot info
           pLots = new String[row];

            for(int j=0;j<row;j++){
                String st = "";
                    for(int i=0; i<col;i++) {
                        Cell z = s.getCell(0, i);
                         st = st +"-->"+ z.getContents();

                    }
                Log.i("getAllData loaded",st);
                pLots[j]=st;
            }



        }catch (Exception e){e.printStackTrace();}

    }
}
