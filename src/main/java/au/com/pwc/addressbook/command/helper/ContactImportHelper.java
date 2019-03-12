package au.com.pwc.addressbook.command.helper;

import java.util.List;

import au.com.pwc.addressbook.AddressBook;
import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.CommandEnum;
import au.com.pwc.addressbook.command.factory.AddCommandFactory;
import au.com.pwc.addressbook.constants.Constants.Strings;

public class ContactImportHelper {

	private static final int NOT_FOUND = -1;
	
	private final String fileName;
	private final List<String> contents;
	private final AddressBook addressBook;
	
	public ContactImportHelper(final String fileName, final List<String> contents, final AddressBook addressBook) {
		this.fileName = fileName;
		this.contents = contents;
		this.addressBook = addressBook;
	}

	public void doImport() {
		String prefix = getCommandPrefix();
		AddCommandFactory factory = new AddCommandFactory();
		
		contents.stream()
			.forEach(contact -> {
				String commandString = prefix + contact;
				Command command = factory.constructCommand(commandString);
				command.execute(addressBook);
			});
		
	}

	protected final String getCommandPrefix() {
		int index = fileName.lastIndexOf(Strings.FILE_PATH_SEPARATOR);
		StringBuilder prefix = new StringBuilder(CommandEnum.ADD.name());
		prefix.append(Strings.SPACE);
		
		if (NOT_FOUND == index) {
			 prefix.append(fileName);
		} else {
			String shortFileName = fileName.substring(++index);
			prefix.append(shortFileName);
		}
		prefix.append(Strings.COMMA);
		String prefixString = prefix.toString();
		return prefixString;
	}
}
