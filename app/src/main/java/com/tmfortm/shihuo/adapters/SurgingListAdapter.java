package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.Surging_Special_column;

import java.util.List;

/**
 * Created by my on 2016/12/15.
 */
public class SurgingListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Surging_Special_column> special_columnList;
    public SurgingListAdapter(Context context, List<Surging_Special_column> special_columnList) {
        this.mContext=context;
        this.special_columnList=special_columnList;
    }

    @Override
    public int getCount() {
        return special_columnList!=null?special_columnList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return special_columnList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret=null;
        if(convertView!=null){
            ret=convertView;
        }else{
            ret= LayoutInflater.from(mContext).inflate(R.layout.surging_jingxuan_listview,parent,false);



        }


        return ret;
    }
}
