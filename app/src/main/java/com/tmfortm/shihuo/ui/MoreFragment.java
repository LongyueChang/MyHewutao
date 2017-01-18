package com.tmfortm.shihuo.ui;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.adapters.SurgingModelAdapter;
import com.tmfortm.shihuo.beans.SurgingModelGoods;
import com.tmfortm.shihuo.beans.Surging_Children_category;
import com.tmfortm.shihuo.beans.Surging_Special_column;
import com.tmfortm.shihuo.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {




    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_more, container, false);

        return ret;
    }



}
