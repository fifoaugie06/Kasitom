package com.example.kasitom.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
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

import static com.example.kasitom.R.drawable.ic_edittext_style;
import static com.example.kasitom.R.drawable.ic_optioncorrect_style;
import static com.example.kasitom.R.drawable.ic_optionwrong_style;

public class QuizActivity extends AppCompatActivity {
    private TextView tv_question, tv_soalPosition, tv_timer;
    private ProgressBar pb_countDown;
    private Button optA, optB, optC, optD;
    private DatabaseReference database;
    private int computerCount, correct = 0;
    private long countQuestion = 0;
    private float nilai;
    private Countdown countdown = new Countdown();

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
                countQuestion = dataSnapshot.getChildrenCount();

                updateQuestion(countQuestion); //Update quiz sampai max
                countdown.Countdown((int) (countQuestion*5), pb_countDown, tv_timer); //Make countdownProgressBar
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateQuestion(final long countQuestion) {
        computerCount++;
        tv_soalPosition.setText("Soal " + computerCount + " / " + countQuestion);

        if (computerCount > countQuestion) {
            nilai = 100f/countQuestion;
            nilai = nilai*(float) correct;
            DecimalFormat decim = new DecimalFormat("#,##.##");

            Toast.makeText(QuizActivity.this, decim.format(nilai), Toast.LENGTH_LONG).show();
        } else {
            database = FirebaseDatabase.getInstance().getReference().child("Quiz").child(String.valueOf(computerCount));

            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final dataQuiz dataQuiz = dataSnapshot.getValue(dataQuiz.class);
                    tv_question.setText(dataQuiz.getSoal());
                    optA.setText(dataQuiz.getOptionA());
                    optB.setText(dataQuiz.getOptionB());
                    optC.setText(dataQuiz.getOptionC());
                    optD.setText(dataQuiz.getOptionD());

                    optA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (optA.getText().toString().equals(dataQuiz.getJawaban())) {
                                correctAnswer(optA);
                                correct++;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        clearAnswer(optA);

                                        updateQuestion(countQuestion);
                                    }
                                }, 800);

                            } else {
                                wrongAnswer(optA);

                                if (optB.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optB);
                                } else if (optC.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optC);
                                } else if (optD.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optD);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        clearAnswer(optA);
                                        clearAnswer(optB);
                                        clearAnswer(optC);
                                        clearAnswer(optD);
                                        updateQuestion(countQuestion);
                                    }
                                }, 800);
                            }
                        }
                    });

                    optB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (optB.getText().toString().equals(dataQuiz.getJawaban())) {
                                correctAnswer(optB);
                                correct++;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        clearAnswer(optB);

                                        updateQuestion(countQuestion);
                                    }
                                }, 800);

                            } else {
                                wrongAnswer(optB);

                                if (optA.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optA);
                                } else if (optC.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optC);
                                } else if (optD.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optD);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        clearAnswer(optA);
                                        clearAnswer(optB);
                                        clearAnswer(optC);
                                        clearAnswer(optD);
                                        updateQuestion(countQuestion);
                                    }
                                }, 800);
                            }
                        }
                    });

                    optC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (optC.getText().toString().equals(dataQuiz.getJawaban())) {
                                correctAnswer(optC);
                                correct++;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        clearAnswer(optC);

                                        updateQuestion(countQuestion);
                                    }
                                }, 800);

                            } else {
                                wrongAnswer(optC);

                                if (optA.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optA);
                                } else if (optB.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optB);
                                } else if (optD.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optD);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        clearAnswer(optA);
                                        clearAnswer(optB);
                                        clearAnswer(optC);
                                        clearAnswer(optD);
                                        updateQuestion(countQuestion);
                                    }
                                }, 800);
                            }
                        }
                    });

                    optD.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (optD.getText().toString().equals(dataQuiz.getJawaban())) {
                                correctAnswer(optD);
                                correct++;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        clearAnswer(optD);

                                        updateQuestion(countQuestion);
                                    }
                                }, 800);

                            } else {
                                wrongAnswer(optD);

                                if (optA.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optA);
                                } else if (optB.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optB);
                                } else if (optC.getText().toString().equals(dataQuiz.getJawaban())) {
                                    correctAnswer(optC);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        clearAnswer(optA);
                                        clearAnswer(optB);
                                        clearAnswer(optC);
                                        clearAnswer(optD);
                                        updateQuestion(countQuestion);
                                    }
                                }, 800);
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
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
