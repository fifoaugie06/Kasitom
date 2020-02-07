package com.example.kasitom.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kasitom.R;
import com.example.kasitom.model.dataQuiz;
import com.example.kasitom.model.dataScoreBoard;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private DecimalFormat decim = new DecimalFormat("###.##");
    private ArrayList<dataQuiz> daftarQuiz;
    private Dialog dialog;

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
            // Clear false button
            optA.setEnabled(true);
            optB.setEnabled(true);
            optC.setEnabled(true);
            optD.setEnabled(true);

            tv_soalPosition.setText("Soal " + (iterator + 1) + " / " + daftarQuiz.size());
            tv_question.setText(daftarQuiz.get(iterator).getSoal());

            optA.setText(daftarQuiz.get(iterator).getOptionA());
            optB.setText(daftarQuiz.get(iterator).getOptionB());
            optC.setText(daftarQuiz.get(iterator).getOptionC());
            optD.setText(daftarQuiz.get(iterator).getOptionD());

            optA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    optB.setEnabled(false);
                    optC.setEnabled(false);
                    optD.setEnabled(false);
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
                    optA.setEnabled(false);
                    optC.setEnabled(false);
                    optD.setEnabled(false);
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
                    optA.setEnabled(false);
                    optB.setEnabled(false);
                    optD.setEnabled(false);
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
                    optA.setEnabled(false);
                    optB.setEnabled(false);
                    optC.setEnabled(false);
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
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        nilai = 100f / countQuestion;
        nilai = nilai * (float) correct;

        database = FirebaseDatabase.getInstance().getReference();
        submitScoreBoard(new dataScoreBoard(googleSignInAccount.getDisplayName(), googleSignInAccount.getPhotoUrl().toString(),
                String.valueOf(correct), decim.format(nilai), String.valueOf(countQuestion)));
        Log.i("tesz", decim.format(nilai));

        showDialogGameOver(decim.format(nilai), String.valueOf(correct), daftarQuiz.size());
    }

    private void submitScoreBoard(final dataScoreBoard dataScoreBoard) {
        database.child("scoreboard")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        // cek apakah child sudah dibuat atau belum
                        if (dataSnapshot.hasChild(dataScoreBoard.getNama())) {
                            database.child("scoreboard")
                                    .child(dataScoreBoard.getNama())
                                    .child("nilai").addValueEventListener(new ValueEventListener() {

                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    // Jika child sudah dibuat
                                    // Cek apakah nilai yang baru didapat lebih besar dari nilai di database
                                    // jika iya replace nilai didatabase
                                    float nilaiBaru = Float.parseFloat(decim.format(nilai));
                                    float nilaiDataBase = Float.parseFloat(dataSnapshot.getValue().toString());
                                    Log.i("nilaiBaru", String.valueOf(nilaiBaru));
                                    Log.i("nilaidataBae", String.valueOf(nilaiDataBase));

                                    if (nilaiBaru > nilaiDataBase) {
                                        updateNilaiBaru(new dataScoreBoard(dataScoreBoard.getNama(), dataScoreBoard.getPhotoURI(),
                                                String.valueOf(correct), String.valueOf(nilaiBaru).replaceAll("\\.?0*$", ""),
                                                String.valueOf(countQuestion)));
                                    } else {
                                        Log.i("replacegagal", "hm");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        } else {
                            // Jika child belum ada, buat child dan isi dulu untuk pertama kali
                            database.child("scoreboard")
                                    .child(dataScoreBoard.getNama())
                                    .setValue(dataScoreBoard)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    // Jika ada nilai lebih besar dari yang ada didatabase update nilai
    private void updateNilaiBaru(final dataScoreBoard dataScoreBoard) {
        database.child("scoreboard")
                .child(dataScoreBoard.getNama())
                .setValue(dataScoreBoard).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
    }

    private void showDialogGameOver(String nilai, String correct, int size) {
        dialog = new Dialog(this);
        Window window = dialog.getWindow();

        window.setBackgroundDrawable(new InsetDrawable(new ColorDrawable(Color.TRANSPARENT), 50));
        dialog.setContentView(R.layout.dialog_scoreboard);

        try {
            if (!(this).isFinishing()) {
                dialog.show();
            }
        } catch (WindowManager.BadTokenException e) {
            Log.i("apa token", String.valueOf(e));
        }
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final TextView tvScore, tvAttempt;
        final Button btnScoreboard, btnMenu;

        tvScore = dialog.findViewById(R.id.tv_Score);
        tvAttempt = dialog.findViewById(R.id.tv_attempt);
        btnScoreboard = dialog.findViewById(R.id.btn_scoreBoard);
        btnMenu = dialog.findViewById(R.id.btn_menu);

        tvScore.setText(nilai + " Score");
        tvAttempt.setText("You attempt " + size + " questions and\nfrom that " + correct + " answer is correct");

        btnScoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, ScoreboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                dialog.dismiss();
                finish();
            }
        });

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
        button.setEnabled(true); // make true to show drawable
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
    protected void onDestroy() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
        super.onDestroy();
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
