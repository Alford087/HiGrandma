package mars.core.net;

import mars.core.utils.Logger;
import rx.functions.Func2;

/**
 * Created by Mars on 2017/2/15.
 */

public class Retry implements Func2<Integer, Throwable, Boolean> {

    private int retryTime = 3;

    public Retry() {
    }

    public Retry(int retryTime) {
        this.retryTime = retryTime;
    }

    @Override
    public Boolean call(Integer integer, Throwable throwable) {
        if (integer < retryTime) {
            Logger.loge(getClass().getSimpleName(), "网络重试第：" + integer);
            return true;
        } else {
            return false;
        }
    }
}
