package com.example.mypc.demosqliterepository.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mypc.demosqliterepository.R;
import com.example.mypc.demosqliterepository.model.ClassR;

import java.util.List;

import static com.example.mypc.demosqliterepository.R.id.tvIdClass;
import static com.example.mypc.demosqliterepository.R.id.tvIdUnit;
import static com.example.mypc.demosqliterepository.R.id.tvNameClass;

/**
 * Created by MyPC on 5/25/2017.
 */

public class ClassAdapter extends BaseAdapter {
   private Context context;
  private   List<ClassR> list;

    public ClassAdapter(Context context, List<ClassR> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_class, null, false);
            holder = new ViewHolder();
            holder.tvIdClass = (TextView) convertView.findViewById(tvIdClass);
            holder.tvNameClass = (TextView) convertView.findViewById(tvNameClass);
            holder.tvIdUnit = (TextView) convertView.findViewById(tvIdUnit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvIdUnit.setText(String.valueOf(list.get(position).getIdUnit()));
        holder.tvNameClass.setText(list.get(position).getName());
        holder.tvIdClass.setText(String.valueOf(list.get(position).getId()));

        return convertView;
    }

    public class ViewHolder{
        public TextView tvIdClass,tvNameClass,tvIdUnit;
    }
}
