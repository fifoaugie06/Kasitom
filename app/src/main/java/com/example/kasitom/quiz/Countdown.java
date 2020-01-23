package com.example.kasitom.quiz;

import android.os.CountDownTimer;
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

        fn_countdown(i, pb_countDown,tv_timer);
    }

    private void fn_countdown(int i, final ProgressBar pb_countDown, final TextView tv_timer) {
        progress = 1;
        endTime = i;

        countDownTimer = new CountDownTimer(endTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setProgress(progress, endTime, pb_countDown);
                progress++;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);

                String newtime = minutes + ":" + seconds;

                if (newtime.equals("0:0:0")) {
                    tv_timer.setText("00:00");
                } else if ((String.valueOf(minutes).length() == 1) && (String.valueOf(seconds).length() == 1)) {
                    tv_timer.setText(minutes + ":0" + seconds);
                } else if ((String.valueOf(minutes).length() == 1)) {
                    tv_timer.setText(minutes + ":" + seconds);
                } else if ((String.valueOf(seconds).length() == 1)) {
                    tv_timer.setText(minutes + ":0" + seconds);
                } else if ((String.valueOf(seconds).length() == 1)) {
                    tv_timer.setText(minutes + ":0" + seconds);
                } else if (String.valueOf(minutes).length() == 1) {
                    tv_timer.setText(minutes + ":" + seconds);
                } else if (String.valueOf(seconds).length() == 1) {
                    tv_timer.setText(minutes + ":0" + seconds);
                } else {
                    tv_timer.setText(minutes + ":" + seconds);
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
