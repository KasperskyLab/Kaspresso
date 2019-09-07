package com.kaspersky.kaspresso.device.server

/**
 * It's a comfortable wrapper to work with AdbServer repository.
 * Important notes:
 * 1. Real connection is established only after a call one of methods of the interface except disconnectServer().
 * So it's lazy wrapper. Keep it in your mind when you decide to put custom implementation od AdbServer.
 * 2. After each test a developer has to disconnect AdbServer. There is disconnectServer() method to complete the disconnection.
 * But Kaspresso calls disconnectServer() after each test if the connection was established during the test. What's why don't worry =)
 */
interface AdbServer {

    /**
     *  Executes shell commands blocking current thread.
     *  Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     *  Required Permissions: INTERNET.
     *
     *  @param commands commands to execute.
     *  @throws AdbServerException if a result status of any command in @param commands is Failed
     *  @return list of answers of commands' execution
     */
    fun performCmd(vararg commands: String): List<String>

    /**
     *  Performs adb commands blocking current thread.
     *  Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     *  Required Permissions: INTERNET.
     *
     *  @param commands commands to execute.
     *  @throws AdbServerException if a result status of any command in @param commands is Failed
     *  @return list of answers of commands' execution
     */
    fun performAdb(vararg commands: String): List<String>

    /**
     *  Performs shell commands blocking current thread.
     *  Please be aware! If any command that is in @param commands failed then AdbServerException will be thrown
     *
     *  Required Permissions: INTERNET.
     *
     *  @param commands commands to execute.
     *  @throws AdbServerException if a result status of any command in @param commands is Failed
     *  @return list of answers of commands' execution
     */
    fun performShell(vararg commands: String): List<String>

    /**
     * Disconnect from AdbServer.
     * The method is called by Kaspresso after each test.
     */
    fun disconnectServer()

}