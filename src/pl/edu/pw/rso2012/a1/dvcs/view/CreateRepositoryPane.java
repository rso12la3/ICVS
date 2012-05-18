package pl.edu.pw.rso2012.a1.dvcs.view;

import java.awt.Component;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;

import pl.edu.pw.rso2012.a1.dvcs.utils.Log;
import pl.edu.pw.rso2012.a1.dvcs.view.utils.WindowUtils;

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class CreateRepositoryPane extends JPanel {
	
	private static final String TAG = CreateRepositoryPane.class.getSimpleName();
	private static final long serialVersionUID = 8183331439977906195L;
	public static final int APPROVE_OPTION = 0, CANCEL_OPTION = 1,
			ERROR_OPTION = -1;
	//
	private static final int LABEL_DEFAULT_SIZE = 100;
	private final static String ACTION_SELECT_DIR = "SELECTDIR",
			ACTION_OK = "OK", ACTION_CANCEL = "CANCEL";
	//
	protected int mReturn = ERROR_OPTION;
	protected File mDirectory;
	protected JLabel mEmailLabel, mPasswordLabel, mDirectoryLabel;
	protected JTextField mPasswordField, mEmailField, mDirectoryField;
	protected JButton mSelectDirectoryButton, mOkButton, mCancelButton;
	
	
	public CreateRepositoryPane() {
		super();
		initialize();
	}
	
	public CreateRepositoryPane(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		initialize();
	}
	
	public CreateRepositoryPane(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		initialize();
	}
	
	public CreateRepositoryPane(LayoutManager layout) {
		super(layout);
		initialize();
	}
	
	private void initialize() {

        mEmailLabel = new JLabel("Email address");
        mEmailField = new JTextField();
        
        mPasswordLabel = new JLabel("Password");
        mPasswordField = new JTextField();
       
        mDirectoryLabel = new JLabel("Base directory");
        mDirectoryField = new JTextField();
        mDirectoryField.setEditable(false);
        
        mSelectDirectoryButton = new JButton("...");
        mSelectDirectoryButton.setActionCommand(ACTION_SELECT_DIR);
        mSelectDirectoryButton.addActionListener(mActionListener);
        
        mOkButton = new JButton("OK");
        mOkButton.setActionCommand(ACTION_OK);
        mOkButton.addActionListener(mActionListener);

        mCancelButton = new JButton("Cancel");
        mCancelButton.setActionCommand(ACTION_CANCEL);
        mCancelButton.addActionListener(mActionListener);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mEmailLabel, GroupLayout.PREFERRED_SIZE, LABEL_DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mEmailField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mPasswordLabel, GroupLayout.PREFERRED_SIZE, LABEL_DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mPasswordField, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mDirectoryLabel, GroupLayout.PREFERRED_SIZE, LABEL_DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mOkButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mCancelButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mDirectoryField)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mSelectDirectoryButton)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mEmailLabel)
                    .addComponent(mEmailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mPasswordLabel)
                    .addComponent(mPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mDirectoryLabel)
                    .addComponent(mDirectoryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(mSelectDirectoryButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mOkButton)
                    .addComponent(mCancelButton))
                .addContainerGap())
        );

    }
	
	protected JDialog createDialog(Component parent) throws HeadlessException {
		Frame frame = (Frame) SwingUtilities.getRoot(parent);
		JDialog dialog = new JDialog(frame);
		dialog.getContentPane().add(this);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mReturn = CANCEL_OPTION;
			}
		});
		dialog.setModal(true);
		dialog.setTitle("Create repository");
		dialog.invalidate();
		dialog.repaint();
		return dialog;
	}
	
	JDialog dialog;
	
	public int showDialog(Component parent)
			throws HeadlessException {
		dialog = createDialog(parent);
		mReturn = ERROR_OPTION;
		dialog.pack();
		WindowUtils.setWindowSizeAndLocationToParent(dialog, parent.getSize(), parent.getLocation(), dialog.getSize());
		dialog.setVisible(true);
		return mReturn;
	}
	
	private ActionListener mActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			Log.o(TAG, Log.getCurrentMethodName());
			
			switch (event.getActionCommand()) {
			case ACTION_SELECT_DIR:
				File dir = FilePickingHelper.openFolderChooser(SwingUtilities
						.getRoot(CreateRepositoryPane.this));
				if (dir != null) {
					mDirectory = dir;
					mDirectoryField.setText(dir.getPath());
				}
				break;
			case ACTION_OK:
				mReturn = APPROVE_OPTION;
				dialog.setVisible(false);
				break;
			case ACTION_CANCEL:
				mReturn = CANCEL_OPTION;
				dialog.setVisible(false);
				break;
			}
		}
	};
	
	public File getBaseDirectory() {
		return mDirectory;
	}
	
	public String getPassword() {
		return mPasswordField.getText().trim();
	}
	
	public String getEmailAddress() {
		return mEmailField.getText().trim();
	}
}
