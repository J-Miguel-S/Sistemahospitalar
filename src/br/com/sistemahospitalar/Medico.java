package br.com.sistemahospitalar;

public class Medico extends Funcionario implements Plantonista {
    // Define o salario base dos medicos, e a quantidade de horas por plantão
    private static final double SALARIO_BASE = 15000.0;
    private static final int HORAS_POR_PLANTAO = 12;

    public Medico(String nome, String cpf, int idade, int cargaHoraria)
            throws CargaHorariaInvalidaException {
        super(nome, cpf, idade, "Medico", cargaHoraria);
    }

    // Sobrescrita
    @Override
    public void calcularSalario() {
        setSalario(SALARIO_BASE);
    }

    @Override
    public double calcularValorPlantao(int horas) {
        // valor da hora = salário / (carga horária mensal aproximada)
        return getSalario() / (getCargaHoraria() * 4.0) * horas;
    }

    // Sobrecarga de calcularSalario, recebendo plantões extras
    public void calcularSalario(int plantoesExtras) throws CargaHorariaInvalidaException {
        int horasExtras = plantoesExtras * HORAS_POR_PLANTAO;
        int novaCargaHoraria = getCargaHoraria() + horasExtras;

        // valor extra calculado ANTES de atualizar a carga horária (usa a taxa atual)
        double valorExtra = calcularValorPlantao(horasExtras);

        // setCargaHoraria já valida o limite de 60h e lança a exceção se necessário
        setCargaHoraria(novaCargaHoraria);

        // CORREÇÃO: soma o valor do plantão ao salário já existente
        setSalario(getSalario() + valorExtra);

        System.out.printf("Plantoes adicionados com sucesso. Valor extra: R$ %.2f%n", valorExtra);
    }
}
