package com.example.myapplication.ProblemDomain;

import java.io.Serializable;

/**
 * Created by jm on 2017-04-23.
 * 愿�愿� �젙蹂� �뜲�씠�꽣 �겢�옒�뒪
 */

public class TourData implements Serializable{

    private String addr1            = null;         // 二쇱냼
    private String addr2            = null;         // �긽�꽭二쇱냼
    private int contentid           = -1;         //
    private int contenttypeid       = -1;         //
    private String firstimage       =  null;       // 硫붿씤 �씠誘몄�
    private String firstimage2      = null;       // �뜽�꽕�씪
    private double mapX = -1, mapY  = -1;    // GPS醫뚰몴
    private String tel              = null;         // �쟾�솕踰덊샇
    private String title            = null;         // �씠由�
    private int mlevel              = -1;         // 吏��룄 異뺤쿃
    private int bigLocation         = -1;
    private int midLocation         = -1;
    private int smallLocation       = -1;


    public TourData(){}

    public TourData(String addr1, String addr2, int contentid, int contenttypeid, String firstimage, String firstimage2, double mapx, double mapy, String tel, String title, int mlevel) {
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.contentid = contentid;
        this.contenttypeid = contenttypeid;
        this.firstimage = firstimage;
        this.firstimage2 = firstimage2;
        this.mapX = mapX;
        this.mapY = mapY;
        this.tel = tel;
        this.title = title;
        this.mlevel = mlevel;
    }

    public void setContentid(int contentid) {
        this.contentid = contentid;
    }

    public void setContenttypeid(int contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    public void setMapX(double mapx) {
        this.mapX = mapx;
    }

    public void setMapY(double mapy) {
        this.mapY = mapy;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public void setFirstimage2(String firstimage2) {
        this.firstimage2 = firstimage2;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMlevel(int mlevel) {
        this.mlevel = mlevel;
    }

    public String getAdd1() {
        if (addr1 != null)
            return addr1;
        else
            return null;
    }

    public String getAdd2() {
        if (addr2 != null)
            return addr2;
        else
            return null;
    }

    public int getContentid() {
        return contentid;
    }

    public int getContenttypeid() {
        return contenttypeid;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public String getSecondimage() {
        return firstimage2;
    }

    public double getMapX() {
        return mapX;
    }

    public double getMapY() {
        return mapY;
    }

    public String getTel() {
        return tel;
    }

    public String getTitle() {
        return title;
    }

    public int getMlevel() {
        return mlevel;
    }

    public int getBigLocation() {
        return bigLocation;
    }

    public void setBigLocation(int bigLocation) {
        this.bigLocation = bigLocation;
    }

    public int getMidLocation() {
        return midLocation;
    }

    public void setMidLocation(int midLocation) {
        this.midLocation = midLocation;
    }

    public int getSmallLocation() {
        return smallLocation;
    }

    public void setSmallLocation(int smallLocation) {
        this.smallLocation = smallLocation;
    }

    public Location toLocation(){
        Location location = new Location();

        location.setBigLocation(bigLocation);
        location.setMidLocation(midLocation);
        location.setSmallLocation(smallLocation);
        location.setTitle(title);
        location.setContentID(contentid);
        location.setContentTypeID(contenttypeid);

        return location;
    }
}
