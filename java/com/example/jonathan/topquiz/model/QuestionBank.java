package com.example.jonathan.topquiz.model;

import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        this.mQuestionList = questionList;

        //Shuffle the question list
        Collections.shuffle(mQuestionList);

        mNextQuestionIndex = 0;
    }

    public Question getQuestion(){
        //ensure we loop over the question
        if(mNextQuestionIndex == mQuestionList.size()){
            mNextQuestionIndex = 0;
        }

        //please note the post incrementation
        return mQuestionList.get(mNextQuestionIndex++);
    }
}
