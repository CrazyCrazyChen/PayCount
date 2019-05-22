package com.example.paycount;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment implements AdapterView.OnItemLongClickListener {


    static final String TAG ="MainFragment";
    public TextView textView;
    public ListView listView;
    public String date = "";
    public View rootView;

    public ListViewAdapter listViewAdapter;
    public LinkedList<RecordBean> records = new LinkedList<>();



    @SuppressLint("ValidFragment")

    public MainFragment(String date){
        this.date = date;



        //测试数据
      //   records.add(new RecordBean());
      //   records.add(new RecordBean());
      //   records.add(new RecordBean());
      //   records.add(new RecordBean());

        records = GlobalUtil.getInstance().databaseHelper.readRecrods(date);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main,container,false);
        initView();

        return rootView;


    }


    public void reload(){
        records = GlobalUtil.getInstance().databaseHelper.readRecrods(date);

        if (listViewAdapter == null){
            listViewAdapter = new ListViewAdapter(getActivity().getApplicationContext());

        }


        listViewAdapter.setData(records);

        listView.setAdapter(listViewAdapter);



        if (listViewAdapter.getCount() > 0 ){
            rootView.findViewById(R.id.no_record_layout).setVisibility(View.INVISIBLE);
        }



    }

    public void initView(){
        textView = rootView.findViewById(R.id.day_text);
        listView = rootView.findViewById(R.id.listView);

        textView.setText(date);





        listViewAdapter = new ListViewAdapter(getContext());

        listViewAdapter.setData(records);

        listView.setAdapter(listViewAdapter);

        if (listViewAdapter.getCount() > 0 ){
            rootView.findViewById(R.id.no_record_layout).setVisibility(View.INVISIBLE);
        }

      //  textView.setText(DateUtil.getDateTitle(date));

        listView.setOnItemLongClickListener(this);





    }


    public int getTotalCost(){
        double totalCost =  0;
        for (RecordBean record : records){
            if (record.getType() == 1){
                totalCost += record.getAmount();

            }
        }
        return (int)totalCost;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Log.d(TAG, "onItemLongClick: "+ position);

        showDialog(position);




        return false;
    }


    public void showDialog( final int index){
        final String[] options = {"删除","编辑"};

         final RecordBean selectedRecord = records.get(index);

        AlertDialog.Builder builder =  new AlertDialog.Builder(getContext());

        builder.create();
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "onClick: "+ options[which]);

                if (which == 0){
                    String uuid = selectedRecord.getUuid();
                    GlobalUtil.getInstance().databaseHelper.removeRecord(uuid);
                    reload();

                    GlobalUtil.getInstance().mainActivity.updateHeader();

                } else if (which == 1){
                    Intent intent = new Intent(getActivity(),AddRecordActivity.class);
                    Bundle extra = new Bundle();
                    extra.putSerializable("record",selectedRecord);
                    intent.putExtras(extra);

                    getActivity().startActivityForResult(intent,1);






                }



            }
        });
        builder.setNegativeButton("取消",null);
        builder.create().show();
    }
}


