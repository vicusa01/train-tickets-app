package com.example.trainticketbooking;

import android.os.Parcel;
import android.os.Parcelable;

public class TrainRVModel implements Parcelable{
    private String trainNum;
    private String src;
    private String dest;
    private String timings;
    private String price;
    private String TrainID;
    private String AvailableSeats;
    public TrainRVModel() {
    }

    public TrainRVModel(String trainNum, String src, String dest, String timings, String price, String trainID, String availableSeats ) {
        this.trainNum = trainNum;
        this.src = src;
        this.dest = dest;
        this.timings = timings;
        this.price = price;
        TrainID = trainID;
        AvailableSeats=availableSeats;
    }

    protected TrainRVModel(Parcel in) {
        trainNum = in.readString();
        src = in.readString();
        dest = in.readString();
        timings = in.readString();
        price = in.readString();
        TrainID = in.readString();
        AvailableSeats=in.readString();
    }

    public static final Creator<TrainRVModel> CREATOR = new Creator<TrainRVModel>() {
        @Override
        public TrainRVModel createFromParcel(Parcel in) {
            return new TrainRVModel(in);
        }

        @Override
        public TrainRVModel[] newArray(int size) {
            return new TrainRVModel[size];
        }
    };

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTrainID() {
        return TrainID;
    }

    public void setTrainID(String trainID) {
        TrainID = trainID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAvailableSeats() {
        return AvailableSeats;
    }

    public void setAvailableSeats(String availableSeats) {
        AvailableSeats = availableSeats;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(trainNum);
        parcel.writeString(src);
        parcel.writeString(dest);
        parcel.writeString(timings);
        parcel.writeString(price);
        parcel.writeString(TrainID);
        parcel.writeString(AvailableSeats);
    }
}
