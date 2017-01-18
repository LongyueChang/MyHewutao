package com.tmfortm.shihuo.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.HomepageSportTop;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportHeaderFragment extends Fragment implements Animation.AnimationListener{

    private String[] path = {"http://www.shihuo.cn/app_swoole_zone/home?channel=vivo&clientCode=869643021398414&haojia_id=13&platform=android&timestamp=1479452712485&token=a6538ad4a73516de8f6a452d145abdec&type=basketball&v=4.1.8"
            ,"http://www.shihuo.cn/app_swoole_zone/home?channel=vivo&clientCode=869643021398414&haojia_id=496&platform=android&timestamp=1479452845791&token=f374c1f0353dcaef0bd2b4772a5764f8&type=running&v=4.1.8"
            ,"http://www.shihuo.cn/app_swoole_zone/home?channel=vivo&clientCode=869643021398414&haojia_id=19&platform=android&timestamp=1479454290443&token=a16ab862ded33eed89e0ea0df9174edb&type=freestyle&v=4.1.8"};

    private LinearLayout mLayout,mBrands,mHaojia,mListing,mLunbo,mLunboTwo;
    private HomepageSportTop mHst;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    mTranslate = AnimationUtils.loadAnimation(getContext(), R.anim.one_out);
                    mLunbo.startAnimation(mTranslate);
                    mTranslate.setAnimationListener(SportHeaderFragment.this);
                    this.sendEmptyMessageDelayed(2,4000);
                    break;
                case 2:
                    mTranslate1 = AnimationUtils.loadAnimation(getContext(), R.anim.one_out);
                    mLunboTwo.startAnimation(mTranslate1);
                    mTranslate1.setAnimationListener(SportHeaderFragment.this);
                    this.sendEmptyMessageDelayed(1,4000);
                    break;
            }
        }
    };
    private Animation mTranslate;
    private Animation mTranslate_two;
    private Animation mTranslate1;
    private Animation mTranslate_two1;

    public SportHeaderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_sport_header, container, false);
        Bundle arguments = getArguments();
        int position = 0;
        if (arguments != null) {
            position = arguments.getInt("position");
        }
        initView(ret);
        initData(position);
        return ret;
    }

    private void initView(View ret) {
        mLayout = (LinearLayout) ret.findViewById(R.id.category_fragment_sport_header);
        mBrands = (LinearLayout) ret.findViewById(R.id.brands_fragment_sport_header);
        mHaojia = (LinearLayout) ret.findViewById(R.id.scrollViewChild);
        mListing = (LinearLayout) ret.findViewById(R.id.scrollViewChildTwo);
        mLunbo = (LinearLayout) ret.findViewById(R.id.ll_lunbo);
        mLunboTwo = (LinearLayout) ret.findViewById(R.id.ll_lunbo_two);
    }

    private void initData(int position) {
        RequestParams entity = new RequestParams(path[position]);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mHst = JSON.parseObject(result,HomepageSportTop.class);
                initCategory();
                initBrands();
                initLunBo();
                initFirstScrollView();
                initSecondScrollView();
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

    private void initLunBo() {
        List<HomepageSportTop.DataBean.ResourceBean> resource = mHst.getData().getResource();
        TextView tv_one = (TextView) mLunbo.getChildAt(0);
        tv_one.setText(resource.get(0).getName());
        TextView tv_two = (TextView) mLunbo.getChildAt(2);
        tv_two.setText(resource.get(1).getName());
        TextView tv_three = (TextView) mLunboTwo.getChildAt(0);
        tv_three.setText(resource.get(2).getName());
        TextView tv_fout = (TextView) mLunboTwo.getChildAt(2);
        tv_fout.setText(resource.get(3).getName());
        mHandler.sendEmptyMessageDelayed(1,2000);
    }

    private void initSecondScrollView() {
        List<HomepageSportTop.DataBean.ListingBean.ListsBean> lists = mHst.getData().getListing().getLists();
        for (int i = 0; i < lists.size(); i++) {
            LinearLayout ll = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.listing_item,null);
            SimpleDraweeView fresco = (SimpleDraweeView) ll.getChildAt(0);
            fresco.setImageURI(lists.get(i).getImg());
            TextView tv = (TextView) ll.getChildAt(1);
            tv.setText(lists.get(i).getTitle());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(15,0,5,0);
            ll.setLayoutParams(params);
            mListing.addView(ll);
        }

    }

    private void initFirstScrollView() {
        List<HomepageSportTop.DataBean.HaojiaBean> haojia = mHst.getData().getHaojia();
        float density = getResources().getDisplayMetrics().density;
        for (int i = 0; i < haojia.size(); i++) {
            RelativeLayout rl = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.haojia_item,null);
            SimpleDraweeView fresco = (SimpleDraweeView) rl.getChildAt(0);
            fresco.setImageURI(haojia.get(i).getImg());
            TextView name = (TextView) rl.getChildAt(1);
            String name1 = haojia.get(i).getName();
            if (name1.length()>15) {
                name.setText(name1.substring(0,15)+"...");
            }else{
                name.setText(name1);
            }
            TextView price = (TextView) rl.getChildAt(2);
            price.setText("￥"+haojia.get(i).getPrice());
            TextView maxPrice = (TextView) rl.getChildAt(3);
            maxPrice.setText(haojia.get(i).getMax_price());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins((int)(10*density),0,0,0);
            rl.setLayoutParams(params);
            mHaojia.addView(rl);
        }
    }

    private void initBrands() {
        List<HomepageSportTop.DataBean.BrandsBean> brands = mHst.getData().getBrands();
        for (int i = 0; i < brands.size(); i++) {
            LinearLayout ll = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.sport_item, null);
            SimpleDraweeView fresco = (SimpleDraweeView) ll.getChildAt(0);
            fresco.setImageURI(brands.get(i).getImg());
            TextView tv = (TextView) ll.getChildAt(1);
            tv.setText(brands.get(i).getName());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels/5
                    ,LinearLayout.LayoutParams.MATCH_PARENT);
            ll.setLayoutParams(params);
            mBrands.addView(ll);
        }
    }

    private void initCategory() {
        List<HomepageSportTop.DataBean.CategoriesBean> categories = mHst.getData().getCategories();
        for (int i = 0; i < categories.size(); i++) {
            LinearLayout ll = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.sport_item, null);
            SimpleDraweeView fresco = (SimpleDraweeView) ll.getChildAt(0);
            fresco.setImageURI(categories.get(i).getImg());
            TextView tv = (TextView) ll.getChildAt(1);
            tv.setText(categories.get(i).getName());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels/5
                    ,LinearLayout.LayoutParams.MATCH_PARENT);
            ll.setLayoutParams(params);
            mLayout.addView(ll);
        }

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation == mTranslate){
            mLunbo.setVisibility(View.GONE);
            mTranslate_two = AnimationUtils.loadAnimation(getContext(), R.anim.two_in);
            mLunboTwo.startAnimation(mTranslate_two);
            mLunboTwo.setVisibility(View.VISIBLE);
        }else{
            mLunboTwo.setVisibility(View.GONE);
            mLunbo.setVisibility(View.VISIBLE);
            mTranslate_two1 = AnimationUtils.loadAnimation(getContext(), R.anim.two_in);
            mLunbo.startAnimation(mTranslate_two1);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
    }
}
