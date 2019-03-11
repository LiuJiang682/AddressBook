package au.com.pwc.addressbook.fixture;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputStreamFixture {

	public static InputStream givenByteArrayInputStream(String input) {
		InputStream in = new ByteArrayInputStream(input.getBytes());
		return in;
	}
}
