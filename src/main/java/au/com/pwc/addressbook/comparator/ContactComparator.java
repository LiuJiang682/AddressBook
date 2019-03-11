package au.com.pwc.addressbook.comparator;

import java.util.Comparator;

import au.com.pwc.addressbook.constants.Constants.Numeric;
import au.com.pwc.addressbook.constants.Constants.Strings;

public class ContactComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		String[] string1Array = o1.split(Strings.COMMA);
		String[] string2Array = o2.split(Strings.COMMA);
		return string1Array[Numeric.ZERO].compareTo(string2Array[Numeric.ZERO]);
	}

}
