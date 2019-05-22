package com.example.paycount;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.LinkedList;

public class MainViewPagerAdapter  extends FragmentPagerAdapter {

    private static final String TAG = "FragmentPagerAdapter";



    LinkedList<MainFragment> fragments = new LinkedList<>();
    LinkedList<String> dates = new LinkedList<>();



    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);

      //  dates = GlobalUtil.getInstance().databaseHelper.getAvaliableDate();

        initFragments();
    }


    public void initFragments(){


     //   dates.add("dddhhhhhhhhhhdd");
      //  dates.add("ddggggggggggd");
      //  dates.add("dsdssssssssssssdd");
     //   dates.add("dddddddssssssd");
//        Log.d(TAG, "initFragments: dddddddddddddddddddddddddddddddddddddddddddddd");



        dates = GlobalUtil.getInstance().databaseHelper.getAvaliableDate();



        if (!dates.contains(DateUtil.getFormatedDate())){
            dates.addLast(DateUtil.getFormatedDate());
        }


        for (String date : dates){
            MainFragment fragment = new MainFragment(date);
            fragments.add(fragment);


        }


    }


    public void reload(){
//        fragments.clear();
//        dates.clear();
//        dates = GlobalUtil.getInstance().databaseHelper.getAvaliableDate();
//        for (String date : dates){
//            MainFragment fragment = new MainFragment(date);
//            fragments.add(fragment);
//
//        }

        for (MainFragment fragment : fragments){
            fragment.reload();
        }

      //  notifyDataSetChanged();

    }




    public int getLastIndex(){
        return fragments.size()-1;

    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }



    public String getDateStr(int index){
        return dates.get(index);
    }


    public int getTotalCost(int index){
        int amount = fragments.get(index).getTotalCost();
        return amount;
    }
}
