package com.matrimony.module.ui.mainscreen.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.matrimony.domain.model.Result;
import com.matrimony.domain.repository.MetrimonyRepository;
import com.matrimony.module.base.MMViewModelResponse;
import com.matrimony.module.base.MatrimonyViewModel;
import com.matrimony.module.ui.mainscreen.adapter.model.UIMembers;
import com.matrimony.module.utils.Const;

import java.util.ArrayList;
import java.util.List;

public class MainScreenViewModel extends MatrimonyViewModel {
    public MetrimonyRepository repository;
    private LiveData<List<UIMembers>> allUIMembers;

    public MainScreenViewModel(@NonNull Application application) {
        super(application);
        repository = di.provideMetrimonyRepository();
        allUIMembers = repository.getAllUIMembers();

    }

    public LiveData<MMViewModelResponse<List<UIMembers>>> getAllData() {

        return Transformations.map(repository.getAllData(10), repoResponse -> {
            if (repoResponse.success) {

                return new MMViewModelResponse<>(mapperMemberInfoToUIMembers(repoResponse.repositoryResponse.results));
            }
            return new MMViewModelResponse<>(repoResponse.failureMessage, repoResponse.code);
        });
    }

    public List<UIMembers> mapperMemberInfoToUIMembers(List<Result> results) {
        ArrayList<UIMembers> uiMembersArrayList = new ArrayList<>();
        results.forEach(result -> {
            UIMembers uiMembers = new UIMembers();
            uiMembers.title = result.name.title;
            uiMembers.memberId = result.id.value;
            uiMembers.firstName = result.name.first;
            uiMembers.secondName = result.name.last;
            uiMembers.streetName = result.location.street.name;
            uiMembers.city = result.location.city;
            uiMembers.state = result.location.state;
            uiMembers.country = result.location.country;
            uiMembers.postcode = result.location.postcode;
            uiMembers.imageUrl = result.picture.large;
            uiMembers.age = result.dob.age;
            uiMembers.acceptOrDeclined = Const.Status.PENDING;

            insert(uiMembers);

            uiMembersArrayList.add(uiMembers);
        });

        return uiMembersArrayList;
    }

    public void insert(UIMembers uiMembers) {
        repository.insert(uiMembers);

    }

    public void update(UIMembers uiMembers) {
        repository.update(uiMembers);
    }

    public void delete(UIMembers uiMembers) {
        repository.delete(uiMembers);
    }

    public void deleteAllMembers() {
        repository.deleteAllMembers();
    }

    public LiveData<List<UIMembers>> getAllUIMembers() {

        return allUIMembers;
    }
}
