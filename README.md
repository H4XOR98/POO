# POO
UMCarroJá!
Foram escritas 4856 linhas.

O presente trabalho prático desenvolve-se no âmbito da Unidade Curricular Programação Orientada a Objetos lecionada no 2o semestre do 2o ano do curso de Engenharia Informática.
Este trabalho tem como objetivo criar um serviço de aluguer de veículo particulares pela internet, em que um proprietário pode registar um veículo que pode vir mais tarde a ser alugado por um cliente de acordo com as suas necessidades e a sua localização. Cada proprietário pode ter o número de veículos que assim entender, mas para já só é possível registar carros a combustível, elétricos ou híbridos, mas como será explicado, resolvemos o problema de modo a facilitar a introdução de novos veículos no futuro.


                                                        ALUGUER
A classe aluguer é dividida por três entidades, na primeira fase os clientes podem fazer um pedido de aluguer ao qual indica,
através de uma notificação que é enviada ao proprietário do veículo, que o cliente intenção de alugar, o tempo médio de
viagem e o veículo pretendido.
A segunda fase consiste na confirmação do aluguer na qual o proprietário pode aceitar ou rejeitar.
Após esta fase o cliente poderá efetuar a viagem e, no caso de o veículo ficar com autonomia baixa, é enviado ao proprietário
uma notificação.


                                                        PONTO
A classe Ponto implementa um ponto num plano 2D, fornecida nas aulas práticas, em que as coordenadas são duas variáveis do
tipo double que juntas criam o ponto (x,y). Esse ponto é usado para saber a localização de um veículo, de um cliente e do
destino que o cliente deseja.
 
 
                                                        VEICULO
A classe Veículo foi criada para saber toda a informação relacionada com os veículos, desde o seu tipo até às classificações
recebidas nos seus alugueres. É criado com o mínimo de requisitos possível, como marca, matrícula, preço por km, entre
outros. Depois é atualizado sempre que usado, ou sempre que o proprietário decide fazer alguma alteração. Sempre que é
alugado, é também acrescentada à sua lista de classificações a classificação dada por o utilizador. O carro tem uma autonomia
associada, que faz com que o programa saiba se é capaz de realizar o percurso pedido, essa mesma autonomia, se estiver abaixo
dos 10% faz com que o veículo não possa ser usado até ser abastecido.
A localização do veículo é atualizada sempre que for usado.


                                                        NOTIFICACAO
A classe Notificação tem um funcionamento semelhante a um email. As suas variáveis de instância são o nif do destinatário, a
data de envio, o assunto e o conteúdo. As suas funcionalidades permitem alertar um proprietário de pedidos de alugueres e se
um dos seus veículos precisa de ser abastecido. Permite também alertar um cliente se o seu pedido de aluguer foi aceite ou
rejeitado.


                                                        UTILIZADOR (abstract)
A classe Utilizador é a classe que carateriza um tipo que define como as classes que a herdam se devem comportar. Aqui são criadas as variáveis mutuamente usadas por o Cliente e por o Proprietário, tal como o email, password, nome, nif e morada. Cria assim uma base para trabalharmos com todos os utilizadores do nosso programa.
• CLIENTE
A classe Cliente é uma subclasse da Utilizador, que segue a sua informação como modulo, sendo que se o utilizador estiver registado, ou se for registar como cliente, o programa necessita saber informações adicionais, como por exemplo a sua localização. Depois mais tarde quando fizer alugueres, vai lhe ser dado um valor de destreza e uma lista de classificações.
• PROPRIETARIO
A classe Cliente é uma subclasse da Utilizador, que segue a sua informação como modulo, sendo que se o utilizador estiver registado como proprietário, o programa
sempre que lhe forem feitos alugueres, vai lhe atribuindo uma lista de classificações.
                                                        

                                                         TipoVeículo (enum)      
Consiste num conjunto fixo de constantes (static final), como uma lista de valores pré- definidos, sendo neste caso apenas ‘Carro’. Criamos em enum porque facilita no futuro a adição de novos tipos de veículos.


                                                        TipoCombustível (enum)
Consiste num conjunto fixo de constantes (static final), como uma lista de valores pré- definidos, sendo neste caso ‘Gasolina’, ‘Elétrico’ e ‘Hibrido’.


                                                        PreferenciaAluguer (enum)
Consiste num conjunto fixo de constantes (static final), como uma lista de valores pré- definidos, neste caso ‘MaisPerto’, ‘MaisBarato’, ‘MaisPertoBarato’e ‘Especifico,Autonomia’.


                                                         EstadoAluguer (enum)
Consiste num conjunto fixo de constantes (static final), descritos como uma lista de valores pré-definidos, sendo neste caso ‘Espera’, ‘Aceite’, ‘Rejeitado’e ‘Terminado’.


                                                          Tráfego (enum)
Consiste num conjunto fixo de constantes (static final), como uma lista de valores pré- definidos, sendo neste caso ‘Fluido(1.05)’ e ’Congestinado(1.6)’. Cada constante tem um valor associado que é uma percentagem que vai afetar o preço e o tempo associado a cada aluguer, que vai ser mais tarde calculado de acordo com o tráfego atual.


                                                        Meteorologia (enum)
Consiste num conjunto fixo de constantes (static final), como uma lista de valores pré- definidos, sendo neste caso ‘Neve(1.8)’, ‘Chuva(1.3)’, ‘Nevoeiro(1.4)’, ‘Vento(1.2)’, ‘Nublado(1.1)’ e ‘Sol(1.0)’. Cada constante tem um valor associado que é uma percentagem que vai afetar o preço e o tempo associado a cada aluguer, que vai ser mais tarde calculado de acordo com a meteorologia atual.

        Gestores

                                                          GestorVeiculos
A classe gestor de veículos implementa um hashmap cuja chave é a matrícula de um veículo e o conteúdo é o respetivo veículo. Serve como base de dados para todos os veículos registados no sistema.
                                                          
                                                         
                                                         GestorUtilizadores
A classe gestor de utilizadores implementa um hashmap cuja chave é o nif do utilizador e o valor é o próprio utilizador. Serve como base de dados para todos os utilizadores registados no sistema.


                                                           GestorAlugueres
A classe gestor de Alugueres implementa um set de todos os alugueres registados no sistema.


                                                           GestorNotificacoes
A classe gestor de notificações implementa um hashmap cuja chave é o nif do utilizador e o valor é a lista de notificações que esse utilizador recebeu. Serve como base de dados para todas as notificações registadas no sistema.

