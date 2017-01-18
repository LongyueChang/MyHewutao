package com.tmfortm.shihuo.ui;

import android.graphics.Color;
import android.graphics.Xfermode;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.alibaba.fastjson.JSON;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.MessageAdapter;
import com.tmfortm.shihuo.beans.EquipTop;
import com.tmfortm.shihuo.beans.HomepageMessage;
import com.tmfortm.shihuo.uri.Constans;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class HomePageMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView previous,overflow;
    private RecyclerView mRecyclerView;
    private List<HomepageMessage.MsgBean> data = new ArrayList<>();
    private MessageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_message);
        initView();
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter = new MessageAdapter(data,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        RequestParams entity = new RequestParams(Constans.HOME_PAGE_MESSAGE);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                HomepageMessage hm = JSON.parseObject(result,HomepageMessage.class);
                data.addAll(hm.getMsg());
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
        previous = (ImageView) findViewById(R.id.previous_message_homepage);
        overflow = (ImageView) findViewById(R.id.overflow_message_homepage);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_message_activity);
        previous.setOnClickListener(this);
        overflow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.previous_message_homepage:
                this.finish();
                break;
            case R.id.overflow_message_homepage:
                initPopupWindow(v);
                break;
        }
    }

    private void initPopupWindow(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.message_popup,null);
        PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT
                , LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#11000000")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(v,0,0, Gravity.TOP);
    }

}
