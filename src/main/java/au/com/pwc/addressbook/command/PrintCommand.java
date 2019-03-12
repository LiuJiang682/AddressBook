package au.com.pwc.addressbook.command;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import au.com.pwc.addressbook.AddressBook;
import au.com.pwc.addressbook.comparator.ContactComparator;
import au.com.pwc.addressbook.constants.Constants.Numeric;
import au.com.pwc.addressbook.constants.Constants.Strings;
import au.com.pwc.addressbook.file.AddressBookFile;

public class PrintCommand implements Command {
	
	private static final Logger LOGGER = Logger.getLogger(PrintCommand.class);
	
	private final String userEntered;
	
	public PrintCommand(final String userEntered) {
		this.userEntered = userEntered;
	}
	
	@Override
	public void execute(AddressBook addressBook) {
		AddressBookFile addressBookFile = null;
		if (userEntered.contains(Strings.SPACE)){
			String[] params = userEntered.split(Strings.SPACE);
			addressBookFile = addressBook.getAddressBookFile(params[Numeric.ONE]);
		} else {
			addressBookFile = addressBook.getDefaultFile();
		}
		
		PrintStream outStream = addressBook.getOutStream();
		try {
			Path path = Paths.get(addressBookFile.getName());
			long size = Files.size(path);
			if (size < Numeric.MAX_SIZE) {
				List<String> contentLines = Files.readAllLines(path);
				TreeSet<String> contacts = new TreeSet(new ContactComparator());
				contacts.addAll(contentLines);
				contacts.forEach(contact -> outStream.println(contact));
			} else {
				LOGGER.error("Address book " + addressBookFile.getName() + " is too big to print!");
			}
		}
		catch (Exception e) {
			LOGGER.error("Failed to print " + addressBookFile.getName(), e);
		}
		
	}

}
