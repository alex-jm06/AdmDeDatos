   package modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class ArchivoXML {

    public static List<Huesped> leerXML() {

        List<Huesped> listaHuespedes = new ArrayList<Huesped>();

        try {

            File archivo = new File("huespedes.xml");

            if (!archivo.exists()) {
                return listaHuespedes;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);

            document.getDocumentElement().normalize();

            NodeList listaNodos = document.getElementsByTagName("HUESPED");

            for (int i = 0; i < listaNodos.getLength(); i++) {

                Node nodo = listaNodos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) nodo;

                    int id = Integer.parseInt(
                            element.getElementsByTagName("ID").item(0).getTextContent());

                    String nombre =
                            element.getElementsByTagName("NOMBRE").item(0).getTextContent();

                    String apellidos =
                            element.getElementsByTagName("APELLIDOS").item(0).getTextContent();

                    String procedencia =
                            element.getElementsByTagName("PROCEDENCIA").item(0).getTextContent();

                    listaHuespedes.add(
                            new Huesped(
                                    id,
                                    nombre,
                                    apellidos,
                                    procedencia
                            )
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaHuespedes;
    }

    public static void crearXML(String nomArchivo,
            List<Huesped> listaHuespedes)
            throws ParserConfigurationException,
            TransformerConfigurationException,
            TransformerException {

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            DOMImplementation implementation =
                    builder.getDOMImplementation();

            Document document =
                    implementation.createDocument(
                            null,
                            nomArchivo,
                            null
                    );

            document.setXmlVersion("1.0");

            Element raiz =
                    document.getDocumentElement();

            for (int i = 0; i < listaHuespedes.size(); i++) {

                Element itemNode =
                        document.createElement("HUESPED");

                Element idNode =
                        document.createElement("ID");

                Text nodeIdValue =
                        document.createTextNode(
                                "" + listaHuespedes.get(i).getIdHuesped()
                        );

                idNode.appendChild(nodeIdValue);

                Element nombreNode =
                        document.createElement("NOMBRE");

                Text nodeNombreValue =
                        document.createTextNode(
                                listaHuespedes.get(i).getNombre()
                        );

                nombreNode.appendChild(nodeNombreValue);

                Element apellidosNode =
                        document.createElement("APELLIDOS");

                Text nodeApellidosValue =
                        document.createTextNode(
                                listaHuespedes.get(i).getApellidos()
                        );

                apellidosNode.appendChild(nodeApellidosValue);

                Element procedenciaNode =
                        document.createElement("PROCEDENCIA");

                Text nodeProcedenciaValue =
                        document.createTextNode(
                                listaHuespedes.get(i).getProcedencia()
                        );

                procedenciaNode.appendChild(nodeProcedenciaValue);

                itemNode.appendChild(idNode);
                itemNode.appendChild(nombreNode);
                itemNode.appendChild(apellidosNode);
                itemNode.appendChild(procedenciaNode);

                raiz.appendChild(itemNode);
            }

            Source source = new DOMSource(document);

            Result result =
                    new StreamResult(
                            new java.io.File(
                                    nomArchivo + ".xml"
                            )
                    );

            Transformer transformer =
                    TransformerFactory
                            .newInstance()
                            .newTransformer();

            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            throw e;
        }
    }
}