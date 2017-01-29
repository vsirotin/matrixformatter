Simple Java 8 solution for formatting representation of matrix diverse types (Double, Integer, String etc.). This is the only class without using other libraries. 

The matrix can be set e.g.as:

```
Integer[][] myMatrix = new Integer[][]{   
    
        new Integer[] {1,   2,  123456, 123},
        new Integer[] {12,  1234,   12,     1},    
        new Integer[] {1,   123,    123456789,  12}
```	
    
The result of formatting can be simple as:
    
```	
[1,       2,    	123456,          123]
    
[12,      1234, 	12,              1  ]
    
[1,   	  123,  	123456789,  	  12 ]

 ```	
    
 or with headers, footers and cell align.
    	
The call of formatter with setting of formatting options can be made as:
```
    
String result = new MatrixFormatter<>(doubleMatrix).setHeader(_header2).setHeaderSeparator('-').setFooterSeparator('=').setSolumnSeparator("|").setAllignCell(AllignCell.CENTER).toString();
```   
Please see JUnit test for details. 
