package com.codeconquerers.crossesandnaughts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Grid extends View {

	Paint brush;

	public Grid(Context context) {
		super(context);
		brush = new Paint(Paint.ANTI_ALIAS_FLAG);
		brush.setColor(Color.WHITE);
		brush.setStrokeWidth(5);
	}

	@Override
	public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    	float w = (float) this.getWidth();
    	float h = (float) this.getHeight();
    	float startXLeft = w / 8;
    	float startYTop = (h - w) / 2;
    	float l = w * 3 / 4; // length
		canvas.drawColor(Color.BLACK);
		canvas.drawLine(startXLeft, startYTop + l/3, startXLeft+l, startYTop + l/3, brush);//Top horizontal
		canvas.drawLine(startXLeft, startYTop + l*2/3, startXLeft + l, startYTop + l*2/3, brush); //Bottom horizontal
		canvas.drawLine(startXLeft + l/3, startYTop, startXLeft + l/3, startYTop + l, brush);//Left vertical
		canvas.drawLine(startXLeft + l*2/3, startYTop, startXLeft + l*2/3, startYTop + l, brush);//Right vertical
	}
}
