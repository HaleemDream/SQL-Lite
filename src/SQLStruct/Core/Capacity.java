package SQLStruct.Core;

public enum Capacity {

    //PAGE_SIZE(4096) - The guide recommended 4096 bytes per page
    TABLE_MAX_PAGES(100),
    ROWS_PER_PAGE(20), // did a few tests, most row obj were < 200 bytes
    TABLE_MAX_ROWS(ROWS_PER_PAGE.value * TABLE_MAX_PAGES.value);

    public final int value;

    Capacity(int value){
        this.value = value;
    }
}
