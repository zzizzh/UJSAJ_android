package com.example.myapplication.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
=======
import android.view.View;
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

<<<<<<< HEAD
import com.example.myapplication.Foundation.PostsList;
import com.example.myapplication.PhysicalArchitecture.ClientControl;
import com.example.myapplication.ProblemDomain.Location;
import com.example.myapplication.ProblemDomain.Posts;
=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
import com.example.myapplication.R;

import org.w3c.dom.Text;

<<<<<<< HEAD
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.example.myapplication.ProblemDomain.Constants.GET_LOCATION;
import static com.example.myapplication.ProblemDomain.Constants.GET_LOCATION_URI;
import static com.example.myapplication.ProblemDomain.Constants.GET_PICTURE_URI;
import static com.example.myapplication.ProblemDomain.Constants.GO_TO_MAIN;


public class WritingPostActivity extends AppCompatActivity {

    ClientControl client;
    Posts posts;
    Location location;
=======
import java.io.FileNotFoundException;
import java.io.IOException;


public class WritingPostActivity extends AppCompatActivity {
    public int GET_PICTURE_URI = 1;
    public int GET_LOCATION_URI = 2;
    public int GO_TO_MAIN = 4;
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_post);

<<<<<<< HEAD
        client=ClientControl.getClientControl();
        posts=new Posts();

=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
        Button buttonPic = (Button) findViewById(R.id.Pic);
        buttonPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GET_PICTURE_URI);
            }
        });

        Button buttonYoutube = (Button) findViewById(R.id.Youtube);
        buttonYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com"));
                startActivity(intent);
            }
        });

        Button buttonLocation = (Button) findViewById(R.id.Location);
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                //startActivityForResult(intent, GET_LOCATION_URI);
            }
        });

        Button buttonPost = (Button) findViewById(R.id.postButton);
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v4) {
<<<<<<< HEAD
                TextView opinionTxt = (TextView)findViewById(R.id.opinionText);
                TextView urlTxt = (TextView)findViewById(R.id.youtubeURL);

                String opinionText = opinionTxt.getText().toString();
                String urlText = urlTxt.getText().toString();

                posts.setComment(opinionText);
                posts.setUrl(urlText);

                posts.setArtist("IU");
                posts.setSong("Palette");

                client.post(posts);
                PostsList postsList=new PostsList();
                postsList.addPosts(posts);
                client.addMyPostsList(postsList);


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
=======
                TextView txt = (TextView)findViewById(R.id.opinionText);

                String text = txt.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(text, "txt");
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

                startActivityForResult(intent, GO_TO_MAIN);
            }
        });

        Button buttonDel = (Button) findViewById(R.id.backButton);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v5) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
<<<<<<< HEAD
                finish();
=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
            }
        });

    }
<<<<<<< HEAD
    public byte[] bitmapToByteArray( Bitmap bitmap ) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        return byteArray ;
    }
=======
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_PICTURE_URI) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Uri selPhotoUri = data.getData();
                    Bitmap selPhoto = MediaStore.Images.Media.getBitmap( getContentResolver(), selPhotoUri );
<<<<<<< HEAD

                    byte[] image=bitmapToByteArray(selPhoto);
                    posts.setIImage(image);


                    ImageView imageView = (ImageView) findViewById(R.id.userChoiceImage) ;
                    imageView.setImageBitmap(selPhoto);
=======
                    ImageView imageView = (ImageView) findViewById(R.id.userChoiceImage) ;
                    imageView.setImageBitmap(selPhoto) ;
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
<<<<<<< HEAD
        else if(requestCode == GET_LOCATION_URI) {
=======
        if(requestCode == GET_LOCATION_URI) {
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
            if (requestCode == Activity.RESULT_OK) {
                /*Intent intent = getIntent();
                String cat1Code = intent.getStringExtra("cat1Code");
                String cat2Code = intent.getStringExtra("cat2Code");
                String cat3Code = intent.getStringExtra("cat3Code");
                String contentTypeIdCode = intent.getStringExtra("contentTypeIdCode");
                String areaCode = intent.getStringExtra("areaCode");
                String sigunguCode = intent.getStringExtra("sigunguCode");
*/
<<<<<<< HEAD
            }
        }
        else if(requestCode == GO_TO_MAIN) {
            if(requestCode == Activity.RESULT_OK) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, 0);

            }
        }
        else if(requestCode == GET_LOCATION){
            location = (Location)data.getSerializableExtra("result");
            Log.d("test", "[ WritingPostActivity ] selected Location : " + location.toString());
            posts.setLocationInfo(location);
            TextView textView=(TextView)findViewById(R.id.LocationText);
            textView.setText("Daegu");
        }
=======

            }
        }
        if(requestCode == GO_TO_MAIN) {
            if(requestCode == Activity.RESULT_OK) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
>>>>>>> 665a5c80bfb719c5e9e91ad7ac588554f0053b2d
    }

}