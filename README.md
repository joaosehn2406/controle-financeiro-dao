<h1 align="center">Projeto Controle Financeiro</h1>

<div align="center">
  <p>Um sistema para gerenciamento de movimentações financeiras com categorização de receitas e despesas.</p>
  <p>Explore, colabore e organize suas finanças de maneira fácil e prática! 😄</p>
</div>

## 📖 Índice

- [Visão Geral](#visão-geral)
- [Tecnologias](#tecnologias)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Como Contribuir](#como-contribuir)
- [Licença](#licença)

## 🔭 Visão Geral

Este projeto tem como objetivo oferecer uma plataforma para controle financeiro, permitindo que usuários possam cadastrar, atualizar, buscar e remover movimentações financeiras. Entre os principais recursos, destacam-se:

- **Gestão de Usuários**: Cadastro e gerenciamento de usuários no sistema.
- **Gestão de Categorias**: Organização das movimentações financeiras em categorias de receitas e despesas.
- **Movimentações Financeiras**: Criação e atualização de transações financeiras.
- **Persistência de Dados em Banco de Dados**: Utiliza SQL para armazenamento de dados, com consultas dinâmicas baseadas em categorias e usuários.
- **Validação de Dados**: As entradas dos usuários são validadas para garantir que todos os dados necessários sejam fornecidos corretamente.

## 💻 Tecnologias

- [Java](https://docs.oracle.com/en/java/) – Linguagem de programação utilizada para o desenvolvimento do projeto.
- [JDBC](https://docs.oracle.com/javase/tutorial/jdbc/) – API para interação com o banco de dados.
- [MySQL](https://www.mysql.com/) – Sistema de gerenciamento de banco de dados utilizado para armazenar os dados.
- [JavaFX](https://openjfx.io/) – Framework utilizado para a criação da interface gráfica (se necessário).
- [SHA-256](https://en.wikipedia.org/wiki/SHA-2) – Algoritmo de criptografia utilizado (se necessário para senhas).

## ⚙️ Configuração do Ambiente

Forneça instruções claras e detalhadas sobre como configurar o ambiente de desenvolvimento localmente. Isso pode incluir:

1. **Pré-requisitos:**
   - [Java JDK](https://www.oracle.com/java/technologies/downloads/) instalado (recomenda-se a versão 11 ou superior).
   - Banco de dados MySQL instalado e configurado.
   - Uma IDE de sua preferência, como [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](https://www.eclipse.org/).

2. **Clonando o Repositório:**
   ```bash
   git clone https://github.com/joaosehn2406/controle-financeiro-dao.git
   cd controle-financeiro-dao
   ```
3. **Configuração do Banco de Dados:**
   - Importe o esquema de banco de dados (caso fornecido) para o MySQL.

   - Configure as credenciais de banco de dados no arquivo de configuração (db.properties)

## 🤝 Como Contribuir

Contribuições são sempre bem-vindas! Para colaborar com o projeto, siga estas etapas:

1. Faça um fork do repositório e clone-o em sua máquina local.

2. Crie uma nova branch para suas modificações:
   ```
   git checkout -b minha-branch
   ```
3. Faça as modificações desejadas e adicione-as ao stage:
   ```
   git add .
   ```
4. Faça um commit das suas alterações:
   ```
   git commit -m "Minhas modificações"
   ```
5. Envie suas alterações para o repositório remoto:
   ```
   git push origin minha-branch
   ```
6. Abra um pull request para que suas modificações sejam revisadas e incorporadas ao projeto.

## 📄 Licença

Este projeto está licenciado sob a [MIT License](https://mit-license.org/). Consulte o arquivo LICENSE para obter mais informações sobre os termos de uso.

Divirta-se explorando e contribuindo para o projeto! Se tiver dúvidas ou sugestões, abra uma issue ou entre em contato. 🚀



Aproveite o projeto e fique à vontade para personalizar este README de acordo com as necessidades do seu repositório. Divirta-se codificando! 🎉😄
