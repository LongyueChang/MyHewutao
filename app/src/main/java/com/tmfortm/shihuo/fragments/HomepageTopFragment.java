package com.tmfortm.shihuo.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.GridAdapter;
import com.tmfortm.shihuo.adapters.HomepagerTopAdapter;
import com.tmfortm.shihuo.beans.HomepageTop;
import com.tmfortm.shihuo.ui.HomepageSportsActivity;
import com.tmfortm.shihuo.ui.TodayBenefitActivity;
import com.tmfortm.shihuo.ui.TuanGouActivity;
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
public class HomepageTopFragment extends Fragment implements View.OnClickListener {

    private HomepageTop mHt;
    private ViewPager mViewPager;
    private RecyclerView mGridView;
    private TextView titleOne,titleTwo,titleThree;
    private TextView subTitleOne,subTitleTwo,subTitleThree;
    private SimpleDraweeView frescoOne,frescoTwo,frescoThree,fresco;
    private HorizontalScrollView mScrollView;
    private LinearLayout one,two,three;
    private int position = 0;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if(this.hasMessages(1)){
                        removeMessages(1);
                    }
                    position++;
                    mViewPager.setCurrentItem(position);
                    this.sendEmptyMessageDelayed(1,3000);
                    break;
                case 2:
                    if(hasMessages(1)) {
                        removeMessages(1);
                    }
                    break;
                case 3:
                    position = msg.arg1;
                    this.sendEmptyMessageDelayed(1,3000);
                    break;
            }
        }
    };
    public HomepageTopFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_homepage_top, container, false);
        initView(ret);
        initData();
        return ret;
    }

    private void initView(View ret) {
        mViewPager = (ViewPager) ret.findViewById(R.id.vp_fragment_homepage_top);
        mGridView = (RecyclerView) ret.findViewById(R.id.rv_fragment_homepage_top);
        titleOne = (TextView) ret.findViewById(R.id.title_horiadapter_item);
        titleTwo = (TextView) ret.findViewById(R.id.title2_horiadapter_item);
        titleThree = (TextView) ret.findViewById(R.id.title3_horiadapter_item);
        subTitleOne = (TextView) ret.findViewById(R.id.subTitle_horiadapter_item);
        subTitleTwo = (TextView) ret.findViewById(R.id.subTitle2_horiadapter_item);
        subTitleThree = (TextView) ret.findViewById(R.id.subTitle3_horiadapter_item);
        frescoOne = (SimpleDraweeView) ret.findViewById(R.id.fresco_horiadapter_item);
        frescoTwo = (SimpleDraweeView) ret.findViewById(R.id.fresco2_horiadapter_item);
        frescoThree = (SimpleDraweeView) ret.findViewById(R.id.fresco3_horiadapter_item);
        fresco = (SimpleDraweeView) ret.findViewById(R.id.fresco_fragment_homepage_top);
        mScrollView = (HorizontalScrollView) ret.findViewById(R.id.hs_fragment_homepage_top);
        one = (LinearLayout) ret.findViewById(R.id.hori_one);
        one.setOnClickListener(this);
        two = (LinearLayout) ret.findViewById(R.id.hori_two);
        two.setOnClickListener(this);
        three = (LinearLayout) ret.findViewById(R.id.hori_three);
        three.setOnClickListener(this);
    }

    private void initData() {
        RequestParams entity = new RequestParams(Constans.HOME_PAGE_TOP);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mHt = JSON.parseObject(result,HomepageTop.class);
                initViewPager();
                initRecyclerGrid();
                initHoriView();
                initSimpleDraweeView();
                initHorizontalScrollView();
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

    private void initHorizontalScrollView() {
        final List<HomepageTop.DataBean.TopicBean> topic = mHt.getData().getTopic();
        float density = getResources().getDisplayMetrics().density;
        for (int i = 0; i < topic.size(); i++) {
            SimpleDraweeView fresco = new SimpleDraweeView(getContext());
            fresco.setImageURI(topic.get(i).getImg_url());
            fresco.setScaleType(ImageView.ScaleType.FIT_XY);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    getResources().getDisplayMetrics().widthPixels/3*2, LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins((int)(10*density),0,0,(int)(10*density));
            fresco.setLayoutParams(params);
            final int finalI = i;
            fresco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),WebViewActivity.class);
                    intent.putExtra("path",topic.get(finalI).getUrl());
                    getContext().startActivity(intent);
                }
            });
            LinearLayout ll = (LinearLayout) mScrollView.getChildAt(0);
            ll.addView(fresco);
        }

    }

    private void initSimpleDraweeView() {
        final HomepageTop.DataBean.AdvertisementBean advertisement = mHt.getData().getAdvertisement();
        String img_url = advertisement.getImg_url();
        if (img_url != null) {
            fresco.setImageURI(img_url);
            fresco.setVisibility(View.VISIBLE);
        }else{
            fresco.setVisibility(View.GONE);
        }
        fresco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),WebViewActivity.class);
                intent.putExtra("path",advertisement.getUrl());
                getContext().startActivity(intent);
            }
        });
    }

    private void initHoriView() {
        List<HomepageTop.DataBean.ChannelBean> data = new ArrayList<>();
        data.addAll(mHt.getData().getChannel());
        for (int i = 0; i < data.size(); i++) {
            HomepageTop.DataBean.ChannelBean channelBean = data.get(i);
            if(i == 0){
                titleOne.setText(channelBean.getTitle());
                subTitleOne.setText(channelBean.getSub_title());
                frescoOne.setImageURI(channelBean.getImg_url());
            }else if(i==1){
                titleTwo.setText(channelBean.getTitle());
                subTitleTwo.setText(channelBean.getSub_title());
                frescoTwo.setImageURI(channelBean.getImg_url());
            }else{
                titleThree.setText(channelBean.getTitle());
                subTitleThree.setText(channelBean.getSub_title());
                frescoThree.setImageURI(channelBean.getImg_url());
            }
        }
    }

    private void initRecyclerGrid() {
        List<HomepageTop.DataBean.ZoneBean> data = new ArrayList<>();
        data.addAll(mHt.getData().getZone());
        GridLayoutManager layout = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position == 0){
                    return 2;
                }else{
                    return 1;
                }
            }
        });
        mGridView.setLayoutManager(layout);
        GridAdapter adapter = new GridAdapter(data,getContext());
        mGridView.setAdapter(adapter);


        adapter.setOnClickListener(new GridAdapter.OnClickListener() {
            @Override
            public void callback(View view) {
                int position = mGridView.getChildAdapterPosition(view);
                Intent intent = new Intent(getContext(), HomepageSportsActivity.class);
                intent.putExtra("position",position);
                getContext().startActivity(intent);
            }
        });
    }

    private void initViewPager() {

        ArrayList<SimpleDraweeView> list = new ArrayList<>();

        final List<HomepageTop.DataBean.BannerBean> banner = mHt.getData().getBanner();
        for (int i = 0; i < banner.size(); i++) {
            SimpleDraweeView fresco = new SimpleDraweeView(getContext());
            fresco.setImageURI(banner.get(i).getImg_url());
            final int finalI = i;
            fresco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("path",banner.get(finalI).getUrl());
                    getContext().startActivity(intent);
                }
            });
            list.add(fresco);
        }
        HomepagerTopAdapter adapter = new HomepagerTopAdapter(list);
        mViewPager.setAdapter(adapter);
//        mViewPager.setCurrentItem(position);
        mHandler.sendEmptyMessageDelayed(1,3000);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.sendMessage(Message.obtain(mHandler,3,position,0));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_DRAGGING){
                    mHandler.sendEmptyMessage(2);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.hori_one:
                intent.setClass(getContext(),TodayBenefitActivity.class);
                break;
            case R.id.hori_two:
                intent.setClass(getContext(),TuanGouActivity.class);
                break;
            case R.id.hori_three:
                break;
        }
        getContext().startActivity(intent);
    }
}
