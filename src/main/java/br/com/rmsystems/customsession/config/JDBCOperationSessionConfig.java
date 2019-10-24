package br.com.rmsystems.customsession.config;

import br.com.rmsystems.customsession.dto.CustomSession;
import br.com.rmsystems.customsession.exception.ValidateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.session.jdbc.JdbcOperationsSessionRepository;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class responsible to store the session in database
 */
public class JDBCOperationSessionConfig extends JdbcOperationsSessionRepository {

    public JDBCOperationSessionConfig(JdbcOperations jdbcOperations, PlatformTransactionManager transactionManager) {
        super(jdbcOperations, transactionManager);
        setDefaultMaxInactiveInterval(10800);
        setLobHandler(new JDBCLobHandlerConfig());
    }

    /**
     * Handle the attributes of the session stored on database
     * Besides get the JSON attribute and parse to the CustomSession class
     */
    private class JDBCLobHandlerConfig extends DefaultLobHandler {

        @Override
        public LobCreator getLobCreator() {
            return new JDBCLobCreatorConfig();
        }

        @Override
        public byte[] getBlobAsBytes(ResultSet rs, int columnIndex) throws SQLException {

            byte[] sessionAttributes = null;

            try {
                ObjectMapper mapper = new ObjectMapper();
                String jsonAttribute = rs.getString(columnIndex);
                CustomSession session = mapper.readValue(jsonAttribute, CustomSession.class);
                sessionAttributes = SerializationUtils.serialize(session);

            } catch(IOException e) {
                throw new ValidateException("Failed to convert json atributte to MCSession. Error: " + e.getMessage());
            }

            return sessionAttributes;
        }
    }

    /**
     * Handle the bytes of the session and stored it in a JSON format on the database
     */
    private class JDBCLobCreatorConfig implements LobCreator {

        @Override
        public void setBlobAsBytes(PreparedStatement ps, int i, byte[] bytes) throws SQLException {

            try {
              ObjectMapper mapper = new ObjectMapper();
              ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(bytes));
              CustomSession session = (CustomSession) in.readObject();
              ps.setString(i, mapper.writeValueAsString(session));

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void setBlobAsBinaryStream(PreparedStatement preparedStatement, int i, InputStream inputStream, int i1) throws SQLException {

        }

        @Override
        public void setClobAsString(PreparedStatement preparedStatement, int i, String s) throws SQLException {

        }

        @Override
        public void setClobAsAsciiStream(PreparedStatement preparedStatement, int i, InputStream inputStream, int i1) throws SQLException {

        }

        @Override
        public void setClobAsCharacterStream(PreparedStatement preparedStatement, int i, Reader reader, int i1) throws SQLException {

        }

        @Override
        public void close() {

        }
    }
}