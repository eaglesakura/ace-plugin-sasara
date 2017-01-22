package org.andriders.talk.sasara.ui.navigation;

import org.junit.Test;

import org.andriders.talk.sasara.AppScenarioTest;

import static com.eaglesakura.android.devicetest.scenario.ScenarioContext.assertTopActivity;

public class ExampleActivityTest extends AppScenarioTest<ExampleActivity> {

    public ExampleActivityTest() {
        super(ExampleActivity.class);
    }

    @Test
    public void Activityが起動できる() throws Throwable {
        assertTopActivity(ExampleActivity.class);
    }
}