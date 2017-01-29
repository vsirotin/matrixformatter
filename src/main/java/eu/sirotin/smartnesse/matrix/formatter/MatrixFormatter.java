package eu.sirotin.smartnesse.matrix.formatter;

import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author vsirotin
 */
public class MatrixFormatter<T> {
    private static final String SPACE_SYMBOL = "" + ' ';

    public enum AllignCell{
        LEFT,
        RIGHT,
        CENTER
    };

    private final String[][] stringMatrix;
    private String columnSeparator = " ";
    private String headerlSeparator = null;
    private String footerSeparator = null;
    private int[] columnWidths;
    private int columnNo;
    private int rowNo;
    private String[][] header = null;
    private String nullRepresenter = " ";

    private AllignCell allignCell = AllignCell.LEFT;
    private StringBuilder strinBuilder;

    public MatrixFormatter(T[][] matrix) {

        rowNo = matrix.length;
        columnNo = matrix[0].length;
        stringMatrix = new String[rowNo][columnNo];
        columnWidths = new int[columnNo];

        //Fill string matrix
        IntStream.range(0, rowNo).forEach(i->{
            IntStream.range(0, columnNo).forEach(j-> stringMatrix[i][j] = "" + matrix[i][ j]);
        });

        //Calculate column width
        IntStream.range(0, columnNo).forEach(j->{
            columnWidths[j] = IntStream.range(0, rowNo).map(i-> stringMatrix[i][j].length()).max().getAsInt();
        });
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        strinBuilder = new StringBuilder();
        renderHeader();
        renderMatrix();
        rendeFooter();
        return strinBuilder.toString();
    }


    /**
     * @return the headerVerticalSeparator
     */
    public String getHeaderVerticalSeparator() {
        return headerlSeparator;
    }
    /**
     * @param headerSeparator the headerVerticalSeparator to set
     */
    public  MatrixFormatter<T>  setHeaderSeparator(char headerSeparator) {
        this.headerlSeparator = "" + headerSeparator;
        return this;
    }
    /**
     * @return the footherVerticalSeparator
     */
    public String getFootherVerticalSeparator() {
        return footerSeparator;
    }
    /**
     * @param footerSeparator the footherVerticalSeparator to set
     */
    public  MatrixFormatter<T>  setFooterSeparator(char footerSeparator) {
        this.footerSeparator = "" + footerSeparator;
        return this;
    }
    /**
     * @return the columnSeparator
     */
    public String getColumnSeparator() {
        return columnSeparator;
    }
    /**
     * @param columnSeparator the columnSeparator to set
     */
    public MatrixFormatter<T> setSolumnSeparator(String columnSeparator) {
        this.columnSeparator = columnSeparator;
        return this;
    }
    /**
     * @return the allignCell
     */
    public AllignCell getAllignCell() {
        return allignCell;
    }
    /**
     * @param allignCell the allignCell to set
     */
    public MatrixFormatter<T> setAllignCell(AllignCell allignCell) {
        this.allignCell = allignCell;
        return this;
    }

    public  MatrixFormatter<T>  setHeader(String[][] header) {
        int columnNo = header[0].length;
        assert columnNo == this.columnNo : "Column count in header must match column count in matrix";

        //Reset column width
        Stream.of(header).forEach(row->{
            IntStream.range(0, row.length).forEach(j->{
                columnWidths[j] = Math.max(columnWidths[j], row[j].length());
            });
        });
        this.header = header;
        return this;
    }

    /**
     * @return the header
     */
    public String[][] getHeader() {
        return header;
    }

    private void renderMatrix() {
        IntStream.range(0, rowNo).forEach(i->{
            strinBuilder.append(openRow());
            IntStream.range(0, columnNo).forEach(j-> strinBuilder.append(cell2String(j, stringMatrix[i][j])));
            strinBuilder.append(closeRow());
        });
    }

    private void renderHeader() {
        if(header == null)return;
        renderHeaderVerticalSeparator();
        IntStream.range(0, header.length).forEach(i->{
            strinBuilder.append(openRow());
            IntStream.range(0, columnNo).forEach(j-> strinBuilder.append(cell2String(j, header[i][j])));
            strinBuilder.append(closeRow());
        });
        renderHeaderVerticalSeparator();
    }

    private void renderHeaderVerticalSeparator() {
        if(headerlSeparator == null)return;
        renderHorizontalSeparator(headerlSeparator);
    }

    private void renderHorizontalSeparator(String separator) {
        IntStream.of(columnWidths).forEach(columnWidth-> strinBuilder.append(String.join("", Collections.nCopies(columnWidth + columnSeparator.length(), separator))));
        strinBuilder.append(String.join("", Collections.nCopies(columnSeparator.length(), separator)));
        strinBuilder.append("\n");
    }
    private void rendeFooter() {
        if(footerSeparator == null)return;
        renderHorizontalSeparator(footerSeparator);
    }

    private String closeRow() {
        return columnSeparator + "\n";
    }
    private String openRow() {
        return "";
    }
    private String cell2String(int columnNumber, String s) {
        if(s == null)s = nullRepresenter;
        int columnWidth = columnWidths[columnNumber];
        String cellContent = columnSeparator;
        switch(allignCell) {
            case LEFT:
                cellContent += allignLeft(s, columnWidth);
                break;

            case RIGHT:
                cellContent += allignRight(s, columnWidth);
                break;

            case CENTER:
                cellContent += allignCenter(s, columnWidth);
                break;

            default:
        }
        return cellContent;
    }
    private String allignLeft(String s, int columnWidth) {
        int dif = columnWidth - s.length();
        return dif == 0 ? s : s + String.format("%" + dif + "s", "");
    }

    private String allignRight(String s, int columnWidth) {
        int dif = columnWidth - s.length();
        return dif == 0 ? s : String.format("%" + dif + "s", "") + s;
    }

    private String allignCenter(String s, int columnWidth) {
        int dif = columnWidth - s.length();
        if(dif == 0)return s;
        int padLeft = dif/2;
        String result = "";
        if(padLeft > 0)result += String.join("", Collections.nCopies(padLeft, SPACE_SYMBOL));
        result += s;
        int padRight = dif - padLeft;
        if(padRight == 0)return result;
        result += String.join("", Collections.nCopies(padRight, SPACE_SYMBOL));
        return result;
    }
}
