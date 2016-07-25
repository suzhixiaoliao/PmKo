package com.pumin.lzl.pumin.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class ClickText extends LinearLayout {

	public ClickText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setPressed(boolean pressed) {
		
		if (pressed && ((View) getParent()).isPressed()) {
			return;
		}
		super.setPressed(pressed);
	}
}
