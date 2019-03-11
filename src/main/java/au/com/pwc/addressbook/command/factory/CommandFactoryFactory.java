package au.com.pwc.addressbook.command.factory;

import au.com.pwc.addressbook.command.Command;

public interface CommandFactoryFactory {

	Command getNextCommand();
	
	void closeInput();
}
