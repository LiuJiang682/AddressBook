package au.com.pwc.addressbook.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.pwc.addressbook.AddressBook;
import au.com.pwc.addressbook.command.helper.ContactImportHelper;
import au.com.pwc.addressbook.constants.Constants.Numeric;
import au.com.pwc.addressbook.constants.Constants.Strings;

public class ImportCommand implements Command {
	
	private static final Logger LOGGER = Logger.getLogger(ImportCommand.class);
	
	private final String userEntered;

	public ImportCommand(String userEntered) {
		this.userEntered = userEntered;
	}

	@Override
	public void execute(AddressBook addressBook) {
		String[] params = userEntered.split(Strings.SPACE);

		String fileName = params[Numeric.ONE];
		Path path = Paths.get(fileName);
		
		if (Files.exists(path)) {
			try {
				long size = Files.size(path);
				if (size < Numeric.MAX_SIZE) {
					List<String> contents = Files.readAllLines(path);			
					new ContactImportHelper(fileName, contents, addressBook).doImport();
				} else {
					LOGGER.error("File " + fileName + " is too big!");
				}
			}
			catch (Exception e) {
				LOGGER.error("Cannot import file " + fileName + " due to: " + e.getMessage(), e);
				throw new RuntimeException(e);
			}
		} else {
			String errorMessage = "File " + fileName + " does not exist!";
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
	} 
}
