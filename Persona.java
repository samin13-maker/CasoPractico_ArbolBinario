public class Persona {
    private int matricula;
    private String categoria; 
    private int edad;

    public Persona(int matricula, String categoria, int edad) {
        this.matricula = matricula;
        this.categoria = categoria;
        this.edad = edad;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "Matrícula: " + matricula + " | Categoría: " + categoria + " | Edad: " + edad;
    }
}
