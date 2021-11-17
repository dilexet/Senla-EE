package eu.senla.implementation.dao;

import eu.senla.abstraction.dao.MessageRepositoryInterface;
import eu.senla.constants.MessageTableConstants;
import eu.senla.constants.SqlScripts;
import eu.senla.domain.MessageEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class MessageRepositoryJdbc implements MessageRepositoryInterface {
    private final Connection connection;

    public MessageRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(MessageEntity message) {
        String sql = String.format(SqlScripts.SQL_ADD, MessageTableConstants.TABLE_NAME, MessageTableConstants.COLUMN_NAME_SEND_DATE, MessageTableConstants.COLUMN_NAME_TEXT);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(message.getSendDate()));
            statement.setString(2, message.getText());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(MessageEntity message) {
        String sql = String.format(SqlScripts.SQL_UPDATE, MessageTableConstants.TABLE_NAME, MessageTableConstants.COLUMN_NAME_SEND_DATE, MessageTableConstants.COLUMN_NAME_TEXT, MessageTableConstants.COLUMN_NAME_ID);

        try (PreparedStatement statement = connection.prepareStatement
                (sql)) {
            statement.setDate(1, Date.valueOf(message.getSendDate()));
            statement.setString(2, message.getText());
            statement.setLong(3, message.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean remove(Long id) {
        String sql = String.format(SqlScripts.SQL_REMOVE, MessageTableConstants.TABLE_NAME, MessageTableConstants.COLUMN_NAME_ID);

        try (PreparedStatement statement = connection.prepareStatement
                (sql)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public MessageEntity findById(Long messageId) {
        String sql = String.format(SqlScripts.SQL_FIND_BY_ID, MessageTableConstants.TABLE_NAME, MessageTableConstants.COLUMN_NAME_ID);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, messageId);
            ResultSet resultSet = statement.executeQuery();
            MessageEntity message = null;
            while (resultSet.next()) {
                Long id = resultSet.getLong(MessageTableConstants.COLUMN_NAME_ID);
                String text = resultSet.getString(MessageTableConstants.COLUMN_NAME_TEXT);
                Date sendDate = resultSet.getDate(MessageTableConstants.COLUMN_NAME_SEND_DATE);
                message = new MessageEntity();
                message.setId(id);
                message.setText(text);
                message.setSendDate(sendDate.toLocalDate());
            }
            return message;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
