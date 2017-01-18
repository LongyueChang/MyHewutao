package com.tmfortm.shihuo.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.EquipHeaderAdapter;
import com.tmfortm.shihuo.beans.EquipTop;
import com.tmfortm.shihuo.ui.WebViewActivity;
import com.tmfortm.shihuo.uri.Constans;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EquipHeaderFragment extends Fragment implements View.OnClickListener {

    
    private SimpleDraweeView channel_one,channel_two,channel_three;
    private EquipTop mEquipTop;
    private RecyclerView mRecyclerView;
    private List<EquipTop.DataBean.BasketballBean> data = new ArrayList<>();
    private List<EquipTop.DataBean.ChannelBean> mChannel;

    public EquipHeaderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_equip_header, container, false);
        initView(ret);
       // Log.d("flag", "---------onCreateView: ");
       // Log.d("flag", "---------onCreateView: ");
        initData();
        return ret;
    }

    private void initData() {
        RequestParams entity = new RequestParams(Constans.EQUIP_TOP);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mEquipTop = JSON.parseObject(result,EquipTop.class);
                initChannel();
                EquipTop.DataBean.BasketballBean basketball = mEquipTop.getData().getBasketball();
                EquipTop.DataBean.RunningBean running = mEquipTop.getData().getRunning();
                EquipTop.DataBean.FreestyleBean freestyle = mEquipTop.getData().getFreestyle();
                initRecyclerView(basketball,running,freestyle);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initRecyclerView(EquipTop.DataBean.BasketballBean basketball, EquipTop.DataBean.RunningBean running, EquipTop.DataBean.FreestyleBean freestyle) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        EquipHeaderAdapter adapter = new EquipHeaderAdapter(basketball,running,freestyle,getContext());
        mRecyclerView.setAdapter(adapter);
    }

    private void initChannel() {
        mChannel = mEquipTop.getData().getChannel();
        channel_one.setImageURI(mChannel.get(0).getImg());
        channel_two.setImageURI(mChannel.get(1).getImg());
        channel_three.setImageURI(mChannel.get(2).getImg());
        channel_one.setOnClickListener(this);
        channel_two.setOnClickListener(this);
        channel_three.setOnClickListener(this);
    }

    private void initView(View ret) {
        channel_one = (SimpleDraweeView) ret.findViewById(R.id.channel_one);
        channel_two = (SimpleDraweeView) ret.findViewById(R.id.channel_two);
        channel_three = (SimpleDraweeView) ret.findViewById(R.id.channel_three);
        mRecyclerView = (RecyclerView) ret.findViewById(R.id.rv_fragment_equip_header);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        switch (v.getId()){
            case R.id.channel_one:
                intent.putExtra("path",mChannel.get(0).getHref());
                break;
            case R.id.channel_two:
                intent.putExtra("path",mChannel.get(1).getHref());
                break;
            case R.id.channel_three:
                intent.putExtra("path",mChannel.get(2).getHref());
                break;
        }
    }
}
