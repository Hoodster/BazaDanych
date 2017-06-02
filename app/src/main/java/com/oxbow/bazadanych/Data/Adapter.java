package com.oxbow.bazadanych.Data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.oxbow.bazadanych.R;

import java.util.List;

/**
 * Created by kubap on 02.06.2017.
 */

public class Adapter extends ArrayAdapter<SampleData>{
        private Context context;
        List<SampleData> datas;

    public Adapter(Context context, List<SampleData> datas) {
            super(context, R.layout.main_list_item, datas);
            this.context = context;
            this.datas = datas;
        }

        public class ViewHolder {
            TextView ElementName;

        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public SampleData getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            // String theme = holder.Color.getText().toString();
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.main_list_item, null);
                holder = new ViewHolder();
                holder.ElementName = (TextView) convertView.findViewById(R.id.elementname);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            SampleData data = (SampleData) getItem(position);
            holder.ElementName.setText(data.getName());
            return convertView;
        }

        @Override
        public void add(SampleData data){
            datas.add(data);
            notifyDataSetChanged();
            super.add(data);
        }

        @Override
        public void remove(SampleData data)
        {
            datas.remove(data);
            notifyDataSetChanged();
            super.remove(data);
        }
}
