package employeeadmin.controller;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import javax.swing.*;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/8/2015
 */
public class AddRoleResultCommand extends SimpleCommand {

	boolean result;

	@Override
	public void execute( INotification note ) {
		if( result == (boolean) note.getBody() ) {
			JOptionPane.showMessageDialog( null, "User already has this role", "User Role Exists",
				JOptionPane.ERROR_MESSAGE );
		}
	}
}
