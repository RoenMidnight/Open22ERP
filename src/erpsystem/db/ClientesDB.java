/*
 * The MIT License
 *
 * Copyright 2015 Diego Geronimo D' Onofre.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package erpsystem.db;

import erpsystem.Log;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class ClientesDB {
    public static int genCode()
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = "select max(codigo) as 'cod' from clientes";
            ResultSet rs = st.executeQuery(update);
            rs.next();
            return rs.getInt("cod") + 1;
        }
        catch ( Exception e ){
            Log.log(e);
            return -1;
        }
    }
    
    public static void add(Cliente cli)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            
            int cod      = genCode();
            String nome  = cli.getNome();
            String cpf   = cli.getCpf();
            String email = cli.getEmail();
            String loc   = cli.getLocalizacao();
            String tel   = cli.getTelefone();
            
            String update = " insert "
                          + " into clientes "
                          + " values(" + cod + ","
                                    + "'" + nome + "',"
                                    + "'" + cpf + "',"
                                    + "'" + email + "',"
                                    + "'" + loc + "',"
                                    + "'" + tel + "'"
                          + ")";
            
            st.executeUpdate(update);
            con.commit();

        }
        catch ( Exception e ){
            Log.log(e);
        }
    }
    
    public static List<Cliente> findClient(String clientName)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = " select clientes.codigo as 'cod',"
                          + "        clientes.nome as 'nome',"
                          + "        clientes.cpf as 'cpf',"
                          + "        clientes.email as 'email',"
                          + "        clientes.localizacao as 'loc',"
                          + "        clientes.telefone as 'tel'"
                          + " from clientes "
                          + " where upper(trim(clientes.nome)) like '%" + clientName.trim().toUpperCase() + "%'";
            
            ResultSet rs = st.executeQuery(update);
            List<Cliente> cliList = new ArrayList<>();
            
            while (rs.next()){
                final int cod  = rs.getInt("cod");
                
                String cliName = rs.getString("nome");
                String cpf     = rs.getString("cpf");
                String email   = rs.getString("email");
                String loc     = rs.getString("loc");
                String tel     = rs.getString("tel");
                
                Cliente cli = new Cliente();
                
                cli.setCodigo(cod);
                cli.setNome(cliName);
                cli.setCpf(cpf);
                cli.setEmail(email);
                cli.setLocalizacao(loc);
                cli.setTelefone(tel);
                
                cliList.add(cli);
            }
            
            return cliList;
        }
        catch ( SQLException e ){
            Log.log(e);
            return null;
        }
    }
    
    public static boolean exists(int code)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = " select codigo as 'cod' "
                          + " from clientes "
                          + " where codigo = " + code;
            ResultSet rs = st.executeQuery(update);
            
            if ( rs.next() )
                return true;
            else
                return false;
        }
        catch ( Exception e ){
            Log.log(e);
            return false;
        }        
    }

    public static Cliente find(int code)
    {
        try{
            Connection con = DB.getConnection();
            Statement st = con.createStatement();
            String update = " select clientes.codigo as 'cod',"
                          + "        clientes.nome as 'nome',"
                          + "        clientes.cpf as 'cpf',"
                          + "        clientes.email as 'email',"
                          + "        clientes.localizacao as 'loc',"
                          + "        clientes.telefone as 'tel'"
                          + " from clientes "
                          + " where codigo = " + code;
            ResultSet rs = st.executeQuery(update);
            
            if (rs.next()){
                final int cod  = rs.getInt("cod");

                String cliName = rs.getString("nome");
                String cpf     = rs.getString("cpf");
                String email   = rs.getString("email");
                String loc     = rs.getString("loc");
                String tel     = rs.getString("tel");

                Cliente cli = new Cliente();

                cli.setCodigo(cod);
                cli.setNome(cliName);
                cli.setCpf(cpf);
                cli.setEmail(email);
                cli.setLocalizacao(loc);
                cli.setTelefone(tel);
                return cli;
            }
            else
                return null;
        }
        catch ( Exception e ){
            Log.log(e);
            return null;
        }        
    }
    
}
