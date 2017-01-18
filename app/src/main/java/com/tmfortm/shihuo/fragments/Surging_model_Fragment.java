package com.tmfortm.shihuo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.HeaderModelAdapterOne;
import com.tmfortm.shihuo.adapters.SurgingHeaderRecyclerAdapter;
import com.tmfortm.shihuo.adapters.SurgingModelAdapter;
import com.tmfortm.shihuo.beans.SurgingModelGoods;
import com.tmfortm.shihuo.beans.Surging_Children_category;
import com.tmfortm.shihuo.beans.Surging_Special_column;
import com.tmfortm.shihuo.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;
import org.xutils.x;


/**
 * Created by my on 2016/12/14.
 */
public class Surging_model_Fragment extends Fragment {
    private RecyclerView mModel_recycler;
    private RecyclerView.Adapter mModelAdapter;
    private RecyclerView mRecyclerOne;
    private RecyclerView mRecyclerTwo;
    private RecyclerView.Adapter mHeaderModelAdapterOne;
    private RecyclerView.Adapter mHeaderModelAdapterTwo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ret=inflater.inflate(R.layout.surging_model,container,false);
        initView(ret);
        initRecycler();
        initData();
        return ret;
    }
    private void initRecycler() {
        RecyclerView.LayoutManager layout=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mModel_recycler.setLayoutManager(layout);
        RecyclerViewHeader viewHeader=RecyclerViewHeader.fromXml(getContext(),R.layout.surging_model_header);
        initHeaderView(viewHeader);
        viewHeader.attachTo(mModel_recycler);
        mModelAdapter = new SurgingModelAdapter(mGoodses,getContext());
        mModel_recycler.setAdapter(mModelAdapter);
    }
    private void initHeaderView(RecyclerViewHeader viewHeader) {
        mRecyclerOne = (RecyclerView) viewHeader.findViewById(R.id.surging_model_header_recycler_one);
        mRecyclerTwo = (RecyclerView) viewHeader.findViewById(R.id.surging_model_header_recycler_two);
        mRecyclerOne.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
        mHeaderModelAdapterOne = new HeaderModelAdapterOne(mChildrenCategories,getContext());
        mRecyclerOne.setAdapter(mHeaderModelAdapterOne);
        mRecyclerTwo.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mHeaderModelAdapterTwo = new SurgingHeaderRecyclerAdapter(getContext(),mSurgingSpecialColumns);
        mRecyclerTwo.setAdapter(mHeaderModelAdapterTwo);
    }
    private List<SurgingModelGoods> mGoodses=new ArrayList<>();
    private void initData() {
        //http://www.shihuo.cn/app_swoole_haitao/homeList?channel=anzhi&clientCode=863167034859028&page=1&page_size=30&platform=android&r=1&timestamp=1481877718077&token=ec5689e2d5634db686810f1577bfbdd0&v=4.2.2
        RequestParams params = new RequestParams("http://www.shihuo.cn/app_swoole_haitao/home?channel=anzhi&clientCode=863167034859028&platform=android&r=1&timestamp=1481874207813&token=0ab4dfea5b4abbda3b09965b0aacdeca&v=4.2.2");
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject= JsonUtils.parseSurging_Children_category(result);
                JSONArray jsonArray = jsonObject.optJSONArray("children_category");
                List<Surging_Children_category> categories = JSON.parseArray(jsonArray.toString(), Surging_Children_category.class);
                initHeaderViewOne(categories);
                JSONArray special_columnArry = jsonObject.optJSONArray("special_column");
                List<Surging_Special_column> specialColumns = JSON.parseArray(special_columnArry.toString(), Surging_Special_column.class);
                initHeaderViewTwo(specialColumns);
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
        RequestParams entity=new RequestParams("http://www.shihuo.cn/app_swoole_haitao/homeList?channel=anzhi&clientCode=863167034859028&page=1&page_size=30&platform=android&r=1&timestamp=1481877718077&token=ec5689e2d5634db686810f1577bfbdd0&v=4.2.2");
        x.http().get(entity, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }
            @Override
            public void onSuccess(String result) {
                List<SurgingModelGoods> modelGoodses=JsonUtils.parseSurgingModelGoods(result);
                mGoodses.addAll(modelGoodses);
                mModelAdapter.notifyDataSetChanged();
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
    private List<Surging_Special_column> mSurgingSpecialColumns=new ArrayList<>();
    private void initHeaderViewTwo(List<Surging_Special_column> specialColumns) {
        mSurgingSpecialColumns.addAll(specialColumns);
        mHeaderModelAdapterTwo.notifyDataSetChanged();
    }
    private List<Surging_Children_category> mChildrenCategories=new ArrayList<>();
    private void initHeaderViewOne(List<Surging_Children_category> categories) {
        mChildrenCategories.clear();
        mChildrenCategories.addAll(categories);
        mHeaderModelAdapterOne.notifyDataSetChanged();
    }
    private void initView(View ret) {
        mModel_recycler = (RecyclerView) ret.findViewById(R.id.surging_model_recycler);
    }
}
