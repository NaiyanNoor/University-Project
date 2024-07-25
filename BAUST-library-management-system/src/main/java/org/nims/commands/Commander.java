package org.nims.commands;

/**
 * This class has been used to implement the Command Pattern
 */
public class Commander {
    /**
     * Executes the passed command
     * @param command : Runnable
     */
   public void execute(Runnable command){
        command.run();
    }

    /**
     * Returns the passed command
     * @param command : Runnable
     * @return : Runnable
     */
    public Runnable getCommand(Runnable command){
       return command;
    }
}
