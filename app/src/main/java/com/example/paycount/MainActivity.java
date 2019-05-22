package com.example.paycount;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{


    public static final String TAG  = "MainActivity";



    public ViewPager viewPager;

    public MainViewPagerAdapter pagerAdapter;

    public TickerView amountText;
    public TextView dateText;

    public int currentPagerPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        GlobalUtil.getInstance().setContext(getApplicationContext());


        GlobalUtil.getInstance().mainActivity = this;



        getSupportActionBar().setElevation(0);

        amountText = findViewById(R.id.amount_text);
        amountText.setCharacterList(TickerUtils.getDefaultNumberList());

        dateText = findViewById(R.id.date_text);


       // RecordBean r=new RecordBean();
        viewPager = findViewById(R.id.view_pager);


        pagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.notifyDataSetChanged();

        viewPager.setAdapter(pagerAdapter);


        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(pagerAdapter.getLastIndex());



        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
                startActivityForResult(intent,1);
            }
        });



   //     GlobalUtil.getInstance().setContext(getApplicationContext());



     //    GlobalUtil.getInstance().databaseHelper.removeRecord("2017-07-15");



     //   GlobalUtil.getInstance().databaseHelper.getAvaliableDate();





    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        pagerAdapter.reload();


        updateHeader();


    }



    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

//        String amount = String.valueOf(pagerAdapter.getTotalCost(i))+" ";
//        Log.d(TAG, "onPageSelected: "+ i+ pagerAdapter.getTotalCost(i)+" ");
//        amountText.setText(amount);
//
//        String date = pagerAdapter.getDateStr(i);
//        dateText.setText(DateUtil.getWeekDay(date));
//

        currentPagerPosition = i;
        updateHeader();




    }


    public void updateHeader(){

        String amount = String.valueOf(pagerAdapter.getTotalCost(currentPagerPosition))+" ";
        Log.d(TAG, "onPageSelected: "+ currentPagerPosition+ pagerAdapter.getTotalCost(currentPagerPosition)+" ");
        amountText.setText(amount);

        String date = pagerAdapter.getDateStr(currentPagerPosition);
        dateText.setText(DateUtil.getWeekDay(date));



    }



    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
