package br.com.sistemahospitalar;

public class Enfermeiro extends Funcionario {
    // Constante que define o salario base da categoria.
    private static final double SALARIO_BASE = 4750.0;

    public Enfermeiro(String nome, String cpf, int idade, int cargaHoraria)
        // Confere e dispara a excessão se nescessario
            throws CargaHorariaInvalidaException {
        // Repassa os dados para o construtor de funcionario,fixando enfermeiro como cargo
        super(nome, cpf, idade, "Enfermeiro", cargaHoraria);
    }

    @Override // sobrescrita
    public void calcularSalario() {
        setSalario(SALARIO_BASE); // Define o salario do enfermeiro como o salario base
    }
}
