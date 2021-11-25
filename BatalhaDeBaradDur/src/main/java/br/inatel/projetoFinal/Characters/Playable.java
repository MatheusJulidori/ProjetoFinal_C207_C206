package br.inatel.projetoFinal.Characters;

import br.inatel.projetoFinal.Weapons.Espada;
import br.inatel.projetoFinal.Weapons.Weapon;
import br.inatel.projetoFinal.Weapons.WeaponNull;

import java.util.ArrayList;

public class Playable extends Personagem{

    private ArrayList<Weapon> armas;//Pos 0 =  Arco  Pos 1 = Espada
    private boolean isPlayer;


    public Playable(String nome,String raca,int HP) {
        super(nome,raca, HP);
        armas = new ArrayList<>();
        armas.add(new WeaponNull(0,"0"));
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Weapon> getArmas() {
        return armas;
    }

    public boolean getIsPlayer(){
        return isPlayer;
    }

    public void addWeapon(Weapon a){
        if(a instanceof Espada){
            armas.add(1,a);
        }else{
            armas.add(0,a);
        }
    }

    public int getArmaType(int index) {
        if(armas.get(index) instanceof Espada){
            return 0;
        }
        else{
            return 1;
        }
    }

    public Weapon getArma(int weapon){
        return armas.get(weapon);
    }

    public void atacar(Personagem p, int weapon,int crit) {//Crit 0 = false 1 = true
        int oldHP = p.getHP();
        Weapon a = armas.get(weapon);
        int newHP;
        if(crit == 0)
            newHP = oldHP - a.getDano();
        else
            newHP = oldHP - (a.getDano()*3);
        p.setHP(newHP);
        if(oldHP <= 0)
            p.isDead = true;
    }

}
