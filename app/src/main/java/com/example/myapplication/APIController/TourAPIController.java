package com.example.myapplication.APIController;

import android.util.Log;

import com.example.myapplication.Data.TourData;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import static com.example.myapplication.Data.Constants.*;

/**
 * Created by jm on 2017-04-23.
 */

public class TourAPIController {

    private static final String initURI     = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";

    private static TourAPIController toruAPIController = new TourAPIController();

    private static final String serviceKey  = "?ServiceKey=Q%2Bh3qGHK7KUnkP%2FiO5s%2BmFf59UnBlEmg4Bkuiuwi8aZxGnzRchGJqZK46x4%2Fh9BGemhiUekc37nT%2BwPGxJMFzA%3D%3D";
    private static final String mobileOS    = "&MobileOS=AND";
    private static final String mobileApp   = "&MobileApp=Paldo";

    private static boolean queryOK = false;

    private TourAPIController(){

    }

    public static TourAPIController getToruAPIController(){
        return toruAPIController;
    }

    private String getCallString(int code) {
        switch(code) {
            case AREA_CODE :
                return "areaCode";
            case SERVICE_CODE:
                return "categoryCode";
            case AREA_TOUR_CODE:
                return "areaBasedList";
            case LOCATION_TOUR_CODE:
                return "locationBasedList";
            default:
                return "";
        }
    }

    public ArrayList<ArrayList<String>> queryAPI(int callCode, String query, boolean isList){
        ArrayList<ArrayList<String>> list = null;

        GetItemThread thread = new GetItemThread(callCode, query);
        thread.start();

        while(thread.isAlive()){
            list = thread.getList();
        }
        return list;
    }
    public ArrayList<TourData> queryAPI(int callCode, String query){
        ArrayList<TourData> tourDatalist = null;

        GetItemThread thread = new GetItemThread(callCode, query);
        thread.start();

        while(thread.isAlive()){
            tourDatalist= thread.getTourDataList();
        }
        return tourDatalist;
    }
    /*
        Created by jm on 2017-04-23.
        getter
     */
/////////////////////////////////////////////////////////

    /*
        Created by jm on 2017-04-23.
        thread class to get Item using API
     */
    class GetItemThread extends Thread{
        // save code and name String list
        private ArrayList<ArrayList<String>> list;
        private ArrayList<TourData> tourDataList;

        private String query = "";
        private String call;

        public GetItemThread(int callCode, String query){
            this.call = getCallString(callCode);
            this.query = query;

            list = new ArrayList<ArrayList<String>>();

            tourDataList = new ArrayList<TourData>();
        }

        @Override
        public void run() {
            String queryURL = "" + initURI + call + serviceKey + query
                    + mobileOS + mobileApp;
            Log.d("URL", queryURL);

            try {
                URL url = new URL(queryURL);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

                InputStream is = new BufferedInputStream(urlConnection.getInputStream());

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new InputStreamReader(is, "UTF-8"));  //inputstream 으로부터 xml 입력받기

                String tag;

                xpp.next();
                int eventType = xpp.getEventType();

                TourData tourData = new TourData();

                ArrayList<String> nameList = new ArrayList<>();
                ArrayList<String> codeList = new ArrayList<>();

                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;

                        case XmlPullParser.START_TAG:
                            tag = xpp.getName();    //테그 이름 얻어오기

                            if (tag.equals("code")){
                                xpp.next();
                                codeList.add(xpp.getText());
                            }

                            else if (tag.equals("name")){
                                xpp.next();
                                nameList.add(xpp.getText());
                            }
                            else if (tag.equals("addr1")) {
                                xpp.next();
                                if(tourData.getAdd1()!=null) {
                                    tourDataList.add(tourData);
                                    tourData = new TourData();
                                }
                                tourData.setAddr1(xpp.getText());
                            }

                            else if(tag.equals("addr2")){
                                xpp.next();
                                tourData.setAddr2(xpp.getText());
                            }


                            else if(tag.equals("contentid")){
                                xpp.next();
                                tourData.setContentid(Integer.parseInt(xpp.getText()));
                            }
                            else if(tag.equals("contenttypeid")){
                                xpp.next();
                                tourData.setContenttypeid(Integer.parseInt(xpp.getText()));
                            }


                            else if(tag.equals("firstimage")){
                                xpp.next();
                                tourData.setFirstimage(xpp.getText());
                            }
                            else if(tag.equals("secondimage")){
                                xpp.next();
                                tourData.setFirstimage2(xpp.getText());
                            }

                            else if(tag.equals("mapx")){
                                xpp.next();
                                tourData.setMapx(Double.parseDouble(xpp.getText()));
                            }


                            else if(tag.equals("mapy")){
                                xpp.next();
                                tourData.setMapy(Double.parseDouble(xpp.getText()));
                            }


                            else if(tag.equals("tel")){
                                xpp.next();
                                tourData.setTel(xpp.getText());
                            }
                            else if(tag.equals("title")){
                                xpp.next();
                                tourData.setTitle(xpp.getText());
                            }
                            else if(tag.equals("mlevel")){
                                xpp.next();
                                tourData.setMlevel(Integer.parseInt(xpp.getText()));
                            }

                        case XmlPullParser.TEXT:
                            break;
                        case XmlPullParser.END_TAG:
                            tag = xpp.getName();    //테그 이름 얻어오기
                            break;
                    }
                    eventType = xpp.next();
                }
                list.add(NAME, nameList);
                list.add(CODE, codeList);
                tourDataList.add(tourData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            queryOK = true;
        }

        public ArrayList<ArrayList<String>> getList(){
            return list;
        }

        public ArrayList<TourData> getTourDataList(){
            return tourDataList;
        }

        Handler handler = new Handler() {
            @Override
            public void publish(LogRecord logRecord) {

            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        };
    }
}