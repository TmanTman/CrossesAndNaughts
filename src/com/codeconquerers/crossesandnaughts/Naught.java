package com.codeconquerers.crossesandnaughts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class Naught extends View{

	private final float x;
	private final float y;
	private final int r;
	private final Paint brush = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	public Naught (Context context, float x, float y, int r){
	super(context);
		brush.setColor(Color.YELLOW);
		brush.setStyle(Paint.Style.STROKE);
		brush.setStrokeWidth(5);
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawCircle(x, y, r, brush);
	}

}
