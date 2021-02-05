package com.matrimony.module.base;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.matrimony.module.ui.mainscreen.viewmodel.MainScreenViewModel;

import org.jetbrains.annotations.NotNull;


public class MatrimonyViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;

    public MatrimonyViewModelFactory(Application application) {
        mApplication = application;
    }

    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {

        if (modelClass == MainScreenViewModel.class) {
            return (T) new MainScreenViewModel(mApplication);
        }
        return super.create(modelClass);
    }
}
