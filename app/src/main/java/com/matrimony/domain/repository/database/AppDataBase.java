package com.matrimony.domain.repository.database;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.matrimony.domain.repository.dao.MembersDAO;
import com.matrimony.module.ui.mainscreen.adapter.model.UIMembers;

@Database(entities = {UIMembers.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public abstract MembersDAO membersDAO();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            //If instance is null that's mean database is not created and create a new database instance
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "matrimony")
                    .addCallback(roomCallBack)
                    .build();
        }
        Log.d("TAG", "Create Database"+instance);


        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("TAG", "Create Database");

        }
    };


}
