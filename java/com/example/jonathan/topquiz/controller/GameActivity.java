package com.example.jonathan.topquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathan.topquiz.R;
import com.example.jonathan.topquiz.model.Question;
import com.example.jonathan.topquiz.model.QuestionBank;

import java.sql.Array;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    TextView mQuestionText;
    private Button mAnswerButton1;
    private Button mAnswerButton2;
    private Button mAnswerButton3;
    private Button mAnswerButton4;

    private Question mCurrentQuestion;
    private QuestionBank mQuestionBank;

    private int mScore;
    public  int mNbreQuestion;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String SAVED_INSTANCE_SCORE = "score";
    public static final String SAVED_INSTANCE_NBRE_QUESTION = "nbreQuestion";

    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt("SAVED_INSTANCE_SCORE");
            mNbreQuestion = savedInstanceState.getInt("SAVED_INSTANCE_NBRE_QUESTION");
        }
        else{
            mScore = 0;
            mNbreQuestion = 3;
        }

        mQuestionBank = this.generateQuestions();

        mEnableTouchEvents = true;

        mQuestionText = (TextView)findViewById(R.id.activity_game_question_txt);
        mAnswerButton1 = (Button)findViewById(R.id.activity_game_answer1_btn);
        mAnswerButton2 = (Button)findViewById(R.id.activity_game_answer2_btn);
        mAnswerButton3 = (Button)findViewById(R.id.activity_game_answer3_btn);
        mAnswerButton4 = (Button)findViewById(R.id.activity_game_answer4_btn);

        mAnswerButton1.setTag(0);
        mAnswerButton2.setTag(1);
        mAnswerButton3.setTag(2);
        mAnswerButton4.setTag(3);

        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
        mAnswerButton3.setOnClickListener(this);
        mAnswerButton4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    private void displayQuestion(final Question question){
        mQuestionText.setText(question.getQuestion());
        mAnswerButton1.setText(question.getChoiceList().get(0));
        mAnswerButton2.setText(question.getChoiceList().get(1));
        mAnswerButton3.setText(question.getChoiceList().get(2));
        mAnswerButton4.setText(question.getChoiceList().get(3));
    }

    @Override
    public void onClick(View view) {
        int responseIndex = (int) view.getTag();

        if(responseIndex == mCurrentQuestion.getAnswerIndex()){
            //Good answer
            Toast.makeText(this,"bonne réponse",Toast.LENGTH_SHORT).show();
            mScore++;
        }
        else{
            //wrong answer
            Toast.makeText(this,"mauvaise réponse",Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;
                //if this is the last question End the Game
                if(--mNbreQuestion == 0) {
                    //End the Game
                    endGame();
                }
                //else display the next question
                else{
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        },2000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("SAVED_INSTANCE_SCORE",mScore);
        outState.putInt("SAVED_INSTANCE_NBRE_QUESTION",mNbreQuestion);
        super.onSaveInstanceState(outState);
    }

    private void endGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Merci d'avoir joué")
                .setMessage("votre score est de "+mScore+" point(s)")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //End the Activity
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE,mScore);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                })
                .create()
                .show();
    }

    private QuestionBank generateQuestions() {
        Question question1 = new Question("quand a débuté la crise anglophone au Cameroun ?",Arrays.asList("2016","1960","2017","2010"),0);
        Question question2 = new Question("quand  eu lieu l'indépendance du Cameroun ?",Arrays.asList("2016","1960","2017","2010"),1);
        Question question3 = new Question("quand est mort Koffi Anan ?",Arrays.asList("2016","1960","2017","2018"),3);
        return new QuestionBank(Arrays.asList(question1,question2,question3));
    }
}
