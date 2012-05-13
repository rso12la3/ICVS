package pl.edu.pw.rso2012.a1.dvcs.view;

import java.awt.Component;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
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

/**
 * 
 * @author Andrzej Makarewicz
 * 
 */
public class CreateRepositoryPane extends JPanel {
	
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
	protected JLabel mNameLabel, mServerUrlLabel, mUsernameLabel, mPasswordLabel,
			mDirectoryLabel;
	protected JTextField mServerUrlField, mPasswordField, mUsernameField, mName,
			mDirectoryField;
	protected JButton mSelectDirectoryButton, mOkButton, mCancelButton;
	
	
	public CreateRepositoryPane() {
		super();
		initialize();
	}
	
	public CreateRepositoryPane(boolean arg0) {
		super(arg0);
		initialize();
	}
	
	public CreateRepositoryPane(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		initialize();
	}
	
	public CreateRepositoryPane(LayoutManager arg0) {
		super(arg0);
		initialize();
	}
	
	private void initialize() {

        mNameLabel = new JLabel();
        mName = new JTextField();
        mServerUrlLabel = new JLabel();
        mServerUrlField = new JTextField();
        mUsernameLabel = new JLabel();
        mUsernameField = new JTextField();
        mPasswordLabel = new JLabel();
        mPasswordField = new JTextField();
        mDirectoryLabel = new JLabel();
        mSelectDirectoryButton = new JButton();
        mDirectoryField = new JTextField();
        mOkButton = new JButton();
        mCancelButton = new JButton();

        mNameLabel.setText("Repository name");

        mName.setText("New repository");

        mServerUrlLabel.setText("Server URL");

        mServerUrlField.setText("");
        
        mUsernameLabel.setText("Username");

        mUsernameField.setText("");

        mPasswordLabel.setText("Password");

        mPasswordField.setText("");

        mDirectoryLabel.setText("Local directory");

        mSelectDirectoryButton.setText("...");
        mSelectDirectoryButton.setActionCommand(ACTION_SELECT_DIR);
        mSelectDirectoryButton.addActionListener(mActionListener);

        mDirectoryField.setText("");
        mDirectoryField.setEditable(false);

        mOkButton.setText("OK");
        mOkButton.setActionCommand(ACTION_OK);
        mOkButton.addActionListener(mActionListener);

        mCancelButton.setText("Cancel");
        mCancelButton.setActionCommand(ACTION_CANCEL);
        mCancelButton.addActionListener(mActionListener);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mNameLabel, GroupLayout.PREFERRED_SIZE, LABEL_DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mName))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(mServerUrlLabel, GroupLayout.PREFERRED_SIZE, LABEL_DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mServerUrlField, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mUsernameLabel, GroupLayout.PREFERRED_SIZE, LABEL_DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mUsernameField))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mPasswordLabel, GroupLayout.PREFERRED_SIZE, LABEL_DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mPasswordField))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(mDirectoryLabel, GroupLayout.PREFERRED_SIZE, LABEL_DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mDirectoryField)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mSelectDirectoryButton))))
                    .addGroup(layout.createSequentialGroup()
                    	.addContainerGap()
                        .addComponent(mOkButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mCancelButton)
                        ))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mNameLabel)
                    .addComponent(mName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mServerUrlLabel)
                    .addComponent(mServerUrlField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mUsernameLabel)
                    .addComponent(mUsernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mPasswordLabel)
                    .addComponent(mPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mDirectoryLabel)
                        .addComponent(mDirectoryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(mSelectDirectoryButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mOkButton)
                    .addComponent(mCancelButton))
                .addContainerGap()
                )
                
        );
    }
	
	protected JDialog createDialog(Component parent, String approveButtonText) throws HeadlessException {
		Frame frame = (Frame) SwingUtilities.getRoot(parent);
		JDialog dialog = new JDialog(frame);
		dialog.getContentPane().add(this);
		if(approveButtonText != null) mOkButton.setText(approveButtonText);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mReturn = CANCEL_OPTION;
			}
		});
		dialog.setModal(true);
		dialog.invalidate();
		dialog.repaint();
		return dialog;
	}
	
	JDialog d;
	
	public int showDialog(Component parent, String approveButtonText)
			throws HeadlessException {
		d = createDialog(parent, approveButtonText);
		mReturn = ERROR_OPTION;
		d.pack();
		d.setVisible(true);
		return mReturn;
	}
	
	private ActionListener mActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
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
				d.setVisible(false);
				break;
			case ACTION_CANCEL:
				mReturn = CANCEL_OPTION;
				d.setVisible(false);
				break;
			}
		}
	};
	
	public File getDirectory() {
		return mDirectory;
	}
	
	public String getRepositoryName() {
		return mName.getText().trim();
	}
	
	public String getServerUrl() {
		return mServerUrlField.getText().trim();
	}
	
	public String getPassword() {
		return mPasswordField.getText().trim();
	}
	
	public String getUsername() {
		return mUsernameField.getText().trim();
	}
}
