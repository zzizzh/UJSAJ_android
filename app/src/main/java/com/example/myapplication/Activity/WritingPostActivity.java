package com.example.myapplication.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Foundation.PostsList;
import com.example.myapplication.PhysicalArchitecture.ClientControl;
import com.example.myapplication.ProblemDomain.Location;
import com.example.myapplication.ProblemDomain.Posts;
import com.example.myapplication.R;

import org.w3c.dom.Text;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_post);

        client=ClientControl.getClientControl();
        posts=new Posts();

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

                startActivityForResult(intent, GO_TO_MAIN);
            }
        });

        Button buttonDel = (Button) findViewById(R.id.backButton);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v5) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public byte[] bitmapToByteArray( Bitmap bitmap ) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        return byteArray ;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_PICTURE_URI) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Uri selPhotoUri = data.getData();
                    Bitmap selPhoto = MediaStore.Images.Media.getBitmap( getContentResolver(), selPhotoUri );

                    byte[] image=bitmapToByteArray(selPhoto);
                    posts.setIImage(image);


                    ImageView imageView = (ImageView) findViewById(R.id.userChoiceImage) ;
                    imageView.setImageBitmap(selPhoto);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(requestCode == GET_LOCATION_URI) {
            if (requestCode == Activity.RESULT_OK) {
                /*Intent intent = getIntent();
                String cat1Code = intent.getStringExtra("cat1Code");
                String cat2Code = intent.getStringExtra("cat2Code");
                String cat3Code = intent.getStringExtra("cat3Code");
                String contentTypeIdCode = intent.getStringExtra("contentTypeIdCode");
                String areaCode = intent.getStringExtra("areaCode");
                String sigunguCode = intent.getStringExtra("sigunguCode");
*/
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
    }

}