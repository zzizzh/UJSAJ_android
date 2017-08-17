package com.example.myapplication.CustomClass;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.PhysicalArchitecture.Client;
import com.example.myapplication.PhysicalArchitecture.ClientControl;
import com.example.myapplication.ProblemDomain.Posts;
import com.example.myapplication.ProblemDomain.User;
import com.example.myapplication.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.view.LayoutInflater;

import static com.example.myapplication.ProblemDomain.Constants.RECEIVE_FAILED;
import static com.example.myapplication.ProblemDomain.Constants.RECEIVE_SUCCESSS;

/**
 * Created by jm on 2017-04-24.
 * 2017.08.14 song
 */


public class ListViewAdapter extends BaseAdapter {
    ArrayList<Posts> post_list;
    ClientControl client;

    boolean Liked = false;
    boolean IsLikeList = false;

    public ListViewAdapter(ArrayList<Posts> postsList, boolean check) {
        post_list = postsList;
        IsLikeList = check;
    }

    public ListViewAdapter() {
        client = ClientControl.getClientControl();
        ArrayList<Posts> postsList = new ArrayList<Posts>();
        post_list = postsList;
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


    public Bitmap byteArrayToBitmap(byte[] byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bitmap;
    }

    public Bitmap getBitmapFromDrawable(BitmapDrawable drawable) {
        Bitmap b = drawable.getBitmap();
        return b;
    }

    public Bitmap resizeBitmapImageFn(Bitmap bmpSource, int maxResolution) {
        int iWidth = bmpSource.getWidth();      //비트맵이미지의 가로
        int iHeight = bmpSource.getHeight();     //비트맵이미지의 세로
        int newWidth = iWidth;
        int newHeight = iHeight;
        float rate = 0.0f;

        //이미지의 가로 세로 비율에 맞게 조절
        if (iWidth > iHeight) {
            if (maxResolution < iWidth) {
                rate = maxResolution / (float) iWidth;
                newHeight = (int) (iHeight * rate);
                newWidth = maxResolution;
            }
        } else {
            if (maxResolution < iHeight) {
                rate = maxResolution / (float) iHeight;
                newWidth = (int) (iWidth * rate);
                newHeight = maxResolution;
            }
        }

        return Bitmap.createScaledBitmap(bmpSource, newWidth, newHeight, true);
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

        final ImageButton like = convertView.findViewById(R.id.LikeStar);
        final ImageButton unlike = convertView.findViewById(R.id.UnlikeStar);

        final TextView youTubeTextView = convertView.findViewById(R.id.musictag);
        final TextView LocationTextView = convertView.findViewById(R.id.locationtag);
        final TextView opinionTextView = convertView.findViewById(R.id.opinionText);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final Posts posts = post_list.get(position);
        final int postsIndex = posts.getPostsIndex();

        Log.d("test", "postindex" + postsIndex);
        Thread mThread = new Thread() {
            public void run() {
                try {

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    BitmapDrawable image = new BitmapDrawable(BitmapFactory.decodeByteArray(posts.getImage(), 0, posts.getImage().length, options));
                    Bitmap resized = image.getBitmap();

                    resized = resizeBitmapImageFn(resized, 1000);
                    PostPic.setImageBitmap(resized);

                    if (!IsLikeList) {
                        like.setVisibility(View.GONE);
                        unlike.setVisibility(View.VISIBLE);
                    } else {
                        unlike.setVisibility(View.GONE);
                        like.setVisibility(View.VISIBLE);
                    }

                    like.setOnClickListener(new ImageButton.OnClickListener() {
                        public void onClick(View view) {
                            like.setVisibility(View.GONE);
                            unlike.setVisibility(View.VISIBLE);
                            Log.d("test", "postindex" + postsIndex);
                        }
                    });

                    //change unlike to like
                    unlike.setOnClickListener(new ImageButton.OnClickListener() {
                        public void onClick(View view) {
                            Liked = true;
                            unlike.setVisibility(View.GONE);
                            like.setVisibility(View.VISIBLE);
                        }
                    });

                    LocationTextView.setText(posts.getLocationInfo().getTitle());
                    opinionTextView.setText(posts.getArtist() + "-" + posts.getSong() + "\n" + posts.getComment());
                    youTubeTextView.setText(posts.getUrl());

                } catch (Exception ex) {}
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

    public void addItem(Posts posts) {
        post_list.add(posts);
    }

    /*
    private void like(int index){
        Thread thread = new Thread(){
            @Override
            public void run() {
                client.like(p.getPostsIndex());
                while(client.isLike());
                Log.d("test","like result:"+client.getStringList().get(0));
                client.getStringList().remove(0);
            }
        };
        thread.start();
    }

    private void dislike(int index){
        Thread thread = new Thread(){
            @Override
            public void run() {
                client.dislike(index);
                while(client.isDislike());
                Log.d("test","dislike result:"+client.getStringList().get(0));
                client.getStringList().remove(0);
            }
        };
        thread.start();
    }*/
}
