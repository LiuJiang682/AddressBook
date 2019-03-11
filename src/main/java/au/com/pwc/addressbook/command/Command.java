package au.com.pwc.addressbook.command;

import au.com.pwc.addressbook.AddressBook;

public interface Command {

	void execute(final AddressBook addressBook);
}
