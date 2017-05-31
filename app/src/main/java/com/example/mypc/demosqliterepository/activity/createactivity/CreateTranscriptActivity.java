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
import com.example.mypc.demosqliterepository.model.Student;
import com.example.mypc.demosqliterepository.model.Transcript;
import com.example.mypc.demosqliterepository.R;
import com.example.mypc.demosqliterepository.Repository.ServiceConnectRepository;
import com.example.mypc.demosqliterepository.Repository.ServiceRepository;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.mypc.demosqliterepository.R.id.btnInputIdStudent;

public class CreateTranscriptActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreateTranscriptFinish, btnInputIdUnit, btnIdClass, btnIdStudent;
    private EditText edtInpuNameTranscript, edtInputPiont15, edtInputPiont45;
    private ProgressDialog progressDialog;

    private ListView lvDialog;
    private TextView tvNameListDialog;
    private int idUnit = 1;
    private int idClass = 1;
    private int idStudent = 1;
    private static final String NAME_UNIT = "UNIT";
    private static final String NAME_CLASS = "CLASS";
    private static final String NAME_STUDENT = "STUDENT";

    ServiceRepository serviceRepository = new ServiceConnectRepository();

    private Student mStudent;
    private ArrayList<String> listClass, listStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_transcript);
        loadIntentStudent();
        initialView();
        addEvent();
        setupView();
    }

    private void addEvent() {
        btnInputIdUnit.setOnClickListener(this);
        btnIdStudent.setOnClickListener(this);
        btnIdClass.setOnClickListener(this);
        btnCreateTranscriptFinish.setOnClickListener(this);
    }

    private void initialView() {
        btnCreateTranscriptFinish = (Button) findViewById(R.id.btnCreateTranscriptFinish);
        btnIdClass = (Button) findViewById(R.id.btnInputIdClass);
        btnInputIdUnit = (Button) findViewById(R.id.btnInputIdUnit);
        btnIdStudent = (Button) findViewById(btnInputIdStudent);
        btnIdClass.setText(serviceRepository.getAllClass(this).get(idClass).getName());
        btnIdStudent.setText(serviceRepository.getAllStudent(this).get(idStudent).getName());
        btnInputIdUnit.setText(serviceRepository.getAllUnit(this).get(idUnit).getName());

        edtInpuNameTranscript = (EditText) findViewById(R.id.edtInputNameTranscript);
        edtInputPiont15 = (EditText) findViewById(R.id.edtInputPiont15);
        edtInputPiont45 = (EditText) findViewById(R.id.edtInputPiont45);

        progressDialog = new ProgressDialog(this);
    }

    private void loadIntentStudent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String mDataClass = bundle.getString(BundleDataIntent.DATA_STUDENT);
            mStudent = new Gson().fromJson(mDataClass, Student.class);
        }
    }

    private void setupView() {
        if (mStudent != null) {
            btnInputIdUnit.setText(serviceRepository.getUnit(this, mStudent.getIdUnit()).getName());
            btnIdClass.setText(serviceRepository.getClass(this, mStudent.getIdClass()).getName());
            btnIdStudent.setText(serviceRepository.getStudent(this, mStudent.getId()).getName());
            idUnit = mStudent.getIdUnit();
            idStudent = mStudent.getId();
            idClass = mStudent.getIdClass();
        }
    }

    private void loadListItemDialog(final String nameList, ArrayList<String> arrayList) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(CreateTranscriptActivity.this);
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
        } else if (nameList.equals(NAME_STUDENT)) {
            btnIdStudent.setText(serviceRepository.getListStudentByIdClass(this,idClass).get(position).getName());
            idStudent = serviceRepository.getListStudentByIdClass(this,idClass).get(position).getId();
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
            case R.id.btnInputIdStudent:
                btnInputIdStudentClick();
                break;
            case R.id.btnCreateTranscriptFinish:
                btnCreateTranscriptFinishClick();
                break;
        }
    }

    private void btnInputIdStudentClick() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < serviceRepository.getListStudentByIdClass(this,idClass).size(); i++) {
            list.add(serviceRepository.getListStudentByIdClass(this,idClass).get(i).getName());
        }
        loadListItemDialog(NAME_STUDENT, list);
    }

    private void btnCreateTranscriptFinishClick() {
        Transcript transcript = new Transcript();
        transcript.setPoint15(Integer.parseInt(edtInputPiont15.getText().toString()));
        transcript.setPoint45(Integer.parseInt(edtInputPiont45.getText().toString()));
        transcript.setName(edtInpuNameTranscript.getText().toString());
        transcript.setIdClass(idClass);
        transcript.setIdUnit(idUnit);
        transcript.setIdStudent(idStudent);

        serviceRepository.addTranscript(this, transcript);
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
        listClass = new ArrayList<>();
        for (int i = 0; i < serviceRepository.getListClassByIdUnit(this, idUnit).size(); i++) {
            listClass.add(serviceRepository.getListClassByIdUnit(this, idUnit).get(i).getName());
        }
        loadListItemDialog(NAME_CLASS, listClass);
    }
}
