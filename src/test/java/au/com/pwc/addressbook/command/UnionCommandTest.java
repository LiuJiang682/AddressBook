package au.com.pwc.addressbook.command;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import au.com.pwc.addressbook.AddressBook;
import au.com.pwc.addressbook.file.AddressBookFile;

public class UnionCommandTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenFirstBookIsNull() {
		//Given
		String userEntered = "abc\\def";
		UnionCommand testInstance = new UnionCommand(userEntered);
		AddressBook mockAddressBook = Mockito.mock(AddressBook.class);
		//When
		testInstance.execute(mockAddressBook);
		fail("Program reached unexpected point!");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionWhenSecondBookIsNull() {
		//Given
		String userEntered = "abc\\def";
		UnionCommand testInstance = new UnionCommand(userEntered);
		AddressBook mockAddressBook = Mockito.mock(AddressBook.class);
		AddressBookFile mockBook = Mockito.mock(AddressBookFile.class);
		when(mockAddressBook.getAddressBookFile(eq("def"))).thenReturn(mockBook);
		//When
		testInstance.execute(mockAddressBook);
		fail("Program reached unexpected point!");
	}
	
	@Test
	public void twoLists() {
		//Given
		List<String> first = new ArrayList<>(Arrays.asList("John", "Paul", "Eric"));
		List<String> second = new ArrayList<>(Arrays.asList("Andy", "John", "Paul"));
		String userEntered = "abc\\def";
		UnionCommand testInstance = new UnionCommand(userEntered);
		//When
		List<String> unique = testInstance.extractUniqueNames(first, second);
		//Then
		assertThat(unique.size(), is(equalTo(2)));
		assertThat(unique.get(0), is(equalTo("Eric")));
		assertThat(unique.get(1), is(equalTo("Andy")));
	}
}
