package com.example.kasitom.quiz;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class Countdown {
    private int progress;
    private CountDownTimer countDownTimer;
    private int endTime = 250;

    public void Countdown(int i, ProgressBar pb_countDown, TextView tv_timer) {
        RotateAnimation makeVertical = new RotateAnimation(0, -90, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        makeVertical.setFillAfter(true);
        pb_countDown.startAnimation(makeVertical);
        pb_countDown.setSecondaryProgress(endTime);
        pb_countDown.setProgress(0);

        fn_countdown(i, pb_countDown, tv_timer);
    }

    private void fn_countdown(final int i, final ProgressBar pb_countDown, final TextView tv_timer) {
        progress = 1;
        endTime = i;

        countDownTimer = new CountDownTimer(endTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setProgress(progress, endTime, pb_countDown);
                progress++;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);


                if (String.valueOf(seconds).length() == 1) {
                    if (String.valueOf(minutes).charAt(0) == '0') {
                        tv_timer.setText(String.valueOf(seconds));
                        tv_timer.setTextColor(Color.parseColor("#D81B60"));
                        tv_timer.setTypeface(null, Typeface.BOLD);
                        tv_timer.setTextSize(20);
                        //Log.e("NEWTIME3", "" + seconds); // Show seconds only in Last 10 Seconds
                    } else {
                        tv_timer.setText(minutes + ":0" + seconds);
                        //Log.e("NEWTIME", minutes + ":0" + seconds);
                    }
                } else {
                    tv_timer.setText(minutes + ":" + seconds);
                    //Log.e("NEWTIME2", minutes + ":" + seconds);
                }
            }

            @Override
            public void onFinish() {
                setProgress(progress, endTime, pb_countDown);
            }
        };
        countDownTimer.start();
    }

    private void setProgress(int startTime, int endTime, ProgressBar pb_countDown) {
        pb_countDown.setMax(endTime);
        pb_countDown.setSecondaryProgress(endTime);
        pb_countDown.setProgress(startTime);
    }
}
