package au.com.pwc.addressbook;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.factory.CommandFactoryFactory;
import au.com.pwc.addressbook.command.factory.DefaultCommandFactoryFactory;
import au.com.pwc.addressbook.constants.Constants.Strings;
import au.com.pwc.addressbook.file.AddressBookFile;
import au.com.pwc.addressbook.file.AddressBookFileFactory;
import au.com.pwc.addressbook.file.LocalDiskAddressBookFileFactory;
import au.com.pwc.addressbook.loader.AddressBookLoader;

public class PwcAddressBook implements AddressBook {

	private static final Logger LOGGER = Logger.getLogger(PwcAddressBook.class);

	private Map<String, AddressBookFile> addressBooks;
	private CommandFactoryFactory commandFactory;
	private AddressBookFileFactory addressBookFileFactory;
	private PrintStream outStream;
	private String dataDir;

	public PwcAddressBook() {
		this(new HashMap<String, AddressBookFile>(), new DefaultCommandFactoryFactory(), new LocalDiskAddressBookFileFactory(), 
				System.out, "src/main/resources/data");
	}
	
	public PwcAddressBook(final Map<String, AddressBookFile> addressBooks, final CommandFactoryFactory commandFactory, 
			final AddressBookFileFactory addressBookFileFactory, final PrintStream outStream, final String dataDir) {
		this.addressBooks = addressBooks;
		this.commandFactory = commandFactory;
		this.addressBookFileFactory = addressBookFileFactory;
		this.outStream = outStream;
		this.dataDir = dataDir;
		new AddressBookLoader(addressBooks, addressBookFileFactory, dataDir).load();
		
	}
	
	public void run() {
		LOGGER.debug("Running...");
		
		Command command = null;
		while (null != (command = commandFactory.getNextCommand())) {
			command.execute(this);
		}
		
		commandFactory.closeInput();
	}

	public static void main(String[] args) {
		new PwcAddressBook().run();
	}

	@Override
	public AddressBookFile getDefaultFile() {
		return addressBooks.get(Strings.DEFAULT);
	}

	@Override
	public AddressBookFile getAddressBookFile(String fileName) {
		return addressBooks.get(fileName);
	}

	@Override
	public AddressBookFileFactory getFactory() {
		return addressBookFileFactory;
	}

	@Override
	public PrintStream getOutStream() {
		return outStream;
	}

	@Override
	public Map<String, AddressBookFile> getAllAddressBooks() {
		return this.addressBooks;
	}

	@Override
	public String getDataDirectory() {
		return dataDir;
	}
}
