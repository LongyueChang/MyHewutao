package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.Surging_Children_category;

import java.util.List;

/**
 * Created by my on 2016/12/16.
 */
public class HeaderModelAdapterOne extends RecyclerView.Adapter {
    private List<Surging_Children_category> childrenCategories;
    private Context context;
    public HeaderModelAdapterOne(List<Surging_Children_category> childrenCategories, Context context) {
                this.childrenCategories=childrenCategories;
                this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.surging_model_one,parent,false);
        MyHolderView myHolderView=new MyHolderView(view);
        return myHolderView;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolderView holderView = (MyHolderView) holder;
        holderView.mDraweeView.setImageURI(childrenCategories.get(position).getImg());
        holderView.mTextView.setText(childrenCategories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return childrenCategories!=null?childrenCategories.size():0;
    }
    private static class MyHolderView extends  RecyclerView.ViewHolder{

        private final SimpleDraweeView mDraweeView;
        private final TextView mTextView;

        public MyHolderView(View itemView) {
            super(itemView);
            mDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.surging_model_one_img);
            mTextView = (TextView) itemView.findViewById(R.id.surging_model_one_tv);

        }
    }


}
