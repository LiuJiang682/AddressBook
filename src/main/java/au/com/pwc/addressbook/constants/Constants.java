package au.com.pwc.addressbook.constants;

public interface Constants {

	interface Strings {

		static final String COMMA = ",";
		static final String LINE_BREAK = System.getProperty("line.separator");
		static final String FILE_PATH_SEPARATOR = System.getProperty("file.separator");
		static final String DEFAULT = "default";
		static final String SPACE = " ";
		static final String BACK_SLASH = "\\";
	}
	
	interface Numeric {

		static final int ZERO = 0;
		static final int ONE = 1;
		static final long MAX_SIZE = 2147483648l; //2 GB
		
	}
}
