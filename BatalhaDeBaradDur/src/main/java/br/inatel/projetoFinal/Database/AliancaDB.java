package br.inatel.projetoFinal.Database;

import br.inatel.projetoFinal.aliancas.Alianca;
import br.inatel.projetoFinal.Characters.Playable;
import br.inatel.projetoFinal.Database.PlayableDB;

import java.sql.SQLException;
import java.util.ArrayList;

public class AliancaDB extends Connection{

    // ----------------------------CRIANDO ALIANÇA----------------------------
    public boolean insertAlianca(Alianca alianca){
        connect();
        String sql = "INSERT INTO alliance(lider, numMembros, raca) VALUES(?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, alianca.getLider().getNome());
            pst.setInt(2,alianca.getNumMembros());
            pst.setString(3,alianca.getRaca());
            pst.execute();                           // executa o comando
            check = true;
            //Inserindo na tabela de aliados
        } catch (SQLException e) {
            System.out.println("Erro ao criar alianca: " + e.getMessage());
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

    // ----------------------------ATUALIZANDO NUMERO DE MEMBROS DE UMA ALIANÇA----------------------------
    public boolean updateAlianca(Alianca alianca,int numMembro){
        connect();
        String sql = "UPDATE alliance SET numMembros = ? WHERE lider = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, numMembro);
            pst.setString(2, alianca.getLider().getNome());
            pst.execute();                           // executa o comando
            check = true;
            //Inserindo na tabela de aliados
        }catch (SQLException e) {
            System.out.println("Erro ao criar alianca: " + e.getMessage());
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

    // ----------------------------ATUALIZANDO ALIADOS DE UMA ALIANÇA----------------------------

    public boolean updateAliados(Alianca alianca){
        connect();
        String sql = "DELETE FROM allys WHERE alianca_lider=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, alianca.getLider().getNome());
            pst.execute();
            check = true;
            //Inserindo na tabela de aliados
            try{
                for(Alianca a:alianca.getAliados()) {
                    String sql2 = "INSERT INTO allys(alianca_lider, alianca_lider1) VALUES(?, ?)";
                    pst = connection.prepareStatement(sql2);
                    pst.setString(1, alianca.getLider().getNome());
                    pst.setString(2, a.getLider().getNome());
                    pst.execute();
                }
            }catch (SQLException e) {
                System.out.println("Erro ao criar alianca: " + e.getMessage());
                check = false;
            }
        }catch (SQLException e) {
            System.out.println("Erro ao criar alianca: " + e.getMessage());
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

    // ----------------------------BUSCANDO TODAS ALIANÇAS----------------------------
    public ArrayList<Alianca> buscarAliancas(){
        connect();
        ArrayList<Alianca> aliancas = new ArrayList<>();
        String sql = "SELECT * FROM alliance";
        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                String nome = result.getString("lider");
                PlayableDB playerdb = new PlayableDB();
                Playable lider = playerdb.buscarPlayableEspecifico(nome);
                Alianca aliancatemp = new Alianca(lider,result.getString("raca"),result.getInt("numMembros") );
                aliancas.add(aliancatemp);
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
        return aliancas;
    }

    // ----------------------------BUSCANDO ALIADOS DE UMA ALIANÇA----------------------------
    public ArrayList<Alianca> buscarAliados(Alianca a){
        connect();
        ArrayList<Alianca> aliados = new ArrayList<>();
        String sql = "SELECT * FROM alliance WHERE alianca_lider=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, a.getLider().getNome());
            result = pst.executeQuery(sql);
            while(result.next()){
                String nome = result.getString("lider");
                PlayableDB playerdb = new PlayableDB();
                Playable lider = playerdb.buscarPlayableEspecifico(nome);
                Alianca aliancatemp = new Alianca(lider,result.getString("raca"),result.getInt("numMembros") );
                aliados.add(aliancatemp);
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
        return aliados;
    }
    // ----------------------------DELETANDO ALIANÇAS----------------------------
    public ArrayList<Alianca> deleteAliancas(){
        connect();
        ArrayList<Alianca> aliados = new ArrayList<>();
        String sql = "DELETE FROM alliance";
        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
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
        return aliados;
    }

}




