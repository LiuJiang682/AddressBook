package au.com.pwc.addressbook.command.factory;

import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.DoNothingCommand;
import au.com.pwc.addressbook.command.UnionCommand;
import au.com.pwc.addressbook.constants.Constants.Numeric;
import au.com.pwc.addressbook.constants.Constants.Strings;

public class UnionCommandFactory implements CommandFactory {

	@Override
	public Command constructCommand(String string) {
		int index = string.indexOf(Strings.BACK_SLASH);
		if ((Numeric.ZERO < index) 
			&& (index < (string.length() - Numeric.ONE))) {
			return new UnionCommand(string);
		}
		
		return new DoNothingCommand();
	}

}
