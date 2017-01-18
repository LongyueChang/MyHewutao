package com.tmfortm.shihuo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.tmfortm.shihuo.ui.EquipFragment;
import com.tmfortm.shihuo.ui.FindFragment;
import com.tmfortm.shihuo.ui.HomePageFragment;
import com.tmfortm.shihuo.ui.MoreFragment;
import com.tmfortm.shihuo.ui.SurgingFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private Fragment mHomePageFragment,mEquipFragment,mFindFragment,mSurgingFragment,mMoreFragment,mCurrentFragment;
    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        initRadioGroupListenter();
    }

    private void initRadioGroupListenter() {
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    private void initFragment() {
        mHomePageFragment = new HomePageFragment();
        mManager = getSupportFragmentManager();
        mManager.beginTransaction().add(R.id.container_main,mHomePageFragment,"homepage").commit();
        mCurrentFragment = mHomePageFragment;
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main);
    }

    //radioButton的监听事件
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.homepage_main:
                mManager.beginTransaction().hide(mCurrentFragment).show(mHomePageFragment).commit();
                mCurrentFragment = mHomePageFragment;
                break;
            case R.id.equip_main:
                if(mEquipFragment == null){
                    mEquipFragment = new EquipFragment();
                    mManager.beginTransaction().hide(mCurrentFragment).add(R.id.container_main,mEquipFragment,"equip").commit();
                }else{
                    mManager.beginTransaction().hide(mCurrentFragment).show(mEquipFragment).commit();
                }
                mCurrentFragment = mEquipFragment;
                break;
            case R.id.find_main:
                if(mFindFragment == null){
                    mFindFragment = new FindFragment();
                    mManager.beginTransaction().hide(mCurrentFragment).add(R.id.container_main,mFindFragment,"find").commit();
                }else{
                    mManager.beginTransaction().hide(mCurrentFragment).show(mFindFragment).commit();
                }
                mCurrentFragment = mFindFragment;
                break;
            case R.id.surging_main:
                if(mSurgingFragment == null){
                    mSurgingFragment = new SurgingFragment();
                    mManager.beginTransaction().hide(mCurrentFragment).add(R.id.container_main,mSurgingFragment,"surging").commit();
                }else{
                    mManager.beginTransaction().hide(mCurrentFragment).show(mSurgingFragment).commit();
                }
                mCurrentFragment = mSurgingFragment;
                break;
            case R.id.more_main:
                if(mMoreFragment == null){
                    mMoreFragment = new MoreFragment();
                    mManager.beginTransaction().hide(mCurrentFragment).add(R.id.container_main,mMoreFragment,"equip").commit();
                }else{
                    mManager.beginTransaction().hide(mCurrentFragment).show(mMoreFragment).commit();
                }
                mCurrentFragment = mMoreFragment;
                break;
        }
    }
}
