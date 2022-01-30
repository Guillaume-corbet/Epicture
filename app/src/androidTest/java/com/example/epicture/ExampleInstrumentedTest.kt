package com.example.epicture

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
//import org.junit.contrib.java.lang.system.SystemOutRule
import org.junit.runner.RunWith
import java.io.ByteArrayOutputStream
import java.io.PrintStream


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val access_token: String = "0979aca437f330fa314a31d2be42f1f9d780eb4a"
    /*private val standardOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    @Before
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @After
    fun tearDown() {
        System.setOut(standardOut)
    }

    @Rule
    val systemOutRule = SystemOutRule().enableLog()*/

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.epicture", appContext.packageName)
    }

    /*@Test
    fun favoriteImage() {
        val id = "U1rTMrR"

        val favorite = FavorisFragment()
        favorite.access_token = access_token
        favorite.favoriteImage(id)
        assertEquals("success: favorite image", systemOutRule.getLog()
            .trim());
    }*/


}