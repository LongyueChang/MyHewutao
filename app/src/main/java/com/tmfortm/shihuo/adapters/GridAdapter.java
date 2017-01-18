package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.HomepageTop;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public class GridAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;
    private List<HomepageTop.DataBean.ZoneBean> data;
    private Context mContext;
    private OnClickListener mOnClickListener;

    public GridAdapter(List<HomepageTop.DataBean.ZoneBean> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ONE){
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.gridadapter_item_one,parent,false);
            itemView.setOnClickListener(this);
            MyViewHodlerOne hodler = new MyViewHodlerOne(itemView);
            return hodler;
        }else {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.gridadapter_item_two,parent,false);
            itemView.setOnClickListener(this);
            MyViewHodlerTwo hodler = new MyViewHodlerTwo(itemView);
            return hodler;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if(type == TYPE_ONE){
            ((MyViewHodlerOne) holder).title.setText(data.get(position).getTitle());
            ((MyViewHodlerOne) holder).subTitle.setText(data.get(position).getSub_title());
            ((MyViewHodlerOne) holder).fresco.setImageURI(data.get(position).getImg_url());
        }else{
            ((MyViewHodlerTwo) holder).title.setText(data.get(position).getTitle());
            ((MyViewHodlerTwo) holder).subTitle.setText(data.get(position).getSub_title());
            ((MyViewHodlerTwo) holder).fresco.setImageURI(data.get(position).getImg_url());
        }
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_ONE;
        }else{
            return TYPE_TWO;
        }
    }

    @Override
    public void onClick(View v) {
        mOnClickListener.callback(v);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener{
        void callback(View view);
    }

    private static class MyViewHodlerOne extends RecyclerView.ViewHolder{
        private TextView title,subTitle;
        private SimpleDraweeView fresco;
        public MyViewHodlerOne(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_gridadapter_item_one);
            subTitle = (TextView) itemView.findViewById(R.id.subTitle_gridadapter_item_one);
            fresco = (SimpleDraweeView) itemView.findViewById(R.id.fresco_gridadapter_item_one);
        }
    }

    private static class MyViewHodlerTwo extends RecyclerView.ViewHolder{
        private TextView title,subTitle;
        private SimpleDraweeView fresco;
        public MyViewHodlerTwo(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_gridadapter_item_two);
            subTitle = (TextView) itemView.findViewById(R.id.subTitle_gridadapter_item_two);
            fresco = (SimpleDraweeView) itemView.findViewById(R.id.fresco_gridadapter_item_two);
        }
    }
}
