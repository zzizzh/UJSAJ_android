package com.example.myapplication.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.CustomClass.ListViewAdapter;
import com.example.myapplication.PhysicalArchitecture.ClientControl;
import com.example.myapplication.R;

import static com.example.myapplication.ProblemDomain.Constants.RECEIVE_FAILED;
import static com.example.myapplication.ProblemDomain.Constants.RECEIVE_SUCCESSS;

/**
 * Created by 유재인 on 2017-07-24.
 */

public class MyPageFragment extends Fragment {

    ClientControl client = null;
 //   private static MyPageFragment myPageFragment = new MyPageFragment();
    View v;
    ListView listview;
    ListViewAdapter adapter;

    public MyPageFragment() {

    }

    /*
    public static MyPageFragment getMyPageFragment(){
        return myPageFragment;
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(client == null)
            client= ClientControl.getClientControl();

        ImageView userspic=(ImageView)v.findViewById(R.id.userpic);
        TextView Nickname=(TextView)v.findViewById(R.id.userNickName);

        final Handler handler = new Handler( ){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case RECEIVE_SUCCESSS   :
                        for(int i=0; i<client.getMyPostsList().size(); i++)
                            adapter.addItem(client.getMyPostsList().get(i));

                        adapter.notifyDataSetChanged();
                    case RECEIVE_FAILED     :
                        break;
                    default:
                        break;
                }
            }
        };

        if(client.getMyPostsList().size() == 0)
        {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    client.myPosts();
                    while (client.isMyPosts()) ;
                    handler.sendEmptyMessage(RECEIVE_SUCCESSS);
                    Log.d("test","timeline size:"+client.getMyPostsList().size());
                }
            };
            thread.start();
        }

        adapter = new ListViewAdapter(client.getMyPostsList());
        listview = (ListView) v.findViewById(R.id.MyPostList);
        listview.setAdapter(adapter);
        return v;
    }
}
