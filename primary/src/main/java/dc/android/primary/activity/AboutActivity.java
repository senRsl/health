package dc.android.primary.activity;

import android.os.Bundle;
import android.widget.TextView;
import dc.android.common.BridgeOpcode;
import dc.android.common.activity.BaseAboutActivity;
import dc.android.common.utils.TaskUtils;
import dc.android.primary.PrimaryContext;
import dc.android.primary.R;

/**
 * @author senrsl
 * @ClassName: AboutActivity
 * @Package: dc.android.weather.activity
 * @CreateTime: 2019/9/17 11:15 AM
 */
public class AboutActivity extends BaseAboutActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_about);
        ((TextView) findViewById(R.id.version)).setText(getString(R.string.app_name) + "  v" + PrimaryContext.VERSION_NAME);

    }

    //sR2dA220
    @Override
    protected void jump() {
        //startActivity(new Intent(AboutActivity.this, HealthActivity.class));
        new TaskUtils().startHome(this, BridgeOpcode.DEFAULT);
        finish();
    }
}
