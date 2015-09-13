package employeeadmin.view;

import employeeadmin.ApplicationFacade;
import employeeadmin.model.UserProxy;
import employeeadmin.model.vo.UserVO;
import employeeadmin.view.components.UserList;
import employeeadmin.view.interfaces.UserListMessage;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

import java.util.ArrayList;


/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/4/2015
 */
public class UserListMediator extends Mediator implements UserListMessage {

	public static final String NAME = "UserListMediator";

	private UserProxy userProxy;

	public UserListMediator( UserList viewComponent ) {
		super( NAME, viewComponent );
	}

	@Override
	public void onRegister() {
		getUserList().setMessage( this );
	}

	public String[] listNotificationInterests() {
		return new String[]{
			ApplicationFacade.INIT_USERS,
			ApplicationFacade.USER_ADDED,
			ApplicationFacade.USER_UPDATED,
			ApplicationFacade.USER_DELETED,
			ApplicationFacade.USER_SELECTED,
			ApplicationFacade.CANCEL_SELECTED
		};
	}

	@Override
	public void handleNotification( final INotification note ) {
		userProxy = (UserProxy) getFacade().retrieveProxy( "UserProxy" );
		final ArrayList<UserVO> users = userProxy.getUsers();
		switch( note.getName() ) {
			case ApplicationFacade.INIT_USERS:
				getUserList().reloadUsers( users );
				break;
			case ApplicationFacade.USER_ADDED:
				getUserList().reloadUsers( users );
				getUserList().addUser( (UserVO) note.getBody() );
				break;
			case ApplicationFacade.USER_UPDATED:
				getUserList().reloadUsers( users );
				break;
			case ApplicationFacade.USER_SELECTED:
				getUserList().setUser( userProxy.getUser( (UserVO) note.getBody() ) );
				break;
			case ApplicationFacade.CANCEL_SELECTED:
				getUserList().cancelSelection();
				break;
			case ApplicationFacade.USER_DELETED:
				getUserList().reloadUsers( users );
				getUserList().cancelSelection();
				break;
		}
	}

	@Override
	public void onNew() {
		sendNotification( ApplicationFacade.NEW_USER );
	}

	@Override
	public void onSelect( UserVO user ) {
		sendNotification( ApplicationFacade.USER_SELECTED, user );
	}

	@Override
	public void onDelete( UserVO user ) {
		sendNotification( ApplicationFacade.DELETE_USER, user );
	}

	private UserList getUserList() {
		return (UserList) viewComponent;
	}

}