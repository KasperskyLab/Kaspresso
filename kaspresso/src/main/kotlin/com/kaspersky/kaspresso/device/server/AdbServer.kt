package com.kaspersky.kaspresso.device.server

import android.annotation.SuppressLint

/**
 * This is a comfortable wrapper to work with AdbServer repository.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java -jar path_to_file/adbserver-desktop.jar"
 *
 * Important notes:
 * 1. Real connection is established only after a call one of methods of the interface except disconnectServer().
 * So it's lazy wrapper. Keep it in your mind when you decide to put custom implementation od AdbServer.
 * 2. Kaspresso calls disconnectServer() after each test if the connection was established during the test.
 */
interface AdbServer {

    /**
     * Executes shell commands blocking current thread.
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
     * @see <a href="https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md">AdbServer documentation</a>
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    @SuppressLint("SdCardPath")
    @Deprecated("This method doesn't work for the commands with the complex arguments containing " +
            "whitespaces (e.g. 'adb pull \"/sdcard/Documents/path_with whitespace to/file.txt\") and doesn't allow commands piping like" +
            "adbServer.performCmd(\"bash\", listOf(\"-c\", \"adb shell dumpsys deviceidle | grep mForceIdle\"))" +
            "which the AdbServer.performCmd(String, List<String>) does. " +
            "For more details, please check out https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md",
        ReplaceWith("adbServer.performCmd(*commands, emptyList())")
    )
    fun performCmd(vararg commands: String): List<String>

    /**
     * Performs shell commands blocking current thread. Allows more control over how arguments are parsed.
     * Each list element is used as is. Refer to the https://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html.
     *
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
     * @see <a href="https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md">AdbServer documentation</a>
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    fun performCmd(command: String, arguments: List<String>): String

    /**
     * Performs adb commands blocking current thread.
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
     * @see <a href="https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md">AdbServer documentation</a>
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    @SuppressLint("SdCardPath")
    @Deprecated("This method doesn't work for the commands with the complex arguments containing " +
            "whitespaces (e.g. 'adb pull \"/sdcard/Documents/path_with whitespace to/file.txt\") and doesn't allow commands piping like" +
            "adbServer.performCmd(\"bash\", listOf(\"-c\", \"adb shell dumpsys deviceidle | grep mForceIdle\"))" +
            "which the AdbServer.performAdb(String, List<String>) does " +
            "For more details, please check out https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md",
        ReplaceWith("adbServer.performAdb(*commands, emptyList())")
    )
    fun performAdb(vararg commands: String): List<String>

    /**
     * Performs adb commands blocking current thread. Allows more control over how arguments are parsed.
     * Each list element is used as is. Refer to the https://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html.
     *
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
     * @see <a href="https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md">AdbServer documentation</a>
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    fun performAdb(command: String, arguments: List<String>): String

    /**
     * Performs shell commands blocking current thread.
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
     * @see <a href="https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md">AdbServer documentation</a>
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    @SuppressLint("SdCardPath")
    @Deprecated("This method doesn't work for the commands with the complex arguments containing " +
            "whitespaces (e.g. 'adb pull \"/sdcard/Documents/path_with whitespace to/file.txt\") and doesn't allow commands piping like" +
            "adbServer.performCmd(\"bash\", listOf(\"-c\", \"adb shell dumpsys deviceidle | grep mForceIdle\"))" +
            "which the AdbServer.performShell(String, List<String>) does " +
            "For more details, please check out https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md",
        ReplaceWith("adbServer.performShell(*commands, emptyList())")
    )
    fun performShell(vararg commands: String): List<String>

    /**
     * Performs shell commands blocking current thread. Allows more control over how arguments are parsed.
     * Each list element is used as is. Refer to the https://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html.
     *
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
     * @see <a href="https://github.com/KasperskyLab/Kaspresso/blob/master/docs/Wiki/Executing_adb_commands.en.md">AdbServer documentation</a>
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    fun performShell(command: String, arguments: List<String>): String

    /**
     * Disconnect from AdbServer.
     * The method is called by Kaspresso after each test.
     */
    fun disconnectServer()
}
