package com.example.devin.nrf_toolbar;

import android.content.Context;
import android.util.AttributeSet;

public class ClosableSpinner extends android.support.v7.widget.AppCompatSpinner {
	public ClosableSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void close() {
		super.onDetachedFromWindow();
	}
}
