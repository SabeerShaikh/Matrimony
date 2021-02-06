package com.matrimony.domain.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.matrimony.domain.RepositoryResponse;
import com.matrimony.domain.model.BioData;
import com.matrimony.domain.repository.dao.MembersDAO;
import com.matrimony.domain.repository.database.AppDataBase;
import com.matrimony.domain.repository.remote.APIErrorResponse;
import com.matrimony.domain.repository.remote.api.MetrimonyService;
import com.matrimony.module.ui.mainscreen.adapter.model.UIMembers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MetrimonyRepositoryImpl implements MetrimonyRepository {


    private MetrimonyService service;
    private MembersDAO membersDao;
    private LiveData<List<UIMembers>> allMembers;

    public MetrimonyRepositoryImpl(MetrimonyService fileService, Application application) {
        this.service = fileService;
        AppDataBase appDataBase = AppDataBase.getInstance(application.getApplicationContext());
        membersDao = appDataBase.membersDAO();
        allMembers = membersDao.getAllData();

//        Log.d("TAG", "inserted111" + allMembers.getValue().size());

    }

    @Override
    public MutableLiveData<RepositoryResponse<BioData>> getAllData(int size) {
        MutableLiveData<RepositoryResponse<BioData>> result = new MutableLiveData<>();
        service.getAllData(size).enqueue(new Callback<BioData>() {
            @Override
            public void onResponse(Call<BioData> call, Response<BioData> response) {

                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(new RepositoryResponse<>(response.body()));

                } else {
                    APIErrorResponse apiErrorResponse = service.errorResponseHandler(response.errorBody());
                    result.setValue(new RepositoryResponse<BioData>(apiErrorResponse.getError(),
                            apiErrorResponse.getStatusCode()));
                    return;
                }
            }

            @Override
            public void onFailure(Call<BioData> call, Throwable t) {
                result.setValue(new RepositoryResponse<BioData>(t.getMessage(),
                        401));

            }
        });
        return result;
    }


    @Override
    public void insert(UIMembers uiMembers) {
       // Log.d("TAG",""+uiMembers.secondName);

        new InsertMembersAsyncTask(membersDao).execute(uiMembers);
    }

    @Override
    public void insertAll(List<UIMembers> uiMembersList) {
        // Log.d("TAG",""+uiMembers.secondName);

        //new InsertPlayerAsyncTask(membersDao).execute(uiMembers);
    }

    @Override
    public void update(UIMembers uiMembers) {
        new UpdatePlayerAsyncTask(membersDao).execute(uiMembers);
    }

    @Override
    public void delete(UIMembers uiMembers) {
        new DeletePlayerAsyncTask(membersDao).execute(uiMembers);
    }

    @Override
    public void deleteAllMembers() {
        new DeleteAllPlayersAsyncTask(membersDao).execute();
    }

    @Override
    public LiveData<List<UIMembers>> getAllUIMembers() {
        return allMembers;
    }

  /*  private static class InsertALLMembersAsyncTask extends AsyncTask<List<UIMembers>, Void, Void> {

        private MembersDAO memberDao;


        public InsertMembersAsyncTask(MembersDAO memberDao) {
            this.memberDao = memberDao;
        }


        @Override
        protected Void doInBackground(List<UIMembers uiMembers) {
            //Log.d("TAG",""+uiMembers.length);
            memberDao.insert(uiMembers[0]);
            return null;
        }
    }*/

    private static class InsertMembersAsyncTask extends AsyncTask<UIMembers, Void, Void> {

        private MembersDAO memberDao;


        public InsertMembersAsyncTask(MembersDAO memberDao) {
            this.memberDao = memberDao;
        }


        @Override
        protected Void doInBackground(UIMembers... uiMembers) {
            //Log.d("TAG",""+uiMembers.length);
            memberDao.insert(uiMembers[0]);
            return null;
        }
    }

    private static class UpdatePlayerAsyncTask extends AsyncTask<UIMembers, Void, Void> {

        private MembersDAO memberDao;

        public UpdatePlayerAsyncTask(MembersDAO memberDao) {
            this.memberDao = memberDao;
        }

        @Override
        protected Void doInBackground(UIMembers... uiMembers) {
            memberDao.update(uiMembers[0]);
            return null;
        }
    }

    //AsyncTask for delete existing player
    private static class DeletePlayerAsyncTask extends AsyncTask<UIMembers, Void, Void> {

        private MembersDAO memberDao;

        public DeletePlayerAsyncTask(MembersDAO memberDao) {
            this.memberDao = memberDao;
        }

        @Override
        protected Void doInBackground(UIMembers... uiMembers) {
            memberDao.delete(uiMembers[0]);
            return null;
        }
    }

    //AsyncTask for delete all players
    private static class DeleteAllPlayersAsyncTask extends AsyncTask<Void, Void, Void> {

        private MembersDAO membersDAO;

        public DeleteAllPlayersAsyncTask(MembersDAO membersDAO) {
            this.membersDAO = membersDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            membersDAO.deleteAllPlayer();
            return null;
        }
    }
}
