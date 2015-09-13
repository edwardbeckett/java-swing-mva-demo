package employeeadmin.view;

import employeeadmin.ApplicationFacade;
import employeeadmin.model.vo.UserVO;
import employeeadmin.view.components.UserForm;
import employeeadmin.view.interfaces.UserFormMessage;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/5/2015
 */
public class UserFormMediator extends Mediator implements UserFormMessage {

	public static String NAME = "UserFormMediator";

	public UserFormMediator( UserForm viewComponent ) {
		super( NAME, viewComponent );
		setViewComponent( viewComponent );
	}

	public String[] listNotificationInterests() {
		return new String[]{
			ApplicationFacade.NEW_USER,
			ApplicationFacade.USER_SELECTED,
			ApplicationFacade.USER_DELETED
		};
	}

	@Override
	public void onRegister() {
		getUserForm().setMessage( this );
		getUserForm().setMode( UserForm.MODE_ADD );
		getUserForm().setState( UserForm.FORM_DISABLED );
	}

	@Override
	public void handleNotification( INotification note ) {

		switch( note.getName() ) {

			case ApplicationFacade.NEW_USER:
				getUserForm().newUser();
				break;
			case ApplicationFacade.USER_SELECTED:
				getUserForm().selectUser( (UserVO) note.getBody() );
				break;
			case ApplicationFacade.USER_DELETED:
				getUserForm().setState( UserForm.FORM_DISABLED );
				getUserForm().clearForm();
				break;
		}
	}

	@Override
	public void onAdd( UserVO user ) {
		sendNotification( ApplicationFacade.USER_ADDED, user );
	}

	@Override
	public void onUpdate( UserVO user ) {
		sendNotification( ApplicationFacade.USER_UPDATED, user );
	}

	@Override
	public void onCancel() {
		sendNotification( ApplicationFacade.CANCEL_SELECTED );
	}

	private UserForm getUserForm() {
		return (UserForm) viewComponent;
	}
}
