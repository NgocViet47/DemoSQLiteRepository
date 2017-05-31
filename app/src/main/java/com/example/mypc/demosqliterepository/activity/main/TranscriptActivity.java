package com.example.mypc.demosqliterepository.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mypc.demosqliterepository.model.Transcript;
import com.example.mypc.demosqliterepository.R;
import com.example.mypc.demosqliterepository.Repository.ServiceConnectRepository;
import com.example.mypc.demosqliterepository.Repository.ServiceRepository;
import com.example.mypc.demosqliterepository.activity.createactivity.CreateTranscriptActivity;
import com.example.mypc.demosqliterepository.adapter.TranscripAdapter;

import java.util.List;

public class TranscriptActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCreateTranscript;
    private List<Transcript> listTrascript;
    private ListView lvTranscript;
    private TranscripAdapter adapter;
    ServiceRepository repository = new ServiceConnectRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcript);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btnCreateTranscript.setOnClickListener(this);
    }

    private void addControl() {
        btnCreateTranscript = (Button) findViewById(R.id.btnCreateTranscript);
        lvTranscript = (ListView) findViewById(R.id.lvTranscript);

        listTrascript = repository.getAllTranscript(this);
        adapter = new TranscripAdapter(this,listTrascript);
        lvTranscript.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreateTranscript:
                btnCreateTranscriptClick();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        listTrascript = repository.getAllTranscript(this);
        adapter = new TranscripAdapter(this,listTrascript);
        lvTranscript.setAdapter(adapter);
    }

    private void btnCreateTranscriptClick() {
        Intent intent = new Intent(this, CreateTranscriptActivity.class);
        startActivity(intent);
    }
}
