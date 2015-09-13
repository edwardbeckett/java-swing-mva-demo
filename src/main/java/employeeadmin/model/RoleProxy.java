package employeeadmin.model;

import employeeadmin.model.enums.Role;
import employeeadmin.model.vo.RoleVO;
import employeeadmin.model.vo.UserVO;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

import java.util.ArrayList;
import java.util.List;

import static employeeadmin.ApplicationFacade.ADD_ROLE_RESULT;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/2/2015
 */
public class RoleProxy extends Proxy {

	public static final String NAME = "RoleProxy";

	private static final List<Role> ROLES = new ArrayList<>();

	public RoleProxy() {
		super( NAME, ROLES );
	}

	@SuppressWarnings( "unchecked" )
	public final List<RoleVO> getRoles() {
		return (List<RoleVO>) this.getData();
	}

	public final void addRole( final RoleVO role ) {
		getRoles().add( role );
	}

	public final void deleteRole( final UserVO user ) {
		for(int i = 0; i < getRoles().size(); i++) {
			if( getRoles().get( i ).username.equals( user.username ) ) {
				getRoles().remove( i );
			}
		}
	}

	public final boolean userHasRole( final UserVO user, final Role role ) {
		boolean hasRole = false;
		for(int i = 0; i < getRoles().size(); i++) {
			if( getRoles().get( i ).username.equals( user.username ) ) {
				List<Role> userRoles = getRoles().get( i ).roles;
				for(Role userRole : userRoles) {
					if( role.equals( userRole ) ) {
						hasRole = true;
						break;
					}
				}
				break;
			}
		}
		return hasRole;
	}

	public final void addRoleToUser( final UserVO user, final Role role ) {
		boolean result = false;
		if( !userHasRole( user, role ) ) {
			for(int i = 0; i < getRoles().size(); i++) {
				if( getRoles().get( i ).username.equals( user.username ) ) {
					List<Role> userRoles = getRoles().get( i ).roles;
					userRoles.add( role );
					result = true;
					break;
				}
			}
		}
		sendNotification( ADD_ROLE_RESULT, result );
	}

	public final void removeRoleFromUser( final UserVO user, final Role role ) {
		if( userHasRole( user, role ) ) {
			for(int i = 0; i < getRoles().size(); i++) {
				if( getRoles().get( i ).username.equals( user.username ) ) {
					List<Role> userRoles = getRoles().get( i ).roles;
					for(int j = 0; j < userRoles.size(); j++) {
						if( userRoles.get( j ).equals( role ) ) {
							userRoles.remove( j );
							break;
						}
					}
					break;
				}
			}
		}
	}

	public final List<Role> getUserRoles( final String username ) {
		List<Role> userRoles = new ArrayList<>();
		for(int i = 0; i < getRoles().size(); i++) {
			if( getRoles().get( i ).username.equals( username ) ) {
				userRoles = getRoles().get( i ).roles;
				break;
			}
		}
		return userRoles;
	}

}
