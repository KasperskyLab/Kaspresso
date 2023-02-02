package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.screen.Screen
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@Ignore("There's a bug in AVD images that makes geolocation change non-possible in AVD launched with '-no-window' flag")
class DeviceLocationSampleTest : TestCase() {

    companion object {
        private const val GPS_SWITCH_DELAY = 1000L
        private const val MUNICH_LOCATION_LAT = 48.136414
        private const val MUNICH_LOCATION_LNG = 11.588115
        private const val DELTA = 0.001
    }

    private val EMPTY_LISTENER = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            // empty
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            // empty
        }
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private lateinit var manager: LocationManager

    @Test
    fun locationSampleTest() {
        before {
            device.location.enableGps()
            manager = device.targetContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
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

                flakySafely(timeoutMs = 60_000, intervalMs = 500) {
                    /** Request single update to apply changes */
                    manager.requestSingleUpdate(
                        LocationManager.GPS_PROVIDER,
                        EMPTY_LISTENER,
                        Looper.getMainLooper()
                    )

                    val location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    assertNotNull(location) // flakySafely doesn't retry after NPE
                    assertEquals(
                        MUNICH_LOCATION_LAT, location!!.latitude,
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
}
