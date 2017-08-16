package com.example.myapplication.CustomClass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.ProblemDomain.Posts;
import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.view.LayoutInflater;

/**
 * Created by jm on 2017-04-24.
 * 2017.08.14 song
 */


public class ListViewAdapter extends BaseAdapter {
    ArrayList<Posts> post_list;
    public ListViewAdapter(ArrayList<Posts> postsList) {
        post_list = postsList;
    }

    public ListViewAdapter(){
        ArrayList<Posts> postsList =new ArrayList<Posts>();
        post_list=postsList;
    }
    @Override
    public int getCount() {
        return post_list.size();
    }

    @Override
    public Object getItem(int i) {
        return post_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();


        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_post, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득

        final ImageView PostPic = convertView.findViewById(R.id.postImage);
        final ImageButton like =convertView.findViewById(R.id.LikeStar);
        final ImageButton unlike =convertView.findViewById(R.id.UnlikeStar);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                like.setVisibility(View.GONE);
                unlike.setVisibility(View.VISIBLE);
            }
        });

        unlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                like.setVisibility(View.VISIBLE);
                unlike.setVisibility(View.GONE);
            }
        });

        final TextView youTubeTextView = convertView.findViewById(R.id.musictag);
        final TextView LocationTextView = convertView.findViewById(R.id.locationtag);
        final TextView opinionTextView = convertView.findViewById(R.id.opinionText);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final Posts posts = post_list.get(position);

        // 아이템 내 각 위젯에 데이터 반영

        Thread mThread = new Thread() {
            public void run() {
                try {
//                    Drawable image = new BitmapDrawable(BitmapFactory.decodeByteArray(posts.getImage(), 0, posts.getImage().length));
//                    LocationTextView.setText(posts.getLocationInfo().LocationInfoString());

                    opinionTextView.setText(posts.getArtist() + "-" + posts.getSong() + "\n" + posts.getComment());
                    youTubeTextView.setText(posts.getUrl());
       //             PostPic.setImageBitmap(byteArrayToBitmap(posts.getIImage()));

                } catch (Exception ex) {

                }
            }
        };
        mThread.start();

        try {
            mThread.join();
        } catch (InterruptedException e) {

        }
/*        for (int i = 0; i < CONTENTES_TYPE_CODE.length; i++)
            if (CONTENTES_TYPE_CODE[i] == tourData.getContenttypeid())
                opinionTextView.setText(CONTENTS_TYPE[i]);
*/
        return convertView;
    }
    public byte[] bitmapToByteArray( Bitmap bitmap ) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        return byteArray ;
    }

    public Bitmap byteArrayToBitmap(byte[] byteArray){
        Bitmap bitmap=null;
        bitmap=BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        byteArray=null;
        return bitmap;
    }
    public void addItem(Posts posts) {
        post_list.add(posts);
    }
}
