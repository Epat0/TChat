package com.tchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class guide_one extends Activity {
	final int Right = 0;
	final int Left = 1;
	private GestureDetector gesturedetector;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide_one);
		gesturedetector = new GestureDetector(guide_one.this,onGestureListener);
	}
	private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener(){
		public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
			float x = e2.getX() - e1.getX();
			float y = e2.getY() - e1.getY();
			if(x > 0 ){
				doResult(Right);
			}else if(x<0){
				doResult(Left);
			}
			return true;
		}
	};
	public boolean onTouchEvent(MotionEvent event){
		return gesturedetector.onTouchEvent(event);
	}
	public void doResult(int action){
		switch (action){
			case Right:				
				break;
			case Left:
				Intent intent = new Intent();
				intent.setClass(guide_one.this, LoginActivity.class);
                startActivity(intent);
				guide_one.this.finish();
				break;
		}
	}
}

