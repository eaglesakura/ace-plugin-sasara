package org.andriders.talk.sasara.ui.navigation;

import com.eaglesakura.android.framework.delegate.activity.ContentHolderActivityDelegate;
import com.eaglesakura.material.widget.support.SupportProgressFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import org.andriders.talk.sasara.R;
import org.andriders.talk.sasara.ui.navigation.base.AppNavigationActivity;
import org.andriders.talk.sasara.ui.navigation.example.ExampleFragmentMain;

public class ExampleActivity extends AppNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Progress Dialogを追加する
        if (savedInstanceState == null) {
            SupportProgressFragment.attach(this, R.id.Root);
        }
    }

    @NonNull
    @Override
    public Fragment newDefaultContentFragment(@NonNull ContentHolderActivityDelegate self) {
        return new ExampleFragmentMain();
    }
}
