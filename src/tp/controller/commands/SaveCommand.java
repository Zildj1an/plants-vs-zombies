package tp.controller.commands;

import tp.exceptions.CommandExecuteException;
import tp.exceptions.CommandParserException;
import tp.logic.Game;
import tp.util.MyStringUtils;

import java.io.*;

public class SaveCommand extends Command {
    public static final String COMMAND_TEXT = "save";
    public static final String COMMAND_TEXT_MSG = "[S]ave <filename>";
    public static final String HELP_TEXT_MSG = "save the game into a file";

    private static final String USAGE = COMMAND_TEXT + " <file>";
    private static final String FILE_EXTENSION = ".dat";

    private String fileName;


    public SaveCommand(){
        super(COMMAND_TEXT, COMMAND_TEXT_MSG, HELP_TEXT_MSG);
    }

    @Override
    public boolean execute(Game game) throws CommandExecuteException {

        if (!MyStringUtils.isValidFilename(fileName)) throw new CommandExecuteException("Invalid file");

        try (BufferedWriter in = new BufferedWriter(new FileWriter(fileName))) {
            in.write(Game.VERSION);
            in.newLine();
            in.newLine();
            game.save(in);

            System.out.println("Game successfully saved in " + fileName);
        }catch (FileNotFoundException e) {
            throw  new CommandExecuteException("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParserException {
        Command c = null;

        if(commandWords[0].equals(COMMAND_TEXT) || commandWords[0].equals(Character.toString(COMMAND_TEXT.charAt(0)))){
            if (commandWords.length != 2) throw new CommandParserException("Incorrect number of arguments for save command:" + USAGE);

            fileName = commandWords[1] + FILE_EXTENSION;
            c = this;
        }

        return c;
    }
}
