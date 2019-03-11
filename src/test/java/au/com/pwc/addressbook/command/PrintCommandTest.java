package au.com.pwc.addressbook.command;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import au.com.pwc.addressbook.AddressBook;
import au.com.pwc.addressbook.constants.Constants.Strings;
import au.com.pwc.addressbook.file.AddressBookFile;
import au.com.pwc.addressbook.file.LocalDiskAddressBookFile;

public class PrintCommandTest {

	@Test
	public void shouldReturnPringCommandWhenConstructorCalled() {
		//Given
		String string = "Print";
		//When
		PrintCommand command = new PrintCommand(string);
		//Then
		assertThat(command, is(instanceOf(PrintCommand.class)));
	}
	
	@Test
	public void shouldPrintAllAddressInBook() throws IOException {
		//Given
		String string = "Print";
		PrintCommand command = new PrintCommand(string);
		AddressBook mockAddressBook = Mockito.mock(AddressBook.class);
		PrintStream mockOutStream = Mockito.mock(PrintStream.class);
		when(mockAddressBook.getOutStream()).thenReturn(mockOutStream);
		AddressBookFile addressBookFile = getAddressBookFile();
		when(mockAddressBook.getDefaultFile()).thenReturn(addressBookFile);
		//When
		command.execute(mockAddressBook);
		//Then
		ArgumentCaptor<String> contactCaptor = ArgumentCaptor.forClass(String.class);
		verify(mockOutStream, times(2)).println(contactCaptor.capture());
		List<String> contacts = contactCaptor.getAllValues();
		assertThat(contacts.size(), is(equalTo(2)));
		assertThat(contacts.get(0), is(equalTo("John,123456")));
		assertThat(contacts.get(1), is(equalTo("Paul,789012")));
		Files.delete(Paths.get("abc"));
	}
	
	@Test
	public void shouldPrintAllAddressInBook1() throws IOException {
		//Given
		String string = "Print book1";
		PrintCommand command = new PrintCommand(string);
		AddressBook mockAddressBook = Mockito.mock(AddressBook.class);
		PrintStream mockOutStream = Mockito.mock(PrintStream.class);
		when(mockAddressBook.getOutStream()).thenReturn(mockOutStream);
		AddressBookFile addressBookFile = getAddressBookFile();
		when(mockAddressBook.getAddressBookFile("book1")).thenReturn(addressBookFile);
		//When
		command.execute(mockAddressBook);
		//Then
		ArgumentCaptor<String> contactCaptor = ArgumentCaptor.forClass(String.class);
		verify(mockOutStream, times(2)).println(contactCaptor.capture());
		List<String> contacts = contactCaptor.getAllValues();
		assertThat(contacts.size(), is(equalTo(2)));
		assertThat(contacts.get(0), is(equalTo("John,123456")));
		assertThat(contacts.get(1), is(equalTo("Paul,789012")));
		Files.delete(Paths.get("abc"));
	}
	
	private static AddressBookFile getAddressBookFile() throws IOException {
		LocalDiskAddressBookFile file = new LocalDiskAddressBookFile("abc");
		Path path = Paths.get("abc");
		String content1 = "Paul,789012" + Strings.LINE_BREAK;
		String content2	= "John,123456" + Strings.LINE_BREAK;
		Files.write(path, content1.getBytes());
		Files.write(path, content2.getBytes(), StandardOpenOption.APPEND);
		return file;
	}
}
