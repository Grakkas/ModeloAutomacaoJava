#language: pt

@mobile
Funcionalidade: demonstrar o funcionamento de uma automacao mobile

  @login_sucesso
Cenario: Realizar o login com sucesso
  Dado que estou na tela principal
    Quando abrir o menu de hamburguer
    E clicar no submenu de login, confirmando o redirecionamento para a tela de login
    Quando clicar no primeiro link de usuario, confirmo que os campos de usuario e senha foram autopreenchidos
    E ao clicar no botao entrar
    Entao confirmo que fui autenticado com sucesso