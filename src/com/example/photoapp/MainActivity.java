package com.example.photoapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Bitmap bp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }
    
    
    private void setup() {
		bp = null;
		
	}


	public void TakePhotoBtnClick(View v){
		
    	openCamera();
		
    }
    
    public void DrawBtnClick(View v){
    	
    }

    public void SendPhotoBtnClick(View v){
    	sendMail();
}

    private void sendMail() {
    	 String[] recipients = {"ricipients"};
    	 	      Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
    	 	      // prompts email clients only
    	 	      email.setType("message/rfc822");
    	 	 
    	 	      email.putExtra(Intent.EXTRA_EMAIL, recipients);
    	 	      email.putExtra(Intent.EXTRA_SUBJECT, "subject");
    	 	      email.putExtra(Intent.EXTRA_TEXT, "body");
    	 	 
    	 	      try {
    	 	        // the user can choose the email client
    	 	         startActivity(Intent.createChooser(email, "Choose an email client from..."));
    	 	      
    	 	      } catch (android.content.ActivityNotFoundException ex) {
    	 	         Toast.makeText(MainActivity.this, "No email client installed.",
    	 	                 Toast.LENGTH_LONG).show();
    	 	      }
	}


	public void openCamera(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
     }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       
       if (resultCode == RESULT_OK){
    		   bp = (Bitmap) data.getExtras().get("data");
       }else{
    	   Toast.makeText(getApplicationContext(), "Nie zapisano zdjêcia", Toast.LENGTH_SHORT).show();
       }
       //imgFavorite.setImageBitmap(bp);
    }
    
}
