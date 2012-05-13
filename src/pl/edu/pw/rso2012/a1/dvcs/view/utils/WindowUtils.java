package pl.edu.pw.rso2012.a1.dvcs.view.utils;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

import pl.edu.pw.rso2012.a1.dvcs.utils.Log;
import pl.edu.pw.rso2012.a1.dvcs.view.Constants;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class WindowUtils {
	
	private WindowUtils() {}
	
	public static void setNativeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			Log.e("Error setting native LAF");
		}
	}
	
	public static void setWindowSizeAndLocation(JFrame window,
			Dimension screenSize, Dimension windowSize) {
		
		window.setSize(windowSize);
		window.setPreferredSize(windowSize);
		
		int placementX = (screenSize.width - windowSize.width) / 2;
		int placementY = (screenSize.height - windowSize.height) / 2;
		window.setLocation(placementX, placementY);
	}
	
	public static void setWindowSizeAndLocation(JFrame window,
			Dimension screenSize, float sizeRatio) {
		Dimension windowSize = new Dimension(
				(int) (screenSize.width * Constants.WINDOW_TO_SCREEN_SIZE),
				(int) (screenSize.height * Constants.WINDOW_TO_SCREEN_SIZE));
		
		setWindowSizeAndLocation(window, screenSize, windowSize);
	}
	
}
