package employeeadmin.model.vo;

import employeeadmin.model.enums.Department;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/2/2015
 */
public class UserVO implements Serializable {

	public static final long serialVersionUID = 1L;

	public String username;
	public String firstName;
	public String lastName;
	public String email;
	public String password;
	public String department;

	public UserVO() {
	}

	public UserVO( final String userName, final String firstName, final String lastName, final String email,
		final String password, final String department ) {
		this.username = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.department = department != null ? department : Department.NONE_SELECTED.getValue();

	}

	public final boolean isValid() {
		return
			( !"".equals( username ) ) &&
			( !"".equals( firstName ) ) &&
			( !"".equals( lastName ) ) &&
			( !"".equals( email ) ) &&
			( !"".equals( password ) ) &&
			( !Department.NONE_SELECTED.getValue().equals( department ) );
	}

	@Override
	public boolean equals( Object o ) {
		if( this == o ) { return true; }

		if( !( o instanceof UserVO ) ) { return false; }

		UserVO userVO = (UserVO) o;

		return new EqualsBuilder()
			.append( username, userVO.username )
			.append( firstName, userVO.firstName )
			.append( lastName, userVO.lastName )
			.append( email, userVO.email )
			.append( password, userVO.password )
			.append( department, userVO.department )
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 17, 37 )
			.append( username )
			.append( firstName )
			.append( lastName )
			.append( email )
			.append( password )
			.append( department )
			.toHashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString( this, ToStringStyle.JSON_STYLE );
	}
}