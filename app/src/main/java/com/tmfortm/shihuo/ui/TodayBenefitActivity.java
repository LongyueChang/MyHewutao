package com.tmfortm.shihuo.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.MySpinnerAdapter;
import com.tmfortm.shihuo.adapters.TodayBenefitAdapter;
import com.tmfortm.shihuo.beans.TodayBenefit;
import com.tmfortm.shihuo.beans.TodaySpinner;
import com.tmfortm.shihuo.beans.TodayTop;
import com.tmfortm.shihuo.uri.Constans;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class TodayBenefitActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<TodayBenefit.DataBean> data = new ArrayList<>();
    private TodayBenefitAdapter mAdapter;
    private LinearLayout mTop,mSpinner;
    private RelativeLayout mTop2,mTop3;
    private View[] views = new View[3];
    private TodayTop mTt;
    private TodaySpinner mTs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_benefit);
        initView();
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter = new TodayBenefitAdapter(data,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        RequestParams entity = new RequestParams(Constans.HOME_PAGE_TODAY);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                TodayBenefit tb = JSON.parseObject(result,TodayBenefit.class);
                data.addAll(tb.getData());
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

        String path = "http://www.shihuo.cn/app_swoole_news/getAd?channel=vivo&clientCode=869643021398414&platform=android&timestamp=1479453393352&token=54b2a74666e70d431ef1917a53590d6d&v=4.1.8";
        RequestParams entity2 = new RequestParams(path);
        x.http().get(entity2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mTt = JSON.parseObject(result,TodayTop.class);
                initTodayTop();
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

        String str = "http://www.shihuo.cn/app_swoole_news/getStore?channel=vivo&clientCode=869643021398414&platform=android&timestamp=1479453393363&token=c5476c8274e5e8c396b017539bd9c923&type=1&v=4.1.8";
        RequestParams entity3 = new RequestParams(str);
        x.http().get(entity3, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mTs = JSON.parseObject(result,TodaySpinner.class);
                initSpinner();
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

    private void initSpinner() {
        TextView tv = (TextView) mSpinner.getChildAt(0);
        tv.setTag(1);
        tv.setOnClickListener(this);
    }

    private void initTodayTop() {
        List<TodayTop.DataBean> list = mTt.getData();
        for (int i = 0; i < 3; i++) {
            if(i==0){
                LinearLayout ll = (LinearLayout) views[i];
                TextView tv = (TextView) ll.getChildAt(0);
                tv.setText(list.get(i).getTitle());
                TextView tv2 = (TextView) ll.getChildAt(1);
                tv2.setText(list.get(i).getSub_title());
                SimpleDraweeView fresco = (SimpleDraweeView) ll.getChildAt(2);
                fresco.setImageURI(list.get(i).getImg_url());
            }else{
                RelativeLayout rl = (RelativeLayout) views[i];
                TextView tv = (TextView) rl.getChildAt(0);
                tv.setText(list.get(i).getTitle());
                TextView tv2 = (TextView) rl.getChildAt(1);
                tv2.setText(list.get(i).getSub_title());
                SimpleDraweeView fresco = (SimpleDraweeView) rl.getChildAt(2);
                fresco.setImageURI(list.get(i).getImg_url());
            }
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_today_activity);
        mTop = (LinearLayout) findViewById(R.id.today_top3);
        mTop2 = (RelativeLayout) findViewById(R.id.today_top_two);
        mTop3 = (RelativeLayout) findViewById(R.id.today_top_three);
        mSpinner = (LinearLayout) findViewById(R.id.ll__today_benefit);
        views[0]=mTop;
        views[1]=mTop2;
        views[2]=mTop3;
    }

    @Override
    public void onClick(View v) {
        Integer tag = (Integer) v.getTag();
        switch (tag){
            case 1:
                initPopupWindow(v);
                break;
        }
    }

    private void initPopupWindow(View v) {
        List<TodaySpinner.DataBean> data = mTs.getData();
        View view = LayoutInflater.from(this).inflate(R.layout.spinner,null);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_spinner);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        MySpinnerAdapter adapter = new MySpinnerAdapter(data,this);
        rv.setAdapter(adapter);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,300,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#11000000")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(v, 0,0,Gravity.BOTTOM);
    }
}
