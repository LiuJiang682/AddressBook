package au.com.pwc.addressbook.command.factory;

import au.com.pwc.addressbook.command.Command;

public interface CommandFactory {

	Command constructCommand(final String string);
}
