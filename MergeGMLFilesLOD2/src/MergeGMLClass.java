import java.io.*; 
//this Code is able to merge Single CityGML Files into one. 
// printOutput defines the path of the resulting merged file , folder defines the Path of the Folder where the GML files are located
//Per File All the lines until the tag <cityObjectMember> are skipped. These have to be manually added in the resulting file. 
public class MergeGMLClass  
{ 
    public static void main(String[] args) throws IOException  
    { 
        // PrintWriter object for Outputfiles
    	String folderpath="C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\Most influential Parameters Analysis\\";
        PrintWriter printOutput = new PrintWriter("C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\Most influential Parameters Analysis\\MergedInfluenceParamsYISHUNLOD2.gml");                               
        printOutput.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n        <CityModel xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.opengis.net/citygml/2.0\"\n        xmlns:xAL=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n        xmlns:gml=\"http://www.opengis.net/gml\"\n        xmlns:bldg=\"http://www.opengis.net/citygml/building/2.0\"\n        xmlns:app=\"http://www.opengis.net/citygml/appearance/2.0\"\n        xsi:schemaLocation=\"  http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd\">\n        <gml:description>3D City Model of Singapore</gml:description>\n        <gml:name>T12A0158</gml:name>\n        <gml:boundedBy>\n           <gml:Envelope srsDimension=\"3\" srsName=\"urn:ogc:def:crs,crs:EPSG::3414,crs:EPSG::6916\">\n              <gml:lowerCorner>27598.841 46298.548 5.348</gml:lowerCorner>\n              <gml:upperCorner>27682.132 46371.857 24.345</gml:upperCorner>\n           </gml:Envelope>\n        </gml:boundedBy>\n");
        File folder = new File(folderpath);
        File[] listOfFiles = folder.listFiles();
        boolean inside_section = false;        
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                System.out.println(file.getParentFile());
                BufferedReader br3=new BufferedReader(new FileReader(folderpath+file.getName()));
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
                    	if(this_line != null && this_line.length()!=0 && this_line.toString().contains("<app")==false && this_line.toString().contains("</app")==false) {
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