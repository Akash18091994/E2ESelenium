package GenericFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException;

public class genericFunctions {

	public static void compareCSV(String file1, String file2, String file3) throws IOException
	{
		//String file1="C:\\Users\\e5647787\\Downloads\\ActualCSV.csv";
	    //String file2="C:\\Users\\e5647787\\Downloads\\ExpectedCSV.csv";
	    //String file3="C:\\Users\\e5647787\\Downloads\\Difference.csv";
	    ArrayList al1=new ArrayList();
	    ArrayList al2=new ArrayList();

	    BufferedReader CSVFile1 = new BufferedReader(new FileReader(file1));
	    String dataRow1 = CSVFile1.readLine();
	    while (dataRow1 != null)
	    {
	        String[] dataArray1 = dataRow1.split(",");
	        for (String item1:dataArray1)
	        { 
	           al1.add(item1);
	        }

	        dataRow1 = CSVFile1.readLine(); // Read next line of data.
	    }

	     CSVFile1.close();

	    BufferedReader CSVFile2 = new BufferedReader(new FileReader(file2));
	    String dataRow2 = CSVFile2.readLine();
	    while (dataRow2 != null)
	    {
	        String[] dataArray2 = dataRow2.split(",");
	        for (String item2:dataArray2)
	        { 
	           al2.add(item2);

	        }
	        dataRow2 = CSVFile2.readLine(); // Read next line of data.
	    }
	     CSVFile2.close();

	     for(Object bs:al2)
	     {
	         al1.remove(bs);
	     }

	     int size=al1.size();
	     if(size == 0)
	     {
	    	 System.out.println("Both CSVFiles are equal");
	     }
	     else
	     {
	    	 System.out.println("Both CSVFiles are not equal");
	    	 System.out.println("Total number of differences are --> " + size);
	    	 try
		        {
		            FileWriter writer=new FileWriter(file3);
		            while(size!=0)
		            {
		                size--;
		                writer.append("" + al1.get(size));
		                writer.append('\n');
		            }
		            writer.flush();
		            writer.close();
		        }
		        catch(IOException e)
		        {
		            e.printStackTrace();
		        }
	    	 System.out.println("Check differences in file --> " + file3);
	     }
	}

	public static void compareXML(String file1, String file2) throws SAXException, IOException
	{
		FileInputStream fis1 = new FileInputStream("C:\\Users\\e5647787\\Downloads\\EnterpriseWorkspace - Copy.xml"); 
		FileInputStream fis2 = new FileInputStream("C:\\Users\\e5647787\\Downloads\\EnterpriseWorkspace.xml");

		BufferedReader source = new BufferedReader(new InputStreamReader(fis1)); 
		BufferedReader target = new BufferedReader(new InputStreamReader(fis2));

		XMLUnit.setIgnoreWhitespace(true);
		
		List differences = compareXML(source, target);
		
		printDifferences(differences);
	}
	
    public static List compareXML(Reader source, Reader target) throws SAXException, IOException{ 
		
		//creating Diff instance to compare two XML files 
		org.custommonkey.xmlunit.Diff xmlDiff = new org.custommonkey.xmlunit.Diff(source, target); 
		
		//for getting detailed differences between two xml files 
		DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff); 
		
		return detailXmlDiff.getAllDifferences(); 
		}

    public static void printDifferences(List differences)
   { 
	int totalDifferences = differences.size(); 
	System.out.println("==============================="); 
	System.out.println("Total differences : " + totalDifferences); 
	System.out.println("================================"); 
	for(Object difference : differences)
	{ 
		System.out.println(difference); 
    } 
  }
    
    public static void compareExcel(String file1, String file2)
    {
    	try
        {
            FileInputStream excellFile1 = new FileInputStream(new File(file1));
            FileInputStream excellFile2 = new FileInputStream(new File(file2));

            XSSFWorkbook workbook1 = new XSSFWorkbook(excellFile1);
            XSSFWorkbook workbook2 = new XSSFWorkbook(excellFile2);

            XSSFSheet sheet1 = workbook1.getSheetAt(0);
            XSSFSheet sheet2 = workbook2.getSheetAt(0);

            if(compareTwoSheets(sheet1, sheet2))
            {
                System.out.println("\nTwo Excelsheets are Equal");
            }
            else
            {
                System.out.println("\nTwo Excelsheets are Not Equal");
            }
            excellFile1.close();
            excellFile2.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static boolean compareTwoSheets(XSSFSheet sheet1, XSSFSheet sheet2)
    {
        int firstRow1 = sheet1.getFirstRowNum();
        int lastRow1 = sheet1.getLastRowNum();
        boolean equalSheets = true;
        for(int i=firstRow1; i <= lastRow1; i++)
        {
            System.out.print("___________________________");
            System.out.println("\nComparing Row "+i);
            System.out.println("___________________________");
            XSSFRow row1 = sheet1.getRow(i);
            XSSFRow row2 = sheet2.getRow(i);
            if(!compareTwoRows(row1, row2))
            {
                equalSheets = false;
                System.out.println(" Row "+i+" | Not Equal");
            }
            else
            {
                System.out.println(" Row "+i+" | Equal");
            }
        }
        return equalSheets;
        
    }
    
    public static boolean compareTwoRows(XSSFRow row1, XSSFRow row2)
    {
        if((row1 == null) && (row2 == null))
        {
            return true;
        }
        else if((row1 == null) || (row2 == null))
        {
            return false;
        }
        int firstCell1 = row1.getFirstCellNum();
        int lastCell1 = row1.getLastCellNum();
        boolean equalRows = true;

        for(int i=firstCell1; i <= lastCell1; i++)
        {
            XSSFCell cell1 = row1.getCell(i);
            XSSFCell cell2 = row2.getCell(i);
            if(!compareTwoCells(cell1, cell2))
            {
                equalRows = false;
                System.err.println("Cell "+i+" | Not Equal");
            }
            else
            {
                System.out.println("Cell "+i+" | Equal");
            }
        }
        return equalRows;
    }

    public static boolean compareTwoCells(XSSFCell cell1, XSSFCell cell2)
    {
        if((cell1 == null) && (cell2 == null))
        {
            return true;
        }
        else if((cell1 == null) || (cell2 == null))
        {
            return false;
        }
        
        boolean equalCells = false;
        //int type1 = cell1.getCellType();
        //int type2 = cell2.getCellType();
        
        CellType type1 = cell1.getCellType();
        CellType type2 = cell2.getCellType();
        
        if (type1 == type2)
        {
            if (cell1.getCellStyle().equals(cell2.getCellStyle()))
            {
                switch (cell1.getCellType())
                {
                    case FORMULA:
                        if (cell1.getCellFormula().equals(cell2.getCellFormula()))
                        {
                            equalCells = true;
                        }
                        break;
                    case NUMERIC:
                        if (cell1.getNumericCellValue() == cell2.getNumericCellValue())
                        {
                            equalCells = true;
                        }
                        break;
                    case STRING:
                        if (cell1.getStringCellValue().equals(cell2.getStringCellValue()))
                        {
                            equalCells = true;
                        }
                        break;
                    case BLANK:
                        if (cell1.getCellType() == cell2.getCellType())
                        {
                            equalCells = true;
                        }
                        break;
                    case BOOLEAN:
                        if (cell1.getBooleanCellValue() == cell2.getBooleanCellValue())
                        {
                            equalCells = true;
                        }
                        break;
                    case ERROR:
                        if (cell1.getErrorCellValue() == cell2.getErrorCellValue())
                        {
                            equalCells = true;
                        }
                        break;
                    default:
                        if (cell1.getStringCellValue().equals(cell2.getStringCellValue()))
                        {
                            equalCells = true;
                        }
                        break;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        return equalCells;
    }
	
	
}
