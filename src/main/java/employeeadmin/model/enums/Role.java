package employeeadmin.model.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/9/2015
 */
public enum Role {

	/**
	 * Roles.
	 */
	NONE_SELECTED( "--None Selected--", -1 ),
	ADMIN( "Administrator", 0 ),
	ACCT_PAY( "Accounts Payable", 1 ),
	ACCT_RCV( "Accounts Receivable", 2 ),
	EMP_BENEFITS( "Employee Benefits", 3 ),
	GEN_LEDGER( "General Ledger", 4 ),
	PAYROLL( "Payroll", 5 ),
	INVENTORY( "Inventory", 6 ),
	PRODUCTION( "Production", 7 ),
	QUALITY_CTL( "Quality Control", 8 ),
	SALES( "Sales", 9 ),
	ORDERS( "Orders", 10 ),
	CUSTOMERS( "Customers", 11 ),
	SHIPPING( "Shipping", 12 ),
	RETURNS( "Returns", 13 );

	private final Integer ordinal;
	private final String value;

	Role( String value, Integer ordinal ) {
		this.value = value;
		this.ordinal = ordinal;
	}

	public static List<Role> list() {
		ArrayList<Role> list = new ArrayList<>();
		list.add( NONE_SELECTED );
		list.add( ADMIN );
		list.add( ACCT_PAY );
		list.add( ACCT_RCV );
		list.add( EMP_BENEFITS );
		list.add( GEN_LEDGER );
		list.add( PAYROLL );
		list.add( INVENTORY );
		list.add( PRODUCTION );
		list.add( QUALITY_CTL );
		list.add( SALES );
		list.add( ORDERS );
		list.add( CUSTOMERS );
		list.add( SHIPPING );
		list.add( RETURNS );

		return list;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public String getValue() {
		return value;
	}
}
