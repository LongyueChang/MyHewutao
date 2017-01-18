package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.Find_Tuijian;
import com.tmfortm.shihuo.ui.DetailMessageActivity;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by my on 2016/12/13.
 */
public class FindRecyclerAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private List<Find_Tuijian> recycler;
    private Context context;
    public FindRecyclerAdapter(List<Find_Tuijian> recycler, Context context) {
            this.recycler=recycler;
            this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate=null;
//        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
//            inflate = LayoutInflater.from(context).inflate(R.layout.find_recycler_item2, parent, false);
//
//        }else{

            inflate = LayoutInflater.from(context).inflate(R.layout.find_recycler_item, parent, false);
//        }

        FindRecyclerViewHolder viewHolder=new FindRecyclerViewHolder(inflate);
        inflate.setOnClickListener(this);
        return viewHolder;
    }
    private int currentIndex=0;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        FindRecyclerViewHolder recyclerViewHolder = (FindRecyclerViewHolder) holder;

        Find_Tuijian.DataBean data = recycler.get(position).getData();
        recyclerViewHolder.mFind_recycler_item_description.setText(data.getTitle());
        recyclerViewHolder.mFind_recycler_item_name.setText(data.getAuthor_name());
        recyclerViewHolder.mFind_recycler_item_time.setText(data.getDate());
        recyclerViewHolder.mFind_recycler_item_dianzanNUm.setText("111");
        recyclerViewHolder.mFind_recycler_item_message_num.setText("222");
        recyclerViewHolder.mFind_recycler_item_simpledra.setImageURI(data.getAvatar());
        recyclerViewHolder.mFind_recycler_item_img.setImageURI(data.getImg());
        currentIndex=position;
    }

    @Override
    public int getItemCount() {
        return recycler!=null?recycler.size():0;
    }

    @Override
    public void onClick(View v) {
        Find_Tuijian.DataBean data = recycler.get(currentIndex).getData();
        String href = data.getHref();
        Intent intent=new Intent(context,DetailMessageActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("href",href);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static class FindRecyclerViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView mFind_recycler_item_simpledra;
        private final TextView mFind_recycler_item_name;
        private final TextView mFind_recycler_item_time;
        private final TextView mFind_recycler_item_description;
        private final SimpleDraweeView mFind_recycler_item_img;
        private final ImageView mFind_recycler_item_dianzan;
        private final TextView mFind_recycler_item_dianzanNUm;
        private final ImageView mFind_recycler_item_message;
        private final TextView mFind_recycler_item_message_num;

        public FindRecyclerViewHolder(View itemView) {
            super(itemView);
            mFind_recycler_item_simpledra = (SimpleDraweeView) itemView.findViewById(R.id.find_recycler_item_simpledra);
            mFind_recycler_item_name = (TextView) itemView.findViewById(R.id.find_recycler_item_name);
            mFind_recycler_item_time = (TextView) itemView.findViewById(R.id.find_recycler_item_time);
            mFind_recycler_item_description = (TextView) itemView.findViewById(R.id.find_recycler_item_description);
            mFind_recycler_item_img = (SimpleDraweeView) itemView.findViewById(R.id.find_recycler_item_img);
            mFind_recycler_item_dianzan = (ImageView) itemView.findViewById(R.id.find_recycler_item_dianzan);
            mFind_recycler_item_dianzanNUm = (TextView) itemView.findViewById(R.id.find_recycler_item_dianzanNUm);
            mFind_recycler_item_message = (ImageView) itemView.findViewById(R.id.find_recycler_item_message);
            mFind_recycler_item_message_num = (TextView) itemView.findViewById(R.id.find_recycler_item_message_num);
        }
    }
}