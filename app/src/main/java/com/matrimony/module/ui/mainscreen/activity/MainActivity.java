package com.matrimony.module.ui.mainscreen.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.matrimony.R;
import com.matrimony.config.DI;
import com.matrimony.config.MatrimonyProducationDI;
import com.matrimony.databinding.ActivityMainBinding;
import com.matrimony.module.ui.mainscreen.adapter.MetriomonyAdapter;
import com.matrimony.module.ui.mainscreen.adapter.model.UIMembers;
import com.matrimony.module.ui.mainscreen.viewmodel.MainScreenViewModel;
import com.matrimony.module.utils.networkchecker.Networkchecker;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding vBinding;
    private MainScreenViewModel viewModel;
    private DI di;
    private MetriomonyAdapter metriomonyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        di = new MatrimonyProducationDI(this.getApplication());

        vBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vBinding.setCallback(this);
        viewModel = (new ViewModelProvider(MainActivity.this, di.provideViewModelFactory())).get(MainScreenViewModel.class);

        init();
        if (Networkchecker.isNetworkAvailable(this)) {
            if (viewModel.getAllData() != null) {
                viewModel.deleteAllPlayers();
            }
            loadAllData();
        } else {
            viewModel.getAllUIMembers().observe(this, new Observer<List<UIMembers>>() {
                @Override
                public void onChanged(List<UIMembers> uiMembersList) {
                    metriomonyAdapter.appendData(uiMembersList);

                }
            });


        }

    }

    public void init() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        vBinding.rvAllData.setLayoutManager(mLayoutManager);
        vBinding.rvAllData.setItemAnimator(new DefaultItemAnimator());
        metriomonyAdapter = new MetriomonyAdapter(this);
        metriomonyAdapter.onAcceptClick.observe(this, result -> {
            viewModel.update(result);

        });
        metriomonyAdapter.onDeclinedClick.observe(this, result -> {
            viewModel.update(result);

        });

    }

    private void showloader(boolean show) {
        int visibility = show ? View.INVISIBLE : View.VISIBLE;

        vBinding.pbForecast.setVisibility(show ? View.VISIBLE : View.GONE);
        vBinding.rvAllData.setVisibility(visibility);

    }

    public void loadAllData() {
        showloader(true);
        if (Networkchecker.isNetworkAvailable(this)) {
            viewModel.getAllData().observe(this, vmResponse -> {
                showloader(false);
                if (vmResponse.success) {
                    if (vmResponse.response != null) {
                        metriomonyAdapter.appendData(vmResponse.response);

                        //  vBinding.setCurrent(vmResponse.response.current);
                    }
                }
            });
            vBinding.rvAllData.setAdapter(metriomonyAdapter);

        } else {
            showloader(false);

        }
    }
}