/*
 * *
 *  * This file is part of mkcommons-java
 *  *
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.commons.logoutput;

public interface ILogOutput {

    /**
     * Log an error.
     *
     * @param message
     */
    public void error(final String message);

    /**
     * Log a warning.
     *
     * @param message
     */
    public void warning(final String message);

    /**
     * Log an information.
     *
     * @param message
     */
    public void info(final String message);

    /**
     * Log a verbose information.
     *
     * @param message
     */
    public void debug(final String message);

    /**
     * Log a verbose information.
     *
     * @param message
     */
    public void verbose(final String message);

}
