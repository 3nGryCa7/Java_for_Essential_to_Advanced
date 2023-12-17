public enum EnumExample {
    Monday("MON"), Tuesday("TUE"), Wednesday("WED"), Thursday("THU"), Friday("FRI"), Saturday("SAT"), Sunday("SUN");

    private final String code;

    // 只能在 enum 內部使用 -> private
    private EnumExample(String code ) {
        this.code = code;
        System.out.println("Constructor called for : " + this.toString());
    }

    public String getCode() {
        return this.code;
    }

    public static void main(String[] args) {
        EnumExample day = EnumExample.Monday;
        System.out.println(day.getCode());

        // values()
        for (EnumExample w : EnumExample.values()) {
            System.out.println(w);
        }

    }
}
