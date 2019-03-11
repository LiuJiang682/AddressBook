package au.com.pwc.addressbook.loader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.log4j.Logger;

import au.com.pwc.addressbook.constants.Constants.Strings;
import au.com.pwc.addressbook.file.AddressBookFile;
import au.com.pwc.addressbook.file.AddressBookFileFactory;

public class AddressBookLoader {
	
	private static final Logger LOGGER = Logger.getLogger(AddressBookLoader.class);
	
	private final Map<String, AddressBookFile> addressBooks;
	private final AddressBookFileFactory addressBookFileFactory;
	private final String dataDir;
	
	public AddressBookLoader(final Map<String, AddressBookFile> addressBooks,
			final AddressBookFileFactory addressBookFileFactory, final String dataDir) {
		this.addressBooks = addressBooks;
		this.addressBookFileFactory = addressBookFileFactory;
		this.dataDir = dataDir;
	}
	
	public void load() {
		AddressBookFile defaultFile = addressBookFileFactory.create(dataDir + Strings.FILE_PATH_SEPARATOR + Strings.DEFAULT);
		addressBooks.put(Strings.DEFAULT, defaultFile);
		Path path = Paths.get(dataDir);
		try {
			Files.list(path)
				.forEach(filePath -> {
					String fileName = filePath.toString();
					AddressBookFile addressBookFile = addressBookFileFactory.create(fileName);
					int index = fileName.lastIndexOf(Strings.FILE_PATH_SEPARATOR);
					String shortfileName = fileName.substring(index);
					addressBooks.putIfAbsent(shortfileName, addressBookFile);
				});
		} catch (IOException e) {
			LOGGER.error("Failed to load address book file due to: " + e.getMessage(), e);
			
		}
	}
}
