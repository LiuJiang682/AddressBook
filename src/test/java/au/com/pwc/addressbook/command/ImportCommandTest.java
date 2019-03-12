package au.com.pwc.addressbook.command;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import au.com.pwc.addressbook.AddressBook;
import au.com.pwc.addressbook.command.helper.ContactImportHelper;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ImportCommand.class})
public class ImportCommandTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenFileDoNotExist() {
		//Given
		String string = "import xxx";
		ImportCommand importCommand = new ImportCommand(string);
		AddressBook addressBook = Mockito.mock(AddressBook.class);
		//When
		importCommand.execute(addressBook);
		fail("Program reached unexpected point!");
	}
	
	@Test
	public void shouldCallContactImportHelper() throws Exception {
		//Given
		String string = "import abc";
		ImportCommand importCommand = new ImportCommand(string);
		Path path = Paths.get("abc");
		try {
			createFile(path);
			AddressBook addressBook = Mockito.mock(AddressBook.class);
			ContactImportHelper mockHelper = Mockito.mock(ContactImportHelper.class);
			PowerMockito.whenNew(ContactImportHelper.class).withArguments(Matchers.eq("abc"), 
					Matchers.anyList(), Matchers.eq(addressBook)).thenReturn(mockHelper);
			//When
			importCommand.execute(addressBook);
			//Then
			PowerMockito.verifyNew(ContactImportHelper.class);
		}
		finally {
			Files.delete(path);
		}
	}
	
	private static void createFile(Path path) throws IOException {
		if (Files.exists(path))
			Files.delete(path);
		Files.createFile(path);
		Files.write(path, "Paul,0312345".getBytes());
	}
}
