package au.com.pwc.addressbook.comparator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import au.com.pwc.addressbook.constants.Constants.Numeric;

public class ContactComparatorTest {

	@Test
	public void shouldSortedByNatualOrder() {
		//Given
		String string1 = "Paul,123456";
		String String2 = "John,789012";
		ContactComparator testInstance = new ContactComparator();
		//When
		int result = testInstance.compare(string1, String2);
		//Then
		assertThat(Numeric.ONE <= result, is(true));
		
		result = testInstance.compare(String2, string1);
		assertThat(result < Numeric.ZERO, is(true));
	}
}
