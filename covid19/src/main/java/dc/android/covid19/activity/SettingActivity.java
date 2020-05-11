package dc.android.covid19.activity;

import android.content.Context;
import android.content.Intent;
import dc.android.base.activity.BridgeActivity;

/**
 * @author senrsl
 * @ClassName: SettingActivity
 * @Package: dc.android.covid19.activity
 * @CreateTime: 2020/5/11 10:14 PM
 */
public class SettingActivity extends BridgeActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingActivity.class);
        context.startActivity(starter);
    }


}
