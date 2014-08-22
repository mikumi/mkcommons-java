/*
 * *
 *  * This file is part of whereisthat-android
 *  *
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.commons;

import com.michael_kuck.commons.logoutput.ConsoleLogOutput;
import com.michael_kuck.commons.logoutput.ILogOutput;

public class Log {

    public static enum Level {
        NONE, ERROR, WARNING, INFO, DEBUG, VERBOSE;

        public boolean isAtLeast(final Level level) {
            return this.ordinal() >= level.ordinal();
        }

    }

    private static ILogOutput logging = new ConsoleLogOutput();

    /**
     * The level of information to be logged.
     */
    private static Level logLevel = Level.INFO;

    /**
     * Private constructor because of Singleton.
     */
    private Log() {
    }

    /**
     * Set the level of information to be logged.
     *
     * @param level
     */
    public static void setLogLevel(final Level level) {
        Log.info("Log level set to: " + level.toString());
        logLevel = level;
    }

    public static Level getLogLevel() {
        return logLevel;
    }

    public static ILogOutput getLogging() {
        return logging;
    }

    /**
     * Set the actual logging implementation, e.g. Console or Android Logcat
     * @param logging
     */
    public static void setLogging(final ILogOutput logging) {
        Log.logging = logging;
    }

    /**
     * Log an error.
     *
     * @param message
     */
    public static void error(final String message) {
        if (logLevel.isAtLeast(Level.ERROR)) {
            logging.error(message);
        }
    }

    /**
     * Log a warning.
     *
     * @param message
     */
    public static void warning(final String message) {
        if (logLevel.isAtLeast(Level.WARNING)) {
            logging.warning(message);
        }
    }

    /**
     * Log an information.
     *
     * @param message
     */
    public static void info(final String message) {
        if (logLevel.isAtLeast(Level.INFO)) {
            logging.info(message);
        }
    }

    /**
     * Log a verbose information.
     *
     * @param message
     */
    public static void debug(final String message) {
        if (logLevel.isAtLeast(Level.DEBUG)) {
            logging.debug(message);
        }
    }

    /**
     * Log a verbose information.
     *
     * @param message
     */
    public static void verbose(final String message) {
        if (logLevel.isAtLeast(Level.VERBOSE)) {
            logging.verbose(message);
        }
    }

}
