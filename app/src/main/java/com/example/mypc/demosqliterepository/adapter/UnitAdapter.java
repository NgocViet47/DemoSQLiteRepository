package com.example.mypc.demosqliterepository.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mypc.demosqliterepository.R;
import com.example.mypc.demosqliterepository.model.Unit;

import java.util.List;

/**
 * Created by MyPC on 5/25/2017.
 */

public class UnitAdapter extends BaseAdapter {
    List<Unit> unitList;
    Context context;

    @Override
    public int getCount() {
        return unitList != null ? unitList.size() : 0;
    }

    public UnitAdapter(List<Unit> unitList, Context context) {
        this.unitList = unitList;
        this.context = context;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_unit, null, false);
            holder = new ViewHolder();
            holder.tvIdUnit = (TextView) convertView.findViewById(R.id.tvIdUnit);
            holder.tvNameUnit = (TextView) convertView.findViewById(R.id.tvNameUnit);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvIdUnit.setText(String.valueOf(unitList.get(position).getId()));
        holder.tvNameUnit.setText(unitList.get(position).getName());
        return convertView;
    }

    public class ViewHolder {
        public TextView tvIdUnit, tvNameUnit;
    }

}
