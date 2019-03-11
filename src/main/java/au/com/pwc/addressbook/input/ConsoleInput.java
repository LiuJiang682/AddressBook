package au.com.pwc.addressbook.input;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleInput implements InputInterf {

private Scanner scanner;
	
	public ConsoleInput() {
		this(System.in);
	}
	
	public ConsoleInput(InputStream in) {
		this.scanner = new Scanner(in);
	}


	public String getNextLine() {
		return scanner.nextLine();
	}

	public Scanner getScanner() {
		return this.scanner;
	}
	
	public void close() {
		scanner.close();
	}

}
