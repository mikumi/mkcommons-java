/**
 *
 */
package com.michael_kuck.commons.logoutput;

import com.michael_kuck.commons.Log;

/**
 * Simple application trace with different logging levels.
 * Designed as a singleton class.
 *
 * @author michaelkuck
 */
public class ConsoleLogOutput implements ILogOutput {

    public ConsoleLogOutput() {
    }

    @Override
    public void error(final String message) {
        System.out.println("[" + Log.Level.ERROR.toString() + "] <" + getCallerInformation() + "> " + message);
    }

    @Override
    public void warning(final String message) {
        System.out.println("[" + Log.Level.WARNING.toString() + "] <" + getCallerInformation() + "> " + message);
    }

    @Override
    public void info(final String message) {
        System.out.println("<" + getCallerInformation() + "> " + message);
    }

    @Override
    public void debug(final String message) {
        System.out.println("<" + getCallerInformation() + "> " + message);
    }

    @Override
    public void verbose(final String message) {
        System.out.println("<" + getCallerInformation() + "> " + message);
    }

    private String getCallerInformation() {
        // 0 is current Thread, 1 is this method, 2 is log method, 3 is the Log class, 4 is original calling method
        final StackTraceElement element = Thread.currentThread().getStackTrace()[4];
        final int lineNumber = element.getLineNumber();
        final String classNameParts[] = element.getClassName().split("\\.");
        final String className = classNameParts[classNameParts.length - 1];
        return className + ":" + lineNumber;
    }

}
