package com.example.paycount;

import android.util.Log;

import java.io.Serializable;
import java.util.UUID;


public class RecordBean  implements Serializable {

    public static String TAG = "RecordBean";
    public enum RecordType{
        RECORD_TYPE_IN,RECORD_TYPE_OUT
    }

    public RecordBean(double amount) {
        this.amount = amount;
    }

    public double amount;
    public RecordType type;
    public String category;
    public String remark;
    public String date;
    public long timeStamp;


    public String uuid;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {

        if(this.type == RecordType.RECORD_TYPE_OUT){
            return 1;
        }else {
            return 2;
        }
    }

    public void setType(int type) {
        if(type == 1){
            this.type = RecordType.RECORD_TYPE_OUT;

        }else {
            this.type = RecordType.RECORD_TYPE_IN;
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public RecordBean(){
        uuid= UUID.randomUUID().toString();


        Log.d(TAG,uuid);


        timeStamp = System.currentTimeMillis();//197011-今




        Log.d(TAG,timeStamp+"");
        Log.d(TAG,timeStamp+"这里这里"+DateUtil.getFormatedTime(timeStamp));


        date = DateUtil.getFormatedDate();


        Log.d(TAG,date+"这里这里");


    }
}
