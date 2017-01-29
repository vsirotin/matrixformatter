Simple Java 8 solution for formatting representation of matrix diverse types (Double, Integer, String etc.). This is the only class without using other libraries. 

The matrix can be set e.g.as:

``
Integer[][] myMatrix = new Integer[][]{   
    
        new Integer[] {1,   2,  123456, 123},
        new Integer[] {12,  1234,   12,     1},    
        new Integer[] {1,   123,    123456789,  12}
	
    
The result of formatting can be simple as:
    
```	
[1,       2,    	123456,          123]
    
[12,      1234, 	12,              1  ]
    
[1,   	  123,  	123456789,  	  12 ]

 ```	
    
 or with headers, footers and cell align as:

   
 ------------------------------------------------------
|This is    |    This is     |   This is      |This is |
|Col. 1     |Very long col.2 |    Col.3       | Col.4  |
--------------------------------------------------------
|  1.0      |      2.1       |  123456.2      | 123.3  |
| 34.1      |    4567.2      |    12.3        |  1.4   |
|  2.2      |     254.3      |1.234567894E8   | 12.5   |
========================================================
```
    	
The call of formatter with setting of formatting options can be made as:
...
    
String result = new MatrixFormatter<>(doubleMatrix).setHeader(_header2).setHeaderSeparator('-').setFooterSeparator('=').setSolumnSeparator("|").setAllignCell(AllignCell.CENTER).toString();

    
Please see JUnit test for details. 
