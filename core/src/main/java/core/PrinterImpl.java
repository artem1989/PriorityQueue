package core;


import java.util.Arrays;

public class PrinterImpl implements Printer {

    private static final String DELIMMITER = ",";
    private static final String EMPTY_STRING = "";

    public <T extends Comparable<T>> String asSortedString(T... values) {
        if(values == null || Arrays.asList(values).isEmpty()) return EMPTY_STRING;
        if(Arrays.asList(values).contains(null)) throw new IllegalArgumentException("Null elements are not allowed");
        Arrays.sort(values);
        return buildStringFromSortedArray(values);
    }

    private <T extends Comparable<T>> String buildStringFromSortedArray(T[] values) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if(i != values.length - 1) sb.append(DELIMMITER);
        }
        return sb.toString();
    }
}
