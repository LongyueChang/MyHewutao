package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.TodayBenefit;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class TodayBenefitAdapter extends RecyclerView.Adapter{
    private List<TodayBenefit.DataBean> data;
    private Context mContext;

    public TodayBenefitAdapter(List<TodayBenefit.DataBean> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.today_item,parent,false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        TodayBenefit.DataBean dataBean = data.get(position);
        myViewHolder.title.setText(dataBean.getTitle());
        myViewHolder.subtitle.setText(dataBean.getSubtitle());
        myViewHolder.data.setText(dataBean.getDate());
        myViewHolder.orginal_type.setText(dataBean.getOrginal_type());
        myViewHolder.fresco.setImageURI(dataBean.getImg());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView fresco;
        private TextView title,subtitle,orginal_type,data;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.today_title);
            fresco = (SimpleDraweeView) itemView.findViewById(R.id.fresco_today);
            subtitle = (TextView) itemView.findViewById(R.id.today_subtitle);
            orginal_type = (TextView) itemView.findViewById(R.id.today_orginal_type);
            data = (TextView) itemView.findViewById(R.id.today_data);
        }
    }
}
