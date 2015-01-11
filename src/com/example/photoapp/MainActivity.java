package com.example.photoapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Bitmap bmp;
	public static String fileName = "/sdcard/PhotoApp/photo.png";
	private String myDirectory;
	private String[] recipients = {"johny.dudek@gmail.com"};
	private Uri uriSavedImage;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }
    
    
    private void setup() {
		bmp = null;
		
	}


	public void TakePhotoBtnClick(View v){
		
    	openCamera();
		
    }
    
    public void DrawBtnClick(View v){
    	Intent intent = new Intent(this,Drawing.class);
    	startActivity(intent);
    }

    public void SendPhotoBtnClick(View v){
    	String path =  savePhoto();
    	
    	sendMail(path);
}

    
    private String savePhoto() {
    	

        File file = new File(new File("/sdcard"+myDirectory+"/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       return file.getAbsolutePath();
	}


	private void sendMail(String path) {
    	 if(uriSavedImage!=null){
    	 	      Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
    	 	      // prompts email clients only
    	 	      email.setType("message/rfc822");
    	 	 
    	 	      email.putExtra(Intent.EXTRA_EMAIL, recipients);
    	 	      email.putExtra(Intent.EXTRA_SUBJECT, "Photo from my App");
    	 	      email.putExtra(Intent.EXTRA_TEXT, "Just enjoy it");
    	 	      
    	 	      
    	 	      email.putExtra(Intent.EXTRA_STREAM, uriSavedImage);
    	 	 
    	 	      try {
    	 	        // the user can choose the email client
    	 	         startActivity(Intent.createChooser(email, "Choose an email client from..."));
    	 	      
    	 	      } catch (android.content.ActivityNotFoundException ex) {
    	 	         Toast.makeText(MainActivity.this, "No email client installed.",
    	 	                 Toast.LENGTH_LONG).show();
    	 	      }
    	 }
	}


	public void openCamera(){
		
        Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        uriSavedImage=Uri.fromFile(new File(fileName));
		camera.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(camera, 1);
     }
   
    
}
