package com.example.mypc.demosqliterepository.activity.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mypc.demosqliterepository.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnUnit,btnClass,btnStudent,btnTranscript;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addControl() {
        btnUnit = (Button) findViewById(R.id.btnListUnit);
        btnClass = (Button) findViewById(R.id.btnListClass);
        btnStudent = (Button) findViewById(R.id.btnListStudent);
        btnTranscript = (Button) findViewById(R.id.btnListTranscript);
    }

    private void addEvent() {
        btnUnit.setOnClickListener(this);
        btnClass.setOnClickListener(this);
        btnStudent.setOnClickListener(this);
        btnTranscript.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnListClass:
                btnClassClick();
                break;
            case R.id.btnListStudent:
                btnStudentClick();
                break;
            case R.id.btnListUnit:
                btnUnitClick();
                break;
            case R.id.btnListTranscript:
                btnTranscriptClick();
                break;
        }
    }

    private void btnTranscriptClick() {
        Intent intent = new Intent(this,TranscriptActivity.class);
        startActivity(intent);
    }

    private void btnUnitClick() {
        Intent intent = new Intent(this,UnitActivity.class);
        startActivity(intent);
    }

    private void btnStudentClick() {
        Intent intent = new Intent(this,StudentActivity.class);
        startActivity(intent);
    }

    private void btnClassClick() {
        Intent intent = new Intent(this,ClassActivity.class);
        startActivity(intent);
    }
}
