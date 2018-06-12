package hjs.zhi.com.drawview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DrawPointAndLineActivity extends AppCompatActivity {

    private ImageView mPointImageView, mLineImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_point_and_line);

        mPointImageView = findViewById(R.id.iv_point);
        mLineImageView = findViewById(R.id.iv_line);

        doDrawPoint();
        doDrawLine();
    }

    private void doDrawLine() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, mConfig);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.parseColor("#666666"));

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);

        float[] pts = new float[]{
                10, 10, mWidth - 10, 10,
                mWidth - 10, 10, mWidth - 10, mHeight - 10,
                mWidth - 10, mHeight - 10, 10, mHeight - 10,
                10, 10, 10, mHeight - 10,
        };
        canvas.drawLines(pts, 0, pts.length, paint);

        mLineImageView.setImageBitmap(bitmap);
    }

    Bitmap.Config mConfig = Bitmap.Config.ARGB_8888;
    int mWidth = 300;
    int mHeight = 300;

    private void doDrawPoint() {

        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, mConfig);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.parseColor("#666666"));

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);

        float[] pts = new float[]{
                20, 20,
                40, 40,
                60, 60,
                80, 80,
                100, 100,
        };
        canvas.drawPoints(pts, paint);

        mPointImageView.setImageBitmap(bitmap);
    }
}
