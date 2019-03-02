package Utils;

import SQLStruct.Core.Row;
import SQLStruct.Core.Table;

public class PrintRow {

    private PrintRow(){}

    public static void print(Table table){
        for(int i = 0; i < table.getRowCount(); i++){
            printRow(table.get(i));
        }
    }

    private static void printRow(Row row){
        System.out.printf("(%d, %s, %s)\n", row.getId(), row.getUsername(), row.getEmail());
    }
}
