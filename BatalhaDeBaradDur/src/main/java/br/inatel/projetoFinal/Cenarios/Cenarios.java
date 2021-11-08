package br.inatel.projetoFinal.Cenarios;

import br.inatel.projetoFinal.Characters.Personagem;
import br.inatel.projetoFinal.Characters.Playable;
import br.inatel.projetoFinal.Weapons.*;

import java.util.Objects;
import java.util.Scanner;

public abstract class Cenarios {

    public static Playable criarPersonagem() {
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        String classe = null;
        Playable jogador = null;
        int hp = 20;
        String raca = null;
        while (flag) {
            System.out.println("Selecione sua raça: ");
            System.out.println("1 - Homem");
            System.out.println("2 - Elfo");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    raca = "Homem";
                    flag = false;
                    break;

                case 2:
                    raca = "Elfo";
                    flag = false;
                    break;

                default:
                    System.out.println("Opção invalida");
                    break;
            }
        }
        flag = true;
        System.out.println("De um nome ao seu " + raca + "!");
        String nome = sc.nextLine();
        jogador = new Playable(nome, raca, hp);
        while (flag) {
            System.out.println("Selecione sua arma inicial: ");
            System.out.println("1 - Espada");
            System.out.println("2 - Arco");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    Espada esp1 = new Espada(4,"S");
                    jogador.addWeapon(esp1);
                    flag = false;
                    break;

                case 2:
                    Arco arc1 = new Arco(4,"L");
                    jogador.addWeapon(arc1);
                    flag = false;
                    break;

                default:
                    System.out.println("Opção invalida");
                    break;
            }

        }
        sc.close();
        jogador.setPlayer(true);
        return jogador;
    }

    public static int cenarioPrincipal(Playable jogador){
        Scanner sc = new Scanner(System.in);
        int decisions = 0;

        System.out.println("Tudo está escuro, embaçado... Você escuta vozes, gritos e um incessável barulho de metal batendo, mas logo tudo se esvai...");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("De repente, você escuta alguém chamar seu nome");
        System.out.println("Desconhecido: "+jogador.getNome()+"...");
        System.out.println("Desconhecido: "+jogador.getNome()+"!!!");
        System.out.println("Desconhecido: "+jogador.getNome().toUpperCase()+" LEVANTA, A GENTE PRECISA DE VOCÊ AQUI!!");
        System.out.println("Você levanta assustado, e percebe que está no meio de uma batalha.");
        System.out.println("Hador: Vamos, a gente precisa se recompor, nosso comandante acabou de ser abatido. Mas ele conseguiu nos dar um tempo para\n" +
                "pensar em alguma coisa. Os outros estão feridos, mas ainda conseguem lutar. Eu não contaria com a força deles para aguentar muito tempo,\n" +
                "somos só nós dois irmão,e por mais que me doa no meu orgulho dizer isso, eu acho que você deveria nos liderar...");
        System.out.println();
        System.out.println("Selecione sua resposta: ");
        System.out.println("1 - Finalmente você admitiu que sou melhor que você.");
        System.out.println("2 - Certo, me ajude a levantar.");
        decisions = sc.nextInt();
        sc.nextLine();
        if(decisions == 1) {
            System.out.println(jogador.getNome() + ": Hump! Finalmente você admitiu que sou melhor que você, esse momento vai entrar pra história!");
            System.out.println("Hador: Isso não é hora de fazer piada. Levanta daí");
        }
        else{
            System.out.println(jogador.getNome() + ": Certo, me da uma mão aqui!");
            System.out.println("Hador: O todo poderoso comandante não consegue se levantar sozinho? Vai, estende o braço ai");
            System.out.println(jogador.getNome() + ": Se eu não te conhecesse desde que nascemos, diria que está zombando da minha cara");
        }
        System.out.println("Hador estende sua mão e te ajuda a levantar.");

        System.out.println(jogador.getNome() + ":Me atualiza do status da batalha.");
        if(Objects.equals(jogador.getRaca(), "Homem")) {
            System.out.println("Hador: Certo. Nosso pelotão foi derrotado,sobramos só nós 4. Precisamos dar um jeito de nos agrupar com alguém, não vamos durar muito tempo assim.\n" +
                    "A aliança com os elfos já está enfraquecida, ninguém se ajuda, só estamos juntos para contar números.Eles não confiam em nós e, pra ser bem sincero,\n" +
                    "também não confio nesses orelhas pontudas,se Sauron tomar a Terra-Média, eles simplesmente vão fugir para Valinor e ficar sob a proteção dos Valar");
            System.out.println(jogador.getNome() + ": Nem todos os elfos pensam assim.");
            System.out.println("Enquanto você falava, você percebe um grupo de orcs vindo na direção de seus homens.\n" +
                    "Também ao seu lado, você percebe um elfo ferido sendo cercados por dois orcs. Ele te vê e clama por ajuda.");

            System.out.print("Selecione sua ação: ");
            System.out.println("1 - Manter posição e ajudar seu pelotão");
            System.out.println("2 - Tentar ajudar o elfo");
            decisions = sc.nextInt();
            sc.nextLine();
            if(decisions == 1) {
                System.out.println(jogador.getNome() + ": Homens, de pé, nós defendemos nosso posto!");
                if(jogador.getArmaType(0) == 1){//Se arma inicial for espada
                    sc.close();
                    return 0;
                }else{
                    sc.close();
                    return 1;
                }
            }
            else{
                System.out.println(jogador.getNome() + ": Segura as pontas um segundo, eu já volto!");
                if(jogador.getArmaType(0) == 1){//Se arma inicial for espada
                    sc.close();
                    return 2;
                }else{
                    sc.close();
                    return 3;
                }
            }


        }else{
            System.out.println("Hador: Certo. Nosso pelotão foi derrotado,sobramos só nós 4. Precisamos dar um jeito de nos agrupar com alguém, não vamos durar muito tempo assim.\n" +
                    "A aliança com os homens já está enfraquecida, ninguém se ajuda, só estamos juntos para contar números.Eles não confiam em nós e, pra ser bem sincero,\n" +
                    "também não confio nesses mortais imundos,se Sauron tomar a Terra-Média, eles vão se aliar a ele e se corromper ao poder do anel, assim como seus antigos reis");
            System.out.println(jogador.getNome() + ": Nem todos os homens pensam assim.");
            System.out.println("Enquanto você falava, você percebe um grupo de orcs vindo na direção do seu pelotão.\n" +
                    "Também ao seu lado, você percebe um homem ferido sendo cercados por dois orcs. Ele te vê e clama por ajuda.");

            System.out.print("Selecione sua ação: ");
            System.out.println("1 - Manter posição e ajudar seu pelotão");
            System.out.println("2 - Tentar ajudar o homem");
            decisions = sc.nextInt();
            sc.nextLine();
            if(decisions == 1) {
                System.out.println(jogador.getNome() + ": Guerreiros, de pé, nós defendemos nosso posto!");
                if(jogador.getArmaType(0) == 1){//Se arma inicial for espada
                    sc.close();
                    return 0;
                }else{
                    sc.close();
                    return 1;
                }
            }
            else{
                System.out.println(jogador.getNome() + ": Segura as pontas um segundo, eu já volto!");
                if(jogador.getArmaType(0) == 1){//Se arma inicial for espada
                    sc.close();
                    return 2;
                }else{
                    sc.close();
                    return 3;
                }
            }
        }
    }

    public static int cenarioFicarEspada(){
        Scanner sc = new Scanner(System.in);
        int decisions = 0;
        sc.close();
        return 0;
    }

    public static int cenarioFicarArco(){
        Scanner sc = new Scanner(System.in);
        int decisions = 0;
        sc.close();
        return 0;
    }

    public static int cenarioAjudarEspada(){
        Scanner sc = new Scanner(System.in);
        int decisions = 0;
        sc.close();
        return 0;
    }

    public static int cenarioAjudarArco(){
        Scanner sc = new Scanner(System.in);
        int decisions = 0;
        sc.close();
        return 0;
    }

}
