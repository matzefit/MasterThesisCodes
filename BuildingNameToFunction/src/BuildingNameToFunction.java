import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class BuildingNameToFunction
{
    public static void main(String[] args) throws IOException
    {
        PrintWriter printOutput = new PrintWriter("C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\whole LOD2 Model\\MergedLOD2YISHUN_NoBuildingParts_BYandFunction.gml");
        printOutput.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n        <CityModel xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.opengis.net/citygml/2.0\"\n        xmlns:xAL=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n        xmlns:gml=\"http://www.opengis.net/gml\"\n        xmlns:bldg=\"http://www.opengis.net/citygml/building/2.0\"\n        xmlns:app=\"http://www.opengis.net/citygml/appearance/2.0\"\n        xsi:schemaLocation=\"  http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd\">\n        <gml:description>3D City Model of Singapore</gml:description>\n        <gml:name>T12A0158</gml:name>\n        <gml:boundedBy>\n           <gml:Envelope srsDimension=\"3\" srsName=\"urn:ogc:def:crs,crs:EPSG::3414,crs:EPSG::6916\">\n              <gml:lowerCorner>27598.841 46298.548 5.348</gml:lowerCorner>\n              <gml:upperCorner>27682.132 46371.857 24.345</gml:upperCorner>\n           </gml:Envelope>\n        </gml:boundedBy>\n");

        BufferedReader br3=new BufferedReader(new FileReader("C:\\Users\\Matthias\\Desktop\\MasterThesis\\Data\\whole LOD2 Model\\MergedLOD2YISHUN_NoBuildingParts.gml"));

        String this_line=br3.readLine();

        while (this_line != null) {

            if(this_line.toString().indexOf("<bldg:function>")<0&&this_line.toString().indexOf(""))
            //Store all SurfaceMember elements of a solid in an array
            if (this_line.toString().indexOf("<gml:name>CHONG PANG MARKET</gml:name>")>=0) {
                printOutput.println("<bldg:function>");
            }
            this_line=br3.readLine();
        }
        br3.close();

        printOutput.flush();
        printOutput.close();
        System.out.println("end");
    }
}

