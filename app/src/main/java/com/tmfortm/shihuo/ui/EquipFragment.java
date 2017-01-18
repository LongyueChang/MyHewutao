package com.tmfortm.shihuo.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.EquipRecommendAdapter;
import com.tmfortm.shihuo.beans.EquipRecommend;
import com.tmfortm.shihuo.beans.HomepageTop;
import com.tmfortm.shihuo.fragments.EquipHeaderFragment;
import com.tmfortm.shihuo.uri.Constans;
import com.tmfortm.shihuo.widget.MyHeader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EquipFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<EquipRecommend.DataBeanX> data = new ArrayList<>();
    private EquipRecommendAdapter mAdapter;

    public EquipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_equip, container, false);

        initView(ret);

        initData();

        initListView();

        return ret;
    }

    private void initListView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        MyHeader header = MyHeader.fromXml(getContext(),R.layout.equip_header);
        getFragmentManager().beginTransaction().add(R.id.container_equip_header,new EquipHeaderFragment(),null).commit();
        header.attachTo(mRecyclerView);
        mAdapter = new EquipRecommendAdapter(data,getContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        RequestParams entity = new RequestParams(Constans.EQUIP_RECOMMEND);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                EquipRecommend er = JSON.parseObject(result,EquipRecommend.class);
                data.addAll(er.getData());
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

    private void initView(View ret) {
        mRecyclerView = (RecyclerView) ret.findViewById(R.id.rv_fragment_equip);
    }

}
