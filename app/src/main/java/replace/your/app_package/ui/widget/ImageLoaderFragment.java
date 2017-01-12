package replace.your.app_package.ui.widget;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import replace.your.app_package.data.res.AppImageLoader;
import replace.your.app_package.ui.navigation.base.AppFragment;

/**
 * ImageLoaderを保持・管理するFragment
 */
public class ImageLoaderFragment extends AppFragment implements AppImageLoader.Holder {
    AppImageLoader mAppImageLoader;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mAppImageLoader == null) {
            mAppImageLoader = new AppImageLoader(context);
        }
    }

    @Override
    public AppImageLoader getImageLoader() {
        return mAppImageLoader;
    }

    public static FragmentTransaction attach(FragmentTransaction transaction) {
        transaction.add(new ImageLoaderFragment(), ImageLoaderFragment.class.getName());
        return transaction;
    }

    public static void attach(AppCompatActivity activity) {
        attach(activity.getSupportFragmentManager().beginTransaction()).commit();
    }
}
