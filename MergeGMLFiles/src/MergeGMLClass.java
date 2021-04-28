import java.io.*; 
//this Code is able to merge Single CityGML Files into one. 
// printOutput defines the path of the resulting merged file , folder defines the Path of the Folder where the GML files are located
//Per File All the lines until the tag <cityObjectMember> are skipped. These have to be manually added in the resulting file. 
public class MergeGMLClass  
{ 
    public static void main(String[] args) throws IOException  
    { 
        // PrintWriter object for Outputfiles
        PrintWriter printOutput = new PrintWriter("C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\Single Buildings\\LOD2\\MergedLOD2.gml");                               
        File folder = new File("C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\Single Buildings\\LOD2\\AllLOD2Buildings");
        File[] listOfFiles = folder.listFiles();
        boolean inside_section = false;        
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                System.out.println(file.getParentFile());
                BufferedReader br3=new BufferedReader(new FileReader("C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\Single Buildings\\LOD2\\AllLOD2Buildings\\"+file.getName()));
                String this_line=br3.readLine();                
                System.out.println(this_line);
                while (this_line != null) {
                	//if (this_line.contentEquals("<cityObjectMember>"))
                	if (this_line.indexOf("<cityObjectMember>") >= 0)
                	   inside_section = true;
                	// if (this_line.contentEquals("</CityModel>")) 
                    if (this_line.indexOf("</CityModel>") >= 0)
                  	  inside_section = false;                		                	                	
                	if (inside_section == true) {
                    	if(this_line != null && this_line.length()!=0) {
                    		printOutput.println(this_line);                    		
                    	}
                	}                	
                	this_line=br3.readLine();
                }
                br3.close();                
            }            
        }        
        printOutput.println("</CityModel>");
        printOutput.flush();           
        printOutput.close();           
        System.out.println("end"); 
    } 
} 