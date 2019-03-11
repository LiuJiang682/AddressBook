package au.com.pwc.addressbook.command;

import org.apache.log4j.Logger;

import au.com.pwc.addressbook.AddressBook;
import au.com.pwc.addressbook.constants.Constants.Numeric;
import au.com.pwc.addressbook.constants.Constants.Strings;
import au.com.pwc.addressbook.file.AddressBookFile;

public class AddCommand implements Command {
	
	private static final Logger LOGGER = Logger.getLogger(AddCommand.class);
	
	private static final int TWO = 2;

	private final String userEntered;
	
	public AddCommand(final String userEntered) {
		this.userEntered = userEntered;
	}

	public void execute(AddressBook addressBook) {
		
		String paramString = userEntered.substring(CommandEnum.ADD.name().length()).trim();
		String[] params = paramString.split(Strings.COMMA);
		AddressBookFile addressBookFile = null;
		String name = null;
		String phoneNumber = null;
		if (TWO == params.length) {
			addressBookFile = addressBook.getDefaultFile();
			name = params[Numeric.ZERO];
			phoneNumber = params[Numeric.ONE];
		} else {
			addressBookFile = addressBook.getAddressBookFile(params[Numeric.ZERO]);
			if (null == addressBookFile)     {
				String fileName = addressBook.getDataDirectory() + Strings.FILE_PATH_SEPARATOR + params[Numeric.ZERO];
				addressBookFile = addressBook.getFactory().create(fileName);
				addressBook.getAllAddressBooks().put(params[Numeric.ZERO], addressBookFile);
			}
			name = params[Numeric.ONE];
			phoneNumber = params[TWO];
		}
		
		try {
			addressBookFile.add(name, phoneNumber);
		} catch(Exception e) {
			LOGGER.error("Failed to add name: " + name +", phoneNumber: " + phoneNumber + " to address book: " + addressBookFile, e);
		}
	}

}
