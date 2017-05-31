package com.example.mypc.demosqliterepository.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mypc.demosqliterepository.model.Transcript;
import com.example.mypc.demosqliterepository.R;

import java.util.List;

import static com.example.mypc.demosqliterepository.R.id.tvIdClass;
import static com.example.mypc.demosqliterepository.R.id.tvIdStudent;
import static com.example.mypc.demosqliterepository.R.id.tvIdTranscript;
import static com.example.mypc.demosqliterepository.R.id.tvIdUnit;
import static com.example.mypc.demosqliterepository.R.id.tvPiont15;
import static com.example.mypc.demosqliterepository.R.id.tvPiont45;

/**
 * Created by MyPC on 5/26/2017.
 */

public class TranscripAdapter extends BaseAdapter {
    Context context;
    List<Transcript> list;

    public TranscripAdapter(Context context, List<Transcript> list) {
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
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_transcript,null,false);
            holder = new ViewHolder();
            holder.tvIdTranscript = (TextView) convertView.findViewById(tvIdTranscript);
            holder.tvNameTrangscript = (TextView) convertView.findViewById(R.id.tvNameTranscript);
            holder.tvIdUnit = (TextView) convertView.findViewById(tvIdUnit);
            holder.tvIdClass = (TextView) convertView.findViewById(tvIdClass);
            holder.tvIdStudent = (TextView) convertView.findViewById(tvIdStudent);
            holder.tvPiont15 = (TextView) convertView.findViewById(tvPiont15);
            holder.tvPiont45 = (TextView) convertView.findViewById(tvPiont45);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvIdTranscript.setText(String.valueOf(list.get(position).getId()));
        holder.tvIdUnit.setText(String.valueOf(list.get(position).getIdUnit()));
        holder.tvIdClass.setText(String.valueOf(list.get(position).getIdClass()));
        holder.tvIdStudent.setText(String.valueOf(list.get(position).getIdStudent()));
        holder.tvPiont15.setText(String.valueOf(list.get(position).getPoint15()));
        holder.tvPiont45.setText(String.valueOf(list.get(position).getPoint45()));
        holder.tvNameTrangscript.setText(list.get(position).getName());
        return convertView;
    }

    public class ViewHolder {
        public TextView tvIdTranscript,tvNameTrangscript,tvIdUnit,tvIdClass,tvIdStudent,tvPiont15,tvPiont45;
    }
}
