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
                System.out.println("Ajudar com espada, montar depois");
                break;
            case 3:
                System.out.println("Ajudar com arco, montar depois");
                break;
            default:
                break;
        }

        int numMembrosNovaAlianca;
        if(decisions == 0){
            numMembrosNovaAlianca = 3;
        }else{
            numMembrosNovaAlianca = 5;
        }

        Playable elendil = null;
        Alianca aliancaElendil = null;

        Espada genericSword = new Espada(3,"S");
        Arco genericArrow = new Arco(3,"L");

        if (Objects.equals(jogador.getRaca(), "Homem")) {
            elendil = new Playable("Elendil", "Elfo", 20);
            elendil.addWeapon(genericArrow);
            aliancaElendil = new Alianca(elendil,"Elfo",numMembrosNovaAlianca);
        }else{
            elendil = new Playable("Elendil", "Homem", 20);
            elendil.addWeapon(genericArrow);
            aliancaElendil = new Alianca(elendil,"Elfo",numMembrosNovaAlianca);
        }
        aliancaPrincipal.adicionarAlianca(aliancaElendil);
        aliancaElendil.adicionarAlianca(aliancaPrincipal);

        playabledb.insertPersonagem(elendil);
        for (Weapon w: elendil.getArmas()) {
            if(w != null){
                weaponDB.insertWeapon(w,elendil);
            }
        }
        aliancaDB.insertAlianca(aliancaElendil);
        aliancaDB.updateAliados(aliancaPrincipal);

        //Cenario unico, final, quase td mnd morre, isildur corta anel
        // sobra vc elendil, buscar aliancas, atualizar aliancas, deletar aliancas depois da despedida
    }

}


