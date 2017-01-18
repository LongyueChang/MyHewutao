package com.tmfortm.shihuo.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.OnePagerAdapter;
import com.tmfortm.shihuo.adapters.SurgingHeaderRecyclerAdapter;
import com.tmfortm.shihuo.adapters.Surging_jingxuan_Adapter;
import com.tmfortm.shihuo.beans.Surging_Banner;
import com.tmfortm.shihuo.beans.Surging_Context;
import com.tmfortm.shihuo.beans.Surging_Hottest;
import com.tmfortm.shihuo.beans.Surging_Special_column;
import com.tmfortm.shihuo.utils.JsonUtils;
import com.tmfortm.shihuo.widget.MyHeader;
import com.tmfortm.shihuo.widget.OnRecyclerItemClickListener;
import com.tmfortm.shihuo.widget.VerticalScrollTv;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static com.tmfortm.shihuo.R.layout.surging_jingxuan_header;

/**
 * Created by my on 2016/12/14.
 */
public class Surging_jingxuan_Fragment extends Fragment implements ViewPager.OnPageChangeListener {


    private RecyclerView mSurging_jingxuan_recycler;
    private List<Surging_Context> mSurgingContextList=new ArrayList<>();
    private int currentPosition = Integer.MAX_VALUE/2;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:

                    if(this.hasMessages(100)){
                        this.removeMessages(100);
                    }
                    currentPosition++;
                    mSurging_jingxuan_header_viewpager.setCurrentItem(currentPosition);
                    this.sendEmptyMessageDelayed(100,2000);
                    break;
                case 101:

                    if(this.hasMessages(100)){
                        this.removeMessages(100);
                    }
                    break;
                case 102:

                    //手滑动的时候，页码变，需要重新赋值
                    currentPosition=msg.arg1;
                    this.sendEmptyMessageDelayed(100,2000);
                    break;


            }
        }
    };
    private RecyclerView.Adapter mAdapter;
    private ViewPager mSurging_jingxuan_header_viewpager;
    private VerticalScrollTv mSurging_jingxuan_header_scroll;
    private RecyclerView mSurging_jingxuan_header_recyclerview;
    private RecyclerView.Adapter mSurgingHeaderRecyclerAdapter;
    //private BaseAdapter mSurgingListAdapter;
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    List<Surging_Special_column> mSpecial_columnList=new ArrayList<>();
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void getMessage(JSONObject jsonObject){
        List<Surging_Banner> surging_banners=JsonUtils.parseSurging_Banner(jsonObject);
        List<Surging_Hottest> surging_hottests=JsonUtils.parseSurging_Hottest(jsonObject);
        initHeadViewPager(surging_banners);
        initSurging_jingxuan_header_scroll(surging_hottests);
        List<Surging_Special_column> surging_special_columns=JsonUtils.parseSurging_Special_column(jsonObject);
        mSpecial_columnList.addAll(surging_special_columns);
        mSurgingHeaderRecyclerAdapter.notifyDataSetChanged();
    }

    private void initSurging_jingxuan_header_scroll(List<Surging_Hottest> surging_hottests) {
       /* LinearLayout layout=new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < surging_hottests.size(); i++) {
            TextView textView=new TextView(getContext());
            textView.setText(surging_hottests.get(i).getName());
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(14);
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
            textView.setLayoutParams(params);
            layout.addView(textView);
        }
        mSurging_jingxuan_header_scroll.addView(layout);*/
        mSurging_jingxuan_header_scroll.setList(surging_hottests);
        mSurging_jingxuan_header_scroll.updateUI();
    }

    private List<SimpleDraweeView> mSimpleDraweeViews=new ArrayList<>();
    private void initHeadViewPager(List<Surging_Banner> surging_banners) {
        for (int i = 0; i < surging_banners.size(); i++) {
            SimpleDraweeView draweeView=new SimpleDraweeView(getContext());
            draweeView.setImageURI(surging_banners.get(i).getImg_url());
            mSimpleDraweeViews.add(draweeView);
        }
        PagerAdapter adapter=new OnePagerAdapter(mSimpleDraweeViews);
        mSurging_jingxuan_header_viewpager.setAdapter(adapter);
        mSurging_jingxuan_header_viewpager.setCurrentItem(Integer.MAX_VALUE/2);
        mHandler.sendEmptyMessageDelayed(100,2000);
        mSurging_jingxuan_header_viewpager.addOnPageChangeListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View ret=inflater.inflate(R.layout.surging_jingxuan,container,false);
            ShareSDK.initSDK(getContext(),"1a403037c95db");
            initView(ret);
            initRecyclerView();
            initRecyclerData();
            return ret;
    }

    private void initRecyclerData() {
        RequestParams entity=new RequestParams("http://www.shihuo.cn/app_swoole_haitao/homeList?channel=anzhi&clientCode=863167034859028&page=1&page_size=30&platform=android&timestamp=1481766366275&token=3a2eb0f7f0048480ab2209f484150669&v=4.2.2");
        x.http().get(entity, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                List<Surging_Context> surging_contexts=JsonUtils.parseContext(result);
                mSurgingContextList.addAll(surging_contexts);
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
    private void initRecyclerView() {
        RecyclerView.LayoutManager layout=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layout.canScrollHorizontally();
        mSurging_jingxuan_recycler.setLayoutManager(layout);
        MyHeader header=MyHeader.fromXml(getContext(), surging_jingxuan_header);
        mSurging_jingxuan_header_viewpager = (ViewPager) header.findViewById(R.id.surging_jingxuan_header_viewpager);
        mSurging_jingxuan_header_scroll = (VerticalScrollTv) header.findViewById(R.id.surging_jingxuan_header_scroll);
        mSurging_jingxuan_header_recyclerview = (RecyclerView) header.findViewById(R.id.surging_jingxuan_header_recyclerview);
        initHeadListView();
        EventBus.getDefault().register(this);
        header.attachTo(mSurging_jingxuan_recycler);
        mAdapter = new Surging_jingxuan_Adapter(mSurgingContextList,getContext());
        mSurging_jingxuan_recycler.setAdapter(mAdapter);
        mSurging_jingxuan_recycler.addOnItemTouchListener(new OnRecyclerItemClickListener(mSurging_jingxuan_recycler) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {

            }

            @Override
            public void onItemLOngClick(RecyclerView.ViewHolder viewHolder) {
                View viewPop=LayoutInflater.from(getContext()).inflate(R.layout.pop,null,false);
                final PopupWindow popupWindow=new PopupWindow(viewPop,MATCH_PARENT,300,true);
                popupWindow.showAtLocation(mSurging_jingxuan_recycler, Gravity.BOTTOM,0,0);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.showAsDropDown(mSurging_jingxuan_recycler);
                TextView imgQQ = (TextView) viewPop.findViewById(R.id.img_qq);
                TextView imgSina = (TextView) viewPop.findViewById(R.id.img_sina);
                Button popBtn= (Button) viewPop.findViewById(R.id.popBtn);
                popBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                imgSina.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareSDK.initSDK(getContext());
                        SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
                        sp.setText("分享");
                        //sp.setImagePath(“/mnt/sdcard/测试分享的图片.jpg”);

                        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                        weibo.setPlatformActionListener(new PlatformActionListener() {
                            @Override
                            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                            }

                            @Override
                            public void onError(Platform platform, int i, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(Platform platform, int i) {

                            }
                        }); // 设置分享事件回调
                        // 执行图文分享
                        weibo.share(sp);
                    }
                });
                imgQQ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(CustomActivity.this, "QQ", Toast.LENGTH_SHORT).show();
                        ShareSDK.initSDK(getContext());
                        //设置QQ控件的分享参数
                        QQ.ShareParams sp = new QQ.ShareParams();
                        sp.setTitle("测试分享的标题");
                        sp.setTitleUrl("http://sharesdk.cn"); // 标题的超链接
                        sp.setText("测试分享的文本");
                        sp.setImageUrl("http://www.someserver.com/测试图片网络地址.jpg");
                        sp.setSite("发布分享的网站名称");
                        sp.setSiteUrl("发布分享网站的地址");

                        Platform qzone = ShareSDK.getPlatform (QQ.NAME);
                        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
                        qzone.setPlatformActionListener (new PlatformActionListener() {
                            public void onError(Platform arg0, int arg1, Throwable arg2) {
                                //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
                            }
                            public void onComplete(Platform arg0, int arg1, HashMap arg2) {
                                //分享成功的回调

                            }
                            public void onCancel(Platform arg0, int arg1) {
                                //取消分享的回调
                            }
                        });
                        // 执行图文分享
                        qzone.share(sp);
                    }

                });


            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private void initHeadListView() {

        RecyclerView.LayoutManager layout=new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        mSurging_jingxuan_header_recyclerview.setLayoutManager(layout);
        mSurgingHeaderRecyclerAdapter = new SurgingHeaderRecyclerAdapter(getContext(),mSpecial_columnList);
        mSurging_jingxuan_header_recyclerview.setAdapter(mSurgingHeaderRecyclerAdapter);

    }

    private void initView(View ret) {
        mSurging_jingxuan_recycler = (RecyclerView) ret.findViewById(R.id.surging_jingxuan_recycler);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mHandler.sendMessage(Message.obtain(mHandler,102,position,0));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state){
            case ViewPager.SCROLL_STATE_DRAGGING://手动拖拽
                mHandler.sendEmptyMessage(101);
                break;
        }
    }
}
