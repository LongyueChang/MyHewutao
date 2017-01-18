package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.HomepageMessage;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class MessageAdapter extends RecyclerView.Adapter{

    private List<HomepageMessage.MsgBean> data;
    private Context mContext;

    public MessageAdapter(List<HomepageMessage.MsgBean> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.message_item,parent,false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        if(position == 0){
            viewHolder.notify.setVisibility(View.VISIBLE);
        }else{
            viewHolder.notify.setVisibility(View.GONE);
        }
        viewHolder.create_time.setText(data.get(position).getCreate_time());
        viewHolder.message.setText(data.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView message,create_time,notify;
        public MyViewHolder(View itemView) {
            super(itemView);
            notify = (TextView) itemView.findViewById(R.id.notify_message);
            message = (TextView) itemView.findViewById(R.id.message_message_item);
            create_time = (TextView) itemView.findViewById(R.id.create_time);
        }
    }
}
