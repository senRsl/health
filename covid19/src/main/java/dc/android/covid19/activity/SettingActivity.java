package dc.android.covid19.activity;

import java.io.File;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import dc.android.base.activity.BridgeActivity;
import dc.android.common.utils.SharePreferencesUtils;
import dc.android.covid19.R;
import dc.android.libs.PermissionUtils;
import dc.android.libs.permission.AbsPermissionCallback;
import dc.common.Logger;

import static dc.android.covid19.Covid19Context.KEY_ID;
import static dc.android.covid19.Covid19Context.KEY_NAME;
import static dc.android.covid19.Covid19Context.PATH_PIC;

/**
 * @author senrsl
 * @ClassName: SettingActivity
 * @Package: dc.android.covid19.activity
 * @CreateTime: 2020/5/11 10:14 PM
 */
public class SettingActivity extends BridgeActivity {

    private final int REQUEST_CODE_IMAGE = 100;
    private final int REQUEST_CODE_RESIZE = 101;

    ImageView ivPic;
    TextView tvPicPath;
    EditText etName;
    EditText etId;

    SharePreferencesUtils sp;

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initLayout() {
        super.initLayout();
        setLayout(true, R.layout.activity_setting, true, Color.WHITE);

        ivPic = findViewById(R.id.iv_pic);
        tvPicPath = findViewById(R.id.tv_pic_path);
        etName = findViewById(R.id.et_name);
        etId = findViewById(R.id.et_id);
    }

    @Override
    protected void initData() {
        super.initData();
        sp = new SharePreferencesUtils(this);

        tvPicPath.setText(PATH_PIC);
        setPic();

        etName.setText(sp.getSharedPreferencesValue(KEY_NAME, getString(R.string.c19_name_value_default)));
        etId.setText(sp.getSharedPreferencesValue(KEY_ID, getString(R.string.c19_id_value_default)));
    }

    private void setPic() {
        Bitmap bitmap = BitmapFactory.decodeFile(PATH_PIC);
        if (null == bitmap) return;
        ivPic.setImageBitmap(bitmap);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.w(getClass().getSimpleName(), requestCode, resultCode, data);
        if (RESULT_OK != resultCode) return;

        switch (requestCode) {
            case REQUEST_CODE_IMAGE:
                if (null == data) return;
                resizeImage(data.getData());
                break;
            case REQUEST_CODE_RESIZE:
                //showResizeImage(data.getExtras());
                setPic();
                break;
        }
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_save:
                sp.saveSharedPreferencesValue(KEY_NAME, etName.getText().toString());
                sp.saveSharedPreferencesValue(KEY_ID, etId.getText().toString());
                finish();
                break;
            case R.id.btn_pick:
                PermissionUtils.with(this)
                        .permisson(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                        .callback(new AbsPermissionCallback() {
                            @Override
                            protected void onResult(boolean isAllGrant, boolean hasDenied, boolean hasRationale) {
                                if (isAllGrant) selectPhoto();
                            }
                        }).request();
                break;
        }
    }


    private void selectPhoto() {
        // 使用意图直接调用手机相册
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // 打开手机相册,设置请求码
        startActivityForResult(intent, REQUEST_CODE_IMAGE);
    }


    //这里增加裁剪
    public void resizeImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //intent.putExtra("crop", "true");//裁剪
        //x、y方向比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪的大小
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);

        intent.putExtra("scale", true); //保持比例

        intent.putExtra("return-data", false);//数据保存在bitmap返回
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(PATH_PIC)));//裁剪完直接存到这个文件
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        //设置返回码
        startActivityForResult(intent, REQUEST_CODE_RESIZE);
    }

//    private void showResizeImage(Bundle extras) {
//        if (extras == null) return;
//        Bitmap photo = extras.getParcelable("data");
//        Logger.w(this, photo);
//        //裁剪之后设置保存图片的路径
//        //ivPic.setImageBitmap(photo);
//    }
}
