package com.tmfortm.shihuo.ui;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tmfortm.shihuo.fragments.Surging_jingxuan_Fragment;
import com.tmfortm.shihuo.fragments.Surging_model_Fragment;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.SurgingViewPagerAdapter;
import com.tmfortm.shihuo.beans.Category;
import com.tmfortm.shihuo.beans.Surging_Banner;
import com.tmfortm.shihuo.beans.Surging_Hottest;
import com.tmfortm.shihuo.utils.JsonUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SurgingFragment extends Fragment {
    private TabLayout mSurging_tablayout;
    private ViewPager mSurging_viewpager;

    public SurgingFragment() {
        // Required empty public constructor
    }
    private List<Fragment> mFragments=new ArrayList<>();
    private List<String> titles=new ArrayList<>();
    private void initTab(List<Category> categories, List<Surging_Banner> surging_banners) {

        for (int i = 0; i < categories.size(); i++) {
            TabLayout.Tab tab=mSurging_tablayout.newTab();
            String name = categories.get(i).getName();
            tab.setText(name);
            mSurging_tablayout.addTab(tab);
            titles.add(name);
        }

        mSurging_tablayout.setupWithViewPager(mSurging_viewpager);

        for (int i = 0; i <categories.size(); i++) {
            Fragment fragment=null;
            if(i==0){
                Log.d("flag", "----------->得到的数据为initTab:" +surging_banners.size());
                //EventBus.getDefault().postSticky(surging_banners);

                fragment=new Surging_jingxuan_Fragment();

            }else{

                fragment=new Surging_model_Fragment();

            }
            mFragments.add(fragment);
        }
        FragmentPagerAdapter adapter=new SurgingViewPagerAdapter(getFragmentManager(),mFragments,titles);
        mSurging_viewpager.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_surging, container, false);

        initSurgingView(ret);
        initRoucesData();

        return ret;
    }
    private void initRoucesData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams entity=new RequestParams("http://www.shihuo.cn/app_swoole_haitao/home?channel=wandoujia&clientCode=869336021137995&platform=android&timestamp=1481684361391&token=1ec514a1f44f04cae4f98da95523c076&v=4.2.2");
                x.http().get(entity, new Callback.CacheCallback<String>() {
                    @Override
                    public boolean onCache(String result) {
                        return false;
                    }
                    @Override
                    public void onSuccess(String result) {

                        JSONObject jsonObject = JsonUtils.parse(result);

                        List<Category> categories=JsonUtils.parseCategory(jsonObject);

                        List<Surging_Banner> surging_banners=JsonUtils.parseSurging_Banner(jsonObject);

                        List<Surging_Hottest> surging_hottests=JsonUtils.parseSurging_Hottest(jsonObject);

                        EventBus.getDefault().postSticky(jsonObject);


                        initTab(categories,surging_banners);

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
        }).start();
    }
    private void initSurgingView(View ret) {
        mSurging_tablayout = (TabLayout) ret.findViewById(R.id.surging_tablayout);
        mSurging_viewpager = (ViewPager) ret.findViewById(R.id.surging_viewpager);
    }

}