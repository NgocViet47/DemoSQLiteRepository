package com.example.mypc.demosqliterepository.activity.createactivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mypc.demosqliterepository.R;
import com.example.mypc.demosqliterepository.Repository.ServiceConnectRepository;
import com.example.mypc.demosqliterepository.Repository.ServiceRepository;
import com.example.mypc.demosqliterepository.Bundle.BundleDataIntent;
import com.example.mypc.demosqliterepository.model.ClassR;
import com.example.mypc.demosqliterepository.model.Unit;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CreateClassActivity extends AppCompatActivity implements View.OnClickListener {

    private ServiceRepository serviceRepository = new ServiceConnectRepository();
    private Button btnCreateClassFinish, btnInputIdUnit;
    private EditText edtInpuNameClass;
    private ProgressDialog progressDialog;

    private ListView lvDialog;
    private TextView tvNameListDialog;
    private int idUnit = 1;
    private Unit unit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        loadIntentUnit();
        initialView();
        addEvent();
        setupView();
    }

    private void addEvent() {
        btnInputIdUnit.setOnClickListener(this);
        btnCreateClassFinish.setOnClickListener(this);
    }
    private void initialView() {
        progressDialog = new ProgressDialog(this);
        btnCreateClassFinish = (Button) findViewById(R.id.btnCreateClassFinish);
        btnInputIdUnit = (Button) findViewById(R.id.btnInputIdUnit);
        btnInputIdUnit.setText(serviceRepository.getUnit(this,idUnit).getName());
        edtInpuNameClass = (EditText) findViewById(R.id.edtInputNameClass);
    }

    private void loadIntentUnit() {
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            String mDataUnit = bundle.getString(BundleDataIntent.DATA_UNIT);
            unit = new Gson().fromJson(mDataUnit, Unit.class);
        }
    }
    private void setupView(){
        if(unit!=null) {
            btnInputIdUnit.setText(unit.getName());
            idUnit = unit.getId();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreateClassFinish:
                btnCreateClassFinishClick();
                break;
            case R.id.btnInputIdUnit:
                btnInputIdUnitClick();
                break;
        }
    }

    private void btnInputIdUnitClick() {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0 ;i<serviceRepository.getAllUnit(this).size();i++){
            list.add(serviceRepository.getAllUnit(this).get(i).getName());
        }
        loadListItemDialog("Unit",list);
    }

    private void btnCreateClassFinishClick() {
        ClassR mClass = new ClassR();
        mClass.setName(edtInpuNameClass.getText().toString());
        mClass.setIdUnit(idUnit);
        serviceRepository.addClass(this,mClass);
        finish();
    }

    private void loadListItemDialog(final String nameList, ArrayList<String> arrayList) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(CreateClassActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.list_dialog, null);
        lvDialog = (ListView) mView.findViewById(R.id.lvItemDialog);
        tvNameListDialog = (TextView) mView.findViewById(R.id.tvNameList);
        tvNameListDialog.setText(nameList);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lvDialog.setAdapter(adapter);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        lvDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadTextButton( position);
                dialog.dismiss();
            }
        });
    }

    private void loadTextButton( int position) {
        btnInputIdUnit.setText(serviceRepository.getAllUnit(this).get(position).getName());
        idUnit = serviceRepository.getAllUnit(this).get(position).getId();
    }
}
