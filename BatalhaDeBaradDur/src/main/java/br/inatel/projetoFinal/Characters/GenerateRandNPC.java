package br.inatel.projetoFinal.Characters;
import br.inatel.projetoFinal.Weapons.Espada;

import java.util.Random;

public abstract class GenerateRandNPC {

    static int cont = 0;

    public static Playable generateRandNPC(String raca){
        cont++;
        int min = 1;
        int max = 10;

        Random random = new Random();

        int hp = random.nextInt(max + min) + min;
        max = 5;
        min = 2;
        int dano = random.nextInt(max + min) + min;
        String nome = "RandNPC" + cont;
        Playable rpc = new Playable(nome,raca,hp);
        Espada a = new Espada(dano,"S");
        rpc.addWeapon(a);

        return rpc;
    }

}
