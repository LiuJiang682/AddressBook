package au.com.pwc.addressbook.file;

public interface AddressBookFile {
	
	String getName();
	void add(final String name, final String phoneNumber) throws Exception;

}
