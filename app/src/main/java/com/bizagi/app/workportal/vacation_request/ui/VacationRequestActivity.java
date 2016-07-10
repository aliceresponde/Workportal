package com.bizagi.app.workportal.vacation_request.ui;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bizagi.app.workportal.App;
import com.bizagi.app.workportal.R;
import com.bizagi.app.workportal.db.entities.Vacation;
import com.bizagi.app.workportal.inbox.ui.InboxActivity;
import com.bizagi.app.workportal.vacation_request.VacationRequestPresenter;
import com.bizagi.app.workportal.vacation_request.di.VacationRequestComponent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bizagi.app.workportal.inbox.ui.InboxActivity.TOTAL_REQUEST;

public class VacationRequestActivity extends AppCompatActivity implements VacationRequestView {


    @BindView(R.id.cordinator)
    RelativeLayout cordinator;

    @BindView(R.id.inputEmployee)
    EditText inputEmployee;

    @BindView(R.id.inputBeginDate)
    EditText inputBeginDate;

    @BindView(R.id.inputEndDate)
    EditText inputEndDate;

    @BindView(R.id.inputRequestedDays)
    EditText inputRequestedDays;

    @BindView(R.id.btnSend)
    Button btnSend;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    String beginDate;
    String endDate;
    String requestedDays;
    String employeeName;


    private int nVactionSaved;

    private VacationRequestPresenter presenter;
    private VacationRequestComponent componet;
    private App app ;
    static final  int DILOG_BEGIN_ID = 1;
    static final  int DILOG_END_ID = 2;


    private int year_x;
    private int month_x;
    private int day_x;

    private DatePickerDialog.OnDateSetListener onDateSetListenerBegin = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            inputBeginDate.setText(year_x + "-" + month_x + "-" + day_x);
        }
    };

    private DatePickerDialog.OnDateSetListener onDateSetListenerEnd = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            inputEndDate.setText(year_x + "-" + month_x + "-" + day_x);
        }
    };


    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DILOG_BEGIN_ID) {
            return new DatePickerDialog(this, onDateSetListenerBegin, year_x, month_x, day_x);
        }else if (id == DILOG_END_ID){
            return new DatePickerDialog(this, onDateSetListenerEnd, year_x, month_x, day_x);
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_request);
        ButterKnife.bind(this);
        setTitle("Request Vacation");

        Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        setupInjection();
        setupDatesDiaglogListener();
        nVactionSaved = getIntent().getIntExtra(TOTAL_REQUEST, -1);
        if (nVactionSaved != -1) {
            presenter.onCreate();
        }
    }

    private void setupDatesDiaglogListener() {
        inputBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DILOG_BEGIN_ID);
            }
        });

        inputEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DILOG_END_ID);
            }
        });
    }

    private void setupInjection() {
        app = (App) getApplication();
        componet = app.getRequestVacationComponet(this,this);
        presenter = componet.getPresenter();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.btnSend)
    public void onClick() {

        beginDate = inputBeginDate.getText().toString();
        endDate = inputEndDate.getText().toString();
        requestedDays = inputRequestedDays.getText().toString();
        employeeName = inputEmployee.getText().toString();

        if(validateRequestData( beginDate,endDate,requestedDays ,employeeName)){
            showProgress();
            presenter.createVaacationRequest( beginDate,endDate,requestedDays, nVactionSaved ,employeeName );
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void disableUI() {
        setInputs(false);
    }

    @Override
    public void enableUI() {
        setInputs(true);
    }


    @Override
    public void showFormError( String error) {
        Snackbar.make(cordinator, error, Snackbar.LENGTH_INDEFINITE ).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleanForm();
            }
        }).show();
    }

    @Override
    public void showProcessError(String error) {

    }

    @Override
    public void goBack(Vacation vacation) {
        cleanForm();
        Intent intent = new Intent();
        intent.putExtra(InboxActivity.EXTRA_K_NEW_VACATION , (Serializable) vacation);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void showBeginDateError(String s) {
        inputBeginDate.setError(s);
    }

    @Override
    public void showEndDateError(String s) {
        inputEndDate.setError(s);
    }

    @Override
    public void showRequestedDaysError(String s) {
        inputRequestedDays.setError(s);
    }


    public void setInputs(boolean enable) {
        inputEmployee.setEnabled(enable);
        inputBeginDate.setEnabled(enable);
        inputEndDate.setEnabled(enable);
        inputRequestedDays.setEnabled(enable);
        btnSend.setEnabled(enable);
    }
    @Override
    public void cleanForm() {
        inputEmployee.setText("");
        inputBeginDate.setText("");
        inputEndDate.setText("");
        inputRequestedDays.setText("");

        inputEmployee.setError(null);
        inputBeginDate.setError(null);
        inputEndDate.setError(null);
        inputRequestedDays.setError(null);
    }

    @TargetApi(Build.VERSION_CODES.N)
    boolean validateRequestData(String beginDate, String endDate, String requestedDays, String employeeName) {
        boolean isValid  = true;
        Date _today  = new Date();
        Date _endDate = new Date(), _beginDate= new Date();
        int nDays =0;
        if (!requestedDays.isEmpty()) {
             nDays = Integer.parseInt(requestedDays);
        }

        if (beginDate.isEmpty()){
            isValid = false;
            showBeginDateError("empty field");
        }

        if (endDate.isEmpty()){
            isValid = false;
            showEndDateError("empty field");
        }

        if (requestedDays.isEmpty()){
            isValid = false;
            showRequestedDaysError("empty field");
        }

        if (employeeName.isEmpty()) {
            isValid = false;
            inputEmployee.setError("empty field");
        }

        //"2012-08-01"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String strToday = dateFormat.format(new Date());


        try{
            _beginDate=dateFormat.parse(beginDate);
            _endDate = dateFormat.parse(endDate);
        }catch (Exception e){
            Log.e("Eroor", e.getLocalizedMessage());
        }

        if ( _beginDate.after(_endDate) || _beginDate.before(_today)){
            isValid = false;
            showBeginDateError("Wrong date");
        }

        if (_endDate.before(_beginDate) || _endDate.before(_today)){
            isValid = false;
            showBeginDateError("Wrong date");
        }

        long diff = _endDate.getTime() -_beginDate.getTime();
        int daysBetweenDates = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if (nDays > daysBetweenDates){
            isValid = false;
            showErrorDays("Request lest time");
        }

        return isValid;

    }



    private void showErrorDays(String s) {

    }
}
