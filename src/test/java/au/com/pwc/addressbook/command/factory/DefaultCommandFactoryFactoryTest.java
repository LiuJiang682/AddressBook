package au.com.pwc.addressbook.command.factory;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import au.com.pwc.addressbook.command.AddCommand;
import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.DoNothingCommand;
import au.com.pwc.addressbook.command.PrintCommand;

public class DefaultCommandFactoryFactoryTest {

private DefaultCommandFactoryFactory defaultCommandFactoryFactory;
	
	@Before
	public void setUp() {
		this.defaultCommandFactoryFactory = new DefaultCommandFactoryFactory();
	}
	
	@After
	public void tearDown() {
		this.defaultCommandFactoryFactory = null;
	}
	
	/**
	 * Given the user enter null string
	 * When the constructCommand method called
	 * Then a DoNothingCommand object should return
	 */
	@Test
	public void whenUserEnteredNullThenADoNothingCommandObjectShouldReturn() {
		//Given user entered null
		String userEntered = null;
		//When the constructCommand called
		Command command = defaultCommandFactoryFactory.constructCommand(userEntered);
		//Then the a DoNothing command object should return
		assertThat(command, is(instanceOf(DoNothingCommand.class)));
	}
	
	/**
	 * Given the user enter an empty string
	 * When the constructCommand method called
	 * Then a DoNothingCommand object should return
	 */
	@Test
	public void whenUserEnteredEmptyStringThenADoNothingCommandObjectShouldReturn() {
		//Given user entered an empty string 
		String userEntered = "";
		//When the constructCommand called
		Command command = defaultCommandFactoryFactory.constructCommand(userEntered);
		//Then the a DoNothing command object should return
		assertThat(command, is(instanceOf(DoNothingCommand.class)));
	}
	
	
	/**
	 * Given the user enter an invalid command string
	 * When the constructCommand method called
	 * Then a DoNothingCommand object should return
	 */
	@Test
	public void whenUserEnteredInvalidStringThenADoNothingCommandObjectShouldReturn() {
		//Given user entered an invalid command string 
		String userEntered = "abc";
		//When the constructCommand called
		Command command = defaultCommandFactoryFactory.constructCommand(userEntered);
		//Then the a DoNothing command object should return
		assertThat(command, is(instanceOf(DoNothingCommand.class)));
	}
	
	/**
	 * Given the user enter the Add command string
	 * When the doCommandConstruct method called
	 * Then the Add command object should return
	 */
	@Test
	public void whenAddCommandEnteredThenDoCommandConstructShouldReturnAddCommand() {
		//Given the user enter the PLACE command string
		String userEntered = "Add addressBook1,Paul,123456";
		//When the doCommandConstruct method called
		Command command = defaultCommandFactoryFactory.constructCommand(userEntered);
		//Then the Place command object should return
		assertThat(command, is(instanceOf(AddCommand.class)));
	}
	
	/**
	 * Given the user enter the Print command string
	 * When the doCommandConstruct method called
	 * Then the Add command object should return
	 */
	@Test
	public void whenPrintCommandEnteredThenDoCommandConstructShouldReturnPrintCommand() {
		//Given the user enter the PLACE command string
		String userEntered = "Print";
		//When the doCommandConstruct method called
		Command command = defaultCommandFactoryFactory.constructCommand(userEntered);
		//Then the Place command object should return
		assertThat(command, is(instanceOf(PrintCommand.class)));
	}
	
	/**
	 * Given the user can access the DefaultCommandFactoryFactory class
	 * When the constructor is called
	 * Then the factories map should populated
	 */
	@Test
	public void whenConstructorCalledThenFactoriesMapShouldPopulated() {
		//Given
		//When
		//Then
		Map<String, CommandFactory> factories = Whitebox.getInternalState(defaultCommandFactoryFactory, "factories");
		assertThat(factories, is(notNullValue()));
		assertThat(factories.size(), is(equalTo(2)));
	}
}
