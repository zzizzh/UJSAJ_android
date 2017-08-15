package com.example.myapplication.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.CustomClass.ListViewAdapter;
import com.example.myapplication.Data.Posts;
import com.example.myapplication.Foundation.PostsList;
import com.example.myapplication.PhysicalArchitecture.Client;
import com.example.myapplication.R;

/**
 * Created by 유재인 on 2017-07-24.
 */

public class TimeLineFragment extends Fragment {
    Client client;
    public TimeLineFragment() {
        client=null;
    }
    public TimeLineFragment(Client client) {
        this.client=client;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_timeline, container, false);
        ListView listview;
        ListViewAdapter adapter;

       // adapter = new ListViewAdapter(client.getcControl().getTimeLine());
        adapter = new ListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) v.findViewById(R.id.TimelineList);
        listview.setAdapter(adapter);
        return v;
    }




}
