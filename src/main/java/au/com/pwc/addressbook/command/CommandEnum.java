package au.com.pwc.addressbook.command;

import org.apache.commons.lang3.StringUtils;

import au.com.pwc.addressbook.constants.Constants.Numeric;
import au.com.pwc.addressbook.constants.Constants.Strings;

public enum CommandEnum {

	ADD,
	PRINT,
	IMPORT,
	UNION,
	DONOTHING;
	
	public static CommandEnum fromString(final String string) {
		if (StringUtils.isNotBlank(string)) {
			if (string.trim().toUpperCase().startsWith(ADD.toString())) {
				return ADD;
			} else if (string.trim().toUpperCase().startsWith(PRINT.toString())) {
				return PRINT;
			} else if(string.trim().toUpperCase().startsWith(IMPORT.toString())) {
				return IMPORT;
			} else if (Numeric.ONE == StringUtils.countMatches(string, Strings.BACK_SLASH)) {
				return UNION;
			}
		}
		return DONOTHING;
	}
}
