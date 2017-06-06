package com.siui.sysapp.applock.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.siui.sysapp.applock.R;

/**
 * Created by yunlong.zhang on 2017/6/6.
 */
public class SideBarView extends View {

    private static String [] letter = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};

    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    private int choosed = -1;

    private Paint mPaint = new Paint();
    private TextView mTextDialog;

    /**
     * set TextView
     * @param textDialog
     */
    public void setmTextDialog (TextView textDialog) {
        this.mTextDialog = textDialog;
    }

    public SideBarView(Context context) {
        super(context);
    }

    public SideBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SideBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int singleLength = height / letter.length;
        for (int i = 0; i < letter.length; i++) {
            mPaint.setColor(Color.rgb(33, 60, 80));
            mPaint.setTypeface(Typeface.DEFAULT_BOLD);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(20);
            if (i == choosed) {
                mPaint.setColor(Color.parseColor("#3399ff"));
                mPaint.setFakeBoldText(true);
            }
            float xpos = width/2 - mPaint.measureText(letter[i])/2;
            float ypos = singleLength * i + singleLength;
            canvas.drawText(letter[i], xpos, ypos, mPaint);
            mPaint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        float ypos = event.getY();
        final int oldchoose = choosed;
        OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int cur = (int)(ypos/getHeight() * letter.length);
        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundColor(0x00000000);
                choosed = -1;
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(INVISIBLE);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.sidebar_background);
                if (oldchoose != cur) {
                    if (cur>=0 && cur<letter.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(letter[cur]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(letter[cur]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choosed = cur;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }
    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }
}
