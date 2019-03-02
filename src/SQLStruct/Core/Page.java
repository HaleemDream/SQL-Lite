package SQLStruct.Core;

public class Page {

    private Row[] rows;

    public Page(){
        rows = new Row[Capacity.ROWS_PER_PAGE.value];
    }

    public Row[] getRows() {
        return rows;
    }

}
