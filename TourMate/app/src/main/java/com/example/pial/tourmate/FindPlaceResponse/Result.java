
package com.example.pial.tourmate.FindPlaceResponse;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result {


    @SerializedName("geometry")
    @Expose
    private Geometry geometry;


    /**
     *
     * @return
     *     The formattedAddress
     */

    public Geometry getGeometry() {
        return geometry;
    }

    /**
     *
     * @param geometry
     *     The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }



}
