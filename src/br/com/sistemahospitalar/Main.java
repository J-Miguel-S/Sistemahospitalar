package br.com.sistemahospitalar;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String nome, CPF;
        int idade, cargaHoraria, opc;

        Scanner scanner = new Scanner(System.in);
        // ArrayList para guardar objetos do tipo funcionario
        ArrayList<Funcionario> hospital = new ArrayList<>();

        //loop infinito até digitar 6
        while (true) {
            System.out.println("---------------------------------------------------------------------------------");
            // lê a opção deigitada pelo usuario e converte em int
            System.out.println("1) Adicionar Funcionario\n2) Remover funcionario\n3) Alterar Funcionario" +
                    "\n4) Exibir Relatorios\n5) Adicionar Plantão\n6) Finalizar programa.");
            opc = Integer.parseInt(scanner.nextLine());

            //Estrutura de decisão principal do menu
            switch (opc) {
                case 1://Cadastra funcionario
                    System.out.println("1) Medico\n2) Enfermeiro");
                    int tipoFun = Integer.parseInt(scanner.nextLine()); // Lẽ o tipo do funcionario
                    // Coleta dados para incerir no construtor
                    System.out.println("Digite o nome: ");
                    nome = scanner.nextLine();
                    System.out.println("Digite o CPF: ");
                    CPF = scanner.nextLine();
                    System.out.println("Digite a idade: ");
                    idade = Integer.parseInt(scanner.nextLine());
                    System.out.println("Digite a carga horaria semanal: ");
                    cargaHoraria = Integer.parseInt(scanner.nextLine());
                    //Bloco try para testar a exceção da carga horaria
                    try {
                        switch (tipoFun) {// Decide qual o tipo especifico do objeto
                            case 1://Objeto do tipo medico
                                Medico medico = new Medico(nome, CPF, idade, cargaHoraria);
                                medico.calcularSalario();// Define salario base de medico
                                hospital.add(medico);// adiciona a lista
                                System.out.println("Medico cadastrado com sucesso");
                                break;
                            case 2://Objeto do tipo enfermeiro
                                Enfermeiro enfermeiro = new Enfermeiro(nome, CPF, idade, cargaHoraria);
                                enfermeiro.calcularSalario();// define salario base de enfermeiro
                                hospital.add(enfermeiro);//adiciona a lista
                                System.out.println("Enfermeiro cadastrado com sucesso");
                                break;
                            default:
                                System.out.println("Tipo invalido.");
                                break;
                        }
                    } catch (CargaHorariaInvalidaException e) {
                        // se o limite de horas estourar cai na excessção, e cancela o cadastro
                        System.out.println(e.getMessage());
                        System.out.println("Cadastro cancelado");
                    }
                    break;

                case 2:// Remove fincionário
                    System.out.println("Qual funcionario deseja remover: ");
                    String nomeRemove = scanner.nextLine();
                    //Utiliza Lambda(f->...) para buscar o funcionario, removeIF() retorna true se conseguir achar e remover
                    boolean removeu = hospital.removeIf(f -> f.getNome().equalsIgnoreCase(nomeRemove));
                    if (removeu) {
                        System.out.println(nomeRemove + " foi removido com sucesso");
                    } else {
                        System.out.println(nomeRemove + " não encontrado na lista");
                    }
                    break;

                case 3: //Altera dados de um funcionario
                    System.out.println("Qual funcionario deseja alterar: ");
                    String nomeAlvo = scanner.nextLine();
                    Funcionario alvo = null; // variavel para guardar o funcionário se encontrado

                    //Busca sequencial para encontrar o funcionario pelo nome
                    for (Funcionario f : hospital) {
                        if (f.getNome().equalsIgnoreCase(nomeAlvo)) {
                            alvo = f;
                            break; // para quando acha o funcionario
                        }
                    }

                    if (alvo == null) {// se não achar alvo continua nul informando no terminal
                        System.out.println(nomeAlvo + " não encontrado na lista.");
                    } else {
                        System.out.println("1) Alterar cargo\n2) Alterar carga horaria\n3) Alterar idade");
                        int campo = Integer.parseInt(scanner.nextLine());
                        switch (campo) {// Menu para decidir qual atributo alterar
                            case 1:
                                System.out.println("Novo cargo: ");
                                alvo.setCargo(scanner.nextLine());
                                System.out.println("Cargo atualizado.");
                                break;
                            case 2:
                                System.out.println("Nova carga horaria: ");
                                int novaCarga = Integer.parseInt(scanner.nextLine());
                                try {
                                    // Tenta setar nova carga horaria
                                    alvo.setCargaHoraria(novaCarga);
                                    System.out.println("Carga horaria atualizada.");
                                } catch (CargaHorariaInvalidaException e) {
                                    // se passar de 60 acusa excessão
                                    System.out.println(e.getMessage());
                                    System.out.println("Alteracao cancelada.");
                                }
                                break;
                            case 3:
                                System.out.println("Nova idade: ");
                                alvo.setIdade(Integer.parseInt(scanner.nextLine()));
                                System.out.println("Idade atualizada.");
                                break;
                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }
                    }
                    break;

                case 4:// Exibe todos os funcionarios
                    System.out.println("Lista de empregados.\n---------------------------------------------------------");
                    for (Funcionario funcionarioAtual : hospital) {
                        if (funcionarioAtual instanceof Funcionario func) {//instacia para a classe funcionario para usar o metodo exibiraDados()
                            func.exibirDados();
                            System.out.println("-------------------------------------------");
                        }
                    }
                    break;

                case 5://Adiciona plantões extras(Medicos apenas)
                    System.out.println("Para qual medico: ");
                    String nomeHrExtra = scanner.nextLine();
                    boolean encontrado = false;
                    for (Funcionario fun : hospital) {// busca funcionario pelo nome
                        if (fun.getNome().equalsIgnoreCase(nomeHrExtra)) {
                            encontrado = true;
                            //verifica se o funcionario é realmente um medico
                            if (fun instanceof Medico) {
                                System.out.println("Quantos plantões? ");
                                int p = Integer.parseInt(scanner.nextLine());
                                //instancia o funiconario para medico, para usar os metodos
                                Medico medico = (Medico) fun;
                                try {
                                    medico.calcularSalario(p);//chama o metodo
                                } catch (CargaHorariaInvalidaException e) {
                                    System.out.println(e.getMessage());// se horas de planão passar das horas semanais, acusa exceção
                                }
                            } else {// Se for enfermeiro
                                System.out.println("Horas extras só para medicos");
                            }
                            break;//Para a procura
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Medico não encontrado.");
                    }
                    break;
                case 6:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
                    break;
            }

            if (opc == 6) {
                break;
            }
        }

        scanner.close();
    }
}