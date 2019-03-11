package au.com.pwc.addressbook;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import au.com.pwc.addressbook.command.factory.CommandFactoryFactory;
import au.com.pwc.addressbook.file.AddressBookFile;


public class PwcAddressBookTest {

	@Test
	public void shouldPopulatedTheFileMapAndCommandFactory() {
		//Given
		//When
		PwcAddressBook testInstance = new PwcAddressBook();
		//Then
		Map<String, AddressBookFile> addressBooks = Whitebox.getInternalState(testInstance, "addressBooks");
		assertThat(addressBooks, is(notNullValue()));
		assertThat(1 <= addressBooks.size(), is(true));
		CommandFactoryFactory commandFactory = Whitebox.getInternalState(testInstance, "commandFactory");
		assertThat(commandFactory, is(notNullValue()));
	}
}
