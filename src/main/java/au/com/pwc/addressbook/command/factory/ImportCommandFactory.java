package au.com.pwc.addressbook.command.factory;

import org.apache.commons.lang3.StringUtils;

import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.DoNothingCommand;
import au.com.pwc.addressbook.command.ImportCommand;
import au.com.pwc.addressbook.constants.Constants.Numeric;
import au.com.pwc.addressbook.constants.Constants.Strings;

public class ImportCommandFactory implements CommandFactory {

	@Override
	public Command constructCommand(String string) {
		int spaceCount = StringUtils.countMatches(string.trim(), Strings.SPACE);
		if (Numeric.ONE == spaceCount) {
			return new ImportCommand(string);
		}
		return new DoNothingCommand();
	}

}
