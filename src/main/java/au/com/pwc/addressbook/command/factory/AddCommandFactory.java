package au.com.pwc.addressbook.command.factory;

import org.apache.commons.lang3.StringUtils;

import au.com.pwc.addressbook.command.AddCommand;
import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.DoNothingCommand;
import au.com.pwc.addressbook.constants.Constants.Numeric;
import au.com.pwc.addressbook.constants.Constants.Strings;

public class AddCommandFactory implements CommandFactory {

	private static final int THREE = 3;
	
	public Command constructCommand(String string) {
		int count = StringUtils.countMatches(string, Strings.COMMA);
		if ((Numeric.ZERO < count) 
				&& (count < THREE)) {
			return new AddCommand(string);
		}
		
		return new DoNothingCommand();
	}
}