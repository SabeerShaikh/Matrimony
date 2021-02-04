package com.matrimony.module.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.matrimony.config.DI;
import com.matrimony.config.MatrimonyProducationDI;

public class MatrimonyViewModel extends AndroidViewModel {

    protected static DI di;
    protected static MatrimonyViewModelFactory vmCommonFactory;
    protected Application application;

    public MatrimonyViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        if (di == null) {
            di = new MatrimonyProducationDI(application);
            vmCommonFactory = di.provideViewModelFactory();
        }
    }
}
