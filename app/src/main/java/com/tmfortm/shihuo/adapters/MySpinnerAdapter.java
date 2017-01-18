package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.TodaySpinner;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15 0015.
 */
public class MySpinnerAdapter extends RecyclerView.Adapter{

    private List<TodaySpinner.DataBean> data;
    private Context mContext ;

    public MySpinnerAdapter(List<TodaySpinner.DataBean> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.spinner_item,parent,false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).name.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.showName);
        }
    }
}
