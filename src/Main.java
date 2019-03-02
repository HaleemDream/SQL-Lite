import SQLEnums.ExecuteResult;
import SQLEnums.MetaCommandResult;
import SQLEnums.PrepareResult;
import SQLEnums.StatementType;

import SQLStruct.Core.Capacity;
import SQLStruct.Core.Table;
import SQLStruct.Statement;

import Utils.PrintRow;
import Utils.ReadRow;

import java.util.Scanner;

public class Main {

    /*
        Front-end consists of:

        - Tokenizer
        - Parser
        - Code Generator

        The input is an SQL query. The output is sql VM bytecode

        Back-end consists:

        - Virtual Machine
        - B-tree
        - Pager
        - OS Interface

        VM takes byte code and performs operations on one or more tables or indexes, each of
        which is stored in a data structure called a B-tree.

        A B-Tree consists of many nodes. ***Each Node is one page in length***.

        The Pager receives commands to read or write pages of data. Responsible for
        reading/writing at appropriate offsets in the database file. Also maintains cache
        and must determine when to write back to memory (save cache).
     */


    // SECTION : Tokenizer - REPL, Read-Execute-Print-Loop

    private Scanner in = new Scanner(System.in);
    private SQLStruct.Statement statement = Statement.getInstance();
    private Table table = new Table();

    public static void main(String[] args) {
        Main main = new Main();

        while(true){
            System.out.print("db > ");
            main.readInput(main.getInput());
        }
    }

    String getInput(){
        return in.nextLine();
    }

    void readInput(String input){

        if(input.charAt(0) == '.'){
            switch (getMetaResult(input)){
                case META_COMMAND_SUCCESS:
                    // todo
                    return;
                case META_COMMAND_UNRECOGNIZED_COMMAND:
                    System.out.printf("unrecognized command '%s'\n", input);
                    return;
            }
        }

        switch (getPrepareResult(input)){
            case PREPARE_SUCCESS:
                // todo
                break;
            case PREPARE_SYNTAX_ERROR:
                System.out.println("Syntax error. Could not parse statement.\n");
                return;
            case PREPARE_UNRECOGNIZED_STATEMENT:
                System.out.printf("unrecognized keyword at start of '%s'.\n", input);
                return;
        }

        switch (executeStatement()){
            case EXECUTE_SUCCESS:
                System.out.println("Executed.");
                break;
            case EXECUTE_TABLE_FULL:
                System.out.println("Table full.\n");
                break;
        }
    }

    MetaCommandResult getMetaResult(String input){
        if(input.equalsIgnoreCase(".exit")){
            System.exit(0);
        }

        return MetaCommandResult.META_COMMAND_UNRECOGNIZED_COMMAND;
    }

    PrepareResult getPrepareResult(String input){
        if(input.startsWith("insert")){
            statement.setType(StatementType.STATEMENT_INSERT);
            statement.setRow(ReadRow.read(input));
            return PrepareResult.PREPARE_SUCCESS;
        }
        if(input.startsWith("select")){
            statement.setType(StatementType.STATEMENT_SELECT);
            return PrepareResult.PREPARE_SUCCESS;
        }

        return PrepareResult.PREPARE_UNRECOGNIZED_STATEMENT;
    }

    ExecuteResult executeStatement(){
        switch (statement.getType()){
            case STATEMENT_INSERT:
                return executeInsert();
            case STATEMENT_SELECT:
                return executeSelect();
        }
        return null; // todo
    }

    ExecuteResult executeInsert(){
        if(table.getRowCount() >= Capacity.TABLE_MAX_ROWS.value){
            return ExecuteResult.EXECUTE_TABLE_FULL;
        }

        table.set(statement.getRow());
        return ExecuteResult.EXECUTE_SUCCESS;
    }

    ExecuteResult executeSelect(){
        PrintRow.print(table);
        return ExecuteResult.EXECUTE_SUCCESS;
    }
}
