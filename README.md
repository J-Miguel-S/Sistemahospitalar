# Sistemahospitalar
Sistema de gestão dos funcionarios hospitalares

# 🏥 Sistema Hospitalar

Este é um projeto desenvolvido em Java como exercício de aprendizado, simulando um sistema básico de gerenciamento de funcionários de um hospital. Ele permite cadastrar médicos e enfermeiros, calcular salários, adicionar plantões extras (apenas para médicos) e gerenciar a equipe por meio de um menu interativo no terminal.

## Funcionalidades

- **Adicionar funcionário** (Médico ou Enfermeiro) com nome, CPF, idade e carga horária semanal.
- **Remover funcionário** por nome.
- **Alterar dados** (cargo, carga horária ou idade) de um funcionário existente.
- **Listar todos os funcionários** com seus dados completos.
- **Adicionar plantões extras** para médicos (com validação de carga horária máxima).
- **Validação de carga horária** – o sistema lança uma exceção personalizada (`CargaHorariaInvalidaException`) se a carga horária semanal ultrapassar 60 horas.

## Tecnologias utilizadas

- Java (JDK 8+)
- Apenas bibliotecas padrão (sem frameworks externos)

## Como executar

1. Compile todos os arquivos `.java` do pacote `br.com.sistemahospitalar`.
2. Execute a classe `Main`.
3. Siga as instruções do menu no terminal.

## Estrutura do projeto

- `Pessoa` – classe base com atributos comuns (nome, CPF, idade).
- `Funcionario` – herda de `Pessoa` e adiciona cargo, carga horária e salário.
- `Medico` – herda de `Funcionario` e implementa cálculo de salário com plantões extras.
- `Enfermeiro` – herda de `Funcionario` com cálculo de salário específico.
- `CargaHorariaInvalidaException` – exceção personalizada para validar limite de horas.
- `Main` – contém o menu principal e a lógica de interação com o usuário.


*Projeto desenvolvido para fins educacionais.*