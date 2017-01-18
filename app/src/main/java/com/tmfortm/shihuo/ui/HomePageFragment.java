package com.tmfortm.shihuo.ui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.GetChars;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.facebook.common.logging.LoggingDelegate;
import com.sdk.pay.demo.PayDemoActivity;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.HomepageAdapter;
import com.tmfortm.shihuo.beans.HomepageRecommend;
import com.tmfortm.shihuo.beans.HomepageTop;
import com.tmfortm.shihuo.fragments.HomepageTopFragment;
import com.tmfortm.shihuo.uri.Constans;
import com.tmfortm.shihuo.widget.BetterRecyclerView;
import com.tmfortm.shihuo.widget.FeedRootRecyclerView;
import com.tmfortm.shihuo.widget.MyHeader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {

//    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipe;
    private TextView search;
    private LinearLayout message;

    private List<HomepageRecommend.DataBeanX> data = new ArrayList<>();
    private HomepageAdapter mAdapter;
    private LinearLayout mLinearLayout;

    public HomePageFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_home_page, container, false);
        initView(ret);
        initSwipe();
        initData();
        initRecyclerView();
        return ret;
    }

    private void initSwipe() {
        swipe.setColorSchemeResources(R.color.blue);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                initData();
            }
        });

    }

    private void initRecyclerView() {
        final LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layout);
        final MyHeader header = MyHeader.fromXml(getContext(), R.layout.home_header);
        getFragmentManager().beginTransaction().add(R.id.container_homepage,new HomepageTopFragment(),null).commit();
        header.attachTo(mRecyclerView);
        mAdapter = new HomepageAdapter(data,getContext());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new HomepageAdapter.OnClickListener() {
            @Override
            public void callback(View v) {
//                int position = mRecyclerView.getChildAdapterPosition(v);
//                Intent intent = new Intent(getContext(),WebActivityTwo.class);
//                String href = data.get(position).getData().getHref();
//                href = "www.shihuo.cn?route=haitaoDetail&id=245547#%7B%22from%22%3A%22shihuo%3A%2F%2F";
//                intent.putExtra("path", href);
//                getContext().startActivity(intent);
                Intent intent = new Intent(getContext(), PayDemoActivity.class);
                intent.putExtra("price","0.01");
                intent.putExtra("subject","galaxy s7 edge");
                intent.putExtra("describe","运行极度流畅，还会自带爆炸");
                getContext().startActivity(intent);
            }
        });

        final int[] scrollY = {0};
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollY[0] +=dy;
                if(scrollY[0]<30){
                    mLinearLayout.setBackgroundColor(Color.parseColor("#00ffffff"));
                }else if(scrollY[0]<60){
                    mLinearLayout.setBackgroundColor(Color.parseColor("#22ff0000"));
                }else if(scrollY[0]<90){
                    mLinearLayout.setBackgroundColor(Color.parseColor("#44ff0000"));
                }else if(scrollY[0]<120){
                    mLinearLayout.setBackgroundColor(Color.parseColor("#66ff0000"));
                }else if(scrollY[0]<150){
                    mLinearLayout.setBackgroundColor(Color.parseColor("#88ff0000"));
                }else if(scrollY[0]<180){
                    mLinearLayout.setBackgroundColor(Color.parseColor("#aaff0000"));
                }else if(scrollY[0]<210){
                    mLinearLayout.setBackgroundColor(Color.parseColor("#ccff0000"));
                }else{
                    mLinearLayout.setBackgroundColor(Color.parseColor("#ffff0000"));
                }
            }
        });
    }

    private void initData() {
        RequestParams entity2 = new RequestParams(Constans.HOME_PAGE_RECOMMEND);
        x.http().get(entity2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                HomepageRecommend hr = JSON.parseObject(result,HomepageRecommend.class);
                data.addAll(hr.getData());
                mAdapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
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
    private void initView(View ret) {
        mRecyclerView = (RecyclerView) ret.findViewById(R.id.recycler_homepage);
        message = (LinearLayout) ret.findViewById(R.id.message_fragment_homepage);
        search = (TextView) ret.findViewById(R.id.search_fragment_homepage);
        mLinearLayout = (LinearLayout) ret.findViewById(R.id.homepage_search);
        swipe = (SwipeRefreshLayout) ret.findViewById(R.id.swipe);
        message.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.message_fragment_homepage://消息的点击事件
                intent.setClass(getContext(),HomePageMessageActivity.class);
                break;
            case R.id.search_fragment_homepage://查询的点击事件
                intent.setClass(getContext(),HomepageSearchActiity.class);
                break;
        }
        getContext().startActivity(intent);
    }
}
