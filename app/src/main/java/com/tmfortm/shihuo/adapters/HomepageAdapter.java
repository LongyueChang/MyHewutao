package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.HomepageRecommend;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public class HomepageAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private List<HomepageRecommend.DataBeanX> data;
    private Context mContext;
    private OnClickListener mOnClickListener;

    public HomepageAdapter(List<HomepageRecommend.DataBeanX> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            itemView = LayoutInflater.from(mContext).inflate(R.layout.homepage_item2, parent, false);
        }else {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.homepage_item, parent, false);
        }
        itemView.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if(position == 0){
            myViewHolder.biaoti.setVisibility(View.VISIBLE);
        }else{
            myViewHolder.biaoti.setVisibility(View.GONE);
        }
        HomepageRecommend.DataBeanX.DataBean list = this.data.get(position).getData();
        myViewHolder.title.setText(list.getTitle());
        String merchant = list.getMerchant();
        if (merchant != null) {
            myViewHolder.where.setText(merchant);
            myViewHolder.where.setVisibility(View.VISIBLE);
        }else{
            myViewHolder.where.setVisibility(View.GONE);
        }
        myViewHolder.assess.setText(list.getColumn_name());
        myViewHolder.price.setText(list.getSubtitle());
        myViewHolder.fresco.setImageURI(list.getImg());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        mOnClickListener.callback(v);
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title,price,assess,where,biaoti;
        private SimpleDraweeView fresco;
        public MyViewHolder(View itemView) {
            super(itemView);
            fresco = (SimpleDraweeView) itemView.findViewById(R.id.fresco_homepage_recycler);
            title = (TextView) itemView.findViewById(R.id.title_homepage);
            price = (TextView) itemView.findViewById(R.id.price_homepage);
            where = (TextView) itemView.findViewById(R.id.where_homepage);
            assess = (TextView) itemView.findViewById(R.id.assess_homepage);
            biaoti = (TextView) itemView.findViewById(R.id.biaoti);
        }
    }

    public interface OnClickListener{
        void callback(View v);
    }
}
