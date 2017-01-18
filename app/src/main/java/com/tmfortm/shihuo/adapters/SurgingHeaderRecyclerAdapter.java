package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.Surging_Special_column;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by my on 2016/12/15.
 */
public class SurgingHeaderRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Surging_Special_column> special_columnList;

    public SurgingHeaderRecyclerAdapter(Context context, List<Surging_Special_column> special_columnList) {
                this.context=context;
        this.special_columnList=special_columnList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.surging_header_recyclerview,parent,false);
        MyRecycleViewHolder viewHolder=new MyRecycleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyRecycleViewHolder viewHolder = (MyRecycleViewHolder) holder;
        Surging_Special_column surging_special_column = special_columnList.get(position);
        viewHolder.mSurging_jingxuan_header_recyclerview_bigpic.setImageURI(surging_special_column.getBanner().getImg_url());



        //View view=LayoutInflater.from(context).inflate(R.layout.surging_jingxuan_header_recyclerview_scroll_item,child,false);
        for (int i = 0; i < surging_special_column.getList().size(); i++) {
            LinearLayout child=new LinearLayout(context);
            child.setBackgroundColor(Color.WHITE);
            child.setPadding(5,5,5,5);
            child.setOrientation(LinearLayout.VERTICAL);
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(300, 400);
            child.setLayoutParams(params);
            SimpleDraweeView draweeView=new SimpleDraweeView(context);
            ViewGroup.LayoutParams param=new ViewGroup.LayoutParams(300,300);
            draweeView.setLayoutParams(param);
            Surging_Special_column.ListBean listBean = surging_special_column.getList().get(i);
            draweeView.setImageURI(listBean.getImg());
            child.addView(draweeView);

            TextView title=new TextView(context);
            title.setText(listBean.getName());
            ViewGroup.LayoutParams titleParam=new ViewGroup.LayoutParams(300,60);
            title.setTextColor(Color.BLACK);
            title.setTextSize(12);
            title.setLayoutParams(titleParam);
            child.addView(title);

            LinearLayout layout=new LinearLayout(context);
            ViewGroup.LayoutParams layParams=new ViewGroup.LayoutParams(300,50);
            layout.setLayoutParams(layParams);
            layout.setOrientation(LinearLayout.HORIZONTAL);

            TextView newPrice=new TextView(context);
            newPrice.setText("¥"+listBean.getPrice());
            ViewGroup.LayoutParams newParams=new ViewGroup.LayoutParams(150,100);
            newPrice.setLayoutParams(newParams);
            newPrice.setTextColor(Color.RED);
            newPrice.setTextSize(12);
            layout.addView(newPrice);


            TextView oldPrice=new TextView(context);
            oldPrice.setText("¥"+listBean.getOriginal_price());
            ViewGroup.LayoutParams oldParams=new ViewGroup.LayoutParams(150,100);
            oldPrice.setLayoutParams(oldParams);
            oldPrice.setGravity(Gravity.RIGHT);
            layout.addView(oldPrice);
            oldPrice.setTextColor(Color.BLACK);
            oldPrice.setTextSize(10);
            child.addView(layout);
            viewHolder.mSurging_jingxuan_header_recyclerview_scroll_linear.addView(child);
        }



        //child.addView(view);
        //viewHolder.mSurging_jingxuan_header_recyclerview_scroll.addView(child);

    }

    @Override
    public int getItemCount() {
        Log.d("flag", "----------->得到的数据为getItemCount:" +special_columnList.size());
        return special_columnList!=null?special_columnList.size():0;
    }
    private static class MyRecycleViewHolder extends RecyclerView.ViewHolder{


        private  SimpleDraweeView mSurging_jingxuan_header_recyclerview_bigpic;
        private  HorizontalScrollView mSurging_jingxuan_header_recyclerview_scroll;
        private final LinearLayout mSurging_jingxuan_header_recyclerview_scroll_linear;

        public MyRecycleViewHolder(View itemView) {
            super(itemView);
            mSurging_jingxuan_header_recyclerview_bigpic = (SimpleDraweeView) itemView.findViewById(R.id.surging_jingxuan_header_recyclerview_bigpic);

            mSurging_jingxuan_header_recyclerview_scroll = (HorizontalScrollView) itemView.findViewById(R.id.surging_jingxuan_header_recyclerview_scroll);
            mSurging_jingxuan_header_recyclerview_scroll_linear = (LinearLayout) itemView.findViewById(R.id.surging_jingxuan_header_recyclerview_scroll_linear);
        }
    }
}
