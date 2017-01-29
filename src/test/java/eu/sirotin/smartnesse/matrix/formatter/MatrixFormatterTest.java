package eu.sirotin.smartnesse.matrix.formatter;

import eu.sirotin.smartnesse.matrix.formatter.MatrixFormatter.AllignCell;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author vsirotin
 */
public class MatrixFormatterTest {

        private static 	final String[][] _header = new String[][] {{"Col. 1", "Very long col.2", "Col.3", "Col.4"}};
        private static 	final String[][] _header2 = new String[][] {
                {"This is", 	"This is", 			"This is", 	"This is"},
                {"Col. 1", 		"Very long col.2", 	"Col.3", 	"Col.4"}
        };
        private Integer[][] _intMatrix;

        @Before
        public void setUp() throws Exception {
            _intMatrix = new Integer[][]{
                    new Integer[] {1,	2,		123456,		123},
                    new Integer[] {34, 	4567, 	12,			1},
                    new Integer[] {2,	254,	123456789, 	12}
            };
        }

        @Test
        public void testIntegerDefault() {
            String result = new MatrixFormatter<>(_intMatrix).toString();
            String expected =
                    " 1  2    123456    123 \n"
                + 	" 34 4567 12        1   \n"
                + 	" 2  254  123456789 12  \n";
            assertEquals(expected, result);
        }

        @Test
        public void testIntegerRightAlign() {
            String result = new MatrixFormatter<>(_intMatrix).setAllignCell(AllignCell.RIGHT).toString();
            String expected =
                    "  1    2    123456 123 \n"
                +   " 34 4567        12   1 \n"
                + 	"  2  254 123456789  12 \n";
            assertEquals(expected, result);
        }
        @Test
        public void testIntegerRightAlignVerticalSeparator() {
            String result = new MatrixFormatter<>(_intMatrix).setAllignCell(AllignCell.RIGHT).setSolumnSeparator("|").toString();
            String expected =
                    "| 1|   2|   123456|123|\n"
                +   "|34|4567|       12|  1|\n"
                + 	"| 2| 254|123456789| 12|\n";
            assertEquals(expected, result);
        }

        @Test
        public void testIntegerWithOneRowHeader() {
            String result = new MatrixFormatter<>(_intMatrix).setHeader(_header).toString();
            String expected =
                    " Col. 1 Very long col.2 Col.3     Col.4 \n"
                + 	" 1      2               123456    123   \n"
                + 	" 34     4567            12        1     \n"
                + 	" 2      254             123456789 12    \n";
            assertEquals(expected, result);
        }

        @Test
        public void testIntegerWithOneRowHeaderAndHeaderSeparator() {
            String result = new MatrixFormatter<>(_intMatrix).setHeader(_header).setHeaderSeparator('-').toString();
            String expected =
                    "----------------------------------------\n"
                            +	" Col. 1 Very long col.2 Col.3     Col.4 \n"
                            +	"----------------------------------------\n"
                            + 	" 1      2               123456    123   \n"
                            + 	" 34     4567            12        1     \n"
                            + 	" 2      254             123456789 12    \n";
            assertEquals(expected, result);
        }

        @Test
        public void testIntegerWithRightAlignAndVerticalSeparatorAndOneRowHeaderAndHeaderSeparator() {
            String result = new MatrixFormatter<>(_intMatrix).setHeader(_header).setHeaderSeparator('-').setAllignCell(AllignCell.RIGHT).setSolumnSeparator("|").toString();
            String expected =
                    "----------------------------------------\n"
                            +	"|Col. 1|Very long col.2|    Col.3|Col.4|\n"
                            +	"----------------------------------------\n"
                            + 	"|     1|              2|   123456|  123|\n"
                            + 	"|    34|           4567|       12|    1|\n"
                            + 	"|     2|            254|123456789|   12|\n";
            assertEquals(expected, result);
        }

        @Test
        public void testIntegerWithRightAlignAndVerticalSeparatorAndOneRowHeaderAndHeaderSeparatorAndHeaderSeparator() {
            String result = new MatrixFormatter<>(_intMatrix).setHeader(_header).setHeaderSeparator('-').setAllignCell(AllignCell.RIGHT).setSolumnSeparator("|").toString();
            String expected =
                    "----------------------------------------\n"
                            +	"|Col. 1|Very long col.2|    Col.3|Col.4|\n"
                            +	"----------------------------------------\n"
                            + 	"|     1|              2|   123456|  123|\n"
                            + 	"|    34|           4567|       12|    1|\n"
                            + 	"|     2|            254|123456789|   12|\n";
            assertEquals(expected, result);
        }

        @Test
        public void testIntegerWithRightAlignAndVerticalSeparatorAndOneRowHeaderAndHeaderSeparatorAndHeaderSeparatorAndFooterSeparator() {
            String result = new MatrixFormatter<>(_intMatrix).setHeader(_header).setHeaderSeparator('-').setFooterSeparator('=').setAllignCell(AllignCell.RIGHT).setSolumnSeparator("|").toString();
            String expected =
                    "----------------------------------------\n"
                            +	"|Col. 1|Very long col.2|    Col.3|Col.4|\n"
                            +	"----------------------------------------\n"
                            + 	"|     1|              2|   123456|  123|\n"
                            + 	"|    34|           4567|       12|    1|\n"
                            + 	"|     2|            254|123456789|   12|\n"
                            + 	"========================================\n";
            assertEquals(expected, result);
        }

        @Test
        public void testIntegerVerticalSeparatorAndTwoRowHeaderAndHeaderSeparatorAndHeaderSeparatorAndFooterSeparator() {
            String result = new MatrixFormatter<>(_intMatrix).setHeader(_header2).setHeaderSeparator('-').setFooterSeparator('=').setSolumnSeparator("|").toString();
            String expected =
                    "-------------------------------------------\n"
                            +	"|This is|This is        |This is  |This is|\n"
                            +	"|Col. 1 |Very long col.2|Col.3    |Col.4  |\n"
                            +	"-------------------------------------------\n"
                            + 	"|1      |2              |123456   |123    |\n"
                            + 	"|34     |4567           |12       |1      |\n"
                            + 	"|2      |254            |123456789|12     |\n"
                            + 	"===========================================\n";
            assertEquals(expected, result);
        }

        @Test
        public void testIntegerVerticalSeparatorAndTwoRowHeaderAndHeaderSeparatorAndHeaderSeparatorAndFooterSeparatorAndAlignCenter() {
            String result = new MatrixFormatter<>(_intMatrix).setHeader(_header2).setHeaderSeparator('-').setFooterSeparator('=').setSolumnSeparator("|").setAllignCell(AllignCell.CENTER).toString();
            String expected =
                    "-------------------------------------------\n"
                            +	"|This is|    This is    | This is |This is|\n"
                            +	"|Col. 1 |Very long col.2|  Col.3  | Col.4 |\n"
                            +	"-------------------------------------------\n"
                            + 	"|   1   |       2       | 123456  |  123  |\n"
                            + 	"|  34   |     4567      |   12    |   1   |\n"
                            + 	"|   2   |      254      |123456789|  12   |\n"
                            + 	"===========================================\n";
            assertEquals(expected, result);
        }

        @Test
        public void testDoubleVerticalSeparatorAndTwoRowHeaderAndHeaderSeparatorAndHeaderSeparatorAndFooterSeparatorAndAlignCenter() {

            //"Convert" integer matrix in double matrix
            Double[][] doubleMatrix = new Double[_intMatrix.length][_intMatrix[0].length];
            IntStream.range(0, _intMatrix.length).forEach((int i) ->{
                IntStream.range(0, _intMatrix[0].length).forEach((int j) -> {
                    doubleMatrix[i][j] = _intMatrix[i][j] + (i + j) / 10.0;
                });
            });

            String result = new MatrixFormatter<>(doubleMatrix).setHeader(_header2).setHeaderSeparator('-').setFooterSeparator('=').setSolumnSeparator("|").setAllignCell(AllignCell.CENTER).toString();
            String expected =
                    "-----------------------------------------------\n"
                    +	"|This is|    This is    |   This is   |This is|\n"
                    +	"|Col. 1 |Very long col.2|    Col.3    | Col.4 |\n"
                    +	"-----------------------------------------------\n"
                    + 	"|  1.0  |      2.1      |  123456.2   | 123.3 |\n"
                    + 	"| 34.1  |    4567.2     |    12.3     |  1.4  |\n"
                    + 	"|  2.2  |     254.3     |1.234567894E8| 12.5  |\n"
                    + 	"===============================================\n";
            assertEquals(expected, result);
        }
}