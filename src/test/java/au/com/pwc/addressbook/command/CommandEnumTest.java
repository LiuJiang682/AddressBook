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
		
//		assertThat(CommandEnum.fromString("Move"), is(equalTo(CommandEnum.MOVE)));
//		assertThat(CommandEnum.fromString("LEFT"), is(equalTo(CommandEnum.LEFT)));
//		assertThat(CommandEnum.fromString("Report"), is(equalTo(CommandEnum.REPORT)));
//		assertThat(CommandEnum.fromString("right"), is(equalTo(CommandEnum.RIGHT)));
	}
	
	@Test
	public void whenPlaceStringEnteredThenPlaceCommandEnumReturn() {
		assertThat(CommandEnum.fromString("add"), is(equalTo(CommandEnum.ADD)));
		assertThat(CommandEnum.fromString("ADD Paul,123456"),
				is(equalTo(CommandEnum.ADD)));
		assertThat(CommandEnum.fromString("ADD addressBook1,Paul,123456"),
				is(equalTo(CommandEnum.ADD)));
	}
}
