package com.example.mypc.demosqliterepository.activity.createactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mypc.demosqliterepository.model.Unit;
import com.example.mypc.demosqliterepository.R;
import com.example.mypc.demosqliterepository.Repository.ServiceConnectRepository;

public class CreateUnitActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCreateUnitFinish;
    private EditText  edtNameUnitCreate;
    ServiceConnectRepository serviceConnectRepository = new ServiceConnectRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_unit);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btnCreateUnitFinish.setOnClickListener(this);
    }

    private void addControl() {
        btnCreateUnitFinish = (Button) findViewById(R.id.btnCreateUnitFinish);
        edtNameUnitCreate = (EditText) findViewById(R.id.edtInputNameunit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreateUnitFinish:
                btnCreateUnitFinishClick();
                break;
        }
    }

    private void btnCreateUnitFinishClick() {
        Unit unit = new Unit();
        unit.setName(edtNameUnitCreate.getText().toString());
        serviceConnectRepository.addUnit(this,unit);
        finish();
    }
}
