#language: pt

@kabum
Funcionalidade: Demonstrar o funcionamento de uma automação WEB

  @cadastrar_cliente
  Cenário: Deve cadastrar com sucesso um novo cliente, com dados falsos
    Dado que estou na tela de cadastro
    Quando realizar o preenchimento do formulario de pessoa fisica, com dados aleatorios
    E continuo o cadastro com o preenchimento de um endereco
    Entao ao clicar em confirmar cadastro, vejo o aviso para acessar o email ou telefone
    Quando clicar para receber o codigo via email
    Entao vejo o email da kabum com o codigo de ativacao
      #E ao preencher o codigo de confirmacao, com um codigo invalido
      #Entao vejo a mensagem de codigo invalido ou expirado
    E ao preencher o codigo de confirmacao, com o codigo correto
    Entao vejo a mensagem de acesso liberado, onde ao clicar em OK sou redirecionado para a home, já autenticado.

  @realizar_compra
  Cenário: Cliente deve se cadastrar para ser autenticado, adicionar um produto e finalizar o checkout com boleto
    Dado que estou autenticado como novo cliente
    Quando buscar o produto "662405" adicionando o mesmo no carrinho
    Quando buscar o produto "Placa-Mãe ASUS TUF Gaming B650M-Plus" adicionando o mesmo no carrinho
    E clicar em agora nao, para seguir ao carrinho
    Entao confirmo que todos os produtos estao no carrinho
    Quando selecionar o meu endereco, assim como o frete mais barato
    Entao confirmo que ao clicar em ir para pagamento, fui redirecionado para preencher as informacoes de pagamento