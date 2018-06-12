package hjs.zhi.com.drawview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Calendar;

public class ClockActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart, btnStop;
    private ImageView mClockImageView;
    Bitmap.Config config = Bitmap.Config.ARGB_8888;
    int width = 500;
    int height = 500;

    private Calendar mCalendar;
    private int mHour, mMinute, mSecond;
    private float mDegrees;
    private float length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        mClockImageView = findViewById(R.id.iv_clock);
        mClockImageView.setImageBitmap(drawClock());

    }

    /**
     * 画表盘
     */
    private Bitmap drawClockFace() {
        Bitmap bm = Bitmap.createBitmap(width, height, config);

        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint();
        paint.setAntiAlias(true);  //锯齿
        paint.setStyle(Paint.Style.STROKE); // 空心
        paint.setStrokeWidth(5);
        paint.setColor(Color.parseColor("#333333"));

        // 外层圆
        canvas.drawCircle(width / 2, height / 2, width / 2, paint);

        // 內层圆  --》圆心
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, 10, paint);

        // 循环画刻度(旋转画刻度)
        for (int i = 0; i < 12; i++) {
            if (i % 3 == 0) {
                paint.setStrokeWidth(10);
                canvas.drawLine(width / 2, 0, width / 2, 24, paint);
                canvas.rotate(30, width / 2, height / 2);
            } else {
                canvas.drawLine(width / 2, 0, width / 2, 10, paint);
                canvas.rotate(30, width / 2, height / 2);
            }
        }


        return bm;
    }

    private Bitmap drawClock() {
        Bitmap bm = drawClockFace();

        Canvas canvas = new Canvas(bm);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#333333"));

        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR);
        mMinute = mCalendar.get(Calendar.MINUTE);
        mSecond = mCalendar.get(Calendar.SECOND);

        //画小时指针
        paint.setStrokeWidth(10);
        mDegrees = mHour * 30 + mMinute / 2;
        length = (width / 2) * 0.7f;
        canvas.save();
        canvas.rotate(mDegrees, width / 2, height / 2);
        canvas.drawLine(width / 2, height / 2, width / 2, height - (height / 2) - length, paint);
        canvas.restore();
//        canvas.rotate(360 - mDegrees, width / 2, height / 2);

        //画分钟指针
        paint.setStrokeWidth(4);
        mDegrees = mMinute * 6 + mSecond / 10;
        length = (width / 2) * 0.78f;
        canvas.save();
        canvas.rotate(mDegrees, width / 2, height / 2);
        canvas.drawLine(width / 2, height / 2, width / 2, height - (height / 2) - length, paint);
        canvas.restore();
//        canvas.rotate(360 - mDegrees, width / 2, height / 2);

        //画分钟指针
        paint.setStrokeWidth(2);
        mDegrees = mHour * 6;
        length = (width / 2) * 0.92f;
        canvas.save();
        canvas.rotate(mDegrees, width / 2, height / 2);
        canvas.drawLine(width / 2, height / 2, width / 2, height - (height / 2) - length, paint);
        canvas.restore();

        return bm;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                break;
            case R.id.btn_stop:
                break;
        }
    }
}
