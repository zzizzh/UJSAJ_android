package com.example.myapplication.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.CustomClass.ListViewAdapter;
import com.example.myapplication.PhysicalArchitecture.ClientControl;
import com.example.myapplication.R;

import static com.example.myapplication.ProblemDomain.Constants.RECEIVE_FAILED;
import static com.example.myapplication.ProblemDomain.Constants.RECEIVE_SUCCESSS;

/**
 * Created by 유재인 on 2017-07-24.
 */

public class TimeLineFragment extends Fragment {
//    private static TimeLineFragment timeLineFragment = new TimeLineFragment();

    ClientControl client = null;
    ListView listview;
    ListViewAdapter adapter;
    View v;

    public TimeLineFragment() {

    }

    /*
    public static TimeLineFragment getTimeLineFragment(){
        return timeLineFragment;
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(client == null)
            client = ClientControl.getClientControl();

        final Handler handler = new Handler( ){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case RECEIVE_SUCCESSS   :
                        for(int i=0; i<client.getTimeLine().size(); i++)
                            adapter.addItem(client.getTimeLine().get(i));

                        adapter.notifyDataSetChanged();
                    case RECEIVE_FAILED     :
                        break;
                    default:
                        break;
                }
            }
        };
        v =inflater.inflate(R.layout.fragment_timeline, container, false);

        if(client.getTimeLine().size() == 0)
        {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    client.refresh();
                    while (client.isRefresh()) ;
                    handler.sendEmptyMessage(RECEIVE_SUCCESSS);
                    Log.d("test","timeline size:"+client.getTimeLine().size());
                }
            };
            thread.start();
        }

        adapter = new ListViewAdapter(client.getTimeLine());
        listview = (ListView) v.findViewById(R.id.TimelineList);
        listview.setAdapter(adapter);

        return v;
    }




}
