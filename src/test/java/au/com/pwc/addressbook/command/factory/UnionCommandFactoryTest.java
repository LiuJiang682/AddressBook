package au.com.pwc.addressbook.command.factory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.DoNothingCommand;
import au.com.pwc.addressbook.command.UnionCommand;

public class UnionCommandFactoryTest {

	@Test
	public void shouldReturnUnionCommandWhenUnionStringProvided() {
		//Given
		String userEntered = "default\\abc";
		UnionCommandFactory testInstance = new UnionCommandFactory();
		//When
		Command command = testInstance.constructCommand(userEntered);
		//Then
		assertThat(command, is(instanceOf(UnionCommand.class)));
	}
	
	@Test
	public void shouldReturnDoNothingCommandWhneStringFirstCharIsForwardSlash() {
		//Given
		String userEntered = "\\abc";
		UnionCommandFactory testInstance = new UnionCommandFactory();
		//When
		Command command = testInstance.constructCommand(userEntered);
		//Then
		assertThat(command, is(instanceOf(DoNothingCommand.class)));
	}
	
	@Test
	public void shouldReturnDoNothingCommandWhneStringLastCharIsForwardSlash() {
		//Given
		String userEntered = "abc\\";
		UnionCommandFactory testInstance = new UnionCommandFactory();
		//When
		Command command = testInstance.constructCommand(userEntered);
		//Then
		assertThat(command, is(instanceOf(DoNothingCommand.class)));
	}
}
