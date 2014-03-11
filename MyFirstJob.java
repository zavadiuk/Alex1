public class MyFirstJob
{
  /** Constant used as a splitter by CSV files. */
  private static final String STR_SPLITTER = ",";
    
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args)
  {
    CsvFile csvFile = new CsvFile();
    // Do some configuration on the CSV file
    csvFile.SetSplitterCharacter(STR_SPLITTER);
    csvFile.HasHeader(true);
    csvFile.ReadCsvFile("C:\\workspace\\MyFirstJo\\Payments.csv");
    // Print CSV data (sorted) on the screen
    csvFile.PrintData();
    
    // Generate an XML file
    XmlFile xmlFile = new XmlFile();
    xmlFile.SetCsvFile(csvFile);
    // Generate an XML file on the disk
    if (xmlFile.GenerateXmlFile())
    {
      System.out.println("OK, XML file has been successfully created.");
    }
    else
    {
      System.out.println("ERROR, XML file hasn't been created.");
    }
    
    System.out.println("Done.");
  }    
}
