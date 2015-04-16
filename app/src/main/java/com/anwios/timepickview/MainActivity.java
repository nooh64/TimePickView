package com.anwios.timepickview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anwios.android.views.TimePickView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, TimePickView.onTimeSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1 = (Button) findViewById(R.id.button1);
        Button bt2 = (Button) findViewById(R.id.button2);
        Button bt3 = (Button) findViewById(R.id.button3);
        Button bt4 = (Button) findViewById(R.id.button4);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);

        TimePickView view = (TimePickView) findViewById(R.id.mv);
        view.setOnTimeSetListener(this);
        view.setSeparator("-");
        view.setTime(10,10);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        final TimePickView pickView = (TimePickView) findViewById(R.id.mv);
        switch (id) {
            case R.id.button1:
                pickView.setTime();
                break;
            case R.id.button2:
                pickView.setHour();
                break;
            case R.id.button3:
                pickView.setMinute();
                break;
            case R.id.button4:
                if (pickView.isMoveHourhandOnMinute()) {
                    pickView.setMoveHourhandOnMinute(false);
                } else {
                    pickView.setMoveHourhandOnMinute(true);
                }

                break;
        }
    }


    @Override
    public void beforeTimeChanged(int hour, int minute) {
        Toast.makeText(this, "Before Setting :" + hour + " " + minute, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeChanged(int hour, int minute) {

    }

    @Override
    public void afterTimeChanged(int hour, int minute) {
        Toast.makeText(this, "AfterSetting :" + hour + " " + minute, Toast.LENGTH_SHORT).show();
    }
}
