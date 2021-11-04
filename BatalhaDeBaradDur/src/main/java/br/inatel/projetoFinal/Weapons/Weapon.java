package br.inatel.projetoFinal.Weapons;

public abstract class Weapon {

    protected int dano;
    protected String distance; //(S - short L - Long)

    public Weapon(int dano, String distance) {
        this.dano = dano;
        this.distance = distance;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
