package com.example.foster.parkthis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    private String[] details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        //get the name of the lot wanted
        final String parkingLotName = getIntent().getStringExtra("Name");

        //grab the details about the lot with the name
        getDetails(parkingLotName);

        setContentView(R.layout.activity_name);

        TextView titleChange = (TextView) findViewById(R.id.titlePark);
        titleChange.setText(parkingLotName);

        String[] lotDetails = getResources().getStringArray(R.array.pLottypes_array);
        ListView listView = (ListView) findViewById(R.id.listView2);
        CustomAdapter custAdapter = new CustomAdapter(this,lotDetails,details);
        listView.setAdapter(custAdapter);

        Button goToMap = (Button)findViewById(R.id.vLocation);
        goToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(getApplicationContext(), MapsActivity.class);
                send.putExtra("Name", parkingLotName);
                startActivity(send);
            }
        });




    }

    public void getDetails(String pLotName){}
}
