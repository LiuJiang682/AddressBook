package au.com.pwc.addressbook.command.factory;

import org.apache.commons.lang3.StringUtils;

import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.DoNothingCommand;
import au.com.pwc.addressbook.command.PrintCommand;
import au.com.pwc.addressbook.constants.Constants.Numeric;
import au.com.pwc.addressbook.constants.Constants.Strings;

public class PrintCommandFactory implements CommandFactory {

	@Override
	public Command constructCommand(String string) {
		int count = StringUtils.countMatches(string, Strings.COMMA);
		if (count < Numeric.ONE) {
			return new PrintCommand(string);
		}
		return new DoNothingCommand();
	}

}
