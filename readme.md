##############################################################
#                                                            #
#                     MOHAA NO cheats                        #
#                                                            #
##############################################################


## Pré requisitos
1. Java 8

## Instruções

1. Copiar o ficheiro anti-cheat-1.X.X.jar para a directoria do jogo
2. Correr o ficheiro copiado para iniciar o jogo
3. Na primeira vez que se corre o ficheiro, muito provávelmente o windows vai abrir um pop up para aceitar uma conexão do ficheiro para o servidor. Aceitar a conexão.
4. Nas opções do jogo por Internet Colocar o IP: localhost:11111
5. Não colocar password
6. Conectar e Jogar

## Como funciona
MOHAA NO cheats age como um gateway de baixa latência. Quando iniciada, a aplicação inicia o jogo como um processo filho e intercepta todo o tráfego com destino ao servidor.
Desta forma qualquer anomalia é detectada no startup, como ficheiros não originais, e assim não permitindo que o jogo continue.
Para o jogo poder conectar ao servidor, a aplicação envia primeiro um pedido para requirir a permissão da conexão, desta forma o servidor apenas aceita conexões através
deste gateway e não directamente do jogo.

### Como contribuir?
A aplicação apenas bloqueia cheats conhecidos entre o grupo. Cheats novos ou desconhecidos podem não ser detectados. Se soubers de algum fala no grupo,
ou então usa-o e fode-os a todos.

### FAQ

- Quando corro o ficheiro anti-cheat-1.X.X.jar nada acontece.
-- Deixa-te de merdas e tira os cheats todos. O ficheiro cria um log na mesma directoria chamado anti-cheat.log. Abre com um editor de texto e verifica os logs. Qualquer cheat encontrado é logado.

- Quando verifico os logs, a mensagem diz que o Ficheiro executável foi modificado, mas eu não uso cheats.
-- Pede ao Carlitos o MOHAA.exe, provávelmente o ficheiro que estás a usar tem um checksum diferente ainda que não tenha cheats.

- Quando verifico os logs, a mensagem diz algo como IOException MOHAA.exe não pode ser encontrado.
-- Certifica-te que o teu ficheiro executável do jogo se chama MOHAA.exe e esta na pasta raiz do jogo

- Quando verifico os logs, a mensagem diz algo como IOException MOHAA.exe precisa de Elevação de privilégios para poder ser executado.
-- Inicia o anti-cheats como Administrador

- Posso usar o jogo directamente sem correr o anti-cheat-1.X.X.jar?
-- Não, o ficheiro age como um gateway que permite aceder ao servidor do jogo.

- Caso não queira jogar no nosso servidor, devo usar o anti-cheat-1.X.X.jar?
-- Não, corre o jogo directamente, o ficheiro apenas pode ser usado no nosso servidor.