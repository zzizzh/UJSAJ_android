package com.example.myapplication.Fragment;
<<<<<<< HEAD
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
=======
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.CustomClass.ListViewAdapter;
<<<<<<< HEAD
import com.example.myapplication.PhysicalArchitecture.ClientControl;
import com.example.myapplication.R;

import static com.example.myapplication.ProblemDomain.Constants.RECEIVE_FAILED;
import static com.example.myapplication.ProblemDomain.Constants.RECEIVE_SUCCESSS;

=======
import com.example.myapplication.Data.Posts;
import com.example.myapplication.Foundation.PostsList;
import com.example.myapplication.PhysicalArchitecture.Client;
import com.example.myapplication.R;

>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
/**
 * Created by 유재인 on 2017-07-24.
 */

public class TimeLineFragment extends Fragment {
<<<<<<< HEAD
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

                        break;
                    case RECEIVE_FAILED     :
                        break;
                    default:
                        break;
                }

            }
        };

        v=inflater.inflate(R.layout.fragment_timeline, container, false);

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



        adapter = new ListViewAdapter(client.getTimeLine(),false);
        listview = (ListView) v.findViewById(R.id.TimelineList);
        listview.setAdapter(adapter);

=======
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
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
        return v;
    }


<<<<<<< HEAD
=======


>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
}
