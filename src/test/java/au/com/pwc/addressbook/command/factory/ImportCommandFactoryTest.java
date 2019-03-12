package au.com.pwc.addressbook.command.factory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.DoNothingCommand;
import au.com.pwc.addressbook.command.ImportCommand;

public class ImportCommandFactoryTest {

	private ImportCommandFactory testInstance;
	
	@Before
	public void setUp() {
		this.testInstance = new ImportCommandFactory();
	}
	
	@Test
	public void shouldReturnImportCommandWithFileName() {
		//Given
		String string = "import address2";
		//When
		Command command = testInstance.constructCommand(string);
		//Then
		assertThat(command, is(instanceOf(ImportCommand.class)));
	}
	
	@Test
	public void shouldReturnImportCommandWithFullFileName() {
		//Given
		String string = "import /var/log/addressBook2";
		//When
		Command command = testInstance.constructCommand(string);
		//Then
		assertThat(command, is(instanceOf(ImportCommand.class)));
	}
	
	@Test
	public void shouldReturnDoNothingCommandWithoutFileName() {
		//Given
		String string = "import";
		//When
		Command command = testInstance.constructCommand(string);
		//Then
		assertThat(command, is(instanceOf(DoNothingCommand.class)));
	}
}
