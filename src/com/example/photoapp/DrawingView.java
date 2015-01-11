package com.example.photoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint.setStrokeWidth(5f);
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		//canvas = new Canvas(bitmap.copy(Bitmap.Config.ARGB_8888, true));
	}

	@Override
	protected void onDraw(Canvas canvasX) {
		canvas = canvasX;
		//Bitmap bitmap = BitmapFactory.decodeFile(MainActivity.fileName);
		
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inSampleSize = 2;
		Bitmap b = BitmapFactory.decodeFile(MainActivity.fileName, o);
		
		canvas = new Canvas(b.copy(Bitmap.Config.ARGB_8888, true));
		canvas.drawPath(path,paint);
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float eventX = event.getX();
		float eventY = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(eventX, eventY);
			return true;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(eventX, eventY);
			break;
		default:
			return false;
		}
		
		invalidate();
		
		return super.onTouchEvent(event);
	}

	private Paint paint = new Paint();
	private Path path  = new Path();
	private Canvas canvas;

	public void SetColor(){
		
	}
	
	public void SetStyle(){
		
	}
	
}
