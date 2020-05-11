package dc.android.health;

import dc.android.health.activity.HealthActivity;
import dc.android.primary.PrimaryApplication;
import dc.android.primary.PrimaryContext;

/**
 * @author senrsl
 * @ClassName: VsfApplication
 * @Package: dc.android.health
 * @CreateTime: 2020/5/11 2:38 PM
 */
public class VsfApplication extends PrimaryApplication {

    @Override
    protected void initData() {
        super.initData();
        PrimaryContext.isDebug = BuildConfig.DEBUG;
        PrimaryContext.VERSION_NAME = BuildConfig.VERSION_NAME;
        PrimaryContext.PACKAGE_NAME = BuildConfig.APPLICATION_ID;
        PrimaryContext.VERSION_CODE = BuildConfig.VERSION_CODE;
        PrimaryContext.CLS_HOME = HealthActivity.class.getCanonicalName();
    }
}
