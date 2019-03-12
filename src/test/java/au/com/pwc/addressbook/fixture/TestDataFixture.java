package au.com.pwc.addressbook.fixture;

import java.util.Arrays;
import java.util.List;

public class TestDataFixture {

	public static List<String> getContactList() {
		String[] contacts = {"John,03123456", "Paul,03789999"};
		return Arrays.asList(contacts);
	}
}
