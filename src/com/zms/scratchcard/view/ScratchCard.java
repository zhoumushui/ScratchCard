package com.zms.scratchcard.view;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ScratchCard extends View {

	/** 路径画笔 */
	private Paint outsidePaint;
	/** 绘制路径 */
	private Path path;
	/** 覆盖Bitmap */
	private Bitmap outsideBitmap;

	/** 文本信息 */
	private String textResult;
	/** 文本尺寸 */
	private int textSize;
	/** 文本信息画笔 */
	private Paint insidePaint;
	/** 记录刮奖信息文本的宽和高，使文本居中 */
	private Rect rectText;

	/** 画布 */
	private Canvas canvas;
	private int lastDrawX, lastDrawY;

	public ScratchCard(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		initial();
	}

	public ScratchCard(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ScratchCard(Context context) {
		this(context, null);
	}

	/** 初始化操作 */
	private void initial() {
		outsidePaint = new Paint();
		path = new Path();

		int randomGift = 1 + (int) (new Random().nextFloat() * 10);
		textResult = "￥" + randomGift;
		textSize = 80;
		rectText = new Rect();
		insidePaint = new Paint();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int width = getMeasuredWidth();
		int height = getMeasuredHeight();

		outsideBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		canvas = new Canvas(outsideBitmap);
		setOutsidePaint();
		setInsidePaint();
		canvas.drawColor(Color.parseColor("#999999"));
	}

	/** 设置绘制文本画笔属性 */
	private void setInsidePaint() {
		insidePaint.setColor(Color.BLACK);
		insidePaint.setStyle(Style.FILL);
		insidePaint.setTextSize(textSize);
		// 绘制当前文本画笔的宽和高
		insidePaint.getTextBounds(textResult, 0, textResult.length(), rectText);
	}

	/** 设置绘制Path画笔属性 */
	private void setOutsidePaint() {
		outsidePaint.setColor(Color.RED);
		outsidePaint.setAntiAlias(true);
		outsidePaint.setDither(true);
		outsidePaint.setStrokeJoin(Paint.Join.ROUND);
		outsidePaint.setStrokeCap(Paint.Cap.ROUND);
		outsidePaint.setStyle(Style.STROKE);
		outsidePaint.setStrokeWidth(20);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			lastDrawX = x;
			lastDrawY = y;
			path.moveTo(lastDrawX, lastDrawY);
			break;

		case MotionEvent.ACTION_UP:

			break;

		case MotionEvent.ACTION_MOVE:
			int dx = Math.abs(x - lastDrawX); // 横向移动的绝对值
			int dy = Math.abs(y - lastDrawY); // 纵向移动的绝对值
			if (dx > 3 || dy > 3) { // 避免频繁的调用lineTo
				path.lineTo(x, y);
			}
			lastDrawX = x;
			lastDrawY = y;

			break;

		default:
			break;
		}

		invalidate();

		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawText(textResult, getWidth() / 2 - rectText.width() / 2,
				getHeight() / 2 + rectText.height() / 2, insidePaint);

		drawPath();
		canvas.drawBitmap(outsideBitmap, 0, 0, null);
	}

	private void drawPath() {

		outsidePaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));

		canvas.drawPath(path, outsidePaint);

	}

}
