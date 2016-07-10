package com.bizagi.app.workportal.inbox.ui.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bizagi.app.workportal.R;
import com.bizagi.app.workportal.db.entities.Vacation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alice on 7/8/16.
 */
public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder> {
    Context context;
    List<Vacation> vacationList;
    private OnItemClickListener onItemClickListener;

    public InboxAdapter(Context context, List<Vacation> vacationList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.vacationList = vacationList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inbox_row_element, parent, false);
        return new ViewHolder(view);
    }


    public void setVacationsRequest(List<Vacation> vacationList) {
        this.vacationList = vacationList;
        notifyDataSetChanged();
    }

    public void  updateVacationsRequest(Vacation vacationRequest){
        int position =  vacationList.indexOf(vacationRequest);
        if (position >= 0 ){
            vacationList.remove(position);
            vacationList.add(vacationRequest);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vacation vacationRequest = vacationList.get(position);
        holder.txtApproved.setText(vacationRequest.getApproved());
        holder.txtbeginDate.setText(vacationRequest.getBeginDate());
        holder.txtEmploye.setText(vacationRequest.getEmployee());

        String status = holder.txtApproved.getText().toString();
        if (status.equalsIgnoreCase(Vacation.STATUS_ACCEPTED)){
            holder.txtApproved.setBackgroundColor( context.getResources().getColor(android.R.color.holo_blue_light));
        }else if(status.equalsIgnoreCase(Vacation.STATUS_REJECTED)){

            holder.txtApproved.setBackgroundColor( context.getResources().getColor(android.R.color.holo_orange_dark));
        }else {
            holder.txtApproved.setText("Pending");
            holder.txtApproved.setBackgroundColor( context.getResources().getColor(android.R.color.darker_gray));
        }

        holder.setOnClickListener(vacationRequest, onItemClickListener);

    }

    private int fetchColor(int color) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[]{color});
        int returnColor = a.getColor(0, 0);
        a.recycle();
        return returnColor;
    }

    @Override
    public int getItemCount() {
        return vacationList.size();
    }

    public void addVaction(Vacation nVacation) {
        vacationList.add(nVacation);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtApproved)
        TextView txtApproved;
        @BindView(R.id.txtbeginDate)
        TextView txtbeginDate;
        @BindView(R.id.txtEmploye)
        TextView txtEmploye;
        @BindView(R.id.cordinator)
        CoordinatorLayout cordinator;

        private View view;


        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        public void setOnClickListener(final Vacation vacationRequest, final OnItemClickListener onItemClickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(vacationRequest);
                }
            });
        }
    }
}
