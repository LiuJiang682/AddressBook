package au.com.pwc.addressbook.command.helper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import au.com.pwc.addressbook.constants.Constants.Numeric;
import au.com.pwc.addressbook.constants.Constants.Strings;
import au.com.pwc.addressbook.file.AddressBookFile;

public class ContactSplitHelper {

	private static final Logger LOGGER = Logger.getLogger(ContactSplitHelper.class);
	
	private final AddressBookFile bookFile;
	
	public ContactSplitHelper(AddressBookFile bookFile) {
		this.bookFile = bookFile;
	}

	public List<String> getNameList() {
		Path path = Paths.get(bookFile.getName());
		try {
			long size = Files.size(path);
			if (size < Numeric.MAX_SIZE) {
				List<String> contents = Files.readAllLines(path);
				return extractNames(contents);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return null;
	}

	protected final List<String> extractNames(final List<String> contents) {
		List<String> nameList = contents.stream()
				.map(content -> content.split(Strings.COMMA)[Numeric.ZERO])
				.collect(Collectors.toList());
		return nameList;
	}
}
