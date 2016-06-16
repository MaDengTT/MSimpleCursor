package com.madengtt.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by MaDeng on 2016/5/14.
 */
public class SimpleCursorView extends View {
    //圆的数量
    int mSize;
    //圆的半径
    int mRedius;
    //实心圆的颜色
    int mSolidColor;
    //空心圆颜色
    int mHollowColor;
    //圆的间隔
    int mSpaceWidth;
    //当前实心圆位置
    int mCursor;

    int[] mX;

    Paint mPaint;
    Paint mSolidPaint;
    public SimpleCursorView(Context context) {
        this(context,null);
    }

    public SimpleCursorView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleCursorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs,defStyleAttr);
        initDraw();
    }
    private void initDraw(){
        mCursor = 0;
        mX = new int[mSize];
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mSolidPaint = new Paint();
        mSolidPaint.setAntiAlias(true);
//        mSolidPaint.setStyle(Paint.Style.FILL); //是否空心

        mPaint.setColor(mHollowColor);
        mSolidPaint.setColor(mSolidColor);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SimpleCursorView,defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0;i<n;i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.SimpleCursorView_cursor_size) {
                mSize = a.getInt(attr, 3);
            } else if (attr == R.styleable.SimpleCursorView_redius) {
                mRedius = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
            } else if (attr == R.styleable.SimpleCursorView_hollow_color) {
                mHollowColor = a.getColor(attr,Color.YELLOW);
            } else if (attr == R.styleable.SimpleCursorView_space_width) {
                mSpaceWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
            } else if (attr == R.styleable.SimpleCursorView_solid_color) {
                mSolidColor = a.getColor(attr, Color.RED);
            }
        }
    }


    /**
     * 设置圆数量
     * @param size
     */
    public void setmSize(int size) {
        mSize = size;
        mX = new int[mSize];
        invalidate();
    }

    /**
     * 设置实心圆颜色
     * @param color
     */
    public void setmSolidColor(int color) {
        mSolidPaint.setColor(color);
    }

    /**
     * 设置空心圆颜色
     * @param color
     */
    public void setmHollowColor(int color) {
        mPaint.setColor(color);
    }

    /**
     * 设置间隔
     * @param width
     */
    public void setmSpaceWidth(int width) {
        this.mSpaceWidth = width;
    }

    /**
     * 更新当前位置
     * @param mCurray
     */
    public void upmCurray(int mCurray) {
        this.mCursor = mCurray;
        invalidate();
    }

    public int getmSize(){
        return mSize;
    }

    public void setmRedius(int mRedius) {
        this.mRedius = mRedius;
    }




    @Override
    protected void onDraw(Canvas canvas) {


        int height = getHeight();
        int y = height/2;
        setX();

        for(int i = 0;i<mX.length;i++) {
            if (i == mCursor) {
                canvas.drawCircle(mX[i],y,mRedius+1,mSolidPaint);
            }else{
                canvas.drawCircle(mX[i],y,mRedius,mPaint);
            }
        }

    }

    private void setX() {
        int width = getWidth();

        int x = width/2;

        if(mSize%2 == 0){
            int mCenter = mSize/2;
            for(int i = 0;i<mSize;i++) {
                if (i < mCenter) {
                    mX[i] = x-(mSpaceWidth/2+mRedius+(2*mRedius+mSpaceWidth)*(mCenter-i-1));
                } else if (i >= mCenter) {
                    mX[i] = x + (mSpaceWidth / 2 + mRedius + (2 * mRedius + mSpaceWidth) * (i - mCenter));
                }
            }
        }else{
            int mCenter = mSize/2;
            for(int i = 0;i<mSize;i++) {
                if(i<(mCenter)){
                    mX[i] = x - (mRedius * 2 + mSpaceWidth) * (mCenter - i );
                } else if (i == mCenter) {
                    mX[i] = x;
                } else if (i > (mCenter)) {
                    mX[i] = x + (mRedius * 2 + mSpaceWidth) * (i - mCenter);
                }
            }
        }
    }
}
