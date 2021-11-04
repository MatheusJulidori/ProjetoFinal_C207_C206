package br.inatel.projetoFinal.Characters;

import br.inatel.projetoFinal.Weapons.Espada;
import br.inatel.projetoFinal.Weapons.Weapon;

import java.util.ArrayList;

public class Playable extends Personagem{

    ArrayList<Weapon> armas = new ArrayList<>(2);
    String nome;


    public Playable(String raca,int HP,String nome) {
        super(raca, HP);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void addWeapon(Weapon a){
        if(armas.isEmpty())
            armas.add(a);
        else if(armas.get(1) != null ){
            if(a instanceof Espada){
                if(armas.get(1) instanceof Espada){
                    armas.add(1,a);
                }else{
                    armas.add(0,a);
                }
            }else{
                if(armas.get(1) instanceof Espada){
                    armas.add(0,a);
                }else{
                    armas.add(1,a);
                }
            }
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

    @Override
    public void atacar(Personagem p) {

    }
}
