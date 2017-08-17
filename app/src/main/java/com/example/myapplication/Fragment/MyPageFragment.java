package com.example.myapplication.Fragment;
<<<<<<< HEAD
import android.os.Handler;
import android.os.Message;
=======
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
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
<<<<<<< HEAD
import com.example.myapplication.PhysicalArchitecture.ClientControl;
=======
import com.example.myapplication.Data.Posts;
import com.example.myapplication.Foundation.PostsList;
import com.example.myapplication.PhysicalArchitecture.Client;
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
import com.example.myapplication.R;

import static com.example.myapplication.ProblemDomain.Constants.RECEIVE_FAILED;
import static com.example.myapplication.ProblemDomain.Constants.RECEIVE_SUCCESSS;

/**
 * Created by 유재인 on 2017-07-24.
 */

public class MyPageFragment extends Fragment {

<<<<<<< HEAD
    ClientControl client= ClientControl.getClientControl();

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


        Log.d("test", "mypost start");

        final Handler handler = new Handler( ){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case RECEIVE_SUCCESSS   :
                        for(int i=0; i<client.getMyPostsList().size(); i++)
                            adapter.addItem(client.getMyPostsList().get(i));
                        Log.d("test", "mypost size : " + client.getMyPostsList().size());
                        adapter.notifyDataSetChanged();
                    case RECEIVE_FAILED     :
                        Log.d("test", "mypost receive fail");
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
                    Log.d("test","myposts siz:"+client.getMyPostsList().size());
                    client.myPosts();
                    while(client.isMyPosts()) ;
                    handler.sendEmptyMessage(RECEIVE_SUCCESSS);
                    Log.d("test","myposts size:"+client.getMyPostsList().size());
                }
            };
            thread.start();
        }

        v=inflater.inflate(R.layout.fragment_mypage, container, false);

        adapter = new ListViewAdapter(client.getMyPostsList(),false);
        listview = (ListView) v.findViewById(R.id.MyPostList);
        listview.setAdapter(adapter);

=======
    Client client;
    public MyPageFragment() {
        client=null;
    }

    public MyPageFragment(Client client) {
        this.client=client;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mypage, container, false);
        ListView listview;
        ListViewAdapter adapter;

        ImageView userspic=(ImageView)v.findViewById(R.id.userpic);
        TextView Nickname=(TextView)v.findViewById(R.id.userNickName);

        // Adapter 생성
//        adapter = new ListViewAdapter(client.getcControl().getMyPostsList());
        adapter = new ListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) v.findViewById(R.id.MyPostList);
        listview.setAdapter(adapter);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
        return v;
    }
}
