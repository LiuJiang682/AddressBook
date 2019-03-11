package au.com.pwc.addressbook.file;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import au.com.pwc.addressbook.constants.Constants.Strings;

public class LocalDiskAddressBookFileTest {

	@Test
	public void shouldReturnFile() {
		//Given
		String fileName = "abc";
		//When
		LocalDiskAddressBookFile localDiskAddressBookFile = new LocalDiskAddressBookFile(fileName);
		//Then
		assertThat(localDiskAddressBookFile, is(notNullValue()));
	}
	
	@Test
	public void shouldWriteToFile() throws IOException {
		//Given
		String fileName = "abc";

		try {
			File file = new File(fileName);
			if (file.exists())
				file.delete();
			LocalDiskAddressBookFile localDiskAddressBookFile = new LocalDiskAddressBookFile(fileName);
			//When
			localDiskAddressBookFile.add("Paul", "123456");
			//Then
			String data = new String(Files.readAllBytes(Paths.get(fileName)));
			String expectedString = "Paul,123456" + Strings.LINE_BREAK;
			assertThat(data, is(equalTo(expectedString)));
		}
		finally {
			new File(fileName).delete();
		}
	}
}
