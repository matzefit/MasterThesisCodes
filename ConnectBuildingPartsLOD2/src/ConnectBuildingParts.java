import java.io.*;
import java.util.ArrayList;
import java.util.List;
//this Code is able to merge Single CityGML Files into one. 
// printOutput defines the path of the resulting merged file , folder defines the Path of the Folder where the GML files are located
//Per File All the lines until the tag <cityObjectMember> are skipped. These have to be manually added in the resulting file. 
public class ConnectBuildingParts
{
	public static void main(String[] args) throws IOException
	{
		//Input
		String folderpath="C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\Singapore CBD LOD2\\LOD2\\Single Files\\";
		File folder = new File(folderpath);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {

			if (file.isFile()) {

				String filename=file.getName();
				System.out.println(file.getName());
				System.out.println(file.getParentFile());
//Output folderpath with merged buildingpart files (single)
				PrintWriter printOutput = new PrintWriter("C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\Singapore CBD LOD2\\LOD2\\MergedBuildingparts\\"+"Solid"+filename);

				printOutput.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n        <CityModel xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.opengis.net/citygml/2.0\"\n        xmlns:xAL=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n        xmlns:gml=\"http://www.opengis.net/gml\"\n        xmlns:bldg=\"http://www.opengis.net/citygml/building/2.0\"\n        xmlns:app=\"http://www.opengis.net/citygml/appearance/2.0\"\n        xsi:schemaLocation=\"  http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd\">\n        <gml:description>3D City Model of Singapore</gml:description>\n        <gml:name>T12A0158</gml:name>\n        <gml:boundedBy>\n           <gml:Envelope srsDimension=\"3\" srsName=\"urn:ogc:def:crs,crs:EPSG::3414,crs:EPSG::6916\">\n              <gml:lowerCorner>27598.841 46298.548 5.348</gml:lowerCorner>\n              <gml:upperCorner>27682.132 46371.857 24.345</gml:upperCorner>\n           </gml:Envelope>\n        </gml:boundedBy>\n");



				BufferedReader br3=new BufferedReader(new FileReader(folderpath+file.getName()));

				//Input File must be a File of buildings already merged together

				// PrintWriter object for Outputfiles

				String this_line=br3.readLine();
				System.out.println(this_line);

				boolean CityobjectMembersection = false;
				List<String> surfaceMemberarray = new ArrayList<String>();
				List<String> gmlSolid = new ArrayList<String>();
				while (this_line != null) {

					//store all entries of gml solid with its id inside an array
					if(this_line.toString().indexOf("<gml:Solid gml:id")>=0) {
						gmlSolid.add(this_line);

					}
					//Store all SurfaceMember elements of a solid in an array
					if (this_line.toString().indexOf("<gml:surfaceMember xlink:href=")>=0) {
						surfaceMemberarray.add(this_line.toString());
					}
					this_line=br3.readLine();
				}




				//Second While

				br3.close();


				br3=new BufferedReader(new FileReader(folderpath+file.getName()));
				this_line=br3.readLine();
				while (this_line != null) {


					//Set a flag at element CityObjectmember
					//Everything inside should be printed to new file
					if (this_line.indexOf("<cityObjectMember>") >= 0)
						CityobjectMembersection=true;

					if (this_line.indexOf("</cityObjectMember>") >= 0)
						CityobjectMembersection=false;

					//Do the following if inside a cityobjectmember  element
					if (CityobjectMembersection == true) {


						//ignores all lines where buildingparts or consistsofbuildingpart, lod2Solid, CompositeSurface, Solid, exterior  elements are declared
						if (this_line.toString().indexOf("bldg:BuildingPart")<0 && this_line.toString().indexOf("bldg:consistsOfBuildingPart")<0 ){
							if(this_line.toString().contains("<bldg:roofType")==false&&this_line.toString().contains("</app")==false&&this_line.toString().contains("<app")==false&&this_line.toString().contains("bldg:lod2Solid")==false&& this_line.toString().contains("gml:exterior")==false && this_line.toString().contains("gml:CompositeSurface")==false && this_line.toString().contains("</gml:Solid")==false&& this_line.toString().contains("<gml:surfaceMember xlink:href=")==false&& this_line.toString().contains("gml:Solid gml:id")==false) {




								//build the lod2Solid element
								if(this_line.toString().contains("<bldg:storeysAboveGround>")==true)	{
									printOutput.println(this_line);

									printOutput.println("			<bldg:lod2Solid>\n");
									printOutput.println(gmlSolid.get(0));
									printOutput.println("							<gml:exterior>\r\n" +
											"								<gml:CompositeSurface>");
									//printOutput.println(gmlSolid.get(0).toString());
									for (String str : surfaceMemberarray) {
										printOutput.println(str);
										System.out.println(str);
									}
									printOutput.println("								</gml:CompositeSurface>\r\n" +
											"							</gml:exterior>\r\n" +
											"						</gml:Solid>\r\n" +
											"					</bldg:lod2Solid>");

								}
								else if(this_line.contains("<gml:Polygon gml:id=")==true){
									printOutput.println(this_line);
									printOutput.println("<gml:exterior>");

								}
								else if(this_line.contains("</gml:LinearRing>")==true){
									printOutput.println(this_line);
									printOutput.println("</gml:exterior>");
								}

								else{
									printOutput.println(this_line);
									System.out.println(this_line);
								}


							}
						}
					}

					this_line=br3.readLine();
				}



				br3.close();

				printOutput.println("</cityObjectMember>");
				printOutput.println("</CityModel>");
				printOutput.flush();
				printOutput.close();
				System.out.println("end");
			}
		}
	}
} 