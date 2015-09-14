package employeeadmin;

import api.Utils.GBC;
import employeeadmin.view.components.RolePanel;
import employeeadmin.view.components.UserForm;
import employeeadmin.view.components.UserList;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.GridBagLayout;

/**
 * @author Edward Beckett
 */
public class EmployeeAdmin extends JFrame implements Runnable {

	private UserList userList;
	private UserForm userForm;
	private RolePanel rolePanel;

	public EmployeeAdmin() {
		build();
	}

	private void build() {
		setTitle( "Java Swing Model View Adapter (MVA) Demo" );
		setLayout( new GridBagLayout() );
		setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		setResizable( false );
		buildComponents();
		startup();
		pack();
		setLocationRelativeTo( null );
		setVisible( true );
	}

	private void buildComponents() {

		JPanel basePanel = new JPanel( new GridBagLayout() );

		userList = new UserList();
		userForm = new UserForm();
		rolePanel = new RolePanel();

		GBC userListConstraints = new GBC( 0, 0, 2, 1 )
			.setWeight( 1, 1 ).setAnchor( GBC.NORTH ).setFill( GBC.BOTH ).setInsets( 0, 0, 0, 0 ).setIpad( 0, 0 );
		basePanel.add( userList, userListConstraints );

		GBC userFormConstraints = new GBC( 0, 1, 1, 1 ).setWeight( 1, .5 )
			.setAnchor( GBC.LINE_START )
			.setFill( GBC.BOTH )
			.setInsets(
				0, 0, 0, 0 )
			.setIpad( 0, 0 );
		basePanel.add( userForm, userFormConstraints );

		GBC rolePanelConstraints = new GBC( 1, 1, 1, 1 )
			.setWeight( 1, .5 )
			.setAnchor( GBC.LINE_END )
			.setFill( GBC.BOTH )
			.setInsets( 0, 0, 0, 0 )
			.setIpad( 0, 0 );
		basePanel.add( rolePanel, rolePanelConstraints );

		GBC basePanelConstraints = new GBC( 0, 0, 1, 1 ).setWeight( 1, 1 )
			.setFill( GBC.BOTH )
			.setAnchor( GBC.CENTER )
			.setInsets( 0, 0, 0, 0 )
			.setIpad( 0, 0 );
		add( basePanel, basePanelConstraints );
	}

	protected void startup() {
		ApplicationFacade.getInstance().startup( this );
	}

	public static void main( String... args ) {
		try {
			UIManager.setLookAndFeel( new NimbusLookAndFeel() );
		} catch( UnsupportedLookAndFeelException e ) {
			e.printStackTrace();
		}
		new EmployeeAdmin().run();
	}

	@Override
	public void run() {
		SwingUtilities.invokeLater( () -> {
			//go.
		} );
	}

	public UserList getUserList() {
		return userList;
	}

	public UserForm getUserForm() {
		return userForm;
	}

	public RolePanel getRolePanel() {
		return rolePanel;
	}

}
