plugins {
    id("com.android.library")
    id("kotlin-android")
    id("convention.android-base")
}

android {
    variantFilter {
        if (name != "release") {
            ignore = true
        }
    }

    // todo doesn't work on AGP 4.2.2
//    @Suppress("UnstableApiUsage")
//    onVariants {
//        androidTest {
//            enabled = false
//        }
//    }
}
