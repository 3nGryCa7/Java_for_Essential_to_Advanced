package Enum;

public enum Ice {
    REGULAR(10, "正常冰"),
    LESS(3, "少冰"),
    FREE(0, "去冰");

    private int level;
    private String description;
    /* 建構式 Start (5pt.) */
    private Ice(int level, String descr) {
        this.level = level;
        this.description = descr;
    }
    /* 建構式 End (5pt.) */

    public String getDescription() {
        return description;
    }

}
