package com.tmfortm.shihuo.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.FindRecyclerAdapter;
import com.tmfortm.shihuo.adapters.OnePagerAdapter;
import com.tmfortm.shihuo.beans.Banner;
import com.tmfortm.shihuo.beans.Channel;
import com.tmfortm.shihuo.beans.Find_Tuijian;
import com.tmfortm.shihuo.beans.Hot_listing;
import com.tmfortm.shihuo.beans.Tag_info;
import com.tmfortm.shihuo.utils.JsonUtils;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import java.util.ArrayList;
import java.util.List;
import org.xutils.x;

import static android.R.attr.cacheColorHint;
import static android.R.attr.tag;
import static android.R.attr.wallpaperCloseEnterAnimation;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.tmfortm.shihuo.R.id.findFragment_linear;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment implements ViewPager.OnPageChangeListener{
    private ViewPager mOneViewPager;
    private int currentPosition = Integer.MAX_VALUE/2;
    private List<Channel> mChannels=new ArrayList<>();
    private Handler mHandler=new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    if(this.hasMessages(100)){
                        this.removeMessages(100);
                    }
                    currentPosition++;
                    mOneViewPager.setCurrentItem(currentPosition);
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
    private LinearLayout mFindFragment_scroll_two_linear;
    private ImageView mFindFragment_recycler_top;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initScrollViewTwo(List<Tag_info> tag_infos) {
        LinearLayout layout=new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.HORIZONTAL);
        RadioGroup rg = new RadioGroup(getContext());
        for (int i = 0; i < tag_infos.size(); i++) {
            final RadioButton myBtn = new RadioButton(getContext());
            myBtn.setClickable(true);
            myBtn.setButtonDrawable(0);
            myBtn.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            myBtn.setBackgroundResource(R.drawable.find_fragment_btn_click);
            myBtn.setText(tag_infos.get(i).getName());
            myBtn.setTextSize(14);
            myBtn.setPadding(10,5,10,5);
            final int finalI = i;
            myBtn.setTag(i);
            myBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                            myBtn.setTextColor(Color.WHITE);
                    }else{
                        myBtn.setTextColor(Color.BLACK);
                    }
                }
            });
            myBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if ((myBtn.getTag())== ((Object) finalI)){
                        Toast.makeText(getContext(), "sssss", Toast.LENGTH_SHORT).show();
                        initRecyclerData(urls[finalI],"a");
                   // }
                }
            });
            RadioGroup.LayoutParams params=new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,5,10,5);
            params.gravity= Gravity.CENTER;
            myBtn.setLayoutParams(params);
            rg.addView(myBtn);
        }
        rg.setOrientation(RadioGroup.HORIZONTAL);
        layout.addView(rg);
        mFindFragment_scroll_two_linear.addView(layout);
    }
    private LinearLayout mFindFragment_scroll_linear;
    private RecyclerView mFindFragment_recycler;
    private RecyclerView.Adapter mFindRecyclerAdapter;
    private void initScrollViewOne(List<Hot_listing> hot_listings) {
        for (int i = 0; i < hot_listings.size(); i++) {
            LinearLayout layout=new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            SimpleDraweeView draweeView=new SimpleDraweeView(getContext());
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(200,200);
            params.setMargins(5,5,5,3);
            draweeView.setLayoutParams(params);
            Hot_listing hot_listing = hot_listings.get(i);
            Log.d("flag", "----------->得到的数据为onClick:hot_listing1.getUrl()" +hot_listing.getUrl());
            draweeView.setImageURI(Uri.parse(hot_listing.getImg_url()));
            layout.addView(draweeView);
            TextView textView=new TextView(getContext());
            textView.setText(hot_listing.getName());
            layout.addView(textView);
            final Hot_listing hot_listing1=hot_listing;
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getContext(),ViewPagerActivity.class);
                    Log.d("flag", "----------->得到的数据为onClick:hot_listing1.getUrl()" +hot_listing1.getUrl());


                    EventBus.getDefault().postSticky(hot_listing1);
                    getContext().startActivity(intent);
                }
            });
            mFindFragment_scroll_linear.addView(layout);
        }

    }
    private PagerAdapter mAdapter;
    private TextView mOneLinearTvOne;
    private TextView mOneLinearTvTwo;
    private TextView mOneLinearTvThree;
    private SimpleDraweeView mOneLinearImgOne;
    private SimpleDraweeView mOneLinearImgTwo;
    private SimpleDraweeView mOneLinearImgThree;
    public FindFragment() {
    }
    private String url0="http://www.shihuo.cn/app_swoole_shiwu/list?channel=anzhi&clientCode=863167034859028&platform=android&tag_id=48&timestamp=1482306580502&token=00881a019101dace11ee3166f66f890f&v=4.2.2";
    private String url1="http://www.shihuo.cn/app_swoole_shiwu/list?channel=anzhi&clientCode=863167034859028&platform=android&tag_id=53&timestamp=1482306971435&token=99716c2b7752574d4cfb8fd9a613e2cc&v=4.2.2";
    private String url2="http://www.shihuo.cn/app_swoole_shiwu/list?channel=anzhi&clientCode=863167034859028&platform=android&tag_id=49&timestamp=1482308396186&token=6dedd5add107a749e2cdc4dbdf9bd0ad&v=4.2.2";
    private String url3="http://www.shihuo.cn/app_swoole_shiwu/list?channel=anzhi&clientCode=863167034859028&platform=android&tag_id=54&timestamp=1482308602710&token=970521d9c8b21e9bfba67eea0d9c34ec&v=4.2.2";
    private String url4="http://www.shihuo.cn/app_swoole_shiwu/list?channel=anzhi&clientCode=863167034859028&platform=android&tag_id=50&timestamp=1482308714234&token=7b55af8a9138c812cd12ef0c80289f70&v=4.2.2";
    private String url5="http://www.shihuo.cn/app_swoole_shiwu/list?channel=anzhi&clientCode=863167034859028&platform=android&tag_id=55&timestamp=1482308859600&token=2d2be00a5bdb102813a9dd3e81fb593d&v=4.2.2";
    private String url6="http://www.shihuo.cn/app_swoole_shiwu/list?channel=anzhi&clientCode=863167034859028&platform=android&tag_id=57&timestamp=1482308926175&token=d19144282502a2615fed52e3e5768c34&v=4.2.2";
    private String url7="http://www.shihuo.cn/app_swoole_shiwu/list?channel=anzhi&clientCode=863167034859028&platform=android&tag_id=78&timestamp=1482308956700&token=274f4a10decf6c3e1c6848145c8064ff&v=4.2.2";
    private String url8="http://www.shihuo.cn/app_swoole_shiwu/list?channel=anzhi&clientCode=863167034859028&platform=android&tag_id=52&timestamp=1482308984191&token=c077f2964af75e5b95d55933943179b6&v=4.2.2";
    private String url9="http://www.shihuo.cn/app_swoole_shiwu/list?channel=anzhi&clientCode=863167034859028&platform=android&tag_id=56&timestamp=1482309009010&token=e31b92efd9acdc0a1b689750a9218ae4&v=4.2.2";
    private String[] urls=new String[]{url0,url1,url2,url3,url4,url5,url6,url7,url8,url9};

    //下拉加载
    private String urlMore="http://www.shihuo.cn/app_swoole_shiwu/list?channel=anzhi&clientCode=863167034859028&create_time=2016-12-22+11%3A06%3A01&platform=android&tag_id=48&timestamp=1482473034036&token=4d604036bbc87297a22dcb74274eb6bf&v=4.2.2";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.fragment_find, container, false);
        initView(ret);
        initOneViewPager();
        initOneViewPagerData();
        initRecyclerView();
        initRecyclerData(url0,"a");
        return ret;
    }

    private void initRecyclerData(String url, final String tag) {
        Log.d("flag", "----------->得到的数据为initRecyclerData:" +url);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }
            @Override
            public void onSuccess(String result) {
                List<Find_Tuijian> find_tuijiens=JsonUtils.parseFind_Tuijian(result);
                switch (tag){
                    case "a":
                        Log.d("flag", "----------->得到的数据为onSuccess:" +find_tuijiens.size());
                        recyclerData.clear();
                        recyclerData.addAll(find_tuijiens);
                        mFindRecyclerAdapter.notifyDataSetChanged();
                        break;
                    case "m":
                        recyclerData.addAll(find_tuijiens);
                        mFindRecyclerAdapter.notifyDataSetChanged();
                        break;
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("flag", "----------->得到的数据为onError:");
            }
            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("flag", "----------->得到的数据为onCancelled:");
            }
            @Override
            public void onFinished() {
                Log.d("flag", "----------->得到的数据为onFinished:");
            }
        });
    }

    private List<Find_Tuijian> recyclerData=new ArrayList<>();
    private void initRecyclerView() {
        final RecyclerView.LayoutManager layout=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mFindFragment_recycler.setLayoutManager(layout);
        mFindRecyclerAdapter = new FindRecyclerAdapter(recyclerData,getContext());
        mFindFragment_recycler.setAdapter(mFindRecyclerAdapter);

        mFindFragment_recycler_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollToPosition(0);
            }
        });
        mFindFragment_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                int totalItemCount = manager.getItemCount();
                //获取最后一个完全显示的ItemPosition
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        //加载更多功能的代码
                       // p++;
                        initRecyclerData(urlMore,"m");
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                if(firstVisibleItemPosition>7){
                    mFindFragment_recycler_top.setVisibility(View.VISIBLE);
                }else{
                    mFindFragment_recycler_top.setVisibility(View.GONE);
                }
            }

        });
    }
    private List<SimpleDraweeView> oneViewPagerPics=new ArrayList<>();
    private void initOneViewPagerData() {
        RequestParams params = new RequestParams("http://www.shihuo.cn/app_swoole_shiwu/home?channel=anzhi&clientCode=863167034859028&platform=android&timestamp=1482134973067&token=05f8f688e9d6e073b4dd357009a6815c&v=4.2.2 ");
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject=JsonUtils.parse(result);
                //JSONObject BannerObj=jsonObject.optJSONObject("banner");
                //图片地址
                final List<Banner> banners=JsonUtils.parseBanner(jsonObject);
                List<SimpleDraweeView> oneViewPagerPicList=new ArrayList<>();
                for (int i = 0; i < banners.size(); i++) {
                    final SimpleDraweeView draweeView=new SimpleDraweeView(getContext());
                    Banner banner = banners.get(i);
                    Uri uri=Uri.parse(banner.getImg_url());
                    draweeView.setImageURI(uri);
                    oneViewPagerPicList.add(draweeView);
                    draweeView.setTag(banner.getUrl());
                    /*draweeView.setOnClickListener(FindFragment.this);*/
                    final Banner banner1=banner;
                    draweeView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getContext(),ViewPagerActivity.class);
                            EventBus.getDefault().postSticky(banner1.getUrl());
                            getContext().startActivity(intent);
                        }
                    });
                }
                oneViewPagerPics.addAll(oneViewPagerPicList);
                mAdapter.notifyDataSetChanged();
                List<Channel> channels=JsonUtils.parseChannel(jsonObject);
                mChannels.addAll(channels);
                mOneLinearTvOne.setText(channels.get(0).getSub_title());
                mOneLinearImgOne.setImageURI(Uri.parse(channels.get(0).getImg_url().get(0)));
                mOneLinearTvTwo.setText(channels.get(1).getSub_title());
                mOneLinearImgTwo.setImageURI(Uri.parse(channels.get(1).getImg_url().get(0)));
                mOneLinearTvThree.setText(channels.get(2).getSub_title());
                mOneLinearImgThree.setImageURI(Uri.parse(channels.get(2).getImg_url().get(0)));
                List<Hot_listing> hot_listings=JsonUtils.parseHot_listing(jsonObject);
                initScrollViewOne(hot_listings);
                List<Tag_info> tag_infos=JsonUtils.parseTag_info(jsonObject);
                initScrollViewTwo(tag_infos);
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
    private void initOneViewPager() {
        mAdapter = new OnePagerAdapter(oneViewPagerPics);
        mOneViewPager.setAdapter(mAdapter);
        mOneViewPager.setCurrentItem(Integer.MAX_VALUE/2);
        mHandler.sendEmptyMessageDelayed(100,2000);
        mOneViewPager.addOnPageChangeListener(this);
    }
    private HorizontalScrollView mFindFragment_scroll;
    private void initView(View ret) {
        mOneViewPager = (ViewPager) ret.findViewById(R.id.findFragment_hot);
        mOneLinearTvOne = (TextView) ret.findViewById(R.id.findFragment_linearOne_one);
        mOneLinearTvTwo = (TextView) ret.findViewById(R.id.findFragment_linearOne_two);
        mOneLinearTvThree = (TextView) ret.findViewById(R.id.findFragment_linearOne_three);
        mOneLinearImgOne = (SimpleDraweeView) ret.findViewById(R.id.findFragment_linearOne_imgOne);
        mOneLinearImgTwo = (SimpleDraweeView) ret.findViewById(R.id.findFragment_linearOne_imgTwo);
        mOneLinearImgThree = (SimpleDraweeView) ret.findViewById(R.id.findFragment_linearOne_imgThree);
        mFindFragment_scroll = (HorizontalScrollView) ret.findViewById(R.id.findFragment_scroll);
        mFindFragment_scroll_linear = (LinearLayout) ret.findViewById(R.id.findFragment_scroll_linear);
        mFindFragment_scroll_two_linear = (LinearLayout) ret.findViewById(R.id.findFragment_scroll_two_linear);
        mFindFragment_recycler = (RecyclerView) ret.findViewById(R.id.findFragment_Recycler);
        mFindFragment_recycler_top = (ImageView) ret.findViewById(R.id.findFragment_Recycler_top);

        LinearLayout findFragment_linear= (LinearLayout) ret.findViewById(R.id.findFragment_linear);
        findFragment_linear.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        LinearLayout findFragment_linearOne= (LinearLayout) ret.findViewById(R.id.findFragment_linearOne);
        findFragment_linearOne.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent=new Intent(getContext(),ViewPagerActivity.class);
                switch (v.getId()){
                    case R.id.findFragment_linearOne:
                        EventBus.getDefault().postSticky(mChannels.get(0));
                        break;
                }
                getContext().startActivity(intent);
                return true;
            }
        });
        LinearLayout findFragment_linearTwo= (LinearLayout) ret.findViewById(R.id.findFragment_linearTwo);
        findFragment_linearTwo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent=new Intent(getContext(),ViewPagerActivity.class);
                switch (v.getId()){
                    case R.id.findFragment_linearTwo:
                        EventBus.getDefault().postSticky(mChannels.get(1));
                        break;
                }
                getContext().startActivity(intent);
                return true;
            }
        });
        LinearLayout findFragment_linearThree= (LinearLayout) ret.findViewById(R.id.findFragment_linearThree);

        findFragment_linearThree.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent=new Intent(getContext(),ViewPagerActivity.class);
                switch (v.getId()){
                    case R.id.findFragment_linearThree:
                        EventBus.getDefault().postSticky(mChannels.get(2));
                        break;
                }
                getContext().startActivity(intent);
                return true;
            }
        });
    }
    /**
     * 手动viewpager
     *
     */
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
