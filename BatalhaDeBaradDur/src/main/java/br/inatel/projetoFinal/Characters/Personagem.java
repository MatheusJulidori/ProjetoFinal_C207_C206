package br.inatel.projetoFinal.Characters;

public abstract class Personagem {

    protected String raca;
    protected int HP;

    public Personagem(String raca, int HP) {
        this.raca = raca;
        this.HP = HP;
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

    public abstract void atacar(Personagem p);

}
