package com.oxbow.bazadanych;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.oxbow.bazadanych.Data.SampleData;

import java.lang.ref.WeakReference;
import java.util.ArrayList;



public class FirstTab extends Fragment {
    ListView list;
    Context activity;
    ArrayList<SampleData> dane;
    NewScheduleAdapter adapter;
    dbHandler handler;
    private getTask task;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        handler = new dbHandler(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first_tab, container, false);
        findViewById(rootView);
        registerForContextMenu(list);
        task = new getTask(getActivity());
        task.execute((Void) null);
        return rootView;
    }

    //DEFINICJE ZMIENNYCH

    private void findViewById(View view) {
        list = (ListView) view.findViewById(R.id.MainList);
    }

    //NIE WIEM PO CO TO

    @Override
    public void onResume() {
        super.onResume();
    }

    //MENU KONTEKSTOWE

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, getResources().getString(R.string.context_delete));
        menu.add(0, 2,0, getResources().getString(R.string.context_edit));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        SampleData data = (SampleData) adapter.getItem(info.position);
        if (item.getItemId() == 1)
        {
            handler.delete(schedule);
            adapter.remove(schedule);
        } else if (item.getItemId() == 2)
        {

        } else {
            return false;
        }
        return true;
    }


    public class getTask extends AsyncTask<Void, Void, ArrayList<SampleData>> {
        private final WeakReference<Activity> activityRef;

        public getTask(Activity context) {
            this.activityRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<SampleData> doInBackground(Void... arg0) {
            ArrayList<SampleData> dataList = handler.getSchedules();
            return dataList;
        }


        @Override
        protected void onPostExecute(ArrayList<SampleData> datList)
        {
            if (activityRef.get() != null && !activityRef.get().isFinishing())
            {
                Log.d("dane", datList.toString());
                schedules = datList;
                if (datList != null) {
                    if (datList.size() != 0) {
                        adapter = new NewScheduleAdapter(activity, datList);
                        list.setAdapter(adapter);
                    } else {
                        Toast.makeText(activity, getResources().getString(R.string.no_trips), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

        public void updateView() {
            task = new getTask(getActivity());
            task.execute((Void) null);
        }
    }
}
