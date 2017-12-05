package com.example.foster.parkthis;

import android.util.Log;

import static java.lang.Double.parseDouble;

/**
 * Created by Foster on 03/12/2017.
 */

class ParkingLotSample {

    //members that match the names of the columns
    //of parking lot data
    private Integer pAssetId;
    private String parkName;
    private Integer numSpaces;
    private Integer numHSpaces;
    private String gisCdStr;
    private String parkCoords;
    private Double parkLat;
    private Double parkLong;
    private String access;

    private void setLatLng(String parkCds) {
        String[] tokens = parkCds.split(",");
        String latString = tokens[0];


        latString = latString.replace("\"(", "");
        //tokens[1] = tokens[1].replace(")\"", "");
        //call the setters for park lattitude and longitude
        Log.d("latStringAfterReplace", latString);
        setParkLat(Double.parseDouble(latString));
        setParkLong(Double.parseDouble(tokens[1]));
    }


    //getters and setters
    public Integer getpAssetId() {
        return pAssetId;
    }

    public void setpAssetId(Integer pAssetId) {
        this.pAssetId = pAssetId;
    }
    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public Integer getNumSpaces() {
        return numSpaces;
    }

    public void setNumSpaces(Integer numSpaces) {
        this.numSpaces = numSpaces;
    }

    public Integer getNumHSpaces() {
        return numHSpaces;
    }

    public void setNumHSpaces(Integer numHSpaces) {

            this.numHSpaces = numHSpaces;

    }

    public String getGisCdStr() {
        return gisCdStr;
    }

    public void setGisCdStr(String gisCdStr) {
        this.gisCdStr = gisCdStr;
        //setParkCoords(gisCdStr);
    }


    public void setParkLat(Double lattitude ){
        this.parkLat = lattitude;
    }

    public Double getParkLat(){
        return parkLat;
    }

    public void setParkLong(Double longitude){
        this.parkLong = longitude;
    }
    public Double getParkLong(){
        return parkLong;
    }
    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }


    @Override
    public String toString() {
        return "ParkingLotSample{" +
                "pAssetId=" + pAssetId +
                ", parkName='" + parkName + '\'' +
                ", numSpaces=" + numSpaces +
                ", numHSpaces=" + numHSpaces +
                ", gisCdStr='" + gisCdStr + '\'' +
                ", parkLat=" + parkLat +
                ", parkLong=" + parkLong +
                ", access='" + access + '\'' +
                '}';
    }

    public void setParkCoords(String parkCoords) {
        this.parkCoords = parkCoords;
    }

    // public void setParkCoords(String token) {setParkCoords(token);
    //}
}
