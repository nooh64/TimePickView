package com.anwios.android.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;


public class TimePickView extends View {
    // ===========================================================
    // Constants
    // ===========================================================
    private static final int DEFAULT_SIZE =300;

    // ===========================================================
    // Fields
    // ===========================================================

    private Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint markerTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    private double hourAngle = 0;
    private double minuteAngle = 0;

    private boolean isSettingHour = true;
    private boolean isSettingMinute = false;
    private boolean autoSetMinuteAfterHour = false;


    private onTimeSetListener listener;

    private boolean showMarkers = true;
    private float markerSize;
    private int markerColor;
    private float markerWidth;

    private boolean showMarkerText = true;
    private float markerTextSize;
    private int markerTextColor;

    private boolean showCenterPoint;
    private float centerPointSize;
    private int centerPointColor;


    private boolean moveHourHandOnMinute = true;
    private float minuteHandWidth;
    private float hourHandWidth;
    private int hourHandColor;
    private int minuteHandColor;

    private float textSize;
    private int textColor;
    private int textPaddingTop;

    private String separator = ":";

    private boolean canSetTime = true;
    private int backgroundColor;

    // ===========================================================
    // Constructors
    // ===========================================================
    public TimePickView(Context context) {
        super(context);
        init(context, null);
    }

    public TimePickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public TimePickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================
    public void setTime(int hour, int minute) {
        setHourAngle(hour);
        setMinuteAngle(minute);
        invalidate();
    }

    public void setTime() {
        autoSetMinuteAfterHour = true;
        isSettingMinute = false;
        isSettingHour = true;
    }

    public int getHour() {
        return angleToHour();
    }

    public void setHour(int hour) {
        setHourAngle(hour);
        invalidate();
    }

    public void setHour() {
        //set hour only
        autoSetMinuteAfterHour = false;

        isSettingMinute = false;
        isSettingHour = true;
    }

    public int getMinute() {
        return angleToMinute();
    }

    public void setMinute(int minute) {
        setMinuteAngle(minute);
        invalidate();
    }

    public void setMinute() {
        isSettingHour = false;
        isSettingMinute = true;
    }

    public boolean isMoveHourhandOnMinute() {
        return moveHourHandOnMinute;
    }

    public void setMoveHourhandOnMinute(boolean moveHourhandOnMinute) {
        this.moveHourHandOnMinute = moveHourhandOnMinute;
    }

    public float getHourMarkerSize() {
        return markerSize;
    }

    public void setHourMarkerSize(float hourMarkerSize) {
        this.markerSize = hourMarkerSize;
        invalidate();
    }

    public boolean isSettingHour() {
        return isSettingHour;
    }


    public boolean isSettingMinute() {
        return isSettingMinute;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
        invalidate();
    }

    public int getTextPaddingTop() {
        return textPaddingTop;
    }

    public void setTextPaddingTop(int textPaddingTop) {
        this.textPaddingTop = textPaddingTop;
        invalidate();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        invalidate();
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        invalidate();
    }

    public int getMinuteHandColor() {
        return minuteHandColor;
    }

    public void setMinuteHandColor(int minuteHandColor) {
        this.minuteHandColor = minuteHandColor;
        invalidate();
    }

    public int getHourHandColor() {
        return hourHandColor;
    }

    public void setHourHandColor(int hourHandColor) {
        this.hourHandColor = hourHandColor;
        invalidate();
    }

    public float getHourHandWidth() {
        return hourHandWidth;
    }

    public void setHourHandWidth(float hourHandWidth) {
        this.hourHandWidth = hourHandWidth;
        invalidate();
    }

    public float getMinuteHandWidth() {
        return minuteHandWidth;
    }

    public void setMinuteHandWidth(float minuteHandWidth) {
        this.minuteHandWidth = minuteHandWidth;
        invalidate();
    }

    public int getCenterPointColor() {
        return centerPointColor;
    }

    public void setCenterPointColor(int centerPointColor) {
        this.centerPointColor = centerPointColor;
        invalidate();
    }

    public float getCenterPointSize() {
        return centerPointSize;
    }

    public void setCenterPointSize(float centerPointSize) {
        this.centerPointSize = centerPointSize;
        invalidate();
    }

    public void setShowCenterPoint(boolean showCenterPoint) {
        this.showCenterPoint = showCenterPoint;
        invalidate();
    }

    public int getMarkerTextColor() {
        return markerTextColor;
    }

    public void setMarkerTextColor(int markerTextColor) {
        this.markerTextColor = markerTextColor;
        invalidate();
    }

    public float getMarkerTextSize() {
        return markerTextSize;
    }

    public void setMarkerTextSize(float markerTextSize) {
        this.markerTextSize = markerTextSize;
        invalidate();
    }

    public boolean isShowMarkerText() {
        return showMarkerText;
    }

    public void setShowMarkerText(boolean showHourText) {
        this.showMarkerText = showHourText;
        invalidate();
    }

    public float getMarkerWidth() {
        return markerWidth;
    }

    public void setMarkerWidth(float markerWidth) {
        this.markerWidth = markerWidth;
        invalidate();
    }

    public boolean isShowMarkers() {
        return showMarkers;
    }

    public void setShowMarkers(boolean showHourMarker) {
        this.showMarkers = showHourMarker;
        invalidate();
    }

    public boolean isAutoSetMinuteAfterHour() {
        return autoSetMinuteAfterHour;
    }

    public void setAutoSetMinuteAfterHour(boolean autoSetMinuteAfterHour) {
        this.autoSetMinuteAfterHour = autoSetMinuteAfterHour;
    }

    public boolean CanSetTime() {
        return canSetTime;
    }

    public void setCanSetTime(boolean canSetTime) {
        this.canSetTime = canSetTime;
    }

    public void setOnTimeSetListener(onTimeSetListener listener) {
        this.listener = listener;
    }

    public int getHourMarkerColor() {
        return markerColor;
    }

    public void setHourMarkerColor(int hourMarkerColor) {
        this.markerColor = hourMarkerColor;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {

        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        final int chosenWidth = getDimension(widthMode, widthSize);
        final int chosenHeight = getDimension(heightMode, heightSize);
        setMeasuredDimension(chosenWidth, chosenHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float drawableWidth = getWidth();
        float drawableHeight = getHeight();

        float radius = (Math.min(drawableWidth, drawableHeight)) / 2;


        float halfWidth = drawableWidth / 2;
        float halfHight = drawableHeight / 2;
        float padding = dpToPx(4);

        //background
        p.setColor(backgroundColor);
        canvas.drawCircle(halfWidth, halfHight, radius, p);
        radius -= padding;
        p.setStrokeWidth(markerWidth);
        p.setColor(Color.parseColor("#3F51B5"));

        //draw markers and Text
        if (showMarkers || showMarkerText) {
            markerTextPaint.setColor(markerTextColor);
            markerTextPaint.setTextSize(markerTextSize);


            int hour = 1;
            for (int i = 0; i < 360; i = i + 30) {
                canvas.rotate(30, halfWidth, halfHight);
                if (showMarkers) {
                    p.setColor(markerColor);
                    canvas.drawLine(halfWidth, halfHight - radius + markerSize, halfWidth, halfHight - radius, p);
                }
                if (showMarkerText) {
                    String text = String.valueOf(hour++);
                    Rect bounds = new Rect();
                    markerTextPaint.getTextBounds(text, 0, text.length(), bounds);
                    float textHeight = bounds.height();
                    int width = bounds.width();
                    canvas.drawText(text, halfWidth - width / 2, halfHight - radius + markerSize + textHeight + padding, markerTextPaint);
                }

            }
        }


        //draw text
        p.setColor(textColor);
        StringBuilder text = new StringBuilder();
        text.append(angleToHour());
        text.append(separator);
        text.append(angleToMinute());
        Rect bounds = new Rect();
        p.setTextSize(textSize);
        p.getTextBounds(text.toString(), 0, text.length(), bounds);
        int textWidth = bounds.width();
        canvas.drawText(text.toString(), halfWidth - textWidth / 2, halfHight + textPaddingTop + textSize, p);

        //draw Hands
        p.setColor(minuteHandColor);
        canvas.rotate((float) minuteAngle, halfWidth, halfHight);
        p.setStrokeWidth(minuteHandWidth);
        canvas.drawLine(halfWidth, halfHight - dpToPx(6) - centerPointSize, halfWidth, halfHight - radius, p);
        canvas.rotate(-(float) minuteAngle, halfWidth, halfHight);


        p.setColor(hourHandColor);
        canvas.rotate((float) hourAngle, halfWidth, halfHight);
        p.setStrokeWidth(hourHandWidth);
        canvas.drawLine(halfWidth, halfHight - dpToPx(6) - centerPointSize, halfWidth, halfHight - ((2 * radius / 3)), p);
        canvas.rotate(-(float) hourAngle, halfWidth, halfHight);


        //draw center point
        if (showCenterPoint) {
            p.setColor(centerPointColor);
            canvas.drawCircle(halfWidth, halfHight, centerPointSize, p);
        }

        super.onDraw(canvas);
    }

    @Override
    public Parcelable onSaveInstanceState() {

        Parcelable superState = super.onSaveInstanceState();

        SavedState state = new SavedState(superState);
        state.hourAngle = this.hourAngle;
        state.minuteAngle = this.minuteAngle;
        return state;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {

        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        this.hourAngle = ss.hourAngle;
        this.minuteAngle = ss.minuteAngle;
    }

    // ===========================================================
    // Methods
    // ===========================================================
    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimePickView);
        showMarkers = typedArray.getBoolean(R.styleable.TimePickView_tpv_showMarkers, true);
        markerSize = typedArray.getDimensionPixelSize(R.styleable.TimePickView_tpv_markerSize, getResources().getDimensionPixelSize(R.dimen.tpv_marker_size));
        markerColor = typedArray.getColor(R.styleable.TimePickView_tpv_markerColor, Color.parseColor("#3F51B5"));
        markerWidth = typedArray.getDimensionPixelSize(R.styleable.TimePickView_tpv_markerWidth, getResources().getDimensionPixelSize(R.dimen.tpv_marker_width));

        showMarkerText = typedArray.getBoolean(R.styleable.TimePickView_tpv_showMarkerText, true);
        markerTextSize = typedArray.getDimensionPixelSize(R.styleable.TimePickView_tpv_markerTextSize, getResources().getDimensionPixelSize(R.dimen.tpv_marker_text_size));

        markerTextColor = typedArray.getColor(R.styleable.TimePickView_tpv_markerTextColor, Color.parseColor("#3F51B5"));

        showCenterPoint = typedArray.getBoolean(R.styleable.TimePickView_tpv_showCenterPoint, true);
        centerPointSize = typedArray.getDimensionPixelSize(R.styleable.TimePickView_tpv_centerPointSize, getResources().getDimensionPixelSize(R.dimen.tpv_center_point_size));
        centerPointColor = typedArray.getColor(R.styleable.TimePickView_tpv_centerPointColor, Color.parseColor("#3F51B5"));

        minuteHandWidth = typedArray.getDimensionPixelSize(R.styleable.TimePickView_tpv_minuteHandWidth, getResources().getDimensionPixelSize(R.dimen.tpv_minute_hand_width));
        hourHandWidth = typedArray.getDimensionPixelSize(R.styleable.TimePickView_tpv_hourHandWidth, getResources().getDimensionPixelSize(R.dimen.tpv_hour_hand_width));

        hourHandColor = typedArray.getColor(R.styleable.TimePickView_tpv_hourHandColor, Color.parseColor("#B71C1C"));
        minuteHandColor = typedArray.getColor(R.styleable.TimePickView_tpv_minuteHandColor, Color.parseColor("#B71C1C"));

        textSize = typedArray.getDimensionPixelSize(R.styleable.TimePickView_tpv_textSize, getResources().getDimensionPixelSize(R.dimen.tpv_text_size));
        textColor = typedArray.getColor(R.styleable.TimePickView_tpv_textColor, Color.parseColor("#3F51B5"));
        textPaddingTop = typedArray.getDimensionPixelSize(R.styleable.TimePickView_tpv_textPaddingTop, getResources().getDimensionPixelSize(R.dimen.tpv_text_padding));

        backgroundColor = typedArray.getColor(R.styleable.TimePickView_tpv_backgroundColor, Color.parseColor("#FFFFFF"));

        canSetTime = typedArray.getBoolean(R.styleable.TimePickView_tpv_canSetTime, true);
    }

    private int getDimension(final int mode, final int size) {
        switch (mode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.UNSPECIFIED:
            default:
                return DEFAULT_SIZE;
        }
    }


    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(canSetTime) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    //call listener (beforeTimeChanged)
                    if (!(autoSetMinuteAfterHour && isSettingMinute)) {

                        beforeSetTime();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    float x = event.getX();
                    float y = event.getY();
                    float centerX = getWidth() / 2;
                    float centerY = getHeight() / 2;
                    //find angle
                    double angle1 = Math.atan2((y - centerY),
                            (x - centerX));

                    double angle2 = Math.atan2((0 - centerY),
                            0);
                    double angle = Math.toDegrees(angle1 - angle2);
                    angle = (angle < 0) ? 360 + angle : angle;
                    //set angle
                    if (isSettingHour) {
                        hourAngle = angle;
                    } else if (isSettingMinute) {
                        minuteAngle = angle;
                        if (moveHourHandOnMinute) {
                            hourAngle = ((int) (hourAngle / 30)) * 30 + (angleToMinute() / 60f) * 30f;
                        }
                    }
                    //call listener
                    onSetTime();
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    if (autoSetMinuteAfterHour) {
                        if (isSettingHour) {
                            isSettingHour = false;
                            isSettingMinute = true;
                        } else if (isSettingMinute) {
                            isSettingMinute = false;
                            //call listener
                            afterSetTime();
                        }
                    } else {
                        //call listener
                        afterSetTime();
                    }
                    break;
            }
            invalidate();
        }
        return true;
    }


    private int angleToHour() {
        int hour = (int) (hourAngle / 30);
        hour = (hour == 0) ? 12 : hour;
        return hour;
    }

    private int angleToMinute() {
        int minute = (int) (minuteAngle / 6);
        return (minute);
    }

    private void setHourAngle(int hour) {
        hour = hour == 12 ? 0 : hour;
        this.hourAngle = hour * 30;
    }

    private void setMinuteAngle(int minute) {
        this.minuteAngle = minute * 6;
        if (moveHourHandOnMinute) {
            hourAngle = ((int) (hourAngle / 30)) * 30 + (minute / 60f) * 30f;
        }
    }

    public interface onTimeSetListener {
        public void beforeTimeChanged(int hour, int minute);

        public void onTimeChanged(int hour, int minute);

        public void afterTimeChanged(int hour, int minute);
    }

    public void beforeSetTime() {
        if (listener != null) {
            listener.beforeTimeChanged(angleToHour(), angleToMinute());
        }
    }

    public void onSetTime() {
        if (listener != null) {
            listener.onTimeChanged(angleToHour(), angleToMinute());
        }
    }

    private void afterSetTime() {
        if (listener != null) {
            listener.afterTimeChanged(angleToHour(), angleToMinute());
        }
    }

    public static class SavedState extends BaseSavedState {
        private double hourAngle;
        private double minuteAngle;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.hourAngle = in.readDouble();
            this.minuteAngle = in.readDouble();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeDouble(this.hourAngle);
            out.writeDouble(this.minuteAngle);
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

}
