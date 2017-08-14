package com.example.myapplication.PhysicalArchitecture;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.example.myapplication.Foundation.PostsList;
import com.example.myapplication.Data.Posts;
import com.example.myapplication.Data.User;

public class Client extends Thread
{
	Socket sock;
	clientWrite clientW;
	clientRead clientR;
	private ClientControl cControl;

    private static final String host = "222.104.203.106";
    private static final int port = 11114;

	private static Client client = null;

	public Client()
	{
		cControl = new ClientControl();
	}

	public void run(){
        cControl=new ClientControl();
        try {
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
		if(obj instanceof String)
			clientW.sendToServer((String)obj);
		else if(obj instanceof Posts)
			clientW.sendToServerPosts((Posts)obj);
	}
	
	public ClientControl getcControl() {
		return cControl;
	}
	public void setcControl(ClientControl cControl) {
		this.cControl = cControl;
	}
}

class clientRead extends Thread
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
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                temp = clientInputStream.readObject();

                if(temp instanceof PostsList)
                {
                    if(cControl.isRefresh()) {
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
                        if(cControl.getStartTime() - System.currentTimeMillis() > 5000)
                        {
                            cControl.setMoreMyPosts(false);
                            Log.i("ERROR", "over time");
                        }
                        else{
                            cControl.setMoreMyPosts(false);
                            cControl.setMyPostsList((PostsList) temp);
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
                    if(cControl.isLogin()){
                        if(cControl.getStartTime() - System.currentTimeMillis() > 5000)
                        {
                            cControl.setLogin(false);
                            Log.i("ERROR", "over time");
                        }
                        else{
                            cControl.setLogin(false);
                            cControl.addString((String) temp);
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
        finally
        {
            try {

                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class clientWrite extends Thread
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

            while (true)
            {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                while(sendToReadyString==true)
                {
                    out.writeObject(console);
                    sendToReadyString=false;
                }
                while(sendToReadyPosts==true)
                {
                    out.writeObject(postsConsole);
                    sendToReadyPosts=false;
                }

            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
