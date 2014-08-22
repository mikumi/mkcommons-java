/**
 * 
 */
package com.michael_kuck.commons;

/**
 * Simple application trace with different logging levels.
 * Designed as a singleton class.
 * 
 * @author michaelkuck
 * 
 */
public class Log {

	public static enum Level {
		NONE, ERROR, WARNING, INFO, DEBUG, VERBOSE;

		public boolean isAtLeast(final Level level)
		{
			return this.ordinal() >= level.ordinal();
		}

	}

	/**
	 * The level of information to be logged.
	 */
	private static Level logLevel = Level.INFO;

	/**
	 * Private constructor because of Singleton.
	 */
	private Log()
	{
	}

	/**
	 * Set the level of information to be logged.
	 * 
	 * @param level
	 */
	public static void setLogLevel(final Level level)
	{
		Log.info("Log level set to: " + level.toString());
		logLevel = level;
	}

	/**
	 * Log an error.
	 * 
	 * @param message
	 */
	public static void error(final String message)
	{
		if (logLevel.isAtLeast(Level.ERROR)) {
			System.out.println("[" + Level.ERROR.toString() + "] <" + getCallerInformation() + "> " + message);
		}
	}

	/**
	 * Log a warning.
	 * 
	 * @param message
	 */
	public static void warning(final String message)
	{
		if (logLevel.isAtLeast(Level.WARNING)) {
			System.out.println("[" + Level.WARNING.toString() + "] <" + getCallerInformation() + "> " + message);
		}
	}

	/**
	 * Log an information.
	 * 
	 * @param message
	 */
	public static void info(final String message)
	{
		if (logLevel.isAtLeast(Level.INFO)) {
			System.out.println("<" + getCallerInformation() + "> " + message);
		}
	}

	/**
	 * Log a verbose information.
	 * 
	 * @param message
	 */
	public static void debug(final String message)
	{
		if (logLevel.isAtLeast(Level.DEBUG)) {
			System.out.println("<" + getCallerInformation() + "> " + message);
		}
	}

	/**
	 * Log a verbose information.
	 * 
	 * @param message
	 */
	public static void verbose(final String message)
	{
		if (logLevel.isAtLeast(Level.VERBOSE)) {
			System.out.println("<" + getCallerInformation() + "> " + message);
		}
	}

	/**
	 * @return
	 */
	private static String getCallerInformation()
	{
		// 0 is current Thread, 1 is this method, 2 is log method, 3 is original calling method
		final StackTraceElement element = Thread.currentThread().getStackTrace()[3];
		final int lineNumber = element.getLineNumber();
		final String classNameParts[] = element.getClassName().split("\\.");
		final String className = classNameParts[classNameParts.length - 1];
		return className + ":" + lineNumber;
	}

}
