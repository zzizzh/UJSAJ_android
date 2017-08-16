package com.example.myapplication.PhysicalArchitecture;

import android.util.Log;

import com.example.myapplication.ProblemDomain.Posts;
import com.example.myapplication.ProblemDomain.User;
import com.example.myapplication.Foundation.PostsList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Client extends Thread implements Serializable
{
	Socket sock;
	clientWrite clientW;
	clientRead clientR;
	private ClientControl cControl;

	private static final String host = "222.104.203.106";
	private static final int port = 11114;

	private static Client client = null;

	public void run(){
		try {
			cControl = cControl.getClientControl();

			System.out.println("-----Ŭ���̾�Ʈ�� ����Ǿ����ϴ�.");
			sock = new Socket(host, port);

		} catch (IOException e) {
			e.printStackTrace();
		}

		clientW=new clientWrite(sock);
		clientR=new clientRead(sock,cControl);

		clientW.start();
		clientR.start();
	}

	static public Client getClient(){
		if(client != null)
			return client;
		else
			return new Client();
	}

	public void sendToServer(Object obj)
	{
		if(obj instanceof String){
			if(clientW != null) {
				clientW.sendToServer((String) obj);
				Log.d("test", "send message : " + (String)obj);
			}
			else
				Log.d("test", "clientW : NULL");
		}
		else if(obj instanceof Posts) {
			if(clientW != null) {
				clientW.sendToServerPosts((Posts) obj);
				Log.d("test", "[clientW:sendToServer:Posts] send message : " + ((Posts) obj).toString());
			}
			else
				Log.d("test", "[clientW:sendToServer:Posts] NULL");
		}
	}

	public ClientControl getcControl() {
		return cControl;
	}
	public void setcControl(ClientControl cControl) {
		this.cControl = cControl;
	}
}

class clientRead extends Thread implements Serializable
{
	Socket socket;
	private ClientControl cControl;

	public clientRead(Socket socket,ClientControl cControl)
	{
		this.socket=socket;
		this.cControl=cControl;
	}

	@Override
	public void run(){
		try {
			ObjectInputStream clientInputStream = new ObjectInputStream(socket.getInputStream());
			Object temp;

			while(true)
			{
				if(cControl.isCloseSocket()){
					try{
						socket.close();
						cControl.setCloseSocket();
					}catch (Exception e){
						e.printStackTrace();;
					}
				}

				Log.d("test", "read thread : running");
				Log.d("test", "[ CLIENTREAD: run ]login check: " + cControl.isLogin());
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(socket == null)
					Log.d("test", "socket is null");
				if(clientInputStream == null)
					Log.d("test", "inputStream is null");

				temp = clientInputStream.readObject();

				if(temp == null)
					Log.d("test", "temp is null");
				else
					Log.d("test", "temp is not null");

				if(temp instanceof PostsList)
				{
					if(cControl.isRefresh()) {
                        Log.d("test","Refresh()");
						if(cControl.getStartTime() - System.currentTimeMillis() > 5000)
						{
							cControl.setRefresh(false);
							Log.i("ERROR", "over time");
						}
						else {
							cControl.setRefresh(false);
							cControl.setTimeLine((PostsList) temp);
						}
					}
					else if(cControl.isMorePosts()){
						if(cControl.getStartTime() - System.currentTimeMillis() > 5000)
						{
							cControl.setMorePosts(false);
							Log.i("ERROR", "over time");
						}
						else{
							cControl.setRefresh(false);
							cControl.addTimeLine((PostsList) temp);
						}
					}
					else if(cControl.isMyPosts()){
						if(cControl.getStartTime() - System.currentTimeMillis() > 5000)
						{
							cControl.setMyPosts(false);
							Log.i("ERROR", "over time");
						}
						else{
							cControl.setMyPosts(false);
							cControl.setMyPostsList((PostsList) temp);
						}

					}
					else if(cControl.isMoreMyPosts()){
						while(cControl.isMoreMyPosts()) {
							if (cControl.getStartTime() - System.currentTimeMillis() > 5000) {
								cControl.setMoreMyPosts(false);
								Log.i("ERROR", "over time");
								break;
							} else {
								cControl.setMoreMyPosts(false);
								cControl.setMyPostsList((PostsList) temp);
							}
						}
					}
					else if(cControl.isMyLike()){
						if(cControl.getStartTime() - System.currentTimeMillis() > 5000)
						{
							cControl.setMyLike(false);
							Log.i("ERROR", "over time");
						}
						else{
							cControl.setMyLike(false);
							cControl.setLikeList((PostsList) temp);
						}
					}
					else if(cControl.isMoreLike()){
						if(cControl.getStartTime() - System.currentTimeMillis() > 5000)
						{
							cControl.setMyLike(false);
							Log.i("ERROR", "over time");
						}
						else{
							cControl.setMyLike(false);
							cControl.addLikeList((PostsList) temp);
						}
					}
				}
				else if(temp instanceof User)
				{
					if(cControl.isUpdateUser()){
						if(cControl.getStartTime() - System.currentTimeMillis() > 5000)
						{
							cControl.setUpdateUser(false);
							Log.i("ERROR", "over time");
						}
						else{
							cControl.setUpdateUser(false);
							cControl.setMe((User) temp);
						}
					}
				}

				else if(temp instanceof String)
				{
					Log.d("test", "[ CLIENTREAD: else if(temp instanceof String) ]login check: " + cControl.isLogin());
					if(cControl.isLogin()){
						Log.d("test", "received String : " + (String)temp);
						if(cControl.getStartTime() - System.currentTimeMillis() > 5000)
						{
							cControl.setLogin(false);
							Log.i("ERROR", "over time");
						}
						else{
							Log.d("test", "login success : " + (String)temp);
							cControl.setLogin(false);
							cControl.addString((String) temp);
							Log.d("test", "string in list : " + cControl.getStringList().get(0));
						}
					}
					else if(cControl.isRegister()){
						if(cControl.getStartTime() - System.currentTimeMillis() > 5000)
						{
							cControl.setRegister(false);
							Log.i("ERROR", "over time");
						}
						else{
							cControl.setRegister(false);
							cControl.addString((String) temp);
						}
					}
					else if(cControl.isPost()){
						if(cControl.getStartTime() - System.currentTimeMillis() > 5000)
						{
							cControl.setPost(false);
							Log.i("ERROR", "over time");
						}
						else{
							cControl.setPost(false);
							cControl.addString((String) temp);
						}
					}
				}
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class clientWrite extends Thread implements Serializable
{
	private Socket socket;
	private String console;
	private Posts postsConsole;

	private boolean sendToReadyString;
	private boolean sendToReadyPosts;

	public clientWrite(Socket socket)
	{
		this.socket=socket;
		sendToReadyString=false;
		sendToReadyPosts=false;
	}

	@Override
	public void run() {
		ObjectOutputStream out;

		try {
			out = new ObjectOutputStream(socket.getOutputStream());

			while(true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while (sendToReadyString == true) {
					Log.d("test", "in thread before writing");
					out.writeObject(console);
					Log.d("test", "write complete");
					sendToReadyString = false;
				}
				while (sendToReadyPosts == true) {
					out.writeObject(postsConsole);
					sendToReadyPosts = false;
				}
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendToServer(String msg)
	{
		console=msg;
		sendToReadyString=true;
	}
	public void sendToServerPosts(Posts posts)
	{
		postsConsole=posts;
		sendToReadyPosts=true;
	}
}