package com.example.paycount;

import android.content.Context;

import java.util.LinkedList;

public class GlobalUtil {
//单例模式
    public static final String TAG = "GlobalUtil";
    public static GlobalUtil instance;





    public RecordDatabaseHelper databaseHelper;
    public Context context;
    public LinkedList<CategoryResBean> costRes = new LinkedList<>();
    public LinkedList<CategoryResBean> earnRes = new LinkedList<>();
    public MainActivity mainActivity;




    public static String[] costTitle = {"饭" , "水果" , "零食" , "日用品" , "购物" ,
            "聚会" , "保险" , "水电" , "月租" , "其它"};
    public static int[] costIconResBlack = {R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp};

    public static int[] costIconRes = {R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp};

    public static int[] earnIconRes = {R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp};

    public static int[] earnIconResBlack ={R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp,R.drawable.outline_add_box_black_18dp,
            R.drawable.outline_add_box_black_18dp};

    public static String[] earnTitle = {"工资", "基金" , "股票" , "期货" , "兼职" , "彩票", "暴富" };



    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;


        databaseHelper = new RecordDatabaseHelper(context,RecordDatabaseHelper.DB_NAME,null,1);



        for (int i=0; i < costTitle.length; i++){
            CategoryResBean res = new CategoryResBean();
            res.title = costTitle[i];
            res.resBlack = costIconResBlack[i];
            res.resWhite = costIconRes[i];
            costRes.add(res);

        }


        for (int i=0; i < earnTitle.length; i++){
            CategoryResBean res = new CategoryResBean();
            res.title = earnTitle[i];
            res.resBlack = earnIconResBlack[i];
            res.resWhite = earnIconRes[i];
            earnRes.add(res);

        }

    }


    public static GlobalUtil getInstance(){
        if(instance == null){
            instance = new GlobalUtil();
        }
        return  instance;

    }

    public int getResourceIcon(String category){
        for (CategoryResBean res : costRes){
            if (res.title.equals(category)){
                return res.resWhite;

            }
        }


        for (CategoryResBean res : earnRes){
            if (res.title.equals(category)){
                return res.resWhite;

            }
        }
        return costRes.get(0).resWhite;

    }






}
