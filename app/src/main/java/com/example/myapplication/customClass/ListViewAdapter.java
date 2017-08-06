package com.example.myapplication.customClass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.Data.TourData;
import com.example.myapplication.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import android.view.LayoutInflater;

import static com.example.myapplication.Data.Constants.CONTENTES_TYPE_CODE;
import static com.example.myapplication.Data.Constants.CONTENTS_TYPE;

/**
 * Created by jm on 2017-04-24.
 */


public class ListViewAdapter extends BaseAdapter {
    private ArrayList<TourData> tour_data_list;
    private ArrayList<Bitmap> bitmap_list;

    public ListViewAdapter(){
        tour_data_list = new ArrayList<TourData>();
        bitmap_list = new ArrayList<Bitmap>();
    }

    @Override
    public int getCount() {
        return tour_data_list.size();
    }

    @Override
    public Object getItem(int i) {
        return tour_data_list.get(i);
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
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        final ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final TourData tourData = tour_data_list.get(position);

        // 아이템 내 각 위젯에 데이터 반영

        Thread mThread = new Thread(){
            public void run() {
                try {
                    URL url = new URL(tourData.getFirstimage());

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    iconImageView.setImageBitmap(bitmap);
                    bitmap_list.add(bitmap);
                } catch (IOException ex) {

                }
            }
        };
        mThread.start();

        try{
            mThread.join();
        }catch(InterruptedException e){

        }

        titleTextView.setText(tourData.getTitle());
        for(int i=0; i<CONTENTES_TYPE_CODE.length; i++)
            if(CONTENTES_TYPE_CODE[i] == tourData.getContenttypeid())
                descTextView.setText(CONTENTS_TYPE[i]);

        return convertView;
    }

    public void addItem(TourData tourData) {
        tour_data_list.add(tourData);
    }
}
