package pl.edu.pw.rso2012.a1.dvcs.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pl.edu.pw.rso2012.a1.dvcs.view.utils.TextUtils;
import pl.edu.pw.rso2012.a1.dvcs.view.utils.WindowUtils;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class WaitbarDialog extends JDialog implements ChangeListener {
	
	private static final long serialVersionUID = -4355198523014547528L;
	private JProgressBar progressBar;
	private String title, text;
	
	public WaitbarDialog(Frame parent, String title, String text) {
		super(parent, "Progress", true);
		
		this.title = title;
		this.text = text;
		
		initialize();
	}
	
	private void initialize() {
		if (!TextUtils.isEmpty(title)) this.setTitle(title);
		setModal(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JPanel panel = (JPanel) getContentPane();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setStringPainted(false);
		Dimension preferred = progressBar.getPreferredSize();
		preferred.width = 300;
		progressBar.setPreferredSize(preferred);
		panel.add(progressBar, BorderLayout.CENTER);
		
		if (!TextUtils.isEmpty(text)) panel.add(new JLabel(text), BorderLayout.NORTH);
	}
	
	public static WaitbarListener showDialog(Component parent, String title, String text) {
		Frame frame = (Frame) SwingUtilities.getRoot(parent);
		WaitbarDialog dialog = new WaitbarDialog(frame, title, text);
		dialog.invalidate();
		dialog.repaint();
		dialog.pack();
		WindowUtils.setWindowSizeAndLocationToParent(dialog, parent.getSize(), parent.getLocation(), dialog.getSize());
		
		WaitbarListener listener = new WaitbarListener(dialog);
		return listener;
	}
	
	@Override
	public void stateChanged(final ChangeEvent event) {
		if (!SwingUtilities.isEventDispatchThread()) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					stateChanged(event);
				}
			});
			return;
		}
		
		WaitbarListener listener = (WaitbarListener) event.getSource();
		if (listener.isVisible()) {
			this.setVisible(true);
		} else {
			dispose();
		}
	}
	
	static class WaitbarListener {
		
		private ChangeListener changeListener;
		private boolean visible = false;
		
		public WaitbarListener(ChangeListener changeListener) {
			this.changeListener = changeListener;
		}
		
		public boolean isVisible() {
			return visible;
		}
		
		public void show() {
			visible = true;
			fireChangeEvent();
		}
		
		public void hide() {
			visible = false;
			fireChangeEvent();
		}
		
		private void fireChangeEvent() {
			changeListener.stateChanged(new ChangeEvent(this));
		}
	}
}
