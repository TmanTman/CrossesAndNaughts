package com.codeconquerers.crossesandnaughts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Cross extends View{

	private final float x;
	private final float y;
	private final int l;
	private final Paint brush = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	public Cross (Context context, float x, float y, int l){
	super(context);
		brush.setColor(Color.BLUE);
		brush.setStrokeWidth(5);
		this.x = x;
		this.y = y;
		this.l = l*4/5; //Scale length to tweak appearance
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawLine(x - l, y - l, x + l, y + l, brush);
		canvas.drawLine(x - l, y + l, x + l, y - l, brush);
	}

}
