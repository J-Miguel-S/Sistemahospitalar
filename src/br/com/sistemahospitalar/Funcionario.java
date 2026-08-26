package br.com.sistemahospitalar;
// Classe abstratic que é um "modelo" base
public abstract class Funcionario extends Pessoa {
    // Constante que define a regra de negócio máxima de horas semanais
    private static final int LIMITE_CARGA_HORARIA_SEMANAL = 60;

    private String cargo;
    private double salario;
    private int cargaHoraria;

    // O construtor avisa que pode laçar uma exceção se a carga horária for invalida
    public Funcionario(String nome, String cpf, int idade, String cargo, int cargaHoraria)
            throws CargaHorariaInvalidaException {
        // Super chama o construtor da classe pai
        super(nome, cpf, idade);
        // Verifique a regra de negocio antes de atribuir o valor
        validarCargaHoraria(cargaHoraria);
        this.cargo = cargo;
        this.cargaHoraria = cargaHoraria;
    }

    // Método responsável pela validação da carga horária semanal.
    //  Lança CargaHorariaInvalidaException caso o valor exceda o limite permitido.
    protected void validarCargaHoraria(int cargaHoraria) throws CargaHorariaInvalidaException {
        if (cargaHoraria > LIMITE_CARGA_HORARIA_SEMANAL) {
            // Se passar de 60, o programa "estoura" um erro proporsitalmente
            throw new CargaHorariaInvalidaException(
                    "ERRO: carga horaria (" + cargaHoraria + "h) excede o limite de "
                            + LIMITE_CARGA_HORARIA_SEMANAL + " horas semanais");
        }
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    // Ao modificar a carga horaria, o sistema revalida o limite.
    public void setCargaHoraria(int cargaHoraria) throws CargaHorariaInvalidaException {
        validarCargaHoraria(cargaHoraria);
        this.cargaHoraria = cargaHoraria;
    }

    // Metodo abstrato, obrigando as classes filhar a ter
    public abstract void calcularSalario();

    //Imprime as informações do funcionário no console
    public void exibirDados() {
        System.out.printf("Nome: %s%nCargo: %s%nSalario: R$ %.2f%nCarga Horaria: %dh%n",
                getNome(), cargo, salario, cargaHoraria);
    }
}
