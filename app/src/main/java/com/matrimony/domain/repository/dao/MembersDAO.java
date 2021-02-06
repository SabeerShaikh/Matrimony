package com.matrimony.domain.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.matrimony.module.ui.mainscreen.adapter.model.UIMembers;

import java.util.List;

@Dao
public interface MembersDAO {
    @Insert
    void insert(UIMembers uiMembers);

    @Insert
    void insertAll(List<UIMembers> uiMembers);

    @Update
    void update(UIMembers uiMembers);

    @Delete
    void delete(UIMembers uiMembers);

    //Delete all player from table
    @Query("DELETE FROM Members")
    void deleteAllPlayer();

    @Query("Select * FROM Members")
    LiveData<List<UIMembers>> getAllData();
}
