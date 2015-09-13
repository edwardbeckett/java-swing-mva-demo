package employeeadmin.controller;

import employeeadmin.EmployeeAdmin;
import employeeadmin.view.RolePanelMediator;
import employeeadmin.view.UserFormMediator;
import employeeadmin.view.UserListMediator;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/5/2015
 */
public class PrepViewCommand extends SimpleCommand {

	@Override
	public final void execute( INotification notification ) {
		EmployeeAdmin employeeAdmin = (EmployeeAdmin) notification.getBody();

		getFacade().registerMediator( new UserListMediator( employeeAdmin.getUserList() ) );
		getFacade().registerMediator( new UserFormMediator( employeeAdmin.getUserForm() ) );
		getFacade().registerMediator( new RolePanelMediator( employeeAdmin.getRolePanel() ) );

	}

}
