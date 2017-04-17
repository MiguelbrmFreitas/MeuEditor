Grupo:
- Miguel Barreto Rezende Marques de Freitas - 12/0130424
- Daniel Nora Castro - 12/0114470

O projeto utiliza a biblioteca externa OpenCV versão 2.4.8
Para executá-lo, é necessário ter a OpenCV versão 2.4.8 e a última versão do Java devidamente configuradas na IDE Eclipse e importar toda esta pasta como um projeto.
Este tutorial ensina como configurar a OpenCV no Eclipse e tem referências para o Download da mesma: http://docs.opencv.org/trunk/doc/tutorials/introduction/java_eclipse/java_eclipse.html
Uma vez com o OpenCV versão 2.4.8 configurado na IDE Eclipse, abra o projeto e execute o mesmo pelo atalho CTRL + F11 ou nos menus de cima.
A IDE irá perguntar se você quer executar como JUnit (testes) ou como Java Application. Para usar a aplicação principal, execute como Java Application, para executar os testes, execute como JUnit.
Nomeie o OpenCV configurado como "user library" como "Open-CV-2.4.8" sem as aspas para não haver problemas com um nome diferente do configurado neste projeto feito no Eclipse.
Se a OpenCV não aparecer automaticamente ao importar o projeto, siga os seguintes passos:
- Clique com o botão direito no projeto
- Agora clique em Propriedades/Properties
- Depois clique em Java Build Path
- Clique em Add Library
- Clique em User Library
- Selecione a OpenCV, considerando que você já a instalou como uma User Library (vide link do tutorial acima)

As instruções de uso do programa estão no documento de texto "instrucoes.txt", que pode ser acessado diretamente pelo
programa em tempo de execução, na aba de "Ajuda", item "Instrucoes de uso".