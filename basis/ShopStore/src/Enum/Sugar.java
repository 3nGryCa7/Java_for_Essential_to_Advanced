package Enum;

public enum Sugar {
    REGULAR(10, "正常糖"),
    LESS(7, "少糖"),
    HALF(5, "半糖"),
    QUARTER(3, "微糖"),
    FREE(0, "無糖");

    private final int level;
    private final String description;
    /* 建構式 Start (5pt., 3) */
    private Sugar(int level, String descr) {
        this.level = level;
        this.description = descr;
    }
    /* 建構式 End (5pt.) */

    public String getDescription() {
        return description;
    }

}
