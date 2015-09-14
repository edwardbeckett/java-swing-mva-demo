package employeeadmin.view.components;

import api.Utils.BorderBuilder;
import api.Utils.GBC;
import api.Utils.UnhandledException;
import employeeadmin.model.vo.UserVO;
import employeeadmin.view.interfaces.UserListMessage;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/3/2015
 */
public class UserList extends JPanel implements ActionListener, ListSelectionListener {

	public static final String NEW_USER = "New";
	public static final String NEW_USER_LABEL = "New";
	public static final String DELETE_USER = "Delete";
	public static final String DELETE_USER_LABEL = "Delete";

	UserListMessage message;

	private UserTable userTable;
	private JPanel buttonPanel;
	private JButton newUserButton;
	private JButton deleteUserButton;
	private JScrollPane userPane;
	private JTableHeader header;
	private ArrayList<UserVO> users;
	private UserVO user;

	public UserList() {
		setLayout( new GridBagLayout() );
		setBorder( new BorderBuilder( "Users", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, 2, Font.BOLD, 12 ) );
		initComponents();
	}

	public class UserTable extends JTable {

		public UserTable() {
			super( new UserListModel() );
			setBackground( new Color( 235, 235, 235 ) );
		}

		@Override
		public UserListModel getModel() {
			return (UserListModel) super.getModel();
		}
	}

	public static class UserListModel extends AbstractTableModel {

		private final String[] columnName = { "Username", "First Name", "Last Name", "Email", "Department" };
		private final Class<?>[] columnType = { String.class, String.class, String.class, String.class, String
			.class };

		private ArrayList<UserVO> users;

		private static final int COLUMN_USERNAME = 0;
		private static final int COLUMN_FIRST_NAME = 1;
		private static final int COLUMN_LAST_NAME = 2;
		private static final int COLUMN_EMAIL = 3;
		private static final int COLUMN_DEPARTMENT = 4;

		public UserListModel() {}

		public void reload( ArrayList<UserVO> users ) {
			this.users = users;
			fireTableDataChanged();
		}

		@Override
		public Class<?> getColumnClass( int column ) {
			return columnType[column];
		}

		@Override
		public int getColumnCount() {
			return columnName.length;
		}

		@Override
		public String getColumnName( int column ) {
			return columnName[column];
		}

		@Override
		public int getRowCount() {
			if( users == null ) {
				return 0;
			}
			return users.size();
		}

		@Override
		public Object getValueAt( int row, int column ) {
			if( users.get( row ) != null ) {
				UserVO user = users.get( row );
				switch( column ) {
					case COLUMN_USERNAME:
						if( user.username != null ) {
							return user.username;
						}
					case COLUMN_FIRST_NAME:
						if( user.firstName != null ) {
							return user.firstName;
						}
					case COLUMN_LAST_NAME:
						if( user.lastName != null ) {
							return user.lastName;
						}
					case COLUMN_EMAIL:
						if( user.email != null ) {
							return user.email;
						}
					case COLUMN_DEPARTMENT:
						if( user.department != null ) {
							return user.department;
						}
					default:
						return null;
				}
			}
			return null;
		}

		@Override
		public boolean isCellEditable( int rowIndex, int columnIndex ) {
			return false;
		}
	}

	private void initComponents() {

		userTable = new UserTable();
		userTable.getSelectionModel().addListSelectionListener( this );

		buttonPanel = new JPanel();

		deleteUserButton = new JButton();
		deleteUserButton.addActionListener( this );

		newUserButton = new JButton();
		newUserButton.addActionListener( this );

		userPane = new JScrollPane();

		userPane.getViewport().setBackground( new Color( 241, 241, 241 ) );
		userTable.getColumnModel().getColumn( 0 ).setPreferredWidth( 100 );
		userTable.getColumnModel().getColumn( 1 ).setPreferredWidth( 100 );
		userTable.getColumnModel().getColumn( 2 ).setPreferredWidth( 100 );
		userTable.getColumnModel().getColumn( 3 ).setPreferredWidth( 100 );
		userTable.getColumnModel().getColumn( 4 ).setPreferredWidth( 100 );

		userTable.setRowHeight( 16 );
		userTable.setShowGrid( true );
		userTable.setVisible( true );
		userTable.setFillsViewportHeight( true );
		userTable.setGridColor( Color.LIGHT_GRAY );
		userTable.setShowHorizontalLines( true );
		userTable.setShowVerticalLines( true );
		userTable.setFont( new Font( "Segoe UI", Font.PLAIN, 12 ) );
		userTable.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
		userTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

		header = userTable.getTableHeader();
		header.setFont( new Font( "Segoe UI", Font.BOLD, 12 ) );

		userPane.setPreferredSize( new Dimension( 600, 200 ) );
		userPane.setViewportView( userTable );

		//@formatter:off
		GBC userPaneConstraints = new GBC( 0, 0, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.CENTER ).setFill( GBC.BOTH ) .setInsets(
			0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( userPane, userPaneConstraints );

		GBC buttonPanelConstraints = new GBC( 0, 1, 1, 1 ).setWeight( 1, 1 ).setAnchor( GBC.LINE_START ).setFill( GBC.BOTH ) .setInsets( 0, 0, 0, 0 ) .setIpad( 0, 0 );
		add( buttonPanel, buttonPanelConstraints );
		//@formatter:on

		buttonPanel.setLayout( new FlowLayout( FlowLayout.LEADING ) );
		newUserButton.setText( NEW_USER_LABEL );
		newUserButton.setToolTipText( "Click to add a new user." );
		deleteUserButton.setText( DELETE_USER_LABEL );
		deleteUserButton.setToolTipText( "Click to delete the selected user" );
		buttonPanel.add( newUserButton );
		buttonPanel.add( deleteUserButton );
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		switch( e.getActionCommand() ) {
			case NEW_USER:
				cancelSelection();
				message.onNew();
				break;
			case DELETE_USER:
				if( getUser() != null ) {
					if( JOptionPane.showConfirmDialog( null, "Delete User?", "Delete User?",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE ) == 0 ) {
						message.onDelete( user );
					}
				}
				break;
			default:
				throw new UnhandledException( "Error Unhandled event [" + e.getID() + "]" );
		}

	}

	@Override
	public void valueChanged( ListSelectionEvent e ) {
		if( userTable.getSelectedRow() > -1 && !e.getValueIsAdjusting() ) {
			UserVO user = getUsers().get( userTable.getSelectedRow() );
			setUser( user );
			message.onSelect( user );
		}
	}

	public void cancelSelection() {
		this.user = null;
		userTable.getSelectionModel().clearSelection();
		deleteUserButton.setEnabled( false );
	}

	public void reloadUsers( ArrayList<UserVO> users ) {
		this.users = users;
		userTable.getModel().reload( users );
		cancelSelection();
	}

	public void addUser( UserVO user ) {
		getUsers().add( user );
		userTable.getModel().reload( users );
	}

	public void setUser( UserVO user ) {
		this.user = user;
		deleteUserButton.setEnabled( true );
	}

	public UserVO getUser() {
		return ( this.user == null ? new UserVO() : this.user );
	}

	public ArrayList<UserVO> getUsers() {
		return users;
	}

	public void setMessage( UserListMessage message ) {
		this.message = message;
	}
}