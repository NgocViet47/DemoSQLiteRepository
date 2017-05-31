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

import com.example.mypc.demosqliterepository.Bundle.BundleDataIntent;
import com.example.mypc.demosqliterepository.model.ClassR;
import com.example.mypc.demosqliterepository.model.Student;
import com.example.mypc.demosqliterepository.R;
import com.example.mypc.demosqliterepository.Repository.ServiceConnectRepository;
import com.example.mypc.demosqliterepository.Repository.ServiceRepository;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CreateStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreateStudentFinish, btnInputIdUnit, btnIdClass;
    private EditText edtInpuNameStudent, edtInputBirthdate;
    private ProgressDialog progressDialog;

    private ListView lvDialog;
    private TextView tvNameListDialog;
    private int idUnit = 1;
    private int idClass = 0;
    private static final String NAME_UNIT = "UNIT";
    private static final String NAME_CLASS = "CLASS";

    private ClassR mClass;

    ServiceRepository serviceRepository = new ServiceConnectRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        loadIntentClass();
        initialView();
        addEvent();
        setupView();
    }

    private void addEvent() {
        btnInputIdUnit.setOnClickListener(this);
        btnIdClass.setOnClickListener(this);
        btnCreateStudentFinish.setOnClickListener(this);
    }

    private void initialView() {
        btnCreateStudentFinish = (Button) findViewById(R.id.btnCreateStudentFinish);
        btnIdClass = (Button) findViewById(R.id.btnInputIdClass);
        btnInputIdUnit = (Button) findViewById(R.id.btnInputIdUnit);
        btnIdClass.setText(serviceRepository.getAllClass(this).get(idClass).getName());
        btnInputIdUnit.setText(serviceRepository.getAllUnit(this).get(idUnit).getName());

        edtInpuNameStudent = (EditText) findViewById(R.id.edtInputNameStudent);
        edtInputBirthdate = (EditText) findViewById(R.id.edtInputBirthdate);

        progressDialog = new ProgressDialog(this);

    }

    private void loadIntentClass() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String mDataClass = bundle.getString(BundleDataIntent.DATA_CLASS);
            mClass = new Gson().fromJson(mDataClass, ClassR.class);
        }
    }

    private void setupView(){
        if(mClass!=null) {
            btnIdClass.setText(mClass.getName());
            btnInputIdUnit.setText(serviceRepository.getUnit(this, mClass.getIdUnit()).getName());
            idUnit = mClass.getIdUnit();
            idClass = mClass.getId();
        }
    }

    private void loadListItemDialog(final String nameList, ArrayList<String> arrayList) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(CreateStudentActivity.this);
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
                loadTextButton(nameList, position);
                dialog.dismiss();
            }
        });
    }

    private void loadTextButton(String nameList, int position) {
        if (nameList.equals(NAME_UNIT)) {
            btnInputIdUnit.setText(serviceRepository.getAllUnit(this).get(position).getName());
            idUnit = serviceRepository.getAllUnit(this).get(position).getId();
        } else if (nameList.equals(NAME_CLASS)) {
            btnIdClass.setText(serviceRepository.getListClassByIdUnit(this,idUnit).get(position).getName());
            idClass = serviceRepository.getListClassByIdUnit(this,idUnit).get(position).getId();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInputIdClass:
                btnIdClassClick();
                break;
            case R.id.btnInputIdUnit:
                btnInputIdUnitClick();
                break;
            case R.id.btnCreateStudentFinish:
                btnCreateStudentFinishClick();
                break;
        }
    }

    private void btnCreateStudentFinishClick() {
        Student student = new Student();
        student.setName(edtInpuNameStudent.getText().toString());
        student.setBirthDate(edtInputBirthdate.getText().toString());
        student.setIdClass(idClass);
        student.setIdUnit(idUnit);
        serviceRepository.addStudent(this, student);
        finish();
    }

    private void btnInputIdUnitClick() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < serviceRepository.getAllUnit(this).size(); i++) {
            list.add(serviceRepository.getAllUnit(this).get(i).getName());
        }
        loadListItemDialog(NAME_UNIT, list);
    }

    private void btnIdClassClick() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < serviceRepository.getListClassByIdUnit(this,idUnit).size(); i++) {
            list.add(serviceRepository.getListClassByIdUnit(this,idUnit).get(i).getName());
        }
        loadListItemDialog(NAME_CLASS, list);

    }
}
