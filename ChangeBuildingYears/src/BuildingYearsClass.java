import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BuildingYearsClass {
	

    public static void main(String[] args)
    {
        modifyFile("C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\whole LOD1 Model\\MergedAllYishun - Copy.gml", "creationDate", "bldg:yearOfConstruction");
         
        System.out.println("done");
    }
 
 static void modifyFile(String filePath, String oldString, String newString)
 {
     File fileToBeModified = new File(filePath);      
     String oldContent = "";      
     BufferedReader reader = null;      
     FileWriter writer = null;      
     try
     {
         reader = new BufferedReader(new FileReader(fileToBeModified));          
         //Reading all the lines of input text file into oldContent          
         String line = reader.readLine();          
         while (line != null) 
         {
             oldContent = oldContent + line + System.lineSeparator();
              
             line = reader.readLine();
         }
          
         //Replacing oldString with newString in the oldContent
          
         String newContent = oldContent.replaceAll(oldString, newString);
          
         //Rewriting the input text file with newContent
          
         writer = new FileWriter(fileToBeModified);
          
         writer.write(newContent);
     }
     catch (IOException e)
     {
         e.printStackTrace();
     }
     finally
     {
         try
         {
             //Closing the resources              
             reader.close();              
             writer.close();
         } 
         catch (IOException e) 
         {
             e.printStackTrace();
         }
     }
 }	
}
    


//public static void main(String[] args) throws IOException  
//{ 
//	//PrintWriter printOutput = new PrintWriter("C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\whole LOD1 Model\\Merged_ModBY_AllYishun.gml");
//	BufferedReader br3=new BufferedReader(new FileReader("C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\whole LOD1 Model\\MergedAllYishun.gml"));
//String line=br3.readLine();
//StringBuffer inputBuffer = new StringBuffer();
//while (line != null) {
//	inputBuffer.append(line);
//	inputBuffer.append('\n');
//}
//String inputStr=inputBuffer.toString();
//br3.close();
//
//System.out.println(inputStr);
////printOutput.flush();           
////printOutput.close();           
//System.out.println("end"); 
//}
	    
	    


