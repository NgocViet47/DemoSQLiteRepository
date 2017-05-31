package com.example.mypc.demosqliterepository.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.mypc.demosqliterepository.Bundle.BundleDataIntent;
import com.example.mypc.demosqliterepository.model.Student;
import com.example.mypc.demosqliterepository.R;
import com.example.mypc.demosqliterepository.Repository.ServiceConnectRepository;
import com.example.mypc.demosqliterepository.Repository.ServiceRepository;
import com.example.mypc.demosqliterepository.activity.createactivity.CreateStudentActivity;
import com.example.mypc.demosqliterepository.activity.createactivity.CreateTranscriptActivity;
import com.example.mypc.demosqliterepository.adapter.StudentAdapter;
import com.google.gson.Gson;

import java.util.List;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCreateStudent;
    private List<Student>listStudent;
    private ListView lvSutdent;
    private StudentAdapter adapter;
    ServiceRepository repository = new ServiceConnectRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        initialView();
        updateClass();
        addEvent();
    }

    private void addEvent() {
        btnCreateStudent.setOnClickListener(this);
        lvSutdent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), CreateTranscriptActivity.class);
                int idStudent = listStudent.get(position).getId();
                Student mStudent = repository.getStudent(view.getContext(),idStudent);
                Gson gson =new Gson();
                String mData = gson.toJson(mStudent);
                intent.putExtra(BundleDataIntent.DATA_STUDENT,mData);
                startActivity(intent);
            }
        });
    }

    private void initialView() {
        btnCreateStudent = (Button) findViewById(R.id.btnCreateStudent);
        lvSutdent = (ListView) findViewById(R.id.lvStudent);
    }
    private void updateClass(){
        listStudent = repository.getAllStudent(this);
        adapter = new StudentAdapter(this,listStudent);
        lvSutdent.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreateStudent:
                btnCreateStudentClick();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateClass();
    }

    private void btnCreateStudentClick() {
        Intent intent = new Intent(this, CreateStudentActivity.class);
        startActivity(intent);
    }
}
