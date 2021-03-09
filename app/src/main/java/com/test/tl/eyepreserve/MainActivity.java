package com.test.tl.eyepreserve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_eye).setOnClickListener(this);
        SeekBar seekBar = findViewById(R.id.sb_eye);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 当拖动条的滑块位置发生改变时触发该方法,在这里直接使用参数progress，即当前滑块代表的进度值
                PreserveEyeManager.instance(MainActivity.this).changeColor(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("------------", "开始滑动！");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("------------", "停止滑动！");
            }
        });
    }

    @Override
    public void onClick(View v) {
        isOpen = !isOpen;
        if (isOpen) {
            PreserveEyeManager.instance(this).changeColor(10);
        } else {
            PreserveEyeManager.instance(this).removeLayout();
        }
    }

}
