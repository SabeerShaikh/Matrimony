package com.matrimony.module.ui.mainscreen.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.matrimony.R;
import com.matrimony.databinding.ItemMetrimonyUserBinding;
import com.matrimony.module.ui.mainscreen.adapter.model.UIMembers;
import com.matrimony.module.utils.Const;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MetriomonyAdapter extends
        RecyclerView.Adapter<MetriomonyAdapter.MetrimonyViewHolder> {
    public final MutableLiveData<UIMembers> onAcceptClick = new MutableLiveData<>();
    public final MutableLiveData<UIMembers> onDeclinedClick = new MutableLiveData<>();
    Context context;
    private final List<UIMembers> uiMembersArrayList = new ArrayList<>();


    public MetriomonyAdapter(Context context) {
        this.context = context;
    }

    public void clearData() {
        uiMembersArrayList.clear();
        notifyDataSetChanged();
    }

    public void appendData(List<UIMembers> list) {
        if (!uiMembersArrayList.contains(list)) {
            this.uiMembersArrayList.addAll(list);
            //notifyDataSetChanged();
            notifyItemRangeChanged(uiMembersArrayList.size(), list.size());

        }

    }

    @NonNull
    @Override
    public MetrimonyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        ItemMetrimonyUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_metrimony_user, parent, false);
        return new MetrimonyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MetrimonyViewHolder holder, int position) {
        UIMembers uiMembers = uiMembersArrayList.get(position);
        uiMembers.position = position;
        holder.vbinding.setUiMembers(uiMembers);


        try {
            Picasso.get().load(uiMembers.imageUrl).into(holder.vbinding.viewUserProfilePic);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        updateStatus(holder, uiMembers.acceptOrDeclined);
    }

    public void updateStatus(MetrimonyViewHolder vh, String status) {
        if (status.equals(Const.Status.ACCEPTED)) {
            vh.vbinding.tvMessage.setVisibility(View.VISIBLE);
            vh.vbinding.tvMessage.setText(R.string.accepted);
            vh.vbinding.tvMessage.setTextColor(context.getResources().getColor(R.color.green));
            vh.vbinding.loActionButton.setVisibility(View.GONE);
        } else if (status.equals(Const.Status.DECLINED)) {

            vh.vbinding.tvMessage.setVisibility(View.VISIBLE);
            vh.vbinding.tvMessage.setText(R.string.declined);
            vh.vbinding.tvMessage.setTextColor(context.getResources().getColor(R.color.red));
            vh.vbinding.loActionButton.setVisibility(View.GONE);
        } else if (status.equals(Const.Status.PENDING)) {
            vh.vbinding.tvMessage.setVisibility(View.GONE);
            vh.vbinding.loActionButton.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return uiMembersArrayList.size();
    }

    public class MetrimonyViewHolder extends RecyclerView.ViewHolder {
        ItemMetrimonyUserBinding vbinding;

        public MetrimonyViewHolder(ItemMetrimonyUserBinding binding) {
            super(binding.getRoot());
            this.vbinding = binding;
            this.vbinding.setCallback(this);

        }

        public void onClickAccept() {
            UIMembers uiMembers = uiMembersArrayList.get(getAdapterPosition());
            uiMembers.position = getAdapterPosition();
            uiMembers.acceptOrDeclined = Const.Status.ACCEPTED;
            vbinding.tvMessage.setVisibility(View.VISIBLE);
            vbinding.tvMessage.setText(R.string.accepted);
            vbinding.tvMessage.setTextColor(context.getResources().getColor(R.color.green));
            vbinding.loActionButton.setVisibility(View.GONE);
            //notifyItemChanged(getAdapterPosition());
            onAcceptClick.postValue(uiMembers);

        }

        public void onClickDeclined() {

            UIMembers uiMembers = uiMembersArrayList.get(getAdapterPosition());
            uiMembers.position = getAdapterPosition();
            uiMembers.acceptOrDeclined = Const.Status.DECLINED;

            vbinding.tvMessage.setVisibility(View.VISIBLE);
            vbinding.tvMessage.setText(R.string.declined);
            vbinding.tvMessage.setTextColor(context.getResources().getColor(R.color.red));
            vbinding.loActionButton.setVisibility(View.GONE);
            //notifyItemChanged(getAdapterPosition());
            onDeclinedClick.postValue(uiMembers);

        }

    }

}
