package com.example.myapplication.Fragment;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.CustomClass.ListViewAdapter;
import com.example.myapplication.Data.Posts;
import com.example.myapplication.Foundation.PostsList;
import com.example.myapplication.PhysicalArchitecture.Client;
import com.example.myapplication.R;

/**
 * Created by 유재인 on 2017-07-24.
 */

public class MyPageFragment extends Fragment {

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
        return v;
    }
}
