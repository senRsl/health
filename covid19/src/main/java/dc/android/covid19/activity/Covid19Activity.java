package dc.android.covid19.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;
import android.widget.TextView;
import dc.android.base.activity.BridgeActivity;
import dc.android.covid19.R;

/**
 * @author senrsl
 * @ClassName: Covid19Activity
 * @Package: dc.android.covid19
 * @CreateTime: 2020/5/11 3:52 PM
 */
public class Covid19Activity extends BridgeActivity {

    private TextView tvDay;
    private TextView tvTime;
    private ImageView ivHead;
    private TextView tvName;
    private TextView tvId;
    private TextView tvTimeSelect;
    private TextView tvTimeExpired;

    SimpleDateFormat sdf;
    Date date;

    @Override
    protected void initLayout() {
        super.initLayout();
        setLayout(true, R.layout.activity_covid19, false, getResources().getColor(R.color.color_2431B3));

        tvDay = findViewById(R.id.tv_day);
        tvTime = findViewById(R.id.tv_time);
        ivHead = findViewById(R.id.iv_head);
        tvName = findViewById(R.id.tv_name);
        tvId = findViewById(R.id.tv_id);
        tvTimeSelect = findViewById(R.id.tv_select);
        tvTimeExpired = findViewById(R.id.tv_expired);

        tvName.setOnLongClickListener(v -> {
            SettingActivity.start(this);
            return false;
        });
    }

    @Override
    protected void initData() {
        super.initData();


        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        date = new Date();
        tvDay.setText(sdf.format(date));

        sdf.applyPattern("HH:mm:ss");
        setTime();

        setHead();
        setInfo();

    }

    private void setHead() {
        String path = "/mnt/sdcard/SENRSL/health02.png";

        Bitmap bitmap = BitmapFactory.decodeFile(path);
        if (null == bitmap) return;
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        drawable.setCircular(false);
        drawable.setCornerRadius(30f);
        ivHead.setImageDrawable(drawable);
//        ivHead.setImageBitmap(bitmap);
    }

    private void setInfo() {
        tvName.setText("赵**");
        tvId.setText("11**************34");
        tvTimeSelect.setText(new SimpleDateFormat("MM-dd HH:mm").format(new Date(System.currentTimeMillis() - 1000 * 3600)));
        tvTimeExpired.setText(new SimpleDateFormat("MM-dd").format(date) + " 24:00");
    }

    private void setTime() {
        tvTime.setText(sdf.format(new Date()));
        new Handler().postDelayed(() -> setTime(), 1000);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
