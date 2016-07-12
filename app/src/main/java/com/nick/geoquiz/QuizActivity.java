package com.nick.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private final Question[] bank = new Question[]{
            new Question(R.string.question_1,false),
            new Question(R.string.question_2,false),
            new Question(R.string.question_3,true),
            new Question(R.string.question_4,false),
            new Question(R.string.question_5,false),
            new Question(R.string.question_6,false),
            new Question(R.string.question_7,true),
            new Question(R.string.question_8,true),
            new Question(R.string.question_9,false),
            new Question(R.string.question_10,true),
    };

    private TextView mTextView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button cheatButton;
    private int index = 0;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG,"onCreate() called");

        if (savedInstanceState != null) index = savedInstanceState.getInt(KEY_INDEX,0);
        mTextView = (TextView) findViewById(R.id.question_text_view);
        mTrueButton = (Button) findViewById(R.id.true_button);  //正确
        mFalseButton = (Button) findViewById(R.id.false_button);//错误
        mNextButton = (Button) findViewById(R.id.next_button);//下一题
        cheatButton = (Button) findViewById(R.id.cheat_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAnswerCorrect(true);
                if(++index >= bank.length) index = 0;
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAnswerCorrect(false);
                if(++index >= bank.length) index = 0;
                updateQuestion();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (++index == bank.length) index = 0;
                updateQuestion();
            }
        });

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = CheatActivity.newIntent(QuizActivity.this,bank[index].isAnswerTrue());
                //startActivity(i);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });
        updateQuestion();
    }

    private void updateQuestion() {
        int question = bank[index].getResourceId();
        mTextView.setText(question);
    }

    /**
     * 显示答案
     * @param answer 答案
     */
    private void isAnswerCorrect(boolean answer){
        boolean result = bank[index].isAnswerTrue();
        int messageResId = 0;
        if(mIsCheater){
            messageResId = R.string.judgment_toast;
        }else{
            if (answer == result){
                messageResId = R.string.correct;
            }else{
                messageResId = R.string.incorrect;
            }
        }
        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState()");
        outState.putInt(KEY_INDEX,index);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"onActivityResult() called");
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            if (data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }

    }

}
