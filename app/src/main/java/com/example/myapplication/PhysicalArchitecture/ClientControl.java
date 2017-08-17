package com.example.myapplication.PhysicalArchitecture;

<<<<<<< HEAD

import android.util.Log;

import com.example.myapplication.ProblemDomain.Posts;
import com.example.myapplication.ProblemDomain.User;
import com.example.myapplication.Foundation.PostsList;

import java.io.Serializable;
import java.util.ArrayList;


public class ClientControl implements Serializable{
=======
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.example.myapplication.Data.Posts;
import com.example.myapplication.Data.User;
import com.example.myapplication.Foundation.PostsList;



public class ClientControl implements Serializable {
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

	private ArrayList<Posts> timeLine;
	private ArrayList<Posts> myPostsList;
	private ArrayList<Posts> myLikeList;
	private ArrayList<String> stringList;

<<<<<<< HEAD
	private User me = null;
=======
	private User me;
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

	private String message = "";
	/*
       checking request complete
     */
	private boolean login = false;
	private boolean register = false;
	private boolean refresh = false;
	private boolean morePosts = false;
	private boolean myPosts = false;
	private boolean moreMyPosts = false;
	private boolean myLike = false;
	private boolean moreLike = false;
	private boolean post = false;
	private boolean delete = false;
	private boolean like = false;
	private boolean dislike = false;
	private boolean updateUser = false;

<<<<<<< HEAD
	private boolean closeSocket = false;

	private long startTime = 0;

	public Client client;
	private static ClientControl cControl = new ClientControl();

	public ClientControl() {
		timeLine = new ArrayList<Posts>();
		myPostsList = new ArrayList<Posts>();
		myLikeList  = new ArrayList<Posts>();
=======
	private long startTime = 0;

	public ClientControl() {
		timeLine = new ArrayList<Posts>();
		myPostsList = new ArrayList<Posts>();
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
		stringList = new ArrayList<String>();

		me = null;

<<<<<<< HEAD
		client = new Client();
	}

	public static ClientControl getClientControl(){
		return cControl;
=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
	}

   /*
      received data control
    */

	public void setTimeLine(PostsList postsList) { timeLine = postsList.getAll();}

	public void addTimeLine(PostsList postsList) {   timeLine.addAll(postsList.getAll());   }

	public void setMyPostsList(PostsList postsList) {   myPostsList = postsList.getAll();   }

	public void addMyPostsList(PostsList postsList) {   myPostsList.addAll(postsList.getAll());   }

	public void setLikeList(PostsList postsList) {   myLikeList = postsList.getAll();   }

	public void addLikeList(PostsList postsList) {   myLikeList.addAll(postsList.getAll());   }

	public void setMe(User user) {
		me = user;
	}

	public void addString(String string) {
		stringList.add(string);
	}

	public void resetAll() {
		stringList = new ArrayList<String>();
		timeLine = new ArrayList<Posts>();
		myPostsList = new ArrayList<Posts>();
<<<<<<< HEAD
		myLikeList  = new ArrayList<Posts>();
=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

		me = null;
	}

//   public void resetTimeLine(){
//      timeLine = new ArrayList<Posts>();
//   }

//   public void resetMyPostsList(){
//      myPostsList = new ArrayList<Posts>();
//   }

//   public void resetMyLikeList() { myLikeList = new ArrayList<Posts>();}

	/*
       send message to server
       if checking boolean is true, don't execute
     */
	public void login(String id, String pass){
		if(!login) {
			startTime = System.currentTimeMillis();

			login = true;

			message = "#login%";
			message += id;
			message += "%";
			message += pass;

<<<<<<< HEAD
			Log.d("test", "[ CLIENTCONTROL : login ]ogin check: " + isLogin());
			client.sendToServer(message);
=======
			if(Client.getClient() == null){
				Log.d("test", "null client");
			}
			Client.getClient().sendToServer(message);

>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
			message = "";
		}
	}

	public void register(String id, String pass){
		if(!register) {
			startTime = System.currentTimeMillis();

			register = true;

			message = "#register%";
			message += id;
			message += "%";
			message += pass;

<<<<<<< HEAD
			client.sendToServer(message);
=======
			Client.getClient().sendToServer(message);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

			message = "";
		}
	}

	public void refresh(){
		if(!refresh) {
			startTime = System.currentTimeMillis();

			refresh = true;

			message = "#refresh";

<<<<<<< HEAD
			client.sendToServer(message);
=======
			Client.getClient().sendToServer(message);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

			message = "";
		}
	}

	public void morePosts(){
		if(!morePosts) {
			startTime = System.currentTimeMillis();

			morePosts = true;

			message = "#morePosts";

<<<<<<< HEAD
			client.sendToServer(message);
=======
			Client.getClient().sendToServer(message);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

			message = "";
		}
	}

	public void myPosts(){
		if(!myPosts) {
			startTime = System.currentTimeMillis();

			myPosts = true;

			message = "#myPosts";

<<<<<<< HEAD
			client.sendToServer(message);
=======
			Client.getClient().sendToServer(message);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

			message = "";
		}
	}

	public void moreMyPosts(){
		if(!moreMyPosts) {
			startTime = System.currentTimeMillis();

			moreMyPosts = true;

			message = "#moreMyPosts";

<<<<<<< HEAD
			client.sendToServer(message);
=======
			Client.getClient().sendToServer(message);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

			message = "";
		}
	}

	public void myLike(){
		if(!myLike) {
			startTime = System.currentTimeMillis();

			myLike = true;

			message = "#myLike";

<<<<<<< HEAD
			client.sendToServer(message);
=======
			Client.getClient().sendToServer(message);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

			message = "";
		}
	}

	public void moreMyLike(){
		if(!moreLike) {
			startTime = System.currentTimeMillis();

			moreLike = true;

			message = "#moreLike";

<<<<<<< HEAD
			client.sendToServer(message);
=======
			Client.getClient().sendToServer(message);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

			message = "";
		}
	}

<<<<<<< HEAD
	public void post(Posts p){
=======
	public void postMessage(){
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
		if(!post) {
			startTime = System.currentTimeMillis();

			post = true;

<<<<<<< HEAD
			client.sendToServer(p);
=======
			message = "#post";

			Client.getClient().sendToServer(message);

			message = "";
		}
	}

	public void post(Posts p){
		if(post) {
			startTime = System.currentTimeMillis();

			Client.getClient().sendToServer(p);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
		}
	}

	public void delete(int index){
		if(!delete) {
			startTime = System.currentTimeMillis();

			delete = true;

			message = "#delete%";
			message += index;

<<<<<<< HEAD
			client.sendToServer(message);
=======
			Client.getClient().sendToServer(message);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

			message = "";
		}
	}

	public void like(int index   ){
		if(!like) {
			startTime = System.currentTimeMillis();

			like = true;

			message = "#like%";
			message += index;

<<<<<<< HEAD
			client.sendToServer(message);
=======
			Client.getClient().sendToServer(message);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

			message = "";
		}
	}

	public void dislike(int index){
		if(!post) {
			startTime = System.currentTimeMillis();

			dislike = true;

			message = "#dislike%";
			message += index;

<<<<<<< HEAD
			client.sendToServer(message);
=======
			Client.getClient().sendToServer(message);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

			message = "";
		}
	}

	public void updateUser(){
		if(!updateUser) {
			startTime = System.currentTimeMillis();

			updateUser = true;

			message = "#updateUser";

<<<<<<< HEAD
			client.sendToServer(message);
=======
			Client.getClient().sendToServer(message);
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

			message = "";
		}
	}

   /*
      get and set method
    */

	public boolean isLogin() {
		return login;
	}

	public boolean isRegister() {
		return register;
	}

	public boolean isRefresh() {
		return refresh;
	}

	public boolean isMorePosts() {
		return morePosts;
	}

	public boolean isMyLike() {
		return myLike;
	}

<<<<<<< HEAD
	public boolean isMyPost() {
		return myPosts;
	}

=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
	public boolean isMoreLike() {
		return moreLike;
	}

	public boolean isPost() {
		return post;
	}

	public boolean isDelete() {
		return delete;
	}

	public boolean isLike() {
		return like;
	}

	public boolean isDislike() {
		return dislike;
	}

	public boolean isUpdateUser() {
		return updateUser;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public void setRegister(boolean register) {
		this.register = register;
	}

	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}

	public void setMorePosts(boolean morePosts) {
		this.morePosts = morePosts;
	}

	public void setMyLike(boolean myLike) {
		this.myLike = myLike;
	}

	public void setMoreLike(boolean moreLike) {
		this.moreLike = moreLike;
	}

	public void setPost(boolean post) {
		this.post = post;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public void setDislike(boolean dislike) {
		this.dislike = dislike;
	}

	public void setUpdateUser(boolean updateUser) {
		this.updateUser = updateUser;
	}

	public boolean isMyPosts() {
		return myPosts;
	}

	public void setMyPosts(boolean myPosts) {
		this.myPosts = myPosts;
	}

	public boolean isMoreMyPosts() {
		return moreMyPosts;
	}

	public void setMoreMyPosts(boolean moreMyPosts) {
		this.moreMyPosts = moreMyPosts;
	}

<<<<<<< HEAD
	public boolean isCloseSocket(){   return closeSocket;   }

	public void setCloseSocket() {
		closeSocket = false;
	}


	public void setME(User ME) {
		this.me = ME;
	}
	public User getMe() {
		return me;
	}

	/*
            get data
         */
=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
	public ArrayList<Posts> getTimeLine() {
		return timeLine;
	}

<<<<<<< HEAD
	public ArrayList<Posts> getMyPostsList() {
		return myPostsList;
	}

	public ArrayList<Posts> getMyLikeList() {
		return myLikeList;
	}
=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

	public ArrayList<String> getStringList() {
		return stringList;
	}

<<<<<<< HEAD
	/*
       socket connection method
     */
	public void terminateConnection(){
		closeSocket = true;
	}

=======
	public ArrayList<Posts> getMyLikeList() {
		return myLikeList;
	}

	public ArrayList<Posts> getMyPostsList() {
		return myPostsList;
	}
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
}