package pl.edu.pw.rso2012.a1.dvcs.utils;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class Log {
	
	private Log() {}
	
	public static boolean isLoggable() {
		return true;
	}
	
	public static void o(String tag, String message) {
		if (isLoggable()) {
			System.out.println(String.format("%-30s %s", tag, message));
		}
	}
	
	public static void o(String message) {
		System.out.println(message);
	}
	
	public static void e(String tag, String message) {
		if (isLoggable()) {
			System.err.println(String.format("%-30s %s", tag, message));
		}
	}
	
	public static void e(String message) {
		System.err.println(message);
	}
	
	public static String getCurrentMethodName() {
		StackTraceElement[] stackTraceElements = Thread.currentThread()
				.getStackTrace();
		
		return String.format("%s() ", stackTraceElements[2].getMethodName());
	}
}
