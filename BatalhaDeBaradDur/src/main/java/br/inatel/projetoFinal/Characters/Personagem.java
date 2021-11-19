package br.inatel.projetoFinal.Characters;

import br.inatel.projetoFinal.aliancas.Alianca;

public abstract class Personagem {

    protected String raca;
    protected int HP;
    public Alianca alianca;
    public boolean isDead;
    String nome;

    public Personagem(String nome,String raca, int HP) {
        this.raca = raca;
        this.HP = HP;
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }


    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
