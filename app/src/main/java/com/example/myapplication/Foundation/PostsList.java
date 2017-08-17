package com.example.myapplication.Foundation;

<<<<<<< HEAD
import com.example.myapplication.ProblemDomain.Posts;

import java.io.Serializable;
import java.util.ArrayList;

public class PostsList implements Serializable{

    private static final long serialVersionUID = 100L;

    ArrayList<Posts> postsList;

    public PostsList() {
        postsList = new ArrayList<Posts>();
    }

    public PostsList(ArrayList<Posts> p) {
        postsList = p;
    }

    public void addPosts(Posts p) {
        postsList.add(p);
    }

    public void addAll(PostsList p){
        postsList.addAll(p.getAll());
    }

    public Posts getPosts(int i) {
        return postsList.get(i);
    }

    public ArrayList<Posts> getAll(){
        return postsList;
    }

    public int size() {
        return postsList.size();
    }

    public void deletePosts(int i) {
        if (postsList.size() > 0)
            postsList.remove(i);
    }

    @Override
    public String toString() {
        String s = new String();
        for(int i = 0;i<this.size();i++){
            s += this.getPosts(i).toString();
            s += "\n";
        }
        return s;
    }


}
=======
import java.util.ArrayList;

import com.example.myapplication.Data.Posts;

public class PostsList {
	ArrayList<Posts> postsList;
	
	public PostsList() {
		postsList = new ArrayList<Posts>();
	}

	public void addPosts(Posts p) {
		postsList.add(p);
	}

	public Posts getPosts(int i) {
		return postsList.get(i);
	}
	
	public ArrayList<Posts> getAll(){
		return postsList;
	}

	public int size() {
		return postsList.size();
	}

	public void deletePosts(int i) {
		if (postsList.size() > 0)
			postsList.remove(i);
		else
			System.out.println("������ �Ұ��� �մϴ�.");
	}
}
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
