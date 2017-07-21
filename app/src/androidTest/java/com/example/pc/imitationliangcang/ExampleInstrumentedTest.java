package com.example.pc.imitationliangcang;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Instrumentation toolbar_menu_bili, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under toolbar_menu_bili.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.pc.imitationliangcang", appContext.getPackageName());
    }
}
