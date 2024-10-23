package ej1;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main {
    protected static ArrayList<Alumno> studentList = new ArrayList<>();
    protected static DocumentBuilderFactory fnaf;
    protected static DocumentBuilder constructor;
    protected static DOMImplementation implement;
    protected static Document doc;

    /*
    Objetivo:
      <Alumnos>
         <Alumno nombre=""apellidos=""ciclo="" curso="" grupo="" nia="" genero="" fechadenacimiento="" >
         </Alumno>
         x5
      </Alumnos>
    */
    protected static void createElements(Alumno a) {
        Element alumno = doc.createElement("Alumno");
        createChildElementWithAttribute(alumno, "nombre", a.getNombre());
        createChildElementWithAttribute(alumno, "apellidos", a.getApellidos());
        createChildElementWithAttribute(alumno, "ciclo", a.getCiclo());
        createChildElementWithAttribute(alumno, "curso", a.getCurso());
        createChildElementWithAttribute(alumno, "grupo", a.getGrupo());
        createChildElementWithAttribute(alumno, "nia", String.valueOf(a.getNia()));
        createChildElementWithAttribute(alumno, "genero", String.valueOf(a.getGenero()));
        createChildElementWithAttribute(alumno, "fechadenacimiento", a.getFechadenacimiento().toString());
        doc.getDocumentElement().appendChild(alumno);
    }

    protected static void createChildElementWithAttribute(Element parent, String name, String contenido) {
        parent.setAttribute(name, contenido);
    }

    protected static void createDocument() {
        try {
            fnaf = DocumentBuilderFactory.newInstance(); 
            constructor = fnaf.newDocumentBuilder();
            implement = constructor.getDOMImplementation();
            doc = implement.createDocument(null, "Alumnos", null);
            doc.setXmlVersion("1.0");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    protected static void createFile(String name) {
        try {
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(name));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        studentList = CreacionDeAlumnos.create5students();
        createDocument();
        for (Alumno a : studentList) {
            createElements(a);
        }
        createFile("resultado.xml");
    }
}