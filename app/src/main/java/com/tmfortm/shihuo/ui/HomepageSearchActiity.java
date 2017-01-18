package com.tmfortm.shihuo.ui;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tmfortm.shihuo.R;

public class HomepageSearchActiity extends AppCompatActivity implements View.OnClickListener {

    private TextView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_search_actiity);
        initView();
    }

    private void initView() {
        cancel = (TextView) findViewById(R.id.cancel_homepage);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_homepage:
                this.finish();
                break;
        }
    }
}
