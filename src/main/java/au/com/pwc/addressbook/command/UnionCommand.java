package au.com.pwc.addressbook.command;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import au.com.pwc.addressbook.AddressBook;
import au.com.pwc.addressbook.command.helper.ContactSplitHelper;
import au.com.pwc.addressbook.constants.Constants.Numeric;
import au.com.pwc.addressbook.constants.Constants.Strings;
import au.com.pwc.addressbook.file.AddressBookFile;

public class UnionCommand implements Command {
	
	private final String userEntered;

	public UnionCommand(final String userEntered) {
		this.userEntered = userEntered;
	}

	@Override
	public void execute(AddressBook addressBook) {
		String[] addressBooks = userEntered.split(Pattern.quote(Strings.BACK_SLASH));
		AddressBookFile firstBook = addressBook.getAddressBookFile(addressBooks[Numeric.ZERO]);
		AddressBookFile secondBook = addressBook.getAddressBookFile(addressBooks[Numeric.ONE]);
		if (null == firstBook) {
			throw new IllegalArgumentException("Address book: " + addressBooks[Numeric.ZERO] + " does not exist!");
		}
		if (null == secondBook) {
			throw new IllegalArgumentException("Address book: " + addressBooks[Numeric.ONE] + " does not exist!");
		}
		List<String> firstList = new ContactSplitHelper(firstBook).getNameList();
		List<String> secondList = new ContactSplitHelper(secondBook).getNameList();
		if ((null != firstList) 
			&& (null != secondList)) {
			List<String> uniqueNames = extractUniqueNames(firstList, secondList);
			PrintStream outStream = addressBook.getOutStream();
			uniqueNames.stream()
				.forEach(name -> outStream.println(name));
		}
	}

	protected final List<String> extractUniqueNames(final List<String> first, final List<String> second) {
		List<String> unique = new ArrayList<>(first);
		unique.removeAll(second);
		
		List<String> another = new ArrayList<>(second);
		another.removeAll(first);
		unique.addAll(another);
		return unique;
	}
}
