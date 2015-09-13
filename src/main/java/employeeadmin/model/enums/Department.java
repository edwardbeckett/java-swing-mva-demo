package employeeadmin.model.enums;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/9/2015
 */
public enum Department {

	NONE_SELECTED( "--None Selected--", -1 ),
	ACCT( "Accounting", 0 ),
	SALES( "Sales", 1 ),
	PLANT( "Plant", 2 ),
	SHIPPING( "Shipping", 3 ),
	QC( "Quality Control", 4 );

	private final String value;
	private final Integer ordinal;

	Department( String value, Integer ordinal ) {
		this.value = value;
		this.ordinal = ordinal;
	}

	public static List<Department> list() {
		ArrayList<Department> list = new ArrayList<>();
		list.add( NONE_SELECTED );
		list.add( ACCT );
		list.add( SALES );
		list.add( PLANT );
		return list;
	}

	public String getValue() {
		return value;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString( this );
	}
}
