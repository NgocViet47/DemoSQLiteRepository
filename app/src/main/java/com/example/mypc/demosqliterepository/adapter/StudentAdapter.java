package com.example.mypc.demosqliterepository.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mypc.demosqliterepository.R;
import com.example.mypc.demosqliterepository.model.Student;

import java.util.List;

import static com.example.mypc.demosqliterepository.R.id.tvBirthdate;
import static com.example.mypc.demosqliterepository.R.id.tvIdStudent;

/**
 * Created by MyPC on 5/26/2017.
 */

public class StudentAdapter extends BaseAdapter {
    Context context;
    List<Student> list;

    public StudentAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_student, null, false);
            holder = new ViewHolder();
            holder.tvBirthdate = (TextView) convertView.findViewById(tvBirthdate);
            holder.tvIdClass = (TextView) convertView.findViewById(R.id.tvIdClass);
            holder.tvIdStudent = (TextView) convertView.findViewById(tvIdStudent);
            holder.tvIdUnit = (TextView) convertView.findViewById(R.id.tvIdUnit);
            holder.tvNameStudent = (TextView) convertView.findViewById(R.id.tvNameStudent);
            holder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvAddress.setText(list.get(position).getAddress());
        holder.tvNameStudent.setText(list.get(position).getName());
        holder.tvBirthdate.setText(list.get(position).getBirthDate());
        holder.tvIdUnit.setText(String.valueOf(list.get(position).getIdUnit()));
        holder.tvIdClass.setText(String.valueOf(list.get(position).getIdClass()));
        holder.tvIdStudent.setText(String.valueOf(list.get(position).getId()));

        return convertView;
    }

    public class ViewHolder {
        public TextView tvIdStudent, tvNameStudent, tvIdUnit, tvIdClass, tvBirthdate,tvAddress;

    }
}
