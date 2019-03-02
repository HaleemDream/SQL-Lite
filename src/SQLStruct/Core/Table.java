package SQLStruct.Core;

public class Table {

    private Page[] pages;
    private int rowCount;

    public Table(){
        pages = new Page[Capacity.TABLE_MAX_PAGES.value];
        initPagesArr();
        rowCount = 0;
    }

    public int getRowCount() {
        return rowCount;
    }

    public Row get(int rowNum){
        int pageIndex = getPageIndex(rowNum);
        int rowIndex = getRowIndex(rowNum);

        Row row = pages[pageIndex].getRows()[rowIndex];

        return (row != null) ? row : Row.EMPTY_ROW();
    }

    public void set(Row row){
        int pageIndex = getPageIndex(rowCount);
        int rowIndex = getRowIndex(rowCount);

        pages[pageIndex].getRows()[rowIndex] = row;

        rowCount++;
    }

    private void initPagesArr(){
        for(int i = 0; i < Capacity.TABLE_MAX_PAGES.value; i++){
            pages[i] = new Page();
        }
    }

    private int getPageIndex(int val){
        return val/Capacity.ROWS_PER_PAGE.value;
    }

    private int getRowIndex(int val){
        return val % Capacity.ROWS_PER_PAGE.value;
    }
}
