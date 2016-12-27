package com.haley.hlseekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RatingBar;

public class HlSeekBar extends RatingBar {

    private int mNumStars;//quantity of icones
    private int mBgBitmapResourceId;//Foreground drawable
    private int mPreBitmapResourceId;//Background drawable
    private Bitmap mBgBitmap;
    private Bitmap mPreBitmap;
    private float mHorizontalSpace;//The spacing among icons
    private Bitmap[] mBgBitmaps;
    private Bitmap[] mPreBitmaps;

    public HlSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HLSeekBar);

        mNumStars = a.getInt(R.styleable.HLSeekBar_numStars, 5);
        mHorizontalSpace = a.getDimension(R.styleable.HLSeekBar_horizontal_space, 0);

        mBgBitmaps = new Bitmap[mNumStars];
        mPreBitmaps = new Bitmap[mNumStars];

        for (int i = 0; i < mNumStars; i++) {
            switch (i) {
                case 0:
                    mBgBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_bg_drawable1, -1);
                    mBgBitmap = BitmapFactory.decodeResource(getResources(), mBgBitmapResourceId);

                    mPreBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_pre_drawable1, -1);
                    mPreBitmap = BitmapFactory.decodeResource(getResources(), mPreBitmapResourceId);
                    break;
                case 1:
                    mBgBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_bg_drawable2, -1);
                    mBgBitmap = BitmapFactory.decodeResource(getResources(), mBgBitmapResourceId);

                    mPreBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_pre_drawable2, -1);
                    mPreBitmap = BitmapFactory.decodeResource(getResources(), mPreBitmapResourceId);
                    break;
                case 2:
                    mBgBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_bg_drawable3, -1);
                    mBgBitmap = BitmapFactory.decodeResource(getResources(), mBgBitmapResourceId);

                    mPreBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_pre_drawable3, -1);
                    mPreBitmap = BitmapFactory.decodeResource(getResources(), mPreBitmapResourceId);
                    break;
                case 3:
                    mBgBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_bg_drawable4, -1);
                    mBgBitmap = BitmapFactory.decodeResource(getResources(), mBgBitmapResourceId);

                    mPreBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_pre_drawable4, -1);
                    mPreBitmap = BitmapFactory.decodeResource(getResources(), mPreBitmapResourceId);
                    break;
                case 4:
                    mBgBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_bg_drawable5, -1);
                    mBgBitmap = BitmapFactory.decodeResource(getResources(), mBgBitmapResourceId);

                    mPreBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_pre_drawable5, -1);
                    mPreBitmap = BitmapFactory.decodeResource(getResources(), mPreBitmapResourceId);
                    break;
                case 5:
                    mBgBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_bg_drawable6, -1);
                    mBgBitmap = BitmapFactory.decodeResource(getResources(), mBgBitmapResourceId);

                    mPreBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_pre_drawable6, -1);
                    mPreBitmap = BitmapFactory.decodeResource(getResources(), mPreBitmapResourceId);
                    break;
                case 6:
                    mBgBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_bg_drawable7, -1);
                    mBgBitmap = BitmapFactory.decodeResource(getResources(), mBgBitmapResourceId);

                    mPreBitmapResourceId = a.getResourceId(R.styleable.HLSeekBar_pre_drawable7, -1);
                    mPreBitmap = BitmapFactory.decodeResource(getResources(), mPreBitmapResourceId);
                    break;
            }
            mBgBitmaps[i] = mBgBitmap;
            mPreBitmaps[i] = mPreBitmap;
        }

        a.recycle();
    }

    public HlSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HlSeekBar(Context context) {
        super(context);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int desiredWidth = (int) (mBgBitmap.getWidth() * mNumStars + (mNumStars - 1) * mHorizontalSpace + getPaddingLeft() + getPaddingRight());

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;

        if (widthMode == MeasureSpec.EXACTLY) {//按当前模式测出的值来取值
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);//不能大于 按期望宽度与当前模式测出来的宽度取较小值
        } else {
            width = desiredWidth;//包裹 按期望宽度取值
        }

        int height;
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = mBgBitmap.getHeight() + getPaddingTop() + getPaddingBottom();
        } else {
            height = width / mNumStars;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Draw background Bitmap
        for (int x = 0; x < mNumStars; x++) {
            canvas.drawBitmap(mBgBitmaps[x], getPaddingLeft() + mBgBitmaps[x].getWidth() * x + mHorizontalSpace * x, getPaddingTop(), null);
        }
        //Draw foreground Bitmap
        for (int x = 0; x < mRating; x++) {
            canvas.drawBitmap(mPreBitmaps[x], getPaddingLeft() + mPreBitmaps[x].getWidth() * x + mHorizontalSpace * x, getPaddingTop(), null);
        }
    }

    int mRating;
    int moveX;
    int lastMoveX;
    int lastX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                moveX = (int) event.getX();
                for (int x = 0; x <= mNumStars; x++) {
                    if (moveX < (getPaddingLeft() + mBgBitmap.getWidth() * x + mHorizontalSpace * x)) {
                        if (mRating != x) {//excuting when it is different from last rating.
                            if (moveX != lastMoveX || event.getAction() == MotionEvent.ACTION_DOWN) {
                                mRating = x;
                            }
                        } else {//excuting when it equal to last rating.
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                mRating = x - 1;
                            }
                        }
                        if (x != lastX) {//防止在滑动过程中不断刷新,只有在x != lastX的情况下才重绘
                            invalidate();
                            lastX = x;
                        }
                        break;
                    }
                }
                lastMoveX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                if (mOnRatingChangeListener != null) {
                    mOnRatingChangeListener.onRatingChange(mRating);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if(!isEnabled()){
            return true;
        }

        return super.dispatchTouchEvent(event);
    }

    @SuppressWarnings("unused")
    public void setRating(int rating) {
        mRating = rating;
        super.setRating(rating);
        invalidate();
    }

    @SuppressWarnings("unused")
    public int getRating(int rating) {
        return mRating;
    }

    @Override
    public int getNumStars() {
        return mNumStars;
    }

    @Override
    public void setNumStars(int numStars) {
        mNumStars = numStars;
    }

    public interface OnRatingChangeListener {
        void onRatingChange(int rating);
    }

    OnRatingChangeListener mOnRatingChangeListener;

    @SuppressWarnings("unused")
    public void setOnRatingChangeListener(OnRatingChangeListener listener) {
        mOnRatingChangeListener = listener;
    }

    @SuppressWarnings("unused")
    public OnRatingChangeListener getOnRatingChangeListener() {
        return mOnRatingChangeListener;
    }
}
