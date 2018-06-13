package hjs.zhi.com.drawview.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class PetalButton extends AppCompatButton {
    private Paint mPaint;
    private float radius;

    public PetalButton(Context context) {
        super(context);
    }

    public PetalButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PetalButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.e("w", widthMeasureSpec + "      " + dp2px(widthMeasureSpec) + "    " + sp2px(widthMeasureSpec));
        Log.e("h", heightMeasureSpec + "");
    }

    Paint textPaint;
    private float fCenterX, fCenterY;
    private float sCenterX, sCenterY;
    private float tCenterX, tCenterY;
    private float cCenterX, cCenterY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);

        mPaint = new Paint();
        mPaint.setStrokeWidth(6);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);

        textPaint = new Paint();
        radius = 90;
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);
        textPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)); //  设置字体  类型


        fCenterX = getMeasuredWidth() / 4 + 60;
        fCenterY = getMeasuredWidth() / 4 + 60;

        canvas.drawCircle(fCenterX, fCenterY, radius, mPaint);
        canvas.drawText("中", getMeasuredWidth() / 4 + 60, getMeasuredWidth() / 4 + 60, textPaint);

        sCenterX = getMeasuredWidth() / 4 * 3 + 40;
        sCenterY = getMeasuredWidth() / 4 - 40;
        canvas.drawCircle(sCenterX, sCenterY, radius, mPaint);
        canvas.drawText("高", getMeasuredWidth() / 4 * 3 + 40, getMeasuredWidth() / 4 - 40, textPaint);

        cCenterX = getMeasuredWidth() / 8;
        cCenterY = getMeasuredWidth() / 8;
        canvas.drawCircle(getMeasuredWidth() / 4 * 3 + 40, getMeasuredWidth() / 4 * 3 + 40, radius, mPaint);

        tCenterX = getMeasuredWidth() / 4 - 40;
        tCenterY = getMeasuredWidth() / 4 * 3 + 40;
        canvas.drawCircle(tCenterX, tCenterY, radius, mPaint);
        canvas.drawText("低", getMeasuredWidth() / 4 - 40, getMeasuredWidth() / 4 * 3 + 40, textPaint);

    }

    private Context context;

    private void whichCircle(float x, float y) {
//        // 将屏幕中的点转换成以屏幕中心为原点的坐标点
        float result = radius;

        Point fPoint = new Point(fCenterX, fCenterY);


        StringBuilder tip = new StringBuilder();
        tip.append("您点击了");
//

        //第一个圆 从左上 顺时针
        Point sPoint = new Point(sCenterX, sCenterY);
        Point tPoint = new Point(tCenterX, tCenterY);
        Point cPoint = new Point(cCenterX, cCenterY);
        if (result <= Math.abs(sPoint.getDistance(new Point(x, y))) && x <= getMeasuredWidth() / 2 && y <= getMeasuredHeight() / 2

                && getMeasuredHeight() / 4 < y && getMeasuredWidth() / 4 < x) {
            Log.e("whichCircle", " 中   " + "x" + sCenterX + "" + sCenterY);
        } else if (result <= Math.abs(tPoint.getDistance(new Point(x, y))) && getMeasuredWidth() / 4 * 3 <= x && getMeasuredHeight() / 4 >= y) {
            Log.e("whichCircle", "  高   " + "x" + tCenterX + "" + tCenterY);
        } else if (result <= Math.abs(cPoint.getDistance(new Point(x, y))) && getMeasuredWidth() / 4 * 3 <= x && getMeasuredHeight() / 4 * 3 <= y) {
            Log.e("whichCircle", "  右   " + "x" + cCenterX + "" + cCenterY);
        } else if (result <= Math.abs(fPoint.getDistance(new Point(x, y))) && getMeasuredWidth() / 4 >= x && getMeasuredHeight() / 4 * 3 <= y) {
            Log.e("whichCircle", "  低   " + "x" + fCenterX + "" + fCenterY);
        } else {
            Log.e("whichCircle", "空白");
        }

//        Toast.makeText(context, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.e("onTouchEvent", "  x " + x + "     " + y);
        whichCircle(x, y);
        return super.onTouchEvent(event);
    }

    /**
     * 内部类   对点进行  封装
     */
    class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getDistance(Point p) {
            double _x = Math.abs(this.x - p.x);
            double _y = Math.abs(this.y - p.y);
            return Math.sqrt(_x * _x + _y * _y);
        }
    }

    private int whichZone(float x, float y) {
        // 简单的象限点处理
        // 第一象限在右下角，第二象限在左下角，代数里面的是逆时针，这里是顺时针
        if (x > 0 && y > 0) {
            return 0;
        } else if (x > 0 && y < 0) {
            return 3;
        } else if (x < 0 && y < 0) {
            return 2;
        } else if (x < 0 && y > 0) {
            return 1;
        }

        return -1;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }
}
