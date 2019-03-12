package au.com.pwc.addressbook.command.factory;

import java.util.HashMap;
import java.util.Map;

import au.com.pwc.addressbook.command.Command;
import au.com.pwc.addressbook.command.CommandEnum;
import au.com.pwc.addressbook.command.DoNothingCommand;
import au.com.pwc.addressbook.input.ConsoleInput;
import au.com.pwc.addressbook.input.InputInterf;

public class DefaultCommandFactoryFactory implements CommandFactoryFactory {
	
	private InputInterf userInput;
	private Map<String, CommandFactory> factories;
	
	public DefaultCommandFactoryFactory() {
		userInput = new ConsoleInput();
		factories = new HashMap<>();
		factories.put(CommandEnum.ADD.name(), new AddCommandFactory());
		factories.put(CommandEnum.PRINT.name(), new PrintCommandFactory());
		factories.put(CommandEnum.IMPORT.name(), new ImportCommandFactory());
		factories.put(CommandEnum.UNION.name(), new UnionCommandFactory());
	}

	public Command getNextCommand() {
		Command command = null;

		// User interactive mode
		System.out.println("Please enter your command: ");
		String userEntered = userInput.getNextLine();
		command = constructCommand(userEntered);

		return command;
	}

	public void closeInput() {
		userInput.close();
	}

	public Command constructCommand(String userEntered) {
		Command command = null;
		CommandFactory  commandFactory = null;
		
		switch(CommandEnum.fromString(userEntered)) {
			case ADD:
				commandFactory = factories.get(CommandEnum.ADD.name());
				break;
			case PRINT:
				commandFactory = factories.get(CommandEnum.PRINT.name());
				break;
			case IMPORT:
				commandFactory = factories.get(CommandEnum.IMPORT.name());
				break;
			case UNION:
				commandFactory = factories.get(CommandEnum.UNION.name());
				break;
			default:
				command = new DoNothingCommand();
		}
		
		if (null != commandFactory) {
			command = commandFactory.constructCommand(userEntered);
		}
		return command;
	}

}
