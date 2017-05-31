package com.example.mypc.demosqliterepository.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.mypc.demosqliterepository.Bundle.BundleDataIntent;
import com.example.mypc.demosqliterepository.model.Unit;
import com.example.mypc.demosqliterepository.R;
import com.example.mypc.demosqliterepository.Repository.ServiceConnectRepository;
import com.example.mypc.demosqliterepository.activity.createactivity.CreateClassActivity;
import com.example.mypc.demosqliterepository.activity.createactivity.CreateUnitActivity;
import com.example.mypc.demosqliterepository.adapter.UnitAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UnitActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCreateUnit;
    private ListView lvUnit = null;
    private List<Unit> unitList = new ArrayList<>();
    UnitAdapter adapter;
    ServiceConnectRepository service = new ServiceConnectRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        initialView();
        getViewListView();
        addEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewListView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void initialView() {
        btnCreateUnit = (Button) findViewById(R.id.btnCreateUnit);
        lvUnit = (ListView) findViewById(R.id.lvUnit);
    }
    private void getViewListView(){
        unitList = service.getAllUnit(this);
        adapter = new UnitAdapter(unitList, this);
        lvUnit.setAdapter(adapter);
    }

    private void addEvent() {
        btnCreateUnit.setOnClickListener(this);
        lvUnit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), CreateClassActivity.class);
                int idUnit = unitList.get(position).getId();
                Unit mUnit = service.getUnit(view.getContext(),idUnit);
                Gson gson =new Gson();
                String mData = gson.toJson(mUnit);
                intent.putExtra(BundleDataIntent.DATA_UNIT,mData);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreateUnit:
                btnCreateUnitClick();
                break;
        }
    }

    private void btnCreateUnitClick() {
        Intent intent = new Intent(this, CreateUnitActivity.class);
        startActivity(intent);
    }
}
