package hjs.zhi.com.drawview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image);
        imageView.setImageBitmap(getBitmap());
    }

    private Bitmap getBitmap() {
        // 1 创建bitmap
        int width = 300;
        int height = 300;
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bm = Bitmap.createBitmap(width, height, config);

        // 2.创建canvas
        Canvas canvas = new Canvas(bm);

        // 3.创建用于绘制矩形的Paint   可设置颜色
        Paint refPaint = new Paint();
//        refPaint.setColor(Color.YELLOW);

        //  3.1   配置  白 --》黄  -- 》 绿 的渐变
        int[] colors = new int[]{Color.WHITE, Color.YELLOW, Color.GREEN};
        float[] positions = new float[]{0f, 0.5f, 1f};
        Shader.TileMode tile = Shader.TileMode.CLAMP;
        LinearGradient shader = new LinearGradient(0, 0, width, height, colors, positions, tile);
        //3.1.1  为shader  配置一个居正
        Matrix matrix = new Matrix();
        matrix.setRotate(45, width / 2, height / 2);
        refPaint.setShader(shader);

        //4.绘制矩形
        canvas.drawRect(0, 0, width, height, refPaint);

        //5.绘制字体   并  设置字体的一些  属性
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(50);
        textPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)); //  设置字体  类型

        // 6.写字
        canvas.drawText("HELLO", width / 2, height / 2, textPaint);

        return bm;
    }
}
