package tp.controller.commands;

import tp.exceptions.CommandExecuteException;
import tp.exceptions.CommandParserException;
import tp.exceptions.FileContentsException;
import tp.logic.Game;
import tp.util.MyStringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadCommand extends Command{
    public static final String COMMAND_TEXT = "load";
    public static final String COMMAND_TEXT_MSG = "[Lo]ad <filename>";
    public static final String HELP_TEXT_MSG = "load the game from a file";

    private static final String USAGE = COMMAND_TEXT + " <file>";
    private static final String FILE_EXTENSION = ".dat";

    private String fileName;

    public LoadCommand() {
        super(COMMAND_TEXT, COMMAND_TEXT_MSG, HELP_TEXT_MSG);
    }

    @Override
    public boolean execute(Game game) throws CommandExecuteException {

        if(!MyStringUtils.isValidFilename(fileName)) throw new CommandExecuteException("Invalid file");
        if(!MyStringUtils.fileExists(fileName)) throw new CommandExecuteException("File not found");

        try(BufferedReader bf = new BufferedReader(new FileReader(fileName))){
            //TODO change Game.Version to recognise other versions
            if(!(bf.readLine().equals(Game.VERSION) && bf.readLine().equals(""))) throw new CommandExecuteException("Invalid file");

            game.load(bf);

            System.out.println("Game successfully loaded from " + fileName);
        } catch (FileNotFoundException e) {
            throw new CommandExecuteException("File not found");
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandExecuteException("Invalid file");
        } catch (FileContentsException e) {
            throw new CommandExecuteException("Invalid file" + e.getMessage());
        }

        return true;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParserException {
        Command c = null;

        if(commandWords[0].equals(COMMAND_TEXT) || commandWords[0].equals(COMMAND_TEXT.substring(0,2))){
            if (commandWords.length != 2) throw new CommandParserException("Incorrect number of arguments for load command:" + USAGE);

            fileName = commandWords[1] + FILE_EXTENSION;
            c = this;
        }

        return c;
    }
}
