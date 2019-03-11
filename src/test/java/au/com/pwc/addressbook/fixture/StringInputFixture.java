package au.com.pwc.addressbook.fixture;

public class StringInputFixture {

	public static final String TEST_DATA = "abc";

	public static String getSingleLineString() {
		return TEST_DATA;
	}

	public static String getTwoLineString() {
		return TEST_DATA + "\n" + TEST_DATA;
	}
}
