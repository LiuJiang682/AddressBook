package au.com.pwc.addressbook.file;

import org.apache.commons.lang3.StringUtils;

public class LocalDiskAddressBookFileFactory implements AddressBookFileFactory {

	@Override
	public AddressBookFile create(String fileName) {
		if (StringUtils.isNotBlank(fileName)) {
			return new LocalDiskAddressBookFile(fileName);
		}
		
		throw new IllegalArgumentException("File name cannot be null or empty!");
	}

	
}
