package com.kaspersky.kaspresso.device.server

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
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    fun performCmd(vararg commands: String): List<String>

    /**
     * Performs shell commands blocking current thread. Allows more control over how arguments are parsed.
     * Each list element is used as is. Refer to the https://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html.
     *
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
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
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    fun performAdb(vararg commands: String): List<String>

    /**
     * Performs adb commands blocking current thread. Allows more control over how arguments are parsed.
     * Each list element is used as is. Refer to the https://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html.
     *
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
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
     * @param commands commands to execute.
     * @throws AdbServerException if a result status of any command in @param commands is Failed
     * @return list of answers of commands' execution
     */
    fun performShell(vararg commands: String): List<String>

    /**
     * Performs shell commands blocking current thread. Allows more control over how arguments are parsed.
     * Each list element is used as is. Refer to the https://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html.
     *
     * Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     * Required Permissions: INTERNET.
     *
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
