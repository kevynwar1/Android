package br.com.helloworld.contatos.database;

/**
 * Created by kevyn on 08/04/2017.
 */

public class ScriptSQL {

    public static String getCreateContato() {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS CONTATO ( ");
        sqlBuilder.append("_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NOME VARCHAR(255), ");
        sqlBuilder.append("TELEFONE  VARCHAR(14), ");
        sqlBuilder.append("TIPOTELEFONE  VARCHAR(1), ");
        sqlBuilder.append("EMAIL  VARCHAR(255), ");
        sqlBuilder.append("TIPOEMAIL VARCHAR(1), ");
        sqlBuilder.append("ENDERECO  VARCHAR(255), ");
        sqlBuilder.append("TIPOENDERECO  VARCHAR(1), ");
        sqlBuilder.append("DATA  DATE, ");
        sqlBuilder.append("TIPODATA  VARCHAR(1), ");
        sqlBuilder.append("GRUPOS  VARCHAR(255) ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }
}
