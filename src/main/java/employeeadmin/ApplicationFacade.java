package employeeadmin;

import employeeadmin.controller.AddRoleResultCommand;
import employeeadmin.controller.DeleteUserCommand;
import employeeadmin.controller.StartupCommand;
import org.puremvc.java.multicore.patterns.facade.Facade;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/7/2015
 */
public class ApplicationFacade extends Facade {

	public static final String STARTUP = "startup";
	public static final String NEW_USER = "newUser";
	public static final String DELETE_USER = "deleteUser";
	public static final String CANCEL_SELECTED = "cancelSelected";
	public static final String USER_SELECTED = "userSelected";
	public static final String USER_ADDED = "userAdded";
	public static final String USER_UPDATED = "userUpdated";
	public static final String USER_DELETED = "userDeleted";
	public static final String INIT_USERS = "initUsers";
	public static final String ADD_ROLE = "addRole";
	public static final String ADD_ROLE_RESULT = "addRoleResult";
	private static ApplicationFacade instance;

	public static final String NAME = "ApplicationFacade";

	public static ApplicationFacade getInstance() {
		if( instance == null ) {
			instance = new ApplicationFacade();
		}
		return instance;
	}

	protected ApplicationFacade() {
		super( NAME );
	}

	public void startup( Object app ) {
		sendNotification( STARTUP, app );

	}

	@Override
	protected void initializeController() {
		super.initializeController();
		registerCommand( STARTUP, new StartupCommand() );
		registerCommand( DELETE_USER, new DeleteUserCommand() );
		registerCommand( ADD_ROLE_RESULT, new AddRoleResultCommand() );

	}

}