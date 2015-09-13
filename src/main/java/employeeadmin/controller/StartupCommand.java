package employeeadmin.controller;

import org.puremvc.java.multicore.patterns.command.MacroCommand;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/2/2015
 */
public class StartupCommand extends MacroCommand {

	@Override
	public final void initializeMacroCommand() {

		addSubCommand( new PrepViewCommand() );
		addSubCommand( new PrepModelCommand() );
	}

}
