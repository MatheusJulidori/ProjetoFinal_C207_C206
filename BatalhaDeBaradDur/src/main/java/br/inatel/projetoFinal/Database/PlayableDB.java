package br.inatel.projetoFinal.Database;

import br.inatel.projetoFinal.Characters.Personagem;
import br.inatel.projetoFinal.Characters.Playable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class PlayableDB extends Connection{

    // ----------------------------CRIANDO PERSONAGEM PLAYABLE----------------------------
    public boolean insertPersonagem(Playable personagem){
        connect();
        String sql = "INSERT INTO person(nome, raca, HP,isPlayable,isDead) VALUES(?, ?, ?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, personagem.getNome());
            pst.setString(2,personagem.getRaca());
            pst.setInt(3,personagem.getHP());
            if(personagem.getHP() <= 0){
                pst.setInt(5,1);
            }else{
                pst.setInt(5,0);
            }
            if(personagem.getIsPlayer()){
                pst.setInt(4,1);
            }else{
                pst.setInt(4,0);
            }
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

    // ----------------------------ATUALIZANDO HP PLAYABLE----------------------------

    public boolean updateHP(Playable personagem){
        connect();
        String sql = "UPDATE person SET hp=? WHERE nome=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(2, personagem.getNome());
            pst.setInt(1,personagem.getHP());

            pst.execute();                           // executa o comando
            check = true;
            if(personagem.getHP() <= 0){
                String sql2 = "UPDATE person SET isDead=1 WHERE nome=?";
                try{
                    pst = connection.prepareStatement(sql2);
                    pst.setString(1, personagem.getNome());
                    pst.execute();
                }catch (SQLException e) {
                    System.out.println("Erro ao atualizar isDead: " + e.getMessage());
                    check = false;
                }
            }
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

    // ----------------------------BUSCANDO TODOS PLAYABLE----------------------------
    public ArrayList<Playable> buscarPlayables(){
        connect();
        ArrayList<Playable> playables = new ArrayList<>();
        String sql = "SELECT * FROM person";
        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                Playable playabletemp = new Playable(result.getString("nome"),result.getString("raca"),result.getInt("HP") );
                playabletemp.isDead = playabletemp.getHP() <= 0;
                playabletemp.setPlayer(result.getInt("isPlayable") == 1);
                playables.add(playabletemp);
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
        return playables;
    }

    public Playable buscarPlayableEspecifico(String nome){
        connect();
        Playable p = null;
        String sql = "SELECT * FROM person WHERE nome = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,nome);
            result = pst.executeQuery(sql);
            while(result.next()){
                p = new Playable(result.getString("nome"),result.getString("raca"),result.getInt("HP") );
                p.isDead = p.getHP() <= 0;
                p.setPlayer(result.getInt("isPlayable") == 1);
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
        return p;
    }

    public boolean deletePlayable(String nome){
        connect();
        String sql = "DELETE FROM PERSON WHERE nome = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,nome);
            pst.execute();
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            check = false;
        }
        return check;
    }

}
