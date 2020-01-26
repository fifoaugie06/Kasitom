package com.example.kasitom.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kasitom.R;
import com.example.kasitom.model.dataQuiz;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.view.animation.Animation.RELATIVE_TO_SELF;
import static com.example.kasitom.R.drawable.ic_edittext_style;
import static com.example.kasitom.R.drawable.ic_optioncorrect_style;
import static com.example.kasitom.R.drawable.ic_optionwrong_style;

public class QuizActivity extends AppCompatActivity {
    private TextView tv_question, tv_soalPosition, tv_timer;
    private ProgressBar pb_countDown;
    private int endTime = 250, progress, correct = 0, iterator = -1;
    private CountDownTimer countDownTimer;
    private Button optA, optB, optC, optD;
    private DatabaseReference database;
    private long countQuestion = 0;
    private float nilai;
    private ArrayList<dataQuiz> daftarQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initView();
        countQuestionData(); //Count max data quiz Firebase


        getSupportActionBar().hide();
    }


    private void countQuestionData() {
        database.child("Quiz").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                countQuestion = dataSnapshot.getChildrenCount(); // cari maks soal

                daftarQuiz = new ArrayList<>(); // Tampung dulu di list
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    dataQuiz dataQuiz = noteDataSnapshot.getValue(dataQuiz.class);
                    dataQuiz.setKey(noteDataSnapshot.getKey());

                    daftarQuiz.add(dataQuiz);
                }

                updateQuestion(); //Update quiz sampai max sesuai iterator
                reverseTimer(countQuestion); // make countdown

                //Log.i("cek", daftarQuiz.get(0).getSoal());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateQuestion() {
        iterator++;

        if (iterator > (daftarQuiz.size() - 1)) {
            optA.setEnabled(false);
            optB.setEnabled(false);
            optC.setEnabled(false);
            optD.setEnabled(false);
            countDownTimer.cancel();
            isGameOver();
        } else {
            tv_soalPosition.setText("Soal " + (iterator + 1) + " / " + daftarQuiz.size());
            tv_question.setText(daftarQuiz.get(iterator).getSoal());
            optA.setText(daftarQuiz.get(iterator).getOptionA());
            optB.setText(daftarQuiz.get(iterator).getOptionB());
            optC.setText(daftarQuiz.get(iterator).getOptionC());
            optD.setText(daftarQuiz.get(iterator).getOptionD());

            optA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (optA.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                        correctAnswer(optA);
                        correct++;

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clearAnswer(optA);

                                updateQuestion();
                            }
                        }, 800);

                    } else {
                        wrongAnswer(optA);

                        if (optB.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optB);
                        } else if (optC.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optC);
                        } else if (optD.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optD);
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                clearAnswer(optA);
                                clearAnswer(optB);
                                clearAnswer(optC);
                                clearAnswer(optD);
                                updateQuestion();
                            }
                        }, 800);
                    }
                }
            });

            optB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (optB.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                        correctAnswer(optB);
                        correct++;

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clearAnswer(optB);

                                updateQuestion();
                            }
                        }, 800);

                    } else {
                        wrongAnswer(optB);

                        if (optA.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optA);
                        } else if (optC.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optC);
                        } else if (optD.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optD);
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                clearAnswer(optA);
                                clearAnswer(optB);
                                clearAnswer(optC);
                                clearAnswer(optD);
                                updateQuestion();
                            }
                        }, 800);
                    }
                }
            });

            optC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (optC.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                        correctAnswer(optC);
                        correct++;

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clearAnswer(optC);

                                updateQuestion();
                            }
                        }, 800);

                    } else {
                        wrongAnswer(optC);

                        if (optA.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optA);
                        } else if (optB.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optB);
                        } else if (optD.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optD);
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                clearAnswer(optA);
                                clearAnswer(optB);
                                clearAnswer(optC);
                                clearAnswer(optD);
                                updateQuestion();
                            }
                        }, 800);
                    }
                }
            });

            optD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (optD.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                        correctAnswer(optD);
                        correct++;

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clearAnswer(optD);

                                updateQuestion();
                            }
                        }, 800);

                    } else {
                        wrongAnswer(optD);

                        if (optA.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optA);
                        } else if (optB.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optB);
                        } else if (optC.getText().toString().equals(daftarQuiz.get(iterator).getJawaban())) {
                            correctAnswer(optC);
                        }

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                clearAnswer(optA);
                                clearAnswer(optB);
                                clearAnswer(optC);
                                clearAnswer(optD);
                                updateQuestion();
                            }
                        }, 800);
                    }
                }
            });
        }
    }


    private void reverseTimer(long countQuestion) {
        RotateAnimation makeVertical = new RotateAnimation(0, -90, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        makeVertical.setFillAfter(true);
        pb_countDown.startAnimation(makeVertical);
        pb_countDown.setSecondaryProgress(endTime);
        pb_countDown.setProgress(0);

        endTime = (int) (countQuestion * 8);
        countDownTimer = new CountDownTimer(endTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setProgress(progress, endTime);
                progress = progress + 1;
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
                isGameOver();
                setProgress(progress, endTime);
            }
        }.start();
    }

    private void setProgress(int startTime, int endTime) {
        pb_countDown.setMax(endTime);
        pb_countDown.setSecondaryProgress(endTime);
        pb_countDown.setProgress(startTime);
    }

    private void isGameOver() {
        nilai = 100f / countQuestion;
        nilai = nilai * (float) correct;
        DecimalFormat decim = new DecimalFormat("###.##");

        Toast.makeText(QuizActivity.this, decim.format(nilai), Toast.LENGTH_LONG).show();
    }


    private void clearAnswer(Button button) {
        Drawable drawable = getResources().getDrawable(ic_edittext_style);

        button.setBackgroundDrawable(drawable);
    }

    private void wrongAnswer(Button button) {
        Drawable drawable = getResources().getDrawable(ic_optionwrong_style);

        button.setBackgroundDrawable(drawable);
    }

    private void correctAnswer(Button button) {
        Drawable drawable = getResources().getDrawable(ic_optioncorrect_style);

        button.setBackgroundDrawable(drawable);
    }


    private void initView() {
        database = FirebaseDatabase.getInstance().getReference();

        optA = findViewById(R.id.btn_optionA);
        optB = findViewById(R.id.btn_optionB);
        optC = findViewById(R.id.btn_optionC);
        optD = findViewById(R.id.btn_optionD);

        tv_question = findViewById(R.id.tv_Question);
        tv_timer = findViewById(R.id.tv_timer);
        tv_soalPosition = findViewById(R.id.count_Questions);

        pb_countDown = findViewById(R.id.pb_countDown);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
