package br.inatel.projetoFinal;

import br.inatel.projetoFinal.Cenarios.*;
import br.inatel.projetoFinal.Characters.*;
import br.inatel.projetoFinal.Database.PlayableDB;
import br.inatel.projetoFinal.aliancas.Alianca;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Playable jogador = null;
        PlayableDB playabledb = new PlayableDB();
        Alianca aliancaPrincipal = new Alianca(jogador, "Homem", 4);
        int decisions = 0;

        System.out.println("================================================================================================================================");
        System.out.println();
        System.out.println("A batalha de Barad-Dûr foi a batalho onde Homens e Elfos se juntaram pela última vez, no que ficou conhecido como a Última Aliança.\n" +
                "Por 7 anos, A Última Aliança lutou contra as forças de Sauron para impedir que o Servo De Morgoth dominasse e espalahsse escuridão sobre a \n" +
                "Terra-Média e, posteriormente, sobre Valinor, a Terra Imortal dos elfos. Você fará parte de um Desicion Making RPG, onde suas decisões levaram\n" +
                "a diferentes cenários e possibilidades. Escolha com sabedoria! Nem todas as decisões te levaram a cenários favoráveis! Pense em suas habilidades e nas\n" +
                "caracteristicas de cada classe! ");
        System.out.println();

        System.out.println("Primeiramente, vamos criar seu personagem: ");

        jogador = Cenarios.criarPersonagem();
        playabledb.insertPersonagem(jogador);

        jogador.alianca=aliancaPrincipal;
        aliancaPrincipal.setRaca(jogador.getRaca());

        decisions = Cenarios.cenarioPrincipal(jogador);


        switch (decisions) {
            case 0:
                System.out.println("Ficar com espada, montar depois");
                break;
            case 1:
                System.out.println("Ficar com arco, montar depois");
                break;
            case 2:
                System.out.println("Ajudar com espada, montar depois");
                break;
            case 3:
                System.out.println("Ajudar com arco, montar depois");
                break;
            default:
                break;
        }
    }

}


