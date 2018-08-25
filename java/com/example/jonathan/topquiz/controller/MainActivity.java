package com.example.jonathan.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jonathan.topquiz.R;
import com.example.jonathan.topquiz.model.User;

public class MainActivity extends AppCompatActivity {
    private TextView mgreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private Button mHistoriqueButton;
    private User mUser;

    private SharedPreferences mPreferences;

    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    public static final String PREFERENCES_FIRSTNAME = "firstname";
    public static final String PREFERENCES_SCORE = "score";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE,0);

            mPreferences.edit().putInt(PREFERENCES_SCORE,score).apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getPreferences(MODE_PRIVATE);

        mUser = new User();
        //referençons les elts graphiques
        mgreetingText = (TextView)findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText)findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button)findViewById(R.id.activity_main_play_btn);
        mHistoriqueButton = (Button)findViewById(R.id.activity_main_historique_btn);

        if(mPreferences.getAll() == null){
            mHistoriqueButton.setVisibility(mHistoriqueButton.INVISIBLE);
        }
        else {
            mHistoriqueButton.setVisibility(mHistoriqueButton.VISIBLE);
        }

        mPlayButton.setEnabled(false);

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPlayButton.setEnabled(charSequence.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser.setFirstname(mNameInput.getText().toString());

                mPreferences.edit().putString(PREFERENCES_FIRSTNAME,mUser.getFirstname()).apply();

                Intent gameActivity = new Intent(MainActivity.this,GameActivity.class);
                //startActivity(gameActivity);
                startActivityForResult(gameActivity,GAME_ACTIVITY_REQUEST_CODE);
            }
        });

        mHistoriqueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent historiqueActivity = new Intent(MainActivity.this,HistoriqueActivity.class);
                startActivity(historiqueActivity);
            }
        });

    }
}
