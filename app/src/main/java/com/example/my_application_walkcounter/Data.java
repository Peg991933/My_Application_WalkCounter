package com.example.my_application_walkcounter;

public class Data {
   private long timestamp;
    private float accx;
    private float accy;
    private float accz;

    public long getTimestamp() {
      return timestamp;
   }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getAccx() {
        return accx;
    }

    public void setAccx(float accx) {
        this.accx = accx;
    }

    public float getAccy() {
        return accy;
    }

    public void setAccy(float accy) {
        this.accy = accy;
    }

    public float getAccz() {
        return accz;
    }

    public void setAccz(float accz) {
        this.accz = accz;
    }

    public Data(long timestamp, float accx, float accy, float accz) {
        this.timestamp = timestamp;
        this.accx = accx;
        this.accy = accy;
        this.accz = accz;
    }

    @Override
    public String toString() {
        return "Data{" +
               "timestamp=" + timestamp +
                ", accx=" + accx +
                ", accy=" + accy +
                ", accz=" + accz +
                '}';
    }
}
