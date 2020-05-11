package dc.android.covid19;

import dc.android.common.utils.AutoMarginUtils;
import dc.android.covid19.activity.Covid19Activity;
import dc.android.primary.PrimaryApplication;

/**
 * @author senrsl
 * @ClassName: VsfApplication
 * @Package: dc.android.covid19
 * @CreateTime: 2020/5/11 3:53 PM
 */
public class VsfApplication extends PrimaryApplication {

    @Override
    protected void initData() {
        super.initData();

        Covid19Context.isDebug = BuildConfig.DEBUG;
        Covid19Context.VERSION_NAME = BuildConfig.VERSION_NAME;
        Covid19Context.PACKAGE_NAME = BuildConfig.APPLICATION_ID;
        Covid19Context.VERSION_CODE = BuildConfig.VERSION_CODE;
        Covid19Context.CLS_HOME = Covid19Activity.class.getCanonicalName();

        Covid19Context.DESIGN_WIDTH = 1080;
        Covid19Context.DESIGN_HEIGHT = 1920;
        AutoMarginUtils.setSize(this, true, Covid19Context.DESIGN_WIDTH, Covid19Context.DESIGN_HEIGHT);

    }
}
