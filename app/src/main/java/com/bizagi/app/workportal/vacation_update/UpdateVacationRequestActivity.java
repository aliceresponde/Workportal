package com.bizagi.app.workportal.vacation_update;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bizagi.app.workportal.R;
import com.bizagi.app.workportal.db.entities.Vacation;
import com.bizagi.app.workportal.inbox.ui.InboxActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateVacationRequestActivity extends AppCompatActivity {

    @BindView(R.id.inputEmployee)
    EditText inputEmployee;
    @BindView(R.id.inputBeginDate)
    EditText inputBeginDate;
    @BindView(R.id.inputEndDate)
    EditText inputEndDate;
    @BindView(R.id.inputRequestedDays)
    TextView inputRequestedDays;
    @BindView(R.id.txtLastVacationOn)
    TextView txtLastVacationOn;
    @BindView(R.id.btn_accept)
    Button btnAccept;
    @BindView(R.id.btn_reject)
    Button btnReject;
    @BindView(R.id.cordinator)
    RelativeLayout cordinator;
    private Vacation currentVacation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Vacation Request");
        setContentView(R.layout.activity_update_vacation_request);
        ButterKnife.bind(this);

        currentVacation = (Vacation) getIntent().getExtras().get(InboxActivity.EXTRA_K_CURRENT_VACATION);
        inputEmployee.setText(currentVacation.getEmployee());
        inputBeginDate.setText(currentVacation.getBeginDate());
        inputEndDate.setText(currentVacation.getEndDate());
        inputRequestedDays.setText(currentVacation.getRequestedDays());
        txtLastVacationOn.setText(currentVacation.getLastVacationOn());
    }



    private void goBack() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick({R.id.btn_accept, R.id.btn_reject})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_accept:
                currentVacation.setApproved(Vacation.STATUS_ACCEPTED);
                currentVacation.save();
                goBack();
                break;
            case R.id.btn_reject:
                currentVacation.setApproved(Vacation.STATUS_REJECTED);
                currentVacation.save();
                goBack();
                break;
        }
    }
}
