package br.inatel.projetoFinal.aliancas;

import br.inatel.projetoFinal.Characters.Personagem;

import java.util.ArrayList;

public class Alianca {

    private Personagem lider;
    private String raca;
    private int numMembros;
    private ArrayList<Alianca> aliados = new ArrayList<>(4);

    public Alianca(Personagem lider, String raca, int numMembros) {
        this.lider = lider;
        this.raca = raca;
        this.numMembros = numMembros;
    }

    public void adicionarAlianca(Alianca aliado){
        this.aliados.add(aliado);
        aliado.aliados.add(this);
    }

    public Personagem getLider() {
        return lider;
    }

    public void setLider(Personagem lider) {
        this.lider = lider;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getNumMembros() {
        return numMembros;
    }

    public void setNumMembros(int numMembros) {
        this.numMembros = numMembros;
    }

    public ArrayList<Alianca> getAliados() {
        return aliados;
    }

}
