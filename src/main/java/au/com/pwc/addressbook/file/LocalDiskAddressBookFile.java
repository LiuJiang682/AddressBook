package au.com.pwc.addressbook.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import au.com.pwc.addressbook.constants.Constants.Strings;

public class LocalDiskAddressBookFile implements AddressBookFile {
	
	private File file;
	
	public LocalDiskAddressBookFile(final String fileName) {
		this.file = new File(fileName);
	}

	@Override
	public void add(String name, String phoneNumber) throws IOException {
		try (FileWriter fw = new FileWriter(file, true)) {
			StringBuilder buf = new StringBuilder(name);
			buf.append(",");
			buf.append(phoneNumber);
			buf.append(Strings.LINE_BREAK);
			fw.append(buf.toString());
			fw.close();
		}	
	}

	@Override
	public String getName() {
		return file.getAbsolutePath();
	}
}
