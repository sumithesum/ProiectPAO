package Audit;

import lombok.Getter;


public class Audit {
    @Getter
    private Integer id;
    @Getter
    private String command;
    @Getter
    private String username;

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
