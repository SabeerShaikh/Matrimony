package com.matrimony.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.matrimony.domain.RepositoryResponse;
import com.matrimony.domain.model.BioData;
import com.matrimony.module.ui.mainscreen.adapter.model.UIMembers;

import java.util.List;

public interface MetrimonyRepository {
    MutableLiveData<RepositoryResponse<BioData>> getAllData(int size);

    void insert(UIMembers uiMembers);

    void insertAll(List<UIMembers> uiMembersList);

    void update(UIMembers uiMembers);

    void delete(UIMembers uiMembers);

    void deleteAllMembers();

    LiveData<List<UIMembers>> getAllUIMembers();

}
