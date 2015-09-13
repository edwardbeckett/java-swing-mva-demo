package employeeadmin.view;

import employeeadmin.ApplicationFacade;
import employeeadmin.model.RoleProxy;
import employeeadmin.model.enums.Role;
import employeeadmin.model.vo.RoleVO;
import employeeadmin.model.vo.UserVO;
import employeeadmin.view.components.RolePanel;
import employeeadmin.view.interfaces.RolePanelMessage;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/5/2015
 */
public class RolePanelMediator extends Mediator implements RolePanelMessage {

	public static String NAME = "RolePanelMediator";

	private RoleProxy roleProxy;

	public RolePanelMediator( RolePanel viewComponent ) {
		super( NAME, viewComponent );
		setViewComponent( viewComponent );
	}

	@Override
	public void onRegister() {
		getRolePanel().setMessage( this );
		getRolePanel().setState( RolePanel.FORM_DISABLED );
	}

	@Override
	public String[] listNotificationInterests() {
		return new String[]{
			ApplicationFacade.NEW_USER,
			ApplicationFacade.USER_ADDED,
			ApplicationFacade.USER_UPDATED,
			ApplicationFacade.USER_DELETED,
			ApplicationFacade.CANCEL_SELECTED,
			ApplicationFacade.USER_SELECTED,
			ApplicationFacade.ADD_ROLE_RESULT
		};
	}

	public void handleNotification( INotification note ) {
		roleProxy = (RoleProxy) getFacade().retrieveProxy( "RoleProxy" );
		switch( note.getName() ) {
			case ApplicationFacade.NEW_USER:
				break;
			case ApplicationFacade.USER_ADDED:
				getRolePanel().setUser( (UserVO) note.getBody() );
				roleProxy.addRole( new RoleVO( ( (UserVO) note.getBody() ).username, new ArrayList<>() ) );
				break;
			case ApplicationFacade.USER_UPDATED:
				break;
			case ApplicationFacade.USER_DELETED:
				break;
			case ApplicationFacade.CANCEL_SELECTED:
				getRolePanel().cancel();
				break;
			case ApplicationFacade.USER_SELECTED:
				List<Role> roles = roleProxy.getUserRoles( ( (UserVO) note.getBody() ).username );
				getRolePanel().setRoles( roles );
				getRolePanel().selectUser( (UserVO) note.getBody() );
				break;
			case ApplicationFacade.ADD_ROLE_RESULT:
				break;
			default:
				break;
		}
	}

	@Override
	public void onAdd( UserVO user, Role role ) {
		List<Role> roles = roleProxy.getUserRoles( user.username );
		roleProxy.addRoleToUser( user, role );
		getRolePanel().refreshPanel();
		getRolePanel().reloadRoles();
		sendNotification( ApplicationFacade.ADD_ROLE, user );
	}

	@Override
	public void onRemove( UserVO user, Role role ) {
		roleProxy.removeRoleFromUser( user, role );

		getRolePanel().refreshPanel();
		getRolePanel().reloadRoles();

		sendNotification( ApplicationFacade.USER_UPDATED, user );
	}

	public RolePanel getRolePanel() {
		return (RolePanel) viewComponent;
	}

}
