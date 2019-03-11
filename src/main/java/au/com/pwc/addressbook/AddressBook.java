package au.com.pwc.addressbook;

import java.io.PrintStream;
import java.util.Map;

import au.com.pwc.addressbook.file.AddressBookFile;
import au.com.pwc.addressbook.file.AddressBookFileFactory;

public interface AddressBook {

	AddressBookFile getDefaultFile();
	AddressBookFile getAddressBookFile(final String fileName);
	AddressBookFileFactory getFactory();
	PrintStream getOutStream();
	Map<String, AddressBookFile> getAllAddressBooks();
	String getDataDirectory();
}
