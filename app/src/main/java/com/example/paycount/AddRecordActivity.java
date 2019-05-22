package com.example.paycount;

import android.annotation.SuppressLint;
import android.support.constraint.solver.GoalRow;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddRecordActivity extends AppCompatActivity implements View.OnClickListener,CategoryRecyclerAdapter.OnCategoryClickListener{


    public static String TAG = "AddRecordActivity";


    public String userInput = "";
    public TextView amountText;



    public RecyclerView recyclerView;
    public CategoryRecyclerAdapter adapter;

    public String category = "饭";
    public RecordBean.RecordType type = RecordBean.RecordType.RECORD_TYPE_OUT;
    public String remark = category;
    public EditText editText;
    public RecordBean record = new RecordBean();
    public boolean inEdit = false;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        findViewById(R.id.keyboard_one).setOnClickListener(this);
        findViewById(R.id.keyboard_two).setOnClickListener(this);
        findViewById(R.id.keyboard_three).setOnClickListener(this);
        findViewById(R.id.keyboard_four).setOnClickListener(this);
        findViewById(R.id.keyboard_five).setOnClickListener(this);
        findViewById(R.id.keyboard_six).setOnClickListener(this);
        findViewById(R.id.keyboard_seven).setOnClickListener(this);
        findViewById(R.id.keyboard_eight).setOnClickListener(this);
        findViewById(R.id.keyboard_nine).setOnClickListener(this);
       // findViewById(R.id.keyboard_dot).setOnClickListener(this);
        findViewById(R.id.keyboard_zero).setOnClickListener(this);




        amountText = findViewById(R.id.textView_amount);

        editText = findViewById(R.id.editText);
        editText.setText(remark);





        handleDot();
        handleTypeChange();

        handleBackspace();
        handleDone();


        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CategoryRecyclerAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.notifyDataSetChanged();



        adapter.setOnCategoryClickListener(this);

        RecordBean record = (RecordBean) getIntent().getSerializableExtra("record");

        if (record != null){
            Log.d(TAG, "onCreate: "+ record.getRemark());
            inEdit = true;
            this.record = record;

        }






    }

    public void handleDot(){
        findViewById(R.id.keyboard_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,". 被点击");

                if (!userInput.contains(".")){
                    userInput +=".";

                }


            }
        });
    }


    public void handleTypeChange(){
        findViewById(R.id.keyboard_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"type类型键");

                if (type == RecordBean.RecordType.RECORD_TYPE_OUT){
                    type = RecordBean.RecordType.RECORD_TYPE_IN;

                }else {
                    type = RecordBean.RecordType.RECORD_TYPE_OUT;
                }

                adapter.changeType(type);
                category = adapter.getSelected();



            }
        });

    }



    public void handleBackspace(){
        findViewById(R.id.keyboard_backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"backspace键");


                if(userInput.length() > 0 ){
                    userInput = userInput.substring(0,userInput.length()-1);
                }

                if(userInput.length() > 0 && userInput.charAt(userInput.length() - 1 ) == '.' ){
                    userInput = userInput.substring(0,userInput.length() - 1);

                }
                updateAmountText();
            }
        });

    }



    public void handleDone(){
        findViewById(R.id.keyboard_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"done 键");

                if (!userInput.equals("")){
                    double amount = Double.valueOf(userInput);
                    Log.d(TAG, "onClick: final str " + amount);



                    record.setAmount(amount);


                    if (type == RecordBean.RecordType.RECORD_TYPE_OUT){
                        record.setType(1);
                    }else {
                        record.setType(2);
                    }

                    record.setCategory(adapter.getSelected());
                    record.setRemark(editText.getText().toString());

                    if (inEdit){
                        GlobalUtil.getInstance().databaseHelper.editRecord(record.getUuid(),record);

                    }else {
                        GlobalUtil.getInstance().databaseHelper.addRecord(record);
                    }


                 //   GlobalUtil.getInstance().databaseHelper.addRecord(record);
                    finish();




                }else {

                    Toast.makeText(getApplicationContext(),"金额不正确",Toast.LENGTH_SHORT).show();

                }





            }
        });

    }



    @Override
    public void onClick(View v) {


        Button button = (Button) v;
        String input = button.getText().toString();

        Log.d(TAG,input);
       // userInput += input;
        Log.d(TAG,userInput);




        if (userInput.contains(".")){

            if(userInput.split("\\.").length == 1 || userInput.split("\\.")[1].length() < 2){
                userInput += input;


            }



        }else {
            userInput += input;

        }

        Log.d(TAG,"sub String "+ userInput);



        updateAmountText();



    }



    public void updateAmountText(){

        if(userInput.contains(".")){


            if (userInput.split("\\.").length == 1){
                amountText.setText(userInput+"00");

            }else if(userInput.split("\\.")[1].length() == 1){
                amountText.setText(userInput+"0");
            }else if (userInput.split("\\.")[1].length() == 2){
                amountText.setText(userInput);
            }
        }else {
            if(userInput.equals("")){
                amountText.setText("0.00");
            }else {
                amountText.setText(userInput+".00");
            }
        }
        Log.d(TAG,"sub String "+ userInput);

    }


    @Override
    public void onClick(String category) {
        this.category = category;
        Log.d(TAG, "onClick: "+category);

        editText.setText(category);




    }
}
