package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso_sample_core.MainActivity
import io.github.kakaocup.kakao.screen.Screen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class DeviceLocationSampleTest : TestCase() {

    companion object {
        private const val GPS_SWITCH_DELAY = 1000L
        private const val MUNICH_LOCATION_LAT = 48.136414
        private const val MUNICH_LOCATION_LNG = 11.588115
        private const val DELTA = 0.001

        private val EMPTY_LISTENER = object : LocationListener {

            override fun onLocationChanged(location: Location?) {
                // empty
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                // empty
            }

            override fun onProviderEnabled(provider: String?) {
                // empty
            }

            override fun onProviderDisabled(provider: String?) {
                // empty
            }
        }
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java, false, true)

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
                device.location.setLocation(
                    MUNICH_LOCATION_LAT,
                    MUNICH_LOCATION_LNG
                )

                /** Request single update to apply changes */
                manager.requestSingleUpdate(
                    LocationManager.GPS_PROVIDER,
                    EMPTY_LISTENER,
                    Looper.getMainLooper()
                )

                flakySafely(timeoutMs = 10_000, intervalMs = 500) {
                    val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    assertNotNull(location)
                }

                val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                assertEquals(
                    MUNICH_LOCATION_LAT, location.latitude,
                    DELTA
                )
                assertEquals(
                    MUNICH_LOCATION_LAT, location.latitude,
                    DELTA
                )
            }
        }
    }
}
