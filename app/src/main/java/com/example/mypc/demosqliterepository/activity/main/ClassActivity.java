package com.example.mypc.demosqliterepository.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.mypc.demosqliterepository.Bundle.BundleDataIntent;
import com.example.mypc.demosqliterepository.model.ClassR;
import com.example.mypc.demosqliterepository.R;
import com.example.mypc.demosqliterepository.Repository.ServiceConnectRepository;
import com.example.mypc.demosqliterepository.activity.createactivity.CreateClassActivity;
import com.example.mypc.demosqliterepository.activity.createactivity.CreateStudentActivity;
import com.example.mypc.demosqliterepository.adapter.ClassAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCreateClass;
    private ListView lvClass = null;
    private List<ClassR> classList = new ArrayList<>();
    private  ClassAdapter adapter;
    private  ServiceConnectRepository service = new ServiceConnectRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        initialView();
        updateClass();
        addEvent();
    }

    private void initialView() {
        btnCreateClass = (Button) findViewById(R.id.btnCreateClass);
        lvClass = (ListView) findViewById(R.id.lvClass);
    }

    private void addEvent() {
        btnCreateClass.setOnClickListener(this);
        lvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), CreateStudentActivity.class);
                int idClass = classList.get(position).getId();
                ClassR mClass = service.getClass(view.getContext(),idClass);
                Gson gson =new Gson();
                String mData = gson.toJson(mClass);
                intent.putExtra(BundleDataIntent.DATA_CLASS,mData);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateClass();
    }

    private void updateClass(){
        classList = service.getAllClass(this);
        adapter = new ClassAdapter(this,classList);
        lvClass.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreateClass:
                btnCreateClassClick();
                break;
        }
    }

    private void btnCreateClassClick() {
        Intent intent = new Intent(this, CreateClassActivity.class);
        startActivity(intent);
    }
}
