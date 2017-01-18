package com.tmfortm.shihuo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.EquipTop;
import com.tmfortm.shihuo.ui.ShoeActivity;
import com.tmfortm.shihuo.ui.WebViewActivity;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class EquipHeaderAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private EquipTop.DataBean.BasketballBean basketball;
    private EquipTop.DataBean.RunningBean running;
    private EquipTop.DataBean.FreestyleBean freestyle;
    private Context context;
    public EquipHeaderAdapter(EquipTop.DataBean.BasketballBean basketball
            , EquipTop.DataBean.RunningBean running, EquipTop.DataBean.FreestyleBean freestyle, Context context) {
        this.basketball = basketball;
        this.running = running;
        this.freestyle = freestyle;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.equipheader_item,parent,false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if(position == 0){
            myViewHolder.ball_bg.setImageURI(basketball.getBackground_img());
            myViewHolder.ball_intro1.setText(basketball.getIntro1());
            myViewHolder.ball_intro2.setText(basketball.getIntro2());
            myViewHolder.ball_one.setImageURI(basketball.getGoods().get(0).getImg());
            myViewHolder.ball_two.setImageURI(basketball.getGoods().get(1).getImg());
            myViewHolder.ball_three.setImageURI(basketball.getGoods().get(2).getImg());
            myViewHolder.ball_four.setImageURI(basketball.getGoods().get(3).getImg());
            myViewHolder.cate_one.setText(basketball.getCategory().get(0).getName());
            myViewHolder.cate_two.setText(basketball.getCategory().get(1).getName());
            myViewHolder.cate_three.setText(basketball.getCategory().get(2).getName());
            myViewHolder.cate_four.setText(basketball.getCategory().get(3).getName());
            myViewHolder.brand_one.setText(basketball.getBrand().get(0).getName());
            myViewHolder.brand_two.setText(basketball.getBrand().get(1).getName());
            myViewHolder.brand_three.setText(basketball.getBrand().get(2).getName());
            myViewHolder.brand_four.setText(basketball.getBrand().get(3).getName());
        }else if(position == 1){
            myViewHolder.ball_bg.setImageURI(running.getBackground_img());
            myViewHolder.ball_intro1.setText(running.getIntro1());
            myViewHolder.ball_intro2.setText(running.getIntro2());
            myViewHolder.ball_one.setImageURI(running.getGoods().get(0).getImg());
            myViewHolder.ball_two.setImageURI(running.getGoods().get(1).getImg());
            myViewHolder.ball_three.setImageURI(running.getGoods().get(2).getImg());
            myViewHolder.ball_four.setImageURI(running.getGoods().get(3).getImg());
            myViewHolder.cate_one.setText(running.getCategory().get(0).getName());
            myViewHolder.cate_two.setText(running.getCategory().get(1).getName());
            myViewHolder.cate_three.setText(running.getCategory().get(2).getName());
            myViewHolder.cate_four.setText(running.getCategory().get(3).getName());
            myViewHolder.brand_one.setText(running.getBrand().get(0).getName());
            myViewHolder.brand_two.setText(running.getBrand().get(1).getName());
            myViewHolder.brand_three.setText(running.getBrand().get(2).getName());
            myViewHolder.brand_four.setText(running.getBrand().get(3).getName());
        }else{
            myViewHolder.ball_bg.setImageURI(freestyle.getBackground_img());
            myViewHolder.ball_intro1.setText(freestyle.getIntro1());
            myViewHolder.ball_intro2.setText(freestyle.getIntro2());
            myViewHolder.ball_one.setImageURI(freestyle.getGoods().get(0).getImg());
            myViewHolder.ball_two.setImageURI(freestyle.getGoods().get(1).getImg());
            myViewHolder.ball_three.setImageURI(freestyle.getGoods().get(2).getImg());
            myViewHolder.ball_four.setImageURI(freestyle.getGoods().get(3).getImg());
            myViewHolder.cate_one.setText(freestyle.getCategory().get(0).getName());
            myViewHolder.cate_two.setText(freestyle.getCategory().get(1).getName());
            myViewHolder.cate_three.setText(freestyle.getCategory().get(2).getName());
            myViewHolder.cate_four.setText(freestyle.getCategory().get(3).getName());
            myViewHolder.brand_one.setText(freestyle.getBrand().get(0).getName());
            myViewHolder.brand_two.setText(freestyle.getBrand().get(1).getName());
            myViewHolder.brand_three.setText(freestyle.getBrand().get(2).getName());
            myViewHolder.brand_four.setText(freestyle.getBrand().get(3).getName());
        }
        myViewHolder.cate_one.setOnClickListener(this);
        myViewHolder.cate_one.setTag(position);
        myViewHolder.cate_two.setOnClickListener(this);
        myViewHolder.cate_two.setTag(position);
        myViewHolder.cate_three.setOnClickListener(this);
        myViewHolder.cate_three.setTag(position);
        myViewHolder.cate_four.setOnClickListener(this);
        myViewHolder.cate_four.setTag(position);
        myViewHolder.brand_one.setOnClickListener(this);
        myViewHolder.brand_one.setTag(position);
        myViewHolder.brand_two.setOnClickListener(this);
        myViewHolder.brand_two.setTag(position);
        myViewHolder.brand_three.setOnClickListener(this);
        myViewHolder.brand_three.setTag(position);
        myViewHolder.brand_four.setOnClickListener(this);
        myViewHolder.brand_four.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView ball_intro1,ball_intro2,brand_one,brand_two,brand_three,
                brand_four,cate_one,cate_two,cate_three,cate_four;
        private SimpleDraweeView ball_one,ball_two,ball_three,ball_four,ball_bg;
        private RelativeLayout rl;
        public MyViewHolder(View itemView) {
            super(itemView);
            ball_intro1 = (TextView) itemView.findViewById(R.id.intro1_fragment_equip_header);
            ball_intro2 = (TextView) itemView.findViewById(R.id.intro2_fragment_equip_header);
            ball_one = (SimpleDraweeView) itemView.findViewById(R.id.ball_good_one);
            ball_two = (SimpleDraweeView) itemView.findViewById(R.id.ball_good_two);
            ball_three = (SimpleDraweeView) itemView.findViewById(R.id.ball_good_three);
            ball_four = (SimpleDraweeView) itemView.findViewById(R.id.ball_good_four);
            ball_bg = (SimpleDraweeView) itemView.findViewById(R.id.basketball_bg);
            cate_one = (TextView) itemView.findViewById(R.id.cate_one);
            cate_two = (TextView) itemView.findViewById(R.id.cate_two);
            cate_three = (TextView) itemView.findViewById(R.id.cate_three);
            cate_four = (TextView) itemView.findViewById(R.id.cate_four);
            brand_one = (TextView) itemView.findViewById(R.id.brand_one);
            brand_two = (TextView) itemView.findViewById(R.id.brand_two);
            brand_three = (TextView) itemView.findViewById(R.id.brand_three);
            brand_four = (TextView) itemView.findViewById(R.id.brand_four);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl_equipheader_item);
        }
    }

    @Override
    public void onClick(View v) {
        Integer tag = (Integer) v.getTag();
        Intent intent = new Intent(context,ShoeActivity.class);
        if(tag == 0) {
            switch (v.getId()) {
                case R.id.cate_one:
                    intent.putExtra("id", 0);
                    break;
                case R.id.cate_two:
                    intent.putExtra("id", 1);
                    break;
                case R.id.cate_three:
                    intent.putExtra("id", 2);
                    break;
                case R.id.cate_four:
                    intent.putExtra("id", 3);
                    break;
                case R.id.brand_one:
                    intent.putExtra("id", 4);
                    break;
                case R.id.brand_two:
                    intent.putExtra("id", 5);
                    break;
                case R.id.brand_three:
                    intent.putExtra("id", 6);
                    break;
                case R.id.brand_four:
                    intent.putExtra("id", 7);
                    break;
            }
        }else if(tag == 1){
            switch (v.getId()) {
                case R.id.cate_one:
                    intent.putExtra("id", 0);
                    break;
                case R.id.cate_two:
                    intent.putExtra("id", 1);
                    break;
                case R.id.cate_three:
                    intent.putExtra("id", 2);
                    break;
                case R.id.cate_four:
                    intent.putExtra("id", 3);
                    break;
                case R.id.brand_one:
                    intent.putExtra("id", 4);
                    break;
                case R.id.brand_two:
                    intent.putExtra("id", 5);
                    break;
                case R.id.brand_three:
                    intent.putExtra("id", 6);
                    break;
                case R.id.brand_four:
                    intent.putExtra("id", 7);
                    break;
            }
        }else{
            switch (v.getId()) {
                case R.id.cate_one:
                    intent.putExtra("id", 0);
                    break;
                case R.id.cate_two:
                    intent.putExtra("id", 1);
                    break;
                case R.id.cate_three:
                    intent.putExtra("id", 2);
                    break;
                case R.id.cate_four:
                    intent.putExtra("id", 3);
                    break;
                case R.id.brand_one:
                    intent.putExtra("id", 4);
                    break;
                case R.id.brand_two:
                    intent.putExtra("id", 5);
                    break;
                case R.id.brand_three:
                    intent.putExtra("id", 6);
                    break;
                case R.id.brand_four:
                    intent.putExtra("id", 7);
                    break;
            }
        }
        intent.putExtra("tag",tag);
        context.startActivity(intent);
    }
}
