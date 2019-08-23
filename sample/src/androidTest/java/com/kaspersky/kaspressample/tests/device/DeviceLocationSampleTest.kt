package com.kaspersky.kaspressample.tests.device

import android.Manifest
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.flakysafety.attempt
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceLocationSampleTest : TestCase() {

    companion object {
        private const val GPS_SWITCH_DELAY = 1000L
        private const val MUNICH_LOCATION_LAT = 48.136414
        private const val MUNICH_LOCATION_LNG = 11.588115
        private const val DELTA = 0.001

        private val EMPTY_LISTENER = object : LocationListener {

            override fun onLocationChanged(location: Location?) { }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) { }

            override fun onProviderEnabled(provider: String?) { }

            override fun onProviderDisabled(provider: String?) { }
        }
    }

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java, false, true)

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private lateinit var manager: LocationManager

    @Test
    fun locationSampleTest() {
        before {
            device.location.enableGps()
            manager = device.targetContext.getSystemService(LocationManager::class.java)
        }.after {
            device.location.enableGps()
        }.run {

            step("Disable GPS") {
                device.location.disableGps()
                Screen.idle(GPS_SWITCH_DELAY)
                assertFalse(manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            }

            step("Enable GPS") {
                device.location.enableGps()
                Screen.idle(GPS_SWITCH_DELAY)
                assertTrue(manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            }

            step("Set fake location") {
                device.location.setLocation(MUNICH_LOCATION_LAT, MUNICH_LOCATION_LNG)

                /** Request single update to apply changes */
                manager.requestSingleUpdate(LocationManager.GPS_PROVIDER, EMPTY_LISTENER,
                    Looper.getMainLooper())

                attempt(timeoutMs = 10_000, intervalMs = 500) {
                    val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    assertNotNull(location)
                }

                val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                assertEquals(MUNICH_LOCATION_LAT, location.latitude, DELTA)
                assertEquals(MUNICH_LOCATION_LAT, location.latitude, DELTA)
            }
        }
    }
}