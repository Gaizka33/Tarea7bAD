package ej1;

import org.w3c.dom.Element;

public class Main7b extends Main {
    public static void main(String[] args) {
        studentList = CreacionDeAlumnos.create5students();
        createDocument();
        for (Alumno a : studentList) {
            createElementsWithAttributes(a);
        }
        createFile("resultadoAtributos.xml");
    }

    protected static void createElementsWithAttributes(Alumno a) {
        Element alumno = doc.createElement("Alumno");
        alumno.setAttribute("nombre", a.getNombre());
        alumno.setAttribute("apellidos", a.getApellidos());
        alumno.setAttribute("ciclo", a.getCiclo());
        alumno.setAttribute("curso", a.getCurso());
        alumno.setAttribute("grupo", a.getGrupo());
        alumno.setAttribute("nia", String.valueOf(a.getNia()));
        alumno.setAttribute("genero", String.valueOf(a.getGenero()));
        alumno.setAttribute("fechadenacimiento", a.getFechadenacimiento().toString());
        doc.getDocumentElement().appendChild(alumno);
    }
}
