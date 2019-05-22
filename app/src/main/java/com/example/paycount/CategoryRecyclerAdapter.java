package com.example.paycount;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;


public class  CategoryRecyclerAdapter  extends RecyclerView.Adapter<CategoryViewHolder>{

    public static String TAG="";
    public LayoutInflater mInfiater;
    public Context mContext;
    public LinkedList<CategoryResBean> cellList = GlobalUtil.getInstance().costRes;

    public String selected = "";



    public OnCategoryClickListener onCategoryClickListener;



    public String getSelected() {
        return selected;
    }



    public void setOnCategoryClickListener(OnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
    }



    public CategoryRecyclerAdapter(Context context){
        this.mContext = context;
        mInfiater = LayoutInflater.from(mContext);
        selected = cellList.get(0).title;


        Toast.makeText(context,"kkkkkkkkkkkk"+selected,Toast.LENGTH_LONG).show();

    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

      //  View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_category,viewGroup,false);

        View view = mInfiater.inflate(R.layout.cell_category,viewGroup,false);
        CategoryViewHolder myViewHolder = new CategoryViewHolder(view);
      //  Toast.makeText(mContext,"kkkkkkkkkkkk"+i ,Toast.LENGTH_LONG).show();
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {

        final CategoryResBean res = cellList.get(i);
     //   Log.d(TAG, "onBindViewHolder: "+i);

        categoryViewHolder.imageView.setImageResource(res.resBlack);
        //categoryViewHolder.textView.setText("fffffffffffffffffffffffff");
        categoryViewHolder.textView.setText(res.title);



        categoryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = res.title;
                notifyDataSetChanged();
                if (onCategoryClickListener != null){
                    onCategoryClickListener.onClick(res.title);
                }
            }
        });



        if(categoryViewHolder.textView.getText().toString().equals(selected)){
            categoryViewHolder.background.setBackgroundResource(R.drawable.bg_edit_text);

        }else {

            categoryViewHolder.background.setBackgroundResource(R.color.colorPrimary);
        }


    }




    public void changeType(RecordBean.RecordType type){
        if (type == RecordBean.RecordType.RECORD_TYPE_OUT){
            cellList = GlobalUtil.getInstance().costRes;

        }else {
            cellList = GlobalUtil.getInstance().earnRes;

        }

        selected = cellList.get(0).title;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return cellList.size();
    }




    public interface OnCategoryClickListener{
        void onClick(String category);

    }



}





class CategoryViewHolder extends RecyclerView.ViewHolder {


    RelativeLayout background;
    ImageView imageView;
    TextView textView;


    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        background = itemView.findViewById(R.id.cell_background);
        imageView = itemView.findViewById(R.id.imageView_category);
        textView = itemView.findViewById(R.id.textView_category);


    }
}
