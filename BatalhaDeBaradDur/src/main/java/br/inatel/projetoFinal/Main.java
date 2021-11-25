package br.inatel.projetoFinal;

import br.inatel.projetoFinal.Cenarios.*;
import br.inatel.projetoFinal.Characters.*;
import br.inatel.projetoFinal.Database.AliancaDB;
import br.inatel.projetoFinal.Database.PlayableDB;
import br.inatel.projetoFinal.Database.WeaponDB;
import br.inatel.projetoFinal.Weapons.Arco;
import br.inatel.projetoFinal.Weapons.Espada;
import br.inatel.projetoFinal.Weapons.Weapon;
import br.inatel.projetoFinal.aliancas.Alianca;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Playable jogador = null;
        PlayableDB playabledb = new PlayableDB();
        WeaponDB weaponDB = new WeaponDB();
        AliancaDB aliancaDB = new AliancaDB();
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
        jogador.alianca=aliancaPrincipal;
        aliancaPrincipal.setRaca(jogador.getRaca());

        playabledb.insertPersonagem(jogador);
        for (Weapon w: jogador.getArmas()) {
            if(w != null){
                weaponDB.insertWeapon(w,jogador);
            }
        }
        aliancaDB.insertAlianca(aliancaPrincipal);


        decisions = Cenarios.cenarioPrincipal(jogador);


        switch (decisions) {
            case 0:
                decisions = Cenarios.cenarioAjudarEspada(jogador);
                break;
            case 1:
                decisions = Cenarios.cenarioAjudarArco(jogador);
                break;
            case 2:
                decisions = Cenarios.cenarioFicarEspada(jogador);
                break;
            case 3:
                decisions = Cenarios.cenarioFicarArco(jogador);
                break;
            default:
                break;
        }

        int numMembrosNovaAlianca;
        if(decisions == 0){
            numMembrosNovaAlianca = 3;
        }else{
            numMembrosNovaAlianca = 5;
            aliancaPrincipal.setNumMembros(3);
        }

        Playable elros = null;
        Alianca aliancaElros = null;

        Espada genericSword = new Espada(3,"S");
        Arco genericArrow = new Arco(3,"L");

        if (Objects.equals(jogador.getRaca(), "Homem")) {
            elros = new Playable("Elros", "Elfo", 20);
            elros.addWeapon(genericArrow);
            aliancaElros = new Alianca(elros,"Elfo",numMembrosNovaAlianca);
        }else{
            elros = new Playable("Elros", "Homem", 20);
            elros.addWeapon(genericSword);
            aliancaElros = new Alianca(elros,"Elfo",numMembrosNovaAlianca);
        }
        aliancaPrincipal.adicionarAlianca(aliancaElros);
        aliancaElros.adicionarAlianca(aliancaPrincipal);

        playabledb.insertPersonagem(elros);
        for (Weapon w: elros.getArmas()) {
            if(w != null){
                weaponDB.insertWeapon(w,elros);
            }
        }
        aliancaDB.insertAlianca(aliancaElros);
        aliancaDB.updateAliados(aliancaPrincipal);
        aliancaDB.updateAlianca(aliancaPrincipal,aliancaPrincipal.getNumMembros());

        aliancaDB.deleteAliancas();

        System.out.println("Fim do prólogo! Jogo ainda em desenvolvimento! Para saber mais das histórias da Terra Média, leia a coleção de livros de JRR Tolkien.");
    }

}


