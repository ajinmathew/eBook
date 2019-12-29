package com.cs.ajinmathew.project.ebook.ui.viewbook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewBookViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewBookViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue( "This is home fragment" );
    }

    public LiveData<String> getText() {
        return mText;
    }
}