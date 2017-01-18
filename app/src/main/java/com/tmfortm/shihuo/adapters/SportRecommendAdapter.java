package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.EquipRecommend;
import com.tmfortm.shihuo.beans.HomepageSport;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class SportRecommendAdapter extends RecyclerView.Adapter{

    private List<HomepageSport.DataBeanX> data;
    private Context mContext;

    public SportRecommendAdapter(List<HomepageSport.DataBeanX> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemHolder = LayoutInflater.from(mContext).inflate(R.layout.equipadapter_item,parent,false);
        MyViewHolder holder = new MyViewHolder(itemHolder);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if(position == 0){
            myViewHolder.ll.setVisibility(View.VISIBLE);
        }else{
            myViewHolder.ll.setVisibility(View.GONE);
        }
        HomepageSport.DataBeanX.DataBean list = data.get(position).getData();
        myViewHolder.hits.setText(list.getHits());
        myViewHolder.merchant.setText(list.getMerchant());
        String tag_name = list.getTag_name();
        if (tag_name != null) {
            myViewHolder.tag_name.setText(tag_name);
            myViewHolder.tag_name.setVisibility(View.VISIBLE);
        }else{
            myViewHolder.tag_name.setVisibility(View.GONE);
        }
        myViewHolder.intro.setText(list.getIntro());
        String price = list.getPrice();
        if (price != null) {
            myViewHolder.price.setText("￥"+ price);
        }
        myViewHolder.title.setText(list.getTitle());
        myViewHolder.fresco.setImageURI(list.getImg());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout ll;
        private SimpleDraweeView fresco;
        private TextView title,intro,price,tag_name,merchant,hits;
        public MyViewHolder(View itemView) {
            super(itemView);
            fresco = (SimpleDraweeView) itemView.findViewById(R.id.fresco_equipadapter_item);
            title = (TextView) itemView.findViewById(R.id.title_equipadapter_item);
            intro = (TextView) itemView.findViewById(R.id.intro_equipadapter_item);
            price = (TextView) itemView.findViewById(R.id.price_equipadapter_item);
            tag_name = (TextView) itemView.findViewById(R.id.tag_name_equipadapter_item);
            merchant = (TextView) itemView.findViewById(R.id.merchant_equipadapter_item);
            hits = (TextView) itemView.findViewById(R.id.hits_equipadapter_item);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_equipadapter_item);
        }
    }
}
