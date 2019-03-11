package au.com.pwc.addressbook.command;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import au.com.pwc.addressbook.AddressBook;
import au.com.pwc.addressbook.file.AddressBookFile;

public class AddCommandTest {

	@Test
	public void shouldCreateObjectWithContent() {
		//Given
		String string = "ADD Paul,123456";
		//When
		AddCommand addCommand = new AddCommand(string);
		//Then
		assertThat(addCommand, is(notNullValue()));
		String content = Whitebox.getInternalState(addCommand, "userEntered");
		assertThat(content, is(equalTo(string)));
	}
	
	@Test
	public void shouldAddUserToDefaultAddressBook() throws Exception {
		//Given
		String string = "ADD Paul,123456";
		AddCommand addCommand = new AddCommand(string);
		AddressBook mockAddressBook = Mockito.mock(AddressBook.class);
		AddressBookFile mockAddressBookFile = Mockito.mock(AddressBookFile.class);
		when(mockAddressBook.getDefaultFile()).thenReturn(mockAddressBookFile);
		//When
		addCommand.execute(mockAddressBook);
		//Then
		ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> phoneCaptor = ArgumentCaptor.forClass(String.class);
		verify(mockAddressBookFile).add(nameCaptor.capture(), phoneCaptor.capture());
		List<String> names = nameCaptor.getAllValues();
		assertThat(names.size(), is(equalTo(1)));
		assertThat(names.get(0), is(equalTo("Paul")));
		List<String> phones = phoneCaptor.getAllValues();
		assertThat(phones.size(), is(equalTo(1)));
		assertThat(phones.get(0), is(equalTo("123456")));
	}
	
	@Test
	public void shouldAddUserToAddressBooka() throws Exception {
		//Given
		String string = "ADD addressBook1,Paul,123456";
		AddCommand addCommand = new AddCommand(string);
		AddressBook mockAddressBook = Mockito.mock(AddressBook.class);
		AddressBookFile mockAddressBookFile = Mockito.mock(AddressBookFile.class);
		when(mockAddressBook.getAddressBookFile("addressBook1")).thenReturn(mockAddressBookFile);
		//When
		addCommand.execute(mockAddressBook);
		//Then
		ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> phoneCaptor = ArgumentCaptor.forClass(String.class);
		verify(mockAddressBookFile).add(nameCaptor.capture(), phoneCaptor.capture());
		List<String> names = nameCaptor.getAllValues();
		assertThat(names.size(), is(equalTo(1)));
		assertThat(names.get(0), is(equalTo("Paul")));
		List<String> phones = phoneCaptor.getAllValues();
		assertThat(phones.size(), is(equalTo(1)));
		assertThat(phones.get(0), is(equalTo("123456")));
	}
}
