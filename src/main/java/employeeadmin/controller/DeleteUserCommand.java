package employeeadmin.controller;

import employeeadmin.ApplicationFacade;
import employeeadmin.model.RoleProxy;
import employeeadmin.model.UserProxy;
import employeeadmin.model.vo.UserVO;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/7/2015
 */
public class DeleteUserCommand extends SimpleCommand {

	@Override
	public void execute( INotification note ) {
		UserVO userVO = (UserVO) note.getBody();
		UserProxy userProxy = (UserProxy) getFacade().retrieveProxy( "UserProxy" );
		RoleProxy roleProxy = (RoleProxy) getFacade().retrieveProxy( "RoleProxy" );
		userProxy.deleteUser( userVO );
		roleProxy.deleteRole( userVO );
		sendNotification( ApplicationFacade.USER_DELETED );
	}
}
