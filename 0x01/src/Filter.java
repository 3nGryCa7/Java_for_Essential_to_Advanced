interface Criteria {
	boolean accept(int item);
}
class Failed implements Criteria {
	public boolean accept(int item) {
		return item < 60;
	}
}
class Passed implements  Criteria {
	public boolean accept(int item) { return item >= 60; }
}
public class Filter {
	public static void main(String[] args) {
		int[] a = { 23, 76, 88, 92, 64, 72, 46, 85};
		// int b [] = filter(a);
		// int b [] = select(a, new Passed());
		// int b [] = select(a, item -> item >= 70 && item <= 90);
		int[] b = filter2(a, item -> item >= 60);
		display(b);
	}
	static int [] filter(int[] x) {
		int count = 0;
        for (int j : x) {
            if (j >= 60) count++;
        }
		int[] y = new int[count];
        int index =0;
        for (int j : x) {
            if (j >= 60) y[index++] = j;
        }
		return y;
	}

	static int [] filter2(int[] x, Criteria criteria) {
		int count = 0;
        for (int j : x) {
            if (criteria.accept(j)) count++;
        }
		int[] y = new int[count];
        int index = 0;
        for (int j : x) {
            if (criteria.accept(j)) y[index++] = j;
        }
		return y;
	}

	static void display(int[] x) {
        for (int j : x) System.out.println(j);
	}
}
