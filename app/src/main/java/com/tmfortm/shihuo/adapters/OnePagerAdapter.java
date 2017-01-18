package com.tmfortm.shihuo.adapters;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by my on 2016/12/12.
 */
public class OnePagerAdapter extends PagerAdapter {

    private List<SimpleDraweeView> oneViewPagerPics;
    public OnePagerAdapter(List<SimpleDraweeView> oneViewPagerPics) {
        this.oneViewPagerPics=oneViewPagerPics;
    }

    @Override
    public int getCount() {
        //return oneViewPagerPics!=null?oneViewPagerPics.size():0;
        return oneViewPagerPics!=null?Integer.MAX_VALUE:0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(oneViewPagerPics.get(position%5));


    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(oneViewPagerPics.size()!=0){
            Log.d("flag", "----------->得到的数据为instantiateItem:11"+position);

            SimpleDraweeView child = oneViewPagerPics.get(position % 5);
            ViewGroup parent = (ViewGroup) child.getParent();
            if(parent !=null){
                parent.removeView(child);
            }
            container.addView(child);

            return oneViewPagerPics.get(position%5);
        }

        return null;
    }

}
