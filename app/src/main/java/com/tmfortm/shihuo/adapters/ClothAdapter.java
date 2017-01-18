package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.EquipCloth;
import com.tmfortm.shihuo.widget.MyHeader;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15 0015.
 */
public class ClothAdapter extends RecyclerView.Adapter{

    private List<EquipCloth.DataBean.InfoBean> data;
    private Context mContext;

    public ClothAdapter(List<EquipCloth.DataBean.InfoBean> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.cloth_item,parent,false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        EquipCloth.DataBean.InfoBean infoBean = data.get(position);
        myViewHolder.fresco.setImageURI(infoBean.getPic());
        myViewHolder.hits.setText("热度:"+infoBean.getHits());
        myViewHolder.price.setText(infoBean.getPrice());
        List<String> tag_name = infoBean.getTag_name();
        if (tag_name.size()>0) {
            myViewHolder.tag_name.setText(tag_name.get(0));
            myViewHolder.tag_name.setVisibility(View.VISIBLE);
        }else{
            myViewHolder.tag_name.setVisibility(View.GONE);
        }
        myViewHolder.intro.setText(infoBean.getIntro());
        String name = infoBean.getName();
        if(name.length()>20) {
            myViewHolder.name.setText(name.substring(0,20)+"...");
        }else {
            myViewHolder.name.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView fresco;
        private TextView name,intro,tag_name,price,hits;
        public MyViewHolder(View itemView) {
            super(itemView);
            fresco = (SimpleDraweeView) itemView.findViewById(R.id.pic_cloth_item);
            name = (TextView) itemView.findViewById(R.id.name_cloth_item);
            intro = (TextView) itemView.findViewById(R.id.intro_cloth_item);
            tag_name = (TextView) itemView.findViewById(R.id.tag_name_cloth_item);
            price = (TextView) itemView.findViewById(R.id.price_cloth_item);
            hits = (TextView) itemView.findViewById(R.id.hits_cloth_item);
        }
    }
}
