
package com.example.pial.tourmate.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("precipIntensity")
    @Expose
    private Double precipIntensity;
    @SerializedName("precipProbability")
    @Expose
    private Double precipProbability;
    @SerializedName("precipIntensityError")
    @Expose
    private Double precipIntensityError;
    @SerializedName("precipType")
    @Expose
    private String precipType;

    /**
     * 
     * @return
     *     The time
     */
    public Integer getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The precipIntensity
     */
    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    /**
     * 
     * @param precipIntensity
     *     The precipIntensity
     */
    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    /**
     * 
     * @return
     *     The precipProbability
     */
    public Double getPrecipProbability() {
        return precipProbability;
    }

    /**
     * 
     * @param precipProbability
     *     The precipProbability
     */
    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    /**
     * 
     * @return
     *     The precipIntensityError
     */
    public Double getPrecipIntensityError() {
        return precipIntensityError;
    }

    /**
     * 
     * @param precipIntensityError
     *     The precipIntensityError
     */
    public void setPrecipIntensityError(Double precipIntensityError) {
        this.precipIntensityError = precipIntensityError;
    }

    /**
     * 
     * @return
     *     The precipType
     */
    public String getPrecipType() {
        return precipType;
    }

    /**
     * 
     * @param precipType
     *     The precipType
     */
    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

}
