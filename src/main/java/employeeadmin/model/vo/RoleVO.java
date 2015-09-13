package employeeadmin.model.vo;

import employeeadmin.model.enums.Role;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/2/2015
 */
public class RoleVO implements Serializable {

	public static final long serialVersionUID = 1L;

	public String username;
	public List<Role> roles = new ArrayList<>();

	public RoleVO( final String username, final List<Role> roles ) {
		if( username != null ) {
			this.username = username;
		}
		if( roles != null ) {
			this.roles = new ArrayList<>( roles );
		}
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) { return true; }

		if( !( o instanceof RoleVO ) ) { return false; }

		RoleVO roleVO = (RoleVO) o;

		return new EqualsBuilder()
			.append( username, roleVO.username )
			.append( roles, roleVO.roles )
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 17, 37 )
			.append( username )
			.append( roles )
			.toHashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString( this, ToStringStyle.JSON_STYLE );
	}
}