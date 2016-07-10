package com.bizagi.app.workportal.inbox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bizagi.app.workportal.App;
import com.bizagi.app.workportal.R;
import com.bizagi.app.workportal.db.entities.Vacation;
import com.bizagi.app.workportal.inbox.InboxPresenter;
import com.bizagi.app.workportal.inbox.di.InboxComponent;
import com.bizagi.app.workportal.inbox.ui.adapters.InboxAdapter;
import com.bizagi.app.workportal.inbox.ui.adapters.OnItemClickListener;
import com.bizagi.app.workportal.vacation_request.ui.VacationRequestActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InboxActivity extends AppCompatActivity implements InboxView, OnItemClickListener {

    @BindView(R.id.reciclerView)
    RecyclerView reciclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tab)
    FloatingActionButton tab;
    @BindView(R.id.cordinator)
    RelativeLayout cordinator;

    private static final int REQUEST_VACATION_CREATE = 0;
    private static final int REQUEST_VACATION_UPDATE = 1;

    private static final String BEGIN_DATE = "beginDate";
    private static final String END_DATE = "endDate";
    public static final String EXTRA_K_NEW_VACATION = "new_vacation";

    public static final String TOTAL_REQUEST = "totalRequest";


    //  ========================INJECTED =====================================================================
    private InboxAdapter adapter;
    private InboxPresenter presenter;
    private InboxComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        ButterKnife.bind(this);
        setTitle(getString(R.string.inbox_title));
        setupInjection();
        setupRecyclerView();
        presenter.onCreate();
        presenter.getVactionsRequest();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupRecyclerView() {
        reciclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        reciclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        App app = (App) getApplication();
        component = app.getInboxComponente(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_VACATION_CREATE && requestCode == RESULT_OK) {
            Vacation nVacation = (Vacation) data.getExtras().get(EXTRA_K_NEW_VACATION);
            adapter.addVaction(nVacation);
        }
    }

    //    ==================================View==========================================
    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hiddeProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setVacationsRequest(List<Vacation> requestedVacationList) {
        adapter.setVacationsRequest(requestedVacationList);
    }

    @Override
    public void showError(String error) {
        Snackbar.make(cordinator, error, Snackbar.LENGTH_SHORT).show();
    }


    @OnClick(R.id.tab)
    public void createVacationRequest() {
        Intent intent = new Intent(this, VacationRequestActivity.class);
        intent.putExtra(TOTAL_REQUEST, adapter.getItemCount());
        startActivityForResult(intent, REQUEST_VACATION_CREATE);
    }

    @Override
    public void onItemClick(Vacation vacationRequest) {
        navegateToUpdateVacationRequest(vacationRequest);
    }

    private void navegateToUpdateVacationRequest(Vacation vacationRequest) {
        Intent intent = new Intent(this, VacationRequestActivity.class);
        intent.putExtra("request", (Serializable) vacationRequest);
        startActivityForResult(intent, REQUEST_VACATION_UPDATE);
    }

    public InboxPresenter getPresenter() {
        return component.getPresenter();
    }

    public InboxAdapter getAdapter() {
        return component.getAdapter();
    }
}
