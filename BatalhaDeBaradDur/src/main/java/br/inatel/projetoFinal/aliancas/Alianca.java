package br.inatel.projetoFinal.aliancas;

import br.inatel.projetoFinal.Characters.Personagem;

public class Alianca {

    private Personagem lider;
    private String raca;
    private int numMembros;

    public Alianca(Personagem lider, String raca, int numMembros) {
        this.lider = lider;
        this.raca = raca;
        this.numMembros = numMembros;
    }
}
