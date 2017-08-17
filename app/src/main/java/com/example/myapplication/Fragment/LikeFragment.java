package com.example.myapplication.Fragment;
<<<<<<< HEAD
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
=======
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

public class LikeFragment extends Fragment {

<<<<<<< HEAD
    //    private static LikeFragment likeFragment = new LikeFragment();
    private ClientControl client = null;
    View v;
    ListView listview;
    ListViewAdapter adapter;

    public LikeFragment() {

    }

    /*
    public static LikeFragment getLikeFragment(){
        return likeFragment;
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(client == null)
            client=ClientControl.getClientControl();

        final Handler handler = new Handler( ){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case RECEIVE_SUCCESSS   :
                        for(int i=0; i<client.getMyLikeList().size(); i++)
                            adapter.addItem(client.getMyLikeList().get(i));
                        Log.d("test","like size:"+client.getMyLikeList().size());
                        adapter.notifyDataSetChanged();
                    case RECEIVE_FAILED     :
                        break;
                    default:
                        break;
                }
            }
        };

        v=inflater.inflate(R.layout.fragment_like, container, false);

        if(client.getMyLikeList().size() == 0)
        {
            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    client.myLike();
                    while(client.isMyLike());
                    handler.sendEmptyMessage(RECEIVE_SUCCESSS);
                    Log.d("test","likes size:"+client.getMyLikeList().size());
                }
            };
            thread1.start();
        }


        adapter = new ListViewAdapter(client.getMyLikeList(),true);
=======
    Client client;
    public LikeFragment() {
        client=null;
    }

    public LikeFragment(Client client) {
        this.client=client;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_like, container, false);
        ListView listview;
        ListViewAdapter adapter;

        // Adapter 생성
        //adapter = new ListViewAdapter(client.getcControl().getMyLikeList());
        adapter = new ListViewAdapter();


        // 리스트뷰 참조 및 Adapter달기
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
        listview = (ListView) v.findViewById(R.id.LikeList);
        listview.setAdapter(adapter);

        return v;
<<<<<<< HEAD
=======

>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
    }
}
