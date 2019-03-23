package com.kaspersky.uitest_framework.device.adb

import com.kaspersky.uitest_framework.configurator.Configurator

object Application {

    /**
     *  Installs an app thorough ADB.
     *  @param apkPath path to an apk to be installed. The apk is hosted on the test server
     */
    fun install(apkPath: String) = Configurator.serverInterface.performAdb("install $apkPath")

    /**
     *  Uninstalls an app thorough ADB.
     *  @param pkg android package name of an app to be deleted.
     */
    fun uninstall(pkg: String) = Configurator.serverInterface.performAdb("uninstall $pkg")
}
