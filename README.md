#AddressBook

Task:

• To be able to display the list of friends and their corresponding phone numbers sorted
  by their name.
• Given another address book that may or may not contain the same friends, display the
  list of friends that are unique to each address book (the union of all the relative
  complements). For example given:

	Book1 = { “Bob”, “Mary”, “Jane” }
	Book2 = { “Mary”, “John”, “Jane” }

The friends that are unique to each address book are:
	
	Book1 \ Book2 = { “Bob”, “John” }
	
#Instruction

1. Download the zip file or run: git clone https://github.com/LiuJiang682/AddressBook.git
2. Open a terminal and run: cd AddressBook
3. At the same terminal, run: mvn clean install assembly:single
4. Again at the same terminal, run: java -jar target/AddressBook-0.1.0-jar-with-dependencies.jar
5. Have fun!
6. Availabel commands user can enter from command line are:

	add Paul,03123456             -- add the contact to default address book
	add address1,Paul,03123456    -- add the contact to address1 address book
	print                         -- print the contact list from default address book
	print address1                -- print the contact list from address1 address book
	import address2               -- import the address book in current directory into the address book app.
	import absoluteFileName       -- import the address book in absolute file name into the address book app with same name.
	default\address1              -- display the unique name of 2 lists.
	
#Assumption

1. It assumpt the file content in the format of "name,phonenumber". It will not accept any other format.
2. It assumpt each file will not bigger than 2 GB. 	