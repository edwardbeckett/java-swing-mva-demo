package employeeadmin.controller;

import employeeadmin.ApplicationFacade;
import employeeadmin.model.RoleProxy;
import employeeadmin.model.UserProxy;
import employeeadmin.model.enums.Department;
import employeeadmin.model.enums.Role;
import employeeadmin.model.vo.RoleVO;
import employeeadmin.model.vo.UserVO;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import java.util.Arrays;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/5/2015
 */
public class PrepModelCommand extends SimpleCommand {

	@Override
	public final void execute( final INotification notification ) {
		UserProxy userProxy = new UserProxy();
		RoleProxy roleProxy = new RoleProxy();

		//@formatter:off
		RoleVO larryRoles = new RoleVO( "lstooge", Arrays.asList( Role.PAYROLL, Role.EMP_BENEFITS ) );
		RoleVO moeRoles = new RoleVO( "mstooge",Arrays.asList( Role.INVENTORY, Role.PRODUCTION, Role.SALES, Role.SHIPPING ) );
		RoleVO curlyRoles = new RoleVO( "cstooge", Arrays.asList( Role.ACCT_PAY, Role.ACCT_RCV, Role.GEN_LEDGER ) );

		userProxy.addUser(new UserVO( "lstooge", "Larry", "Stooge", "larry@stooges.com", "ijk456", Department.ACCT.getValue() ) );
		userProxy.addUser(new UserVO( "mstooge", "Moe", "Stooge", "moe@stooges.com", "abc123", Department.PLANT.getValue() ) );
		userProxy.addUser(new UserVO( "cstooge", "Curly", "Stooge", "curly@stooges.com", "xyz987", Department.SALES.getValue() ) );
		//@formatter:on

		roleProxy.addRole( larryRoles );
		roleProxy.addRole( moeRoles );
		roleProxy.addRole( curlyRoles );

		getFacade().registerProxy( userProxy );
		getFacade().registerProxy( roleProxy );

		getFacade().sendNotification( ApplicationFacade.INIT_USERS, userProxy, "UserProxy" );
		getFacade().removeCommand( ApplicationFacade.STARTUP );
	}

}
