package employeeadmin.view.components;

import api.Utils.BorderBuilder;
import api.Utils.GBC;
import api.Utils.UnhandledException;
import employeeadmin.model.enums.Department;
import employeeadmin.model.vo.RoleVO;
import employeeadmin.model.vo.UserVO;
import employeeadmin.view.interfaces.UserFormMessage;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 1/29/2015
 */

public class UserForm extends JPanel implements ActionListener {

	public static final int MODE_ADD = 1;
	public static final int USER_SELECTED = 2;
	public static final int MODE_UPDATE = 3;
	public static final int DELETE_USER = 4;
	public static final int FORM_ENABLED = 5;
	public static final int FORM_DISABLED = 6;

	private static final String FIRST_NAME = "First Name";
	private static final String LAST_NAME = "Last Name";
	private static final String EMAIL = "Email";
	private static final String USERNAME = "Username *";
	private static final String PASSWORD = "Password *";
	private static final String CONFIRM = "Confirm Password *";
	private static final String DEPARTMENT = "Department *";
	private static final String SAVE_PROFILE = "Save Profile";
	private static final String UPDATE_PROFILE = "Update Profile";
	private static final String CANCEL = "Cancel";

	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel emailLabel;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JButton cancelButton;
	private JLabel passwordConfirmLabel;
	private JLabel departmentLabel;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmField;
	private DefaultComboBoxModel<String> departmentListModel;
	private JComboBox<String> departmentList;
	private JButton updateUserButton;
	private UserVO user;
	private RoleVO roles;

	private UserFormMessage message;

	public UserForm() {
		setLayout( new GridBagLayout() );
		setBorder(
			new BorderBuilder( "User Profile", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, 2, Font.BOLD, 12 ) );
		initComponents();
	}

	private void initComponents() {

		firstNameLabel = new JLabel( FIRST_NAME );
		lastNameLabel = new JLabel( LAST_NAME );
		emailLabel = new JLabel( EMAIL );
		userNameLabel = new JLabel( USERNAME );
		passwordLabel = new JLabel( PASSWORD );
		passwordConfirmLabel = new JLabel( CONFIRM );
		departmentLabel = new JLabel( DEPARTMENT );

		firstNameField = new JTextField();
		firstNameField.addActionListener( this );

		lastNameField = new JTextField();
		lastNameField.addActionListener( this );

		emailField = new JTextField();
		emailField.addActionListener( this );

		userNameField = new JTextField();
		userNameField.addActionListener( this );

		passwordField = new JPasswordField();
		passwordField.addActionListener( this );

		passwordConfirmField = new JPasswordField();
		passwordConfirmField.addActionListener( this );

		departmentListModel = new DefaultComboBoxModel<>();
		Department.list().stream().map( Department:: getValue ).forEach( departmentListModel:: addElement );
		departmentList = new JComboBox<>( departmentListModel );

		updateUserButton = new JButton();
		updateUserButton.addActionListener( this );

		cancelButton = new JButton( CANCEL );
		cancelButton.addActionListener( this );

		//@formatter:off
		GBC firstNameLabelConstraints = new GBC( 0, 0, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.LINE_START ).setFill( GBC.NONE ) .setInsets(
			0, 0, 0, 10 ) .setIpad( 0, 0 );
		add( firstNameLabel, firstNameLabelConstraints );

		GBC lastNameLabelConstraints = new GBC( 0, 1, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.LINE_START ).setFill( GBC.NONE ) .setInsets(
			0, 0, 0, 10 ) .setIpad( 0, 0 );
		add( lastNameLabel, lastNameLabelConstraints );

		GBC emailLabelConstraints = new GBC( 0, 2, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.LINE_START ).setFill( GBC.NONE ) .setInsets(
			0, 0, 0, 10 ) .setIpad( 0, 0 );
		add( emailLabel, emailLabelConstraints );

		GBC userNameLabelConstraints = new GBC( 0, 3, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.LINE_START ).setFill( GBC.NONE ) .setInsets(
			0, 0, 0, 10 ) .setIpad( 0, 0 );
		add( userNameLabel, userNameLabelConstraints );

		GBC passwordLabelConstraints = new GBC( 0, 4, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.LINE_START ).setFill( GBC.NONE ) .setInsets(
			0, 0, 0, 10 ) .setIpad( 0, 0 );
		add( passwordLabel, passwordLabelConstraints );

		GBC passwordConfirmLabelConstraints = new GBC( 0, 5, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.LINE_START ) .setFill(
			GBC.NONE ) .setInsets( 0, 0, 0, 10 ) .setIpad( 0, 0 );
		add( passwordConfirmLabel, passwordConfirmLabelConstraints );

		GBC departmentLabelConstraints = new GBC( 0, 6, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.LINE_START ).setFill( GBC.NONE ) .setInsets(
			0, 0, 0, 10 ) .setIpad( 0, 0 );
		add( departmentLabel, departmentLabelConstraints );

		GBC firstNameFieldConstraints = new GBC( 1, 0, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.WEST ).setFill( GBC.HORIZONTAL ) .setInsets(
			0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( firstNameField, firstNameFieldConstraints );

		GBC lastNameFieldConstraints = new GBC( 1, 1, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.WEST ).setFill( GBC.HORIZONTAL ) .setInsets(
			0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( lastNameField, lastNameFieldConstraints );

		GBC emailFieldConstraints = new GBC( 1, 2, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.WEST ).setFill( GBC.HORIZONTAL ) .setInsets(
			0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( emailField, emailFieldConstraints );

		GBC userNameFieldConstraints = new GBC( 1, 3, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.WEST ).setFill( GBC.HORIZONTAL ) .setInsets(
			0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( userNameField, userNameFieldConstraints );

		GBC passwordFieldConstraints = new GBC( 1, 4, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.WEST ).setFill( GBC.HORIZONTAL ) .setInsets(
			0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( passwordField, passwordFieldConstraints );

		GBC passwordConfirmFieldConstraints = new GBC( 1, 5, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.WEST ).setFill( GBC.HORIZONTAL ) .setInsets(
			0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( passwordConfirmField, passwordConfirmFieldConstraints );

		GBC departmentListConstraints = new GBC( 1, 6, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.WEST ).setFill( GBC.HORIZONTAL ) .setInsets(
			0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( departmentList, departmentListConstraints );

		GBC updateUserButtonConstraints = new GBC( 0, 7, 1, 1 ).setWeight( 0, 0 ).setAnchor( GBC.LINE_START ).setFill( GBC.NONE ) .setInsets(
			0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( updateUserButton, updateUserButtonConstraints );

		GBC cancelButtonConstraints = new GBC( 1, 7, 1, 1 ).setWeight( 0, 0 ).setAnchor( GBC.LINE_START ).setFill( GBC.NONE ) .setInsets(
			0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( cancelButton, cancelButtonConstraints );
		//@formatter:on
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if( Arrays.asList( SAVE_PROFILE, UPDATE_PROFILE, CANCEL ).contains( e.getActionCommand() ) ) {
			switch( e.getActionCommand() ) {
				case SAVE_PROFILE:
					getUserDetails( user );
					if( !user.isValid() ) {
						invalidUser();
						return;
					}
					message.onAdd( user );
					break;
				case UPDATE_PROFILE:
					getUserDetails( user );
					if( !user.isValid() ) {
						invalidUser();
						return;
					}
					message.onUpdate( user );
					break;
				case CANCEL:
					cancel();
					message.onCancel();
					break;
				default:
					throw new UnhandledException( "Error Unhandled event [" + e.getSource() + "]" );
			}
		}
	}

	public void newUser() {
		UserVO newUser = new UserVO();
		clearForm();
		setState( FORM_ENABLED );
		setMode( MODE_ADD );
		setUser( newUser );
	}

	public void setUser( UserVO user ) {
		this.user = user;
	}

	public void selectUser( UserVO selectedUser ) {
		clearForm();
		setState( FORM_ENABLED );
		setMode( MODE_UPDATE );
		setUser( selectedUser );
		refreshForm();
	}

	public void getUserDetails( UserVO currentUser ) {
		currentUser.firstName = firstNameField.getText();
		currentUser.lastName = lastNameField.getText();
		currentUser.email = emailField.getText();
		currentUser.username = userNameField.getText();
		currentUser.password = Arrays.toString( passwordField.getPassword() );
		currentUser.department = (String) departmentList.getSelectedItem();
	}

	public void cancel() {
		clearForm();
		setUser( null );
		setMode( MODE_ADD );
		setState( FORM_DISABLED );
	}

	public void refreshForm() {
		firstNameField.setText( user.firstName );
		lastNameField.setText( user.lastName );
		emailField.setText( user.email );
		userNameField.setText( user.username );
		passwordField.setText( user.password );
		passwordConfirmField.setText( user.password );
		departmentList.setSelectedItem( user.department );
	}

	public void clearForm() {
		firstNameField.setText( "" );
		lastNameField.setText( "" );
		emailField.setText( "" );
		userNameField.setText( "" );
		passwordField.setText( "" );
		passwordConfirmField.setText( "" );
		departmentList.setSelectedItem( Department.NONE_SELECTED.getValue() );
	}

	public void setMode( int mode ) {
		switch( mode ) {
			case MODE_ADD:
				firstNameField.requestFocus();
				updateUserButton.setText( SAVE_PROFILE );
				break;
			case MODE_UPDATE:
				updateUserButton.setText( UPDATE_PROFILE );
				setState( FORM_ENABLED );
				break;
			default:
				break;
		}
	}

	public void setState( int state ) {
		boolean enabled = ( state == FORM_ENABLED );
		firstNameField.setEnabled( enabled );
		lastNameField.setEnabled( enabled );
		emailField.setEnabled( enabled );
		userNameField.setEnabled( enabled );
		passwordField.setEnabled( enabled );
		passwordConfirmField.setEnabled( enabled );
		departmentList.setEnabled( enabled );
		updateUserButton.setEnabled( enabled );
		cancelButton.setEnabled( enabled );
	}

	public void invalidUser(){
		JOptionPane.showMessageDialog( null, "All fields are required.","Invalid User",JOptionPane.WARNING_MESSAGE );
	}

	public void setMessage( UserFormMessage message ) {
		this.message = message;
	}

}