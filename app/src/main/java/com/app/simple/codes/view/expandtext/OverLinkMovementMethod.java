package com.app.simple.codes.view.expandtext;

import android.text.NoCopySpan;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;

public class OverLinkMovementMethod extends LinkMovementMethod {

	public static boolean canScroll = false;

	@Override
	public boolean onTouchEvent(android.widget.TextView widget, Spannable buffer, MotionEvent event) {
		int action = event.getAction();

		if(action == MotionEvent.ACTION_MOVE){
			if(!canScroll){
				return true;
			}
		}

		return super.onTouchEvent(widget, buffer, event);
	}

	public static android.text.method.MovementMethod getInstance() {
		if (sInstance == null)
			sInstance = new OverLinkMovementMethod();

		return sInstance;
	}

	private static OverLinkMovementMethod sInstance;
	private static Object FROM_BELOW = new NoCopySpan.Concrete();
}
