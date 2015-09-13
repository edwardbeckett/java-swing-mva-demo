package employeeadmin.view.components;

import api.Utils.BorderBuilder;
import api.Utils.GBC;
import api.Utils.UnhandledException;
import employeeadmin.model.enums.Role;
import employeeadmin.model.vo.UserVO;
import employeeadmin.view.interfaces.RolePanelMessage;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/3/2015
 */
public class RolePanel extends JPanel implements ActionListener, ListSelectionListener, ItemListener {

	public static final int MODE_ADD = 1;
	public static final int MODE_ACTIVE = 2;
	public static final int MODE_UPDATE = 3;
	public static final int FORM_ENABLED = 4;
	public static final int FORM_DISABLED = 5;

	private static final String ADD_ROLE = "Add";
	private static final String REMOVE_ROLE = "Remove";

	private JList<String> roles;
	private DefaultListModel<String> roleModel;
	private DefaultComboBoxModel<String> roleListModel;
	private JComboBox<String> roleList;
	private JButton addButton;
	private JButton removeButton;
	private List<Role> userRoles;
	private UserVO user;
	private Role role;
	private String roleValue;

	private RolePanelMessage message;

	public RolePanel() {
		setLayout( new GridBagLayout() );
		//@formatter:off
		setBorder(new BorderBuilder( "User Roles", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, 2, Font.BOLD, 12 ) );
		//@formatter:on
		initComponents();
	}

	private void initComponents() {

		roleModel = new DefaultListModel<>();
		roles = new JList<>( roleModel );
		roles.setPreferredSize( new Dimension( 260, 200 ) );
		roles.addListSelectionListener( this );

		roleListModel = new DefaultComboBoxModel<>();
		for(Role role : Role.list()) {
			roleListModel.addElement( role.getValue() );
		}
		roleList = new JComboBox<>( roleListModel );
		roleList.addItemListener( this );

		addButton = new JButton( ADD_ROLE );
		addButton.addActionListener( this );

		removeButton = new JButton( REMOVE_ROLE );
		removeButton.addActionListener( this );

		//@formatter:off
		GBC rolesConstraints = new GBC( 0, 0, 3, 1 ).setWeight( 1, 1 ).setAnchor( GBC.NORTH ).setFill( GBC.BOTH ) .setInsets(
			0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( roles, rolesConstraints );

		GBC roleListConstraints = new GBC( 0, 1, 1, 1 ).setWeight( 0, 0 ).setAnchor( GBC.LINE_START ).setFill( GBC.NONE ) .setInsets(
			0, 0, 0, 2 ) .setIpad( 0, 0 );
		add( roleList, roleListConstraints );

		GBC addButtonConstraints = new GBC( 1, 1, 1, 1 ).setWeight( 0, 0 ).setAnchor( GBC.LINE_START ).setFill( GBC.NONE ) .setInsets(
			0, 0, 0, 2 ) .setIpad( 0, 0 );
		add( addButton, addButtonConstraints );

		GBC removeButtonConstraints = new GBC( 2, 1, 1, 1 ).setWeight( 0, 0 ).setAnchor( GBC.LINE_START ).setFill( GBC.NONE ) .setInsets( 0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( removeButton, removeButtonConstraints );
		//@formatter:on
	}

	@Override
	public void actionPerformed( ActionEvent e ) {

		if( Arrays.asList( ADD_ROLE, REMOVE_ROLE ).contains( e.getActionCommand() ) ) {

			switch( e.getActionCommand() ) {
				case ADD_ROLE:
					onAdd( user, roleValue );
					break;
				case REMOVE_ROLE:
					onRemove( user, roleValue );
					break;
				default:
					throw new UnhandledException( "Error Unhandled event [" + e.getSource() + "]" );
			}
		}
	}

	private void onRemove( UserVO user, String roleValue ) {
		setUser( user );
		setRole( roleValue );
		message.onRemove( user, role );
	}

	private void onAdd( UserVO user, String roleValue ) {
		setUser( user );
		setRole( roleValue );
		message.onAdd( user, role );
	}

	@Override
	public void valueChanged( ListSelectionEvent e ) {
		if( !roles.isSelectionEmpty() && !e.getValueIsAdjusting() ) {
			this.roleValue = roles.getSelectedValue();
			setMode( MODE_UPDATE );
		}
	}

	@Override
	public void itemStateChanged( ItemEvent e ) {
		if( e.getStateChange() == ItemEvent.SELECTED ) {
			this.roleValue = (String) e.getItem();
			setMode( roleValue.equals( Role.NONE_SELECTED.getValue() ) ? MODE_UPDATE : MODE_ADD );
		}
	}

	public void setUser( UserVO selectedUser ) {
		this.user = selectedUser;
	}

	public void reloadRoles() {
		for(Role role : userRoles) {
			roleModel.addElement( role.getValue() );
		}

	}

	public void setRole( String selectedValue ) {
		Role.list().stream().filter( role -> role.getValue().equals( selectedValue ) ).forEach( role -> {
			this.role = role;
		} );

	}

	public void setMode( int mode ) {

		switch( mode ) {
			case MODE_ADD:
				roles.getSelectionModel().clearSelection();
				roleList.setEnabled( true );
				addButton.setEnabled( true );
				removeButton.setEnabled( false );
				break;
			case MODE_ACTIVE:
				roles.getSelectionModel().clearSelection();
				roleListModel.setSelectedItem( Role.NONE_SELECTED.getValue() );
				roleList.setEnabled( true );
				addButton.setEnabled( false );
				removeButton.setEnabled( false );
				break;
			case MODE_UPDATE:
				roleList.setEnabled( true );
				addButton.setEnabled( false );
				removeButton.setEnabled( true );
				break;
			default:
				break;
		}

	}

	public void setState( int state ) {

		boolean enabled = ( state == FORM_ENABLED );
		roles.setEnabled( enabled );
		roleList.setEnabled( enabled );
		addButton.setEnabled( enabled );
		removeButton.setEnabled( enabled );

	}

	public void setMessage( RolePanelMessage message ) {
		this.message = message;
	}

	public void refreshPanel() {
		roleModel.clear();
	}

	public void setRoles( List<Role> userRoles ) {
		this.userRoles = userRoles;
	}

	public UserVO getUser() {
		return user;
	}

	public void selectUser( UserVO user ) {
		refreshPanel();
		setState( RolePanel.FORM_ENABLED );
		setUser( user );
		setMode( MODE_ACTIVE );
		reloadRoles();
	}

	public void cancel() {
		refreshPanel();
		setState( RolePanel.FORM_DISABLED );
	}
}
