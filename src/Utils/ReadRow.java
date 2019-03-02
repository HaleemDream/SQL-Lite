package Utils;

import SQLStruct.Core.Row;

public class ReadRow {

    private ReadRow(){}

    /*
        input in the format of - insert [INT] ID [STRING] Name [STRING] email
     */
    public static Row read(String input){
        String[] command = input.split(" ");

        int id = Integer.valueOf(command[1]);
        String name = command[2];
        String email = command[3];

        return new Row(id, name, email);
    }
}
