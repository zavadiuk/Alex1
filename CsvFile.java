import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.lang.Math;

/**
 * Functionality to read a CSV file.
 */
public class CsvFile
{
  /** Constant to split the CSV file */
  private String m_sSplitter;
  /** Name of the current directory */
  private String m_sDirectoryName;  
  /** Name of the CSV file*/
  private String m_sCsvFileName;
  /** Flag to point if the current CSV file contains a header row */
  private boolean m_bHasHeader;
  /** Hash map to store CSV file's data */
  private SortedMap<Double, String[]> m_smCsvData;
  /** Contains the header of the CSV file */
  private String[] m_sHeader;

  /**
   * Ctor.
   */
  public CsvFile()
  {
    m_sSplitter       = "";
    m_sDirectoryName  = "";
    m_sCsvFileName    = "";
    m_bHasHeader      = false;
    m_sHeader         = null;
  }
  
  /**
   * Prints the columns on the screen.
   * 
   * @param sColumns columns to print
   */
  private void printColumns(String[] sColumns)
  {
    if (sColumns.length > 0)
    {
      // Print first element
      System.out.print(sColumns[0].toString());

      // Print other elements
      for (int iNum = 1; iNum < sColumns.length; ++iNum)
      {
        System.out.print("  " + sColumns[iNum].toString());
      }
    }
  }
  
  public void HasHeader(boolean bHasHeader)
  {
    m_bHasHeader = bHasHeader;
  }
  
  /**
   * Sets a splitter character.
   * 
   * @param sSplitter value of the splitter
   */
  public void SetSplitterCharacter(String sSplitter)
  {
    m_sSplitter = sSplitter;
  }
  
  /**
   * Reads a CSV file.
   *
   * @param sFileName name of the CSV file
   * @return true, if the file could be successfully read, otherwise false
   */
  public boolean ReadCsvFile(String sFileName)
  {
    boolean bRet = true;
    
    BufferedReader bufferReader = null;
    
    try
    {
      bufferReader = new BufferedReader(new FileReader(sFileName));
      m_smCsvData = new TreeMap<Double, String[]>();
      
      // Read the first line from the file
      String sLine = bufferReader.readLine();
      
      // Store the header?
      if (m_bHasHeader)
      {
        // Yes, do it...
        m_sHeader = sLine.split(m_sSplitter);
        // Move to the next line
        sLine = bufferReader.readLine();
      }

      // Read CSV file's data line after line
      while (sLine != null)
      {
        // Split the columns and read the lines
        String[] sCsvDataRow = sLine.split(m_sSplitter);
                
        // Store file data into the hash map
        Double dbKey = Double.parseDouble(sCsvDataRow[0].toString());

        // Put the data into the sorted map
        m_smCsvData.put(dbKey, sCsvDataRow);
        
        // read next line
        sLine = bufferReader.readLine();
      }
      // String everything = stringBuilder.toString();
    }
    catch (FileNotFoundException ex)
    {
      bRet = false;
      System.out.println("ERROR, " + ex.getMessage());
    }
    catch (IOException ex)
    {
      bRet = false;
      System.out.println("ERROR, " + ex.getMessage());
    }
    catch (NullPointerException ex)
    {
      bRet = false;
      System.out.println("ERROR, " + ex.getMessage());
    }    
    finally 
    {
      if (bufferReader != null)
      {
        try
        {
          bufferReader.close();
        }
        catch (IOException ex)
        {
          bRet = false;
          System.out.println("ERROR, " + ex.getMessage());
        }        
      }
    } // finally
    
    return bRet;
  }

  /**
   * Sorts data ...
   * 
   * @return 
   */
  public boolean PrintData()
  {
    boolean bRet = true;
    Double dbTotalAmount = 0.0;
    
    // Print the header
    if (m_bHasHeader)
    {
      // Print the elements according to the key      
      printColumns(m_sHeader);
      // Print new line
      System.out.println();
    }
    
    // Declare a iterator to read map's entries
    Iterator<Double> mIterator = m_smCsvData.keySet().iterator();
    
    // Move through the map
    while (mIterator.hasNext())
    {
      // Get the key of the current iterator's position
      Double dbKey = mIterator.next();
      // Print the elements according to the key      
      printColumns(m_smCsvData.get(dbKey));
      // Print new line
      System.out.println();
      // Calculate the total amount
      dbTotalAmount += dbKey;
    }
    
    // Print the total amount
    System.out.println("Total amount is: " + String.valueOf(Math.round(dbTotalAmount*100.00)/100.00) );
    
    return bRet;
  }
  
  /**
   * Returns sorted CSV data.
   * 
   * @return sorted CSV data (without the header)
   */
  public SortedMap<Double, String[]> GetRawData()
  {
    return m_smCsvData;
  }
  
  /**
   * returns the header.
   * 
   * @return header of the CSV file.
   */
  public String[] GetHeader()
  {
    return m_sHeader;
  }
}
