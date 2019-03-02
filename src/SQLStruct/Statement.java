package SQLStruct;

import SQLEnums.StatementType;

import SQLStruct.Core.Row;

public class Statement {

    private static Statement instance = new Statement();
    private StatementType type;
    private Row row;

    private Statement(){}

    public static Statement getInstance(){
        return instance;
    }

    public StatementType getType() {
        return type;
    }

    public void setType(StatementType type) {
        this.type = type;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }
}
