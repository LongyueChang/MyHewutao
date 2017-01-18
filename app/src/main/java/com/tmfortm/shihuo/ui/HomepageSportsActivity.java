package com.tmfortm.shihuo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.SportRecommendAdapter;
import com.tmfortm.shihuo.beans.HomepageSport;
import com.tmfortm.shihuo.fragments.SportHeaderFragment;
import com.tmfortm.shihuo.widget.MyHeader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class HomepageSportsActivity extends AppCompatActivity {

    private String [] paths = {"http://www.shihuo.cn/app_swoole_zone/getNews?channel=vivo&clientCode=869643021398414&page=1&page_size=30&platform=android&timestamp=1479453240066&token=138c86bb7632aedfa5d78aa149c60c21&type=basketball&v=4.1.8",
                                "http://www.shihuo.cn/app_swoole_zone/getNews?channel=vivo&clientCode=869643021398414&page=1&page_size=30&platform=android&timestamp=1479452846531&token=e98a5aa9158a209411ab00d73bd658b2&type=running&v=4.1.8",
                                "http://www.shihuo.cn/app_swoole_zone/getNews?channel=vivo&clientCode=869643021398414&page=1&page_size=30&platform=android&timestamp=1479454291371&token=97f7966785f5a7432a423519b9d1f3c7&type=freestyle&v=4.1.8"};
    private RecyclerView mRecyclerView;
    private List<HomepageSport.DataBeanX> data = new ArrayList<>();
    private SportRecommendAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_sports);
        int position = getIntent().getIntExtra("position", 0);
        initView();
        initData(position);
        initRecyclerView(position);
    }

    private void initRecyclerView(int position) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        MyHeader header = MyHeader.fromXml(this,R.layout.sport_header);
        SportHeaderFragment fragment = new SportHeaderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.container_sport_header, fragment,null).commit();
        header.attachTo(mRecyclerView);
        mAdapter = new SportRecommendAdapter(data,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData(int position) {
        RequestParams entity = new RequestParams(paths[position]);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                HomepageSport hs = JSON.parseObject(result,HomepageSport.class);
                data.addAll(hs.getData());
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

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_sport_homepage);
    }
}
