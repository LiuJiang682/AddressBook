package au.com.pwc.addressbook.command.factory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.DoNothingCommand;
import au.com.pwc.addressbook.command.PrintCommand;

public class PrintCommandFactoryTest {

	@Test
	public void shouldReturnPrintCommandWhenUserEnteredPrintString() {
		//Given
		String string = "Print";
		//When
		Command command = new PrintCommandFactory().constructCommand(string);
		//Then
		assertThat(command, is(instanceOf(PrintCommand.class)));
	}
	
	@Test
	public void shouldReturnDoNothingCommandWhenUserEnteredPrintStringWithRubbish() {
		//Given
		String string = "Print a,b";
		//When
		Command command = new PrintCommandFactory().constructCommand(string);
		//Then
		assertThat(command, is(instanceOf(DoNothingCommand.class)));
	}
}
