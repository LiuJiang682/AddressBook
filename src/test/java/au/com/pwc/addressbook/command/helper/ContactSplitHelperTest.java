package au.com.pwc.addressbook.command.helper;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import au.com.pwc.addressbook.file.AddressBookFile;
import au.com.pwc.addressbook.fixture.TestDataFixture;

public class ContactSplitHelperTest {

	@Test
	public void shouldExtractNameFromContent() {
		//Given
		List<String> contents = TestDataFixture.getContactList();
		ContactSplitHelper testInstance = new ContactSplitHelper(Mockito.mock(AddressBookFile.class));
		//When
		List<String> names = testInstance.extractNames(contents);
		//Then
		assertThat(names.size(), is(equalTo(2)));
		assertThat(names.get(0), is(equalTo("John")));
		assertThat(names.get(1), is(equalTo("Paul")));
	}
	
}
