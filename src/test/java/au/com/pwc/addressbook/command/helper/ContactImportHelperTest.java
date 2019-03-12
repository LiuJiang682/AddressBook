package au.com.pwc.addressbook.command.helper;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import au.com.pwc.addressbook.AddressBook;
import au.com.pwc.addressbook.command.AddCommand;
import au.com.pwc.addressbook.command.factory.AddCommandFactory;
import au.com.pwc.addressbook.fixture.TestDataFixture;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ContactImportHelper.class})
public class ContactImportHelperTest {

	private ContactImportHelper testInstance;
	
	@Test
	public void shouldReturnCorrectPrefixForShortFileName() {
		//Given
		String fileName = "abc";
		givenTestInstance(fileName);
		//When
		String prefix = testInstance.getCommandPrefix();
		//Then
		assertThat(prefix, is(equalTo("ADD abc,")));
	}
	
	@Test
	public void shouldReturnCorrectPrefixForFullFileName() {
		//Given
		String fileName = "/var/log/abc";
		givenTestInstance(fileName);
		//When
		String prefix = testInstance.getCommandPrefix();
		//Then
		assertThat(prefix, is(equalTo("ADD abc,")));
	}
	
	@Test
	public void shouldDoImport() throws Exception {
		//Given
		String fileName = "abc";
		givenTestInstance(fileName);
		AddCommandFactory mockFactory = Mockito.mock(AddCommandFactory.class);
		PowerMockito.whenNew(AddCommandFactory.class).withNoArguments().thenReturn(mockFactory);
		AddCommand mockCommand = Mockito.mock(AddCommand.class);
		when(mockFactory.constructCommand(Matchers.anyString())).thenReturn(mockCommand);
		//When
		testInstance.doImport();
		//Then
		verify(mockCommand, times(2)).execute(Matchers.any(AddressBook.class));
	}
	
	private void givenTestInstance(final String fileName) {
		List<String> contents = TestDataFixture.getContactList();
		AddressBook mockAddressBook = Mockito.mock(AddressBook.class);
		testInstance = new ContactImportHelper(fileName, contents, mockAddressBook);
	}
}
