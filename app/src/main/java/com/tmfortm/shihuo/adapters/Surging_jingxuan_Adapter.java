package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.Surging_Context;

import org.w3c.dom.Text;

import java.util.List;

import static com.alipay.sdk.app.statistic.c.r;

/**
 * Created by my on 2016/12/15.
 */
public class Surging_jingxuan_Adapter extends RecyclerView.Adapter {
    private List<Surging_Context> surgingContextList;
    private Context mContext;
    public Surging_jingxuan_Adapter(List<Surging_Context> surgingContextList, Context context) {
        this.surgingContextList=surgingContextList;
        this.mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View ret=LayoutInflater.from(mContext).inflate(R.layout.surging_jingxuan_recycler_item,parent,false);

        JingxuanViewHolder jingxuanViewHolder=new JingxuanViewHolder(ret);
        //ret.setOnLongClickListener(this);
        return jingxuanViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        JingxuanViewHolder viewHolder = (JingxuanViewHolder) holder;
        Surging_Context surging_context = surgingContextList.get(position);
        viewHolder.mSurging_jingxuan_recycler_item_pic_one.setImageURI(surging_context.getImg());
        viewHolder.mSurging_jingxuan_recycler_item_name_one.setText(surging_context.getTitle());
        viewHolder.mSurging_jingxuan_recycler_item_price_one.setText("￥"+surging_context.getPrice());
        viewHolder.mSurging_jingxuan_recycler_item_source_one.setText(surging_context.getBusiness());

       /* Surging_Context surging_context_two = surgingContextList.get(position*2+1);
        viewHolder.mSurging_jingxuan_recycler_item_pic_two.setImageURI(surging_context_two.getImg());
        viewHolder.mSurging_jingxuan_recycler_item_name_two.setText(surging_context_two.getTitle());
        viewHolder.mSurging_jingxuan_recycler_item_price_two.setText("￥"+surging_context_two.getPrice());
        viewHolder.mSurging_jingxuan_recycler_item_source_two.setText(surging_context_two.getBusiness());*/
    }

    @Override
    public int getItemCount() {
        return surgingContextList!=null?surgingContextList.size():0;
    }
    private static class JingxuanViewHolder extends RecyclerView.ViewHolder{


        private  SimpleDraweeView mSurging_jingxuan_recycler_item_pic_one;
        private  SimpleDraweeView mSurging_jingxuan_recycler_item_pic_two;
        private  TextView mSurging_jingxuan_recycler_item_name_one;
        private  TextView mSurging_jingxuan_recycler_item_name_two;
        private  TextView mSurging_jingxuan_recycler_item_price_one;
        private  TextView mSurging_jingxuan_recycler_item_price_two;
        private  TextView mSurging_jingxuan_recycler_item_source_one;
        private  TextView mSurging_jingxuan_recycler_item_source_two;

        public JingxuanViewHolder(View itemView) {
            super(itemView);
            mSurging_jingxuan_recycler_item_pic_one = (SimpleDraweeView) itemView.findViewById(R.id.surging_jingxuan_recycler_item_pic_one);
          //  mSurging_jingxuan_recycler_item_pic_two = (SimpleDraweeView) itemView.findViewById(R.id.surging_jingxuan_recycler_item_pic_two);

            mSurging_jingxuan_recycler_item_name_one = (TextView) itemView.findViewById(R.id.surging_jingxuan_recycler_item_name_one);
           // mSurging_jingxuan_recycler_item_name_two = (TextView) itemView.findViewById(R.id.surging_jingxuan_recycler_item_name_two);
            mSurging_jingxuan_recycler_item_price_one = (TextView) itemView.findViewById(R.id.surging_jingxuan_recycler_item_price_one);
           // mSurging_jingxuan_recycler_item_price_two = (TextView) itemView.findViewById(R.id.surging_jingxuan_recycler_item_price_two);
            mSurging_jingxuan_recycler_item_source_one = (TextView) itemView.findViewById(R.id.surging_jingxuan_recycler_item_source_one);
          //  mSurging_jingxuan_recycler_item_source_two = (TextView) itemView.findViewById(R.id.surging_jingxuan_recycler_item_source_two);
        }
    }
}
