package mars.core.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mars.core.utils.Logger;

/**
 * 所有Activity基类
 * Created by TC on 2016/1/26.
 */
public abstract class BaseFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.logd(this.getClass().getSimpleName(), "onCreate");
        initDataUponLoadXML(savedInstanceState);
        super.onCreate(savedInstanceState);
        if (getLayoutResID() > 0) {
            setContentView(getLayoutResID());
            initComponent(savedInstanceState);
        } else {
            Logger.loge(getClass().getSimpleName(), "缺少布局文件");
        }
    }

    /**
     * @return
     * @notice:必须返回true，界面方能继续加载.
     */
    public boolean initDataUponLoadXML(Bundle arg0) {
        return true;
    }

    /**
     * 提供layout id，必须返回，否则页面无.
     *
     * @return
     */
    public abstract int getLayoutResID();

    /**
     * 初始化控件.
     */
    public abstract void initComponent(Bundle savedInstanceState);

    /**
     * 页面名字.
     *
     * @return
     */
    public abstract String getThisActivityName();

    /**
     * 是否有内部fragment.
     *
     * @return
     */
    public abstract boolean hasInnerFragment();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
