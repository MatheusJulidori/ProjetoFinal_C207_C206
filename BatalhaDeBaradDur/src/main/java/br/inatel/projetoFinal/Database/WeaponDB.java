package br.inatel.projetoFinal.Database;

import br.inatel.projetoFinal.Characters.Personagem;
import br.inatel.projetoFinal.Characters.Playable;
import br.inatel.projetoFinal.Weapons.Arco;
import br.inatel.projetoFinal.Weapons.Espada;
import br.inatel.projetoFinal.Weapons.Weapon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;


public class WeaponDB extends Connection{

    // ----------------------------CRIANDO PERSONAGEM PLAYABLE----------------------------
    public boolean insertWeapon(Weapon w, Playable p){
        connect();
        String sql = "INSERT INTO weapon(distance, dano, Character_nome) VALUES(?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, w.getDistance());
            pst.setInt(2,w.getDano());
            pst.setString(3,p.getNome());
            pst.execute();                           // executa o comando
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro ao criar personagem: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return check;
    }

    // ----------------------------ATUALIZANDO ARMA----------------------------

    public boolean updateArma(Playable p,Weapon w){
        connect();
        String sql = "UPDATE weapon SET dano=? WHERE Character_nome=? AND distance = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, w.getDano());
            pst.setString(2, p.getNome());
            pst.setString(3,w.getDistance());
            pst.execute();
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar HP: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return check;
    }

    // ----------------------------BUSCANDO ARMAS DO PLAYER----------------------------
    public ArrayList<Weapon> buscarArmas(Playable p){
        connect();
        ArrayList<Weapon> weapons = new ArrayList<>(2);
        String sql = "SELECT * FROM weapon WHERE Character_nome = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,p.getNome());
            result = pst.executeQuery(sql);
            while(result.next()){
                if(Objects.equals(result.getString("distance"), "S")){
                    Espada esp = new Espada(result.getInt("dano"),result.getString("distance"));
                    weapons.add(esp);
                }else{
                    Arco arc = new Arco(result.getInt("dano"),result.getString("distance"));
                    weapons.add(arc);
                }
            }
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }finally {
            try {
                connection.close();
                statement.close();
                result.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return weapons;
    }

    // ----------------------------APAGANDO ARMA DE UM PLAYER----------------------------
    public void DeleteWeapon(Playable p, String distance){
        connect();
        String sql = "DELETE FROM weapon WHERE Character_nome = ? and distance = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,p.getNome());
            pst.setString(2,distance);
            pst.executeQuery(sql);
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }finally {
            try {
                connection.close();
                statement.close();
                result.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

}
