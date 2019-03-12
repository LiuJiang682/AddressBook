package au.com.pwc.addressbook.command;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class CommandEnumTest {

	/**
	 * Given all combination of strings
	 * When the FormString method called
	 * Then correct command enum returns
	 */
	@Test
	public void whenStringProvidedThenCommandEnumReturn() {
		assertThat(CommandEnum.fromString(null), is(equalTo(CommandEnum.DONOTHING)));
		assertThat(CommandEnum.fromString(""), is(equalTo(CommandEnum.DONOTHING)));
		assertThat(CommandEnum.fromString("abc"), is(equalTo(CommandEnum.DONOTHING)));
	}
	
	@Test
	public void whenAddStringEnteredThenPlaceCommandEnumReturn() {
		assertThat(CommandEnum.fromString("add"), is(equalTo(CommandEnum.ADD)));
		assertThat(CommandEnum.fromString("ADD Paul,123456"),
				is(equalTo(CommandEnum.ADD)));
		assertThat(CommandEnum.fromString("ADD addressBook1,Paul,123456"),
				is(equalTo(CommandEnum.ADD)));
	}
	
	@Test
	public void shouldReturnPrintCommandWhenPrintStringProvided() {
		assertThat(CommandEnum.fromString("print"), is(equalTo(CommandEnum.PRINT)));
		assertThat(CommandEnum.fromString("Print address1"), is(equalTo(CommandEnum.PRINT)));
	}
	
	@Test
	public void shouldReturnImportCommandWhenImportStringProvided() {
		assertThat(CommandEnum.fromString("import"), is(equalTo(CommandEnum.IMPORT)));
		assertThat(CommandEnum.fromString("import addressBook2"), is(equalTo(CommandEnum.IMPORT)));
		assertThat(CommandEnum.fromString("import ~/addressBook2"), is(equalTo(CommandEnum.IMPORT)));
	}
}
