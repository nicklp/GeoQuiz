package com.nick.geoquiz;

/**
 * Created by Nick on 2016/5/27.
 */
public class Question {
    public Question(int resourceId, boolean isAnswerTrue) {
        mResourceId = resourceId;
        mIsAnswerTrue = isAnswerTrue;
    }

    private int mResourceId;
    private boolean mIsAnswerTrue;

    public int getResourceId() {
        return mResourceId;
    }

    public void setResourceId(int resourceId) {
        mResourceId = resourceId;
    }

    public boolean isAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mIsAnswerTrue = answerTrue;
    }
}
