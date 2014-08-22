package org.droidplanner.android.lib.parcelables;

import android.os.Parcel;
import android.os.Parcelable;

import org.droidplanner.core.drone.variables.Battery;
import org.droidplanner.core.model.Drone;

/**
 * Parcelable wrapper for a Battery object.
 */
public class ParcelableBattery implements Parcelable {

    private double battVolt = -1;
    private double battRemain = -1;
    private double battCurrent = -1;

    public ParcelableBattery(Battery battery){
        battVolt = battery.getBattVolt();
        battRemain = battery.getBattRemain();
        battCurrent = battery.getBattCurrent();
    }

    public double getBattVolt() {
        return battVolt;
    }

    public double getBattRemain() {
        return battRemain;
    }

    public double getBattCurrent() {
        return battCurrent;
    }

    public Battery getBattery(Drone drone){
        final Battery battery = new Battery(drone);
        return updateBattery(battery);
    }

    public Battery updateBattery(Battery reuse){
        reuse.setBatteryState(battVolt, battRemain, battCurrent);
        return reuse;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.battVolt);
        dest.writeDouble(this.battRemain);
        dest.writeDouble(this.battCurrent);
    }

    private ParcelableBattery(Parcel in) {
        this.battVolt = in.readDouble();
        this.battRemain = in.readDouble();
        this.battCurrent = in.readDouble();
    }

    public static final Parcelable.Creator<ParcelableBattery> CREATOR = new Parcelable
            .Creator<ParcelableBattery>() {
        public ParcelableBattery createFromParcel(Parcel source) {return new ParcelableBattery(source);}

        public ParcelableBattery[] newArray(int size) {return new ParcelableBattery[size];}
    };
}
