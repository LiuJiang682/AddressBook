package au.com.pwc.addressbook.file;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class LocalDiskAddressBookFileFactoryTest {

	@Test
	public void shouldCreateALocalDiskFileWhenFileNameIsCorrect() {
		//Given
		String fileName = "abc";
		//When
		AddressBookFile file = new LocalDiskAddressBookFileFactory().create(fileName);
		//Then
		assertThat(file, is(notNullValue()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotCreateFileWhenFileNameIsNull() {
		//Given
		String fileName = null;
		//When
		new LocalDiskAddressBookFileFactory().create(fileName);
		fail("Program reached unexpected point!");
	}
}
