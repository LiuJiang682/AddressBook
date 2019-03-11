package au.com.pwc.addressbook.command.factory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import au.com.pwc.addressbook.command.AddCommand;
import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.DoNothingCommand;

public class AddCommandFactoryTest {

	private AddCommandFactory testInstance;
	
	@Before
	public void setUp() {
		testInstance = new AddCommandFactory();
	}
	
	@Test
	public void shouldReturnAddCommandWithNameAndNumber() {
		//Given
		String string = "ADD Paul,123456";
		//When
		Command command = testInstance.constructCommand(string);
		//Then
		assertThat(command, is(instanceOf(AddCommand.class)));
	}
	
	@Test
	public void shouldReturnAddCommandWithAddressBookNameAndNumber() {
		//Given
		String string = "ADD addressBook,Paul,123456";
		//When
		Command command = testInstance.constructCommand(string);
		//Then
		assertThat(command, is(instanceOf(AddCommand.class)));
	}
	
	@Test
	public void shouldReturnDoNothingCommandWithEmptyAdd() {
		//Given
		String string = "ADD";
		//When
		Command command = testInstance.constructCommand(string);
		//Then
		assertThat(command, is(instanceOf(DoNothingCommand.class)));
	}
	
	@Test
	public void shouldReturnDoNothingCommandWithRubbishParameters() {
		//Given
		String string = "ADD addressBook,Paul,123456,xxx";
		//When
		Command command = testInstance.constructCommand(string);
		//Then
		assertThat(command, is(instanceOf(DoNothingCommand.class)));
	}
}
