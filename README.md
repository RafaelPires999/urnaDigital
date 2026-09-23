# 🗳️ Urna Digital

Simulação de uma **urna eletrônica** com interface gráfica, desenvolvida em **Java (Swing)** como projeto acadêmico na FAI - Centro Universitário de Adamantina. Permite cadastrar mesários, eleitores e candidatos, realizar a votação para **Prefeito** e **Vereador** e apurar o resultado.

## ✨ Funcionalidades

**Cadastros**
- Mesários (login para operar a urna)
- Eleitores (CPF e título de eleitor)
- Candidatos (número, nome, partido, vice, cargo e foto em PNG)

**Sessão de votação**
- Login do mesário
- Emissão da **Zerésima** (comprovante de que a urna começa sem votos)
- Início e encerramento da sessão, com registro do horário
- Identificação e liberação do eleitor pelo mesário

**Votação**
- Teclado numérico no estilo da urna real, com os botões **BRANCO**, **CORRIGE** e **CONFIRMA**
- Exibição do nome, partido, vice e foto do candidato ao digitar o número
- Votação em sequência para Prefeito e Vereador

**Apuração**
- Contagem de votos por candidato, votos em branco e votos nulos
- Percentual de cada candidato
- Opção de limpar a apuração

## 🛠️ Tecnologias

- Java
- Java Swing (telas criadas no NetBeans GUI Builder)
- Maven
- Persistência em arquivos de texto (`.txt`) com o padrão **DAO**

## 📁 Estrutura

```
src/main/java/fai/aulas/urnafai/
├── Candidato.java / CandidatoDAO.java
├── Eleitor.java   / EleitorDAO.java
├── Mesario.java   / MesarioDAO.java
├── TelaLogin.java              # Login do mesário
├── TelaCadastro.java           # Escolha do tipo de cadastro
├── TelaCadastroCandidato.java
├── TelaCadastroEleitor.java
├── TelaCadastroMesario.java
├── Zeresima.java               # Relatório inicial da urna
└── TelaPrincipal.java          # Urna, sessão e apuração
```

Os dados são gravados em `candidatos.txt`, `eleitores.txt`, `mesarios.txt` e `votos.txt`, criados automaticamente na pasta do projeto. As fotos dos candidatos ficam em `src/main/java/fai/aulas/urnafai/fotos/`, com o número do candidato como nome (ex.: `13.png`).

## ▶️ Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/RafaelPires999/urnaDigital.git
   ```
2. Abra a pasta do projeto no **NetBeans** (ou outra IDE com suporte a Maven).
3. Execute a classe `TelaLogin`.
4. Use o botão **Cadastro** para criar um mesário, eleitores e candidatos antes de iniciar a votação.

> Requer o JDK compatível com a versão definida no `pom.xml`.

## 👨‍💻 Autor

**Rafael Aguiar Pires** — estudante de Análise e Desenvolvimento de Sistemas na FAI (Adamantina-SP)
[GitHub](https://github.com/RafaelPires999)
