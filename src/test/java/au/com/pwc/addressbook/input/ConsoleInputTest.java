package au.com.pwc.addressbook.input;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

import au.com.pwc.addressbook.fixture.InputStreamFixture;
import au.com.pwc.addressbook.fixture.StringInputFixture;


/**
 * Test class for ConsoleInput class.
 * @author leo
 *
 */
public class ConsoleInputTest {

	private ConsoleInput consoleInput;

	//Given the user can access to the ConsoleInput class
	//When the user called the default constructor
	//Then Scanner object should be exit
	@Test
	public void whenDefaultConstructorCalledThenScannerShouldExist() {
		//Given the user can access the consoleInput class
		//When the default constructor called
		consoleInput = new ConsoleInput();
		//Then the scanner should exist
		assertNotNull(consoleInput);
		assertNotNull(consoleInput.getScanner());
	}
	
	/**
	 * Given the InputStream to ConsoleInput
	 * When the user called the real constructor
	 * Then the Command should be accept input from provided InputStream
	 */
	@Test
	public void whenByteArryInputStreamProvideThenRobotTableShouldAcceptInput() {
		//Given the byte array input stream
		InputStream in = InputStreamFixture.givenByteArrayInputStream(StringInputFixture.getSingleLineString());
		//When the constructor called
		this.consoleInput = new ConsoleInput(in);
		//Then the robot table should be accept input
		Scanner scanner = this.consoleInput.getScanner();
		assertThat(scanner.nextLine(), is(equalTo(StringInputFixture.TEST_DATA)));
		
		try {
			scanner.nextLine();
			fail("program reached unexpected point!");
		}
		catch (NoSuchElementException e) {
			assertThat(e.getMessage(), is(equalTo("No line found")));
		}
	}
}
