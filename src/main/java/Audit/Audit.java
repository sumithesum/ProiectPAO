package Audit;

import lombok.Getter;

/**
 * The Audit class represents an audit record with a command and a username.
 */
public class Audit {
    @Getter
    private Integer id;
    @Getter
    private String command;
    @Getter
    private String username;
    /**
     * Constructs an Audit object with the specified command and username.
     *
     * @param command the command being audited
     * @param username the username of the user executing the command
     */
    public Audit(Integer id, String command, String username) {
        this.id = id;
        this.command = command;
        this.username = username;
    }
    public Audit(String command, String username) {
        this.command = command;
        this.username = username;
    }
    public void printAudit(){
        System.out.println("Command: " + command + " , User: " + username);
    }
}
