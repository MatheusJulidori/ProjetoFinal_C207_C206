package br.inatel.projetoFinal.Cenarios;

import br.inatel.projetoFinal.Characters.GenerateRandNPC;
import br.inatel.projetoFinal.Characters.Playable;
import br.inatel.projetoFinal.Database.AliancaDB;
import br.inatel.projetoFinal.Database.PlayableDB;
import br.inatel.projetoFinal.Database.WeaponDB;
import br.inatel.projetoFinal.Weapons.*;
import br.inatel.projetoFinal.aliancas.Alianca;

import java.util.Objects;
import java.util.Scanner;

public abstract class Cenarios {

    private static PlayableDB pdb = new PlayableDB();
    private static WeaponDB wdb = new WeaponDB();
    private static AliancaDB adb = new AliancaDB();

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
                    "Também ao seu lado, você percebe um grupo de elfos feridos sendo cercados por quatro orcs. O líder te vê e clama por ajuda.");

            System.out.print("Selecione sua ação: ");
            System.out.println("1 - Manter posição e ajudar seu pelotão");
            System.out.println("2 - Tentar ajudar os elfos");
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
            System.out.println("Narrador: Enquanto você falava, você percebe um grupo de orcs vindo na direção do seu pelotão.\n" +
                    "Também ao seu lado, você percebe um grupo de homens feridos sendo cercados por quatro orcs. O líder te vê e clama por ajuda.");

            System.out.print("Selecione sua ação: ");
            System.out.println("1 - Manter posição e ajudar seu pelotão");
            System.out.println("2 - Tentar ajudar os homens");
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

    public static int cenarioFicarEspada(Playable jogador){
        Scanner sc = new Scanner(System.in);
        int decisions = 0;
        System.out.println("Narrador: Você e seus aliados se preparam para a batalha contra 4 orcs.\n" +
                "Durante a batalha, você comando seus aliados para derrotar os orcs, até que um deles vem em sua direção\n" +
                "Você saca sua espada e se prepara para batalha.");
        System.out.println();
        System.out.println();
        Playable orc = GenerateRandNPC.generateRandNPC("Orc");
        pdb.insertPersonagem(orc);
        System.out.println("Dados do jogador:");
        System.out.println("HP: " + jogador.getHP());
        System.out.println("Arma: Espada");
        System.out.println("Dano da arma: " + jogador.getArma(1).getDano());
        System.out.println("Dados do Inimigo:");
        System.out.println("HP: " + orc.getHP());
        System.out.println("Arma: Espada e porradaria franca");
        System.out.println("Dano da arma: " + orc.getArma(1).getDano());
        System.out.println("Narrador: O orc vem pra cima de você ferozmente ");
        System.out.println("Selecione sua ação: ");
        System.out.println("1 - Esquivar");
        System.out.println("2 - Bloquear o ataque");
        decisions = sc.nextInt();
        sc.nextLine();
        if(decisions == 1) {
            System.out.println("Narrador: Você esquiva do ataque, mas o peso da espada te deixa lento, e o orc acaba atingindo seu ombro\n." +
                    "Mesmo assim, o orc se desequilibra e cai e você consegue atingi-lo, desprevenido, em um ponto fraco, causando um dano critico");
            orc.atacar(jogador,1,0);
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.deletePlayable(orc.getNome());
            pdb.updateHP(jogador);
        }
        else{
            System.out.println("Narrador: Você bloqueia o ataque e o orc se desequilibra e cai. Com toda sua força, você crava a espada no crânio do orc, matando-ô");
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.deletePlayable(orc.getNome());
        }

        System.out.println("Hador: Você está bem? Não consegui vir te ajudar a tempo, mas deu pra ver que você deu conta do recado.");

        if(Objects.equals(jogador.getRaca(), "Homem")) {
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, tem um grupo de elfos em perigo logo ao lado");
            System.out.println("Narrador: Vocês correm em direção ao grupo, mas não chegam a tempo de salvar todos. Ao chegarem, um elfo" +
                    "vem e sua direção e pede para falar com o líder");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Elendil : Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Hador : Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Elendil percebem que foram cercados por um exército de incontáveis orcs");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um elfo");
            System.out.println("Elendil : Você devia se sentir honrado. Nai yaryuvalyë estë sambassë Mandosto");
            System.out.println("Hador : Que? Esse maluco de orelha pontuda xingou a gente?");
            System.out.println(jogador.getNome() + ": Não, ele disse 'Que você tenha pax na morte'.");
            System.out.println("Hador : Primeiro, onde você aprendeu a falar Quenya? Segundo, que astral baixo pra quem tem que encorajar um grupo de soldados a lutar contra" +
                    "pelo menos 200 orcs");
            System.out.println("Elendil : Foco na batalha!");
        }
        else{
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, tem um grupo de homens em perigo logo ao lado");
            System.out.println("Narrador: Vocês correm em direção ao grupo, mas não chegam a tempo de salvar todos. Ao chegarem, um homem" +
                    "vem e sua direção e pede para falar com o líder");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Elendil : Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Hador : Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Elendil percebem que foram cercados por um exército de incontáveis orcs");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um elfo");
            System.out.println("Elendil : Você devia se sentir honrado.");
            System.out.println("Hador : Honrado em morrer ao lado de uma raça corrompida por poder e ganância. Não me parece muito coerente");
            System.out.println("Elendil : Ai o orelha pontuda, se você quiser morrer mais cedo é só falar, to doidinho pra espetar um otário");
            System.out.println(jogador.getNome() + ": Foco na batalha!");
        }

        sc.close();
        return 0;
    }

    public static int cenarioFicarArco(Playable jogador){
        Scanner sc = new Scanner(System.in);
        int decisions = 0;
        System.out.println("Narrador: Você e seus aliados se preparam para a batalha contra 4 orcs.\n" +
                "Durante a batalha, você comando seus aliados para derrotar os orcs, até que um deles vem em sua direção\n" +
                "Você saca seu arco e se prepara para batalha.");
        System.out.println();
        System.out.println();
        Playable orc = GenerateRandNPC.generateRandNPC("Orc");
        pdb.insertPersonagem(orc);
        System.out.println("Dados do jogador:");
        System.out.println("HP: " + jogador.getHP());
        System.out.println("Arma: Arco");
        System.out.println("Dano da arma: " + jogador.getArma(0).getDano());
        System.out.println("Dados do Inimigo:");
        System.out.println("HP: " + orc.getHP());
        System.out.println("Arma: Espada e porradaria franca");
        System.out.println("Dano da arma: " + orc.getArma(1).getDano());
        System.out.println("Narrador: O orc vem pra cima de você ferozmente ");
        System.out.println("Selecione sua ação: ");
        System.out.println("1 - Esquivar");
        System.out.println("2 - Bloquear o ataque");
        decisions = sc.nextInt();
        sc.nextLine();
        if(decisions == 1) {
            System.out.println("Narrador: Você esquiva do ataque com maestria e agilidade e o orc acaba atingindo o solo\n." +
                    "Aproveitando o momento, você dispara uma flecha na cabeça do orc desprevenido, matando-ô");
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.deletePlayable(orc.getNome());
        }
        else{
            System.out.println("Narrador: Você bloqueia o ataque com seu arco com o braço de sua armadura, mas a força do impacto é tão grande que você sente seu ombro deslocar.\n" +
                    " Mesmo assim, você rapidamente chuta o orc, que se desequilibra e cai. Com o braço que lhe resta, você crava uma flecha no crânio do orc, matando-ô. Após mata-lô, você coloca seu ombro no lugar" +
                    "e se prepara para mais uma batalha");
            orc.atacar(jogador,1,0);
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.updateHP(jogador);
            pdb.deletePlayable(orc.getNome());
        }

        System.out.println("Hador: Você está bem? Não consegui vir te ajudar a tempo, mas deu pra ver que você deu conta do recado.");

        if(Objects.equals(jogador.getRaca(), "Homem")) {
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, tem um grupo de elfos em perigo logo ao lado");
            System.out.println("Narrador: Vocês correm em direção ao grupo, mas não chegam a tempo de salvar todos. Ao chegarem, um elfo" +
                    "vem e sua direção e pede para falar com o líder");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Elendil : Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Hador : Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Elendil percebem que foram cercados por um exército de incontáveis orcs");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um elfo");
            System.out.println("Elendil : Você devia se sentir honrado. Nai yaryuvalyë estë sambassë Mandosto");
            System.out.println("Hador : Que? Esse maluco de orelha pontuda xingou a gente?");
            System.out.println(jogador.getNome() + ": Não, ele disse 'Que você tenha pax na morte'.");
            System.out.println("Hador : Primeiro, onde você aprendeu a falar Quenya? Segundo, que astral baixo pra quem tem que encorajar um grupo de soldados a lutar contra" +
                    "pelo menos 200 orcs");
            System.out.println("Elendil : Foco na batalha!");
        }
        else{
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, tem um grupo de homens em perigo logo ao lado");
            System.out.println("Narrador: Vocês correm em direção ao grupo, mas não chegam a tempo de salvar todos. Ao chegarem, um homem" +
                    "vem e sua direção e pede para falar com o líder");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Elendil : Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Hador : Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Elendil percebem que foram cercados por um exército de incontáveis orcs");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um elfo");
            System.out.println("Elendil : Você devia se sentir honrado.");
            System.out.println("Hador : Honrado em morrer ao lado de uma raça corrompida por poder e ganância. Não me parece muito coerente");
            System.out.println("Elendil : Ai o orelha pontuda, se você quiser morrer mais cedo é só falar, to doidinho pra espetar um otário");
            System.out.println(jogador.getNome() + ": Foco na batalha!");
        }

        sc.close();
        return 0;
    }

    public static int cenarioAjudarEspada(Playable jogador){
        Scanner sc = new Scanner(System.in);
        int decisions = 0;
        sc.close();
        return 0;
    }

    public static int cenarioAjudarArco(Playable jogador){
        Scanner sc = new Scanner(System.in);
        int decisions = 0;
        sc.close();
        return 0;
    }

}
