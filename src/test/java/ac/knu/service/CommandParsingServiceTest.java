package ac.knu.service;

import ac.knu.exceptions.NoCommandOptionException;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class CommandParsingServiceTest {
    private CommandParsingService commandParsingService;
    private FriendDataBase friendDataBase;
    @Before
    public void setup() {
        friendDataBase = new FriendDataBase();
        friendDataBase.addFriendToDataBase("hangeul",11,Friend.Gender.MAN);
        friendDataBase.addFriendToDataBase("hankook",21,Friend.Gender.MAN);
        commandParsingService = new CommandParsingService();
    }
    @Test
    public void executeAddCommandAndReturnSuccessMessage() {
        assertTrue(commandParsingService.parsingCommand(
                friendDataBase, "add james 11 male").equalsIgnoreCase("james is added successfully!"));
    }
    @Test
    public void executeTimeCommandAndReturnLocalTime() {
        assertTrue(commandParsingService.parsingCommand(
                friendDataBase, "time").equalsIgnoreCase("Current Time: " + new Date().toString()));
    }
    @Test
    public void executeRemoveCommandAndReturnSuccessMessage() {
        assertTrue(commandParsingService.parsingCommand(
                friendDataBase, "remove hangeul").equalsIgnoreCase("hangeul is removed successfully!"));
    }
    @Test
    public void executeFindCommandAndReturnFriendInformation() {
        assertTrue(commandParsingService.parsingCommand(
                friendDataBase, "find hangeul").equalsIgnoreCase("hangeul, 11, MAN"));
    }
    @Test
    public void executeListCommandAndReturnFriendsListOfDatabase() {
        assertTrue(commandParsingService.parsingCommand(
                friendDataBase,"list").equalsIgnoreCase("hangeul, 11, MAN\nhankook, 21, MAN\n"));
    }
    @Test(expected = NoCommandOptionException.class)
    public void makeErrorWhenNoOptionToCommand() {
        commandParsingService.parsingCommand(friendDataBase, "add");
        commandParsingService.parsingCommand(friendDataBase, "find");
        commandParsingService.parsingCommand(friendDataBase, "remove");
    }
    @Test(expected = NoCommandOptionException.class)
    public void makeErrorWhenNoOptionToCommandInCheckOptionIsExistMethod() {
        commandParsingService.checkIfCommandOptionIsExist("add   ".split("\\s+"), new AddCommand());
        commandParsingService.checkIfCommandOptionIsExist("find  ".split("\\s+"), new FindCommand());
        commandParsingService.checkIfCommandOptionIsExist("remove  ".split("\\s+"), new RemoveCommand());
    }
}
