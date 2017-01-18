package com.tmfortm.shihuo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.ClothAdapter;
import com.tmfortm.shihuo.beans.EquipCloth;
import com.tmfortm.shihuo.beans.TodayBenefit;
import com.tmfortm.shihuo.uri.Constans;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ShoeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<EquipCloth.DataBean.InfoBean> data = new ArrayList<>();
    private ClothAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        int tag = intent.getIntExtra("tag",-1);
        initView();
        initData(id,tag);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter = new ClothAdapter(data,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_shoe_activity);
    }

    private void initData(int id, int tag) {
        if(tag == -1){
            return;
        }
        if(tag == 0){
            RequestParams entity = new RequestParams(Constans.EQUIP_PATH_ONE[id]);
            x.http().get(entity, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    EquipCloth ec = JSON.parseObject(result,EquipCloth.class);
                    data.addAll(ec.getData().getInfo());
                    mAdapter.notifyDataSetChanged();
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
        }else if(tag == 1){
            RequestParams entity = new RequestParams(Constans.EQUIP_PATH_TWO[id]);
            x.http().get(entity, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    EquipCloth ec = JSON.parseObject(result,EquipCloth.class);
                    data.addAll(ec.getData().getInfo());
                    mAdapter.notifyDataSetChanged();
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
        }else{
            RequestParams entity = new RequestParams(Constans.EQUIP_PATH_THREE[id]);
            x.http().get(entity, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    EquipCloth ec = JSON.parseObject(result,EquipCloth.class);
                    data.addAll(ec.getData().getInfo());
                    mAdapter.notifyDataSetChanged();
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
    }
}
