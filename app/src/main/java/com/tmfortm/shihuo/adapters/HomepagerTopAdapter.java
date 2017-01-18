package com.tmfortm.shihuo.adapters;

import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public class HomepagerTopAdapter extends PagerAdapter {
    private List<SimpleDraweeView> list;
    public HomepagerTopAdapter(List<SimpleDraweeView> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
//        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View ret = list.get(position%list.size());
        ViewGroup parent = (ViewGroup) ret.getParent();
        if(parent !=null){
           parent.removeView(ret);
        }
        container.addView(ret);
        return ret;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position%list.size()));
    }
}
