import java.util.SortedMap;

/**
 * Reads an CSV file and generates an XML file from that.
 */
public class XmlFile
{
  private CsvFile m_CSV;
  
  /**
   * Ctor.
   */
  public XmlFile()
  {
    // nothing to do here yet...
  }
  
  /**
   * Sets CSV file object.
   * 
   * @param csvObj CsvFile object
   */
  public void SetCsvFile(CsvFile csvObj)
  {
    m_CSV = csvObj;
  }
  
  /**
   * Creates an XML file.
   * 
   * @return true, if the XML file could be successfully generated, otherwise false
   */
  public boolean GenerateXmlFile()
  {
    boolean bRet = true;
    
    // Read the header
    String[] sHeader = m_CSV.GetHeader();
    // Get (sorted) CSV data
    SortedMap<Double, String[]> smCsvData = m_CSV.GetRawData();
    
    // Build an XML file from the provided data
    
    return bRet;
  }
  
}
