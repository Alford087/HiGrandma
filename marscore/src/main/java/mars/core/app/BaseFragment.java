package mars.core.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mars.core.utils.Logger;

/**
 * Created by tc on 2016/1/26.
 */
public abstract class BaseFragment extends Fragment {

    private Activity act;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.act = activity;
    }

    public String getFragmentName() {
        return "";
    }

    /**
     * fragment 名称
     *
     * @return
     */
    public int getFragmentNameRes() {
        return 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.logd(getClass().getSimpleName(), "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.logd(getClass().getSimpleName(), "onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.logd(getClass().getSimpleName(), "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.logd(getClass().getSimpleName(), "onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.logd(getClass().getSimpleName(), "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.logd(getClass().getSimpleName(), "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.logd(getClass().getSimpleName(), "onDestroyView");
    }


}
