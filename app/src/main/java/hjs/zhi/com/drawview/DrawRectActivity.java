package hjs.zhi.com.drawview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DrawRectActivity extends AppCompatActivity {
    private ImageView mRoundRectImageView, mDrawPathImageView;
    Bitmap.Config config = Bitmap.Config.ARGB_8888;
    int width = 300;
    int height = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_rect);

        mRoundRectImageView = findViewById(R.id.iv_round_rect);
        mDrawPathImageView = findViewById(R.id.iv_path);

        doDrawRoundRect();
        doDrawPathRect();
    }

    private void doDrawPathRect() {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        int width = 300;
        int height = 300;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.parseColor("#666666"));

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);

        Path path = new Path();
        path.moveTo(73 * 2, 0);
        path.lineTo(131 * 2, 30 * 2);
        path.lineTo(146 * 2, 84 * 2);
        path.lineTo(106 * 2, 146 * 2);
        path.lineTo(40 * 2, 146 * 2);
        path.lineTo(0 * 2, 84 * 2);
        path.lineTo(14 * 2, 29 * 2);
        path.lineTo(73 * 2, 0 * 2);

        canvas.drawPath(path, paint);

        mRoundRectImageView.setImageBitmap(bitmap);
    }

    private void doDrawRoundRect() {

        Bitmap bitmap = Bitmap.createBitmap(width, height, config);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.parseColor("#666666"));

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE); // 空心
        paint.setStrokeWidth(2);

        RectF rect = new RectF();
        rect.set(10, 10, 300 - 10, 100 - 10);
        float rx = 0;
        float ry = 0;
        canvas.drawRoundRect(rect, rx, ry, paint);


//        canvas.drawRect();
//        canvas.drawOval();
//        canvas.drawCircle();

        mDrawPathImageView.setImageBitmap(bitmap);

    }
}
