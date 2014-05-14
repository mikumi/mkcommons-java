/**
 *
 */
package com.michael_kuck.commons.mysql;

import com.michael_kuck.commons.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * @author michaelkuck
 */
public class MySqlSelectStatementBuilder {

    final private static String PLACEHOLDER = "?";

    final private List<String>        selectFields;
    final private String              tableName;
    final private Map<String, String> whereFields;
    final private Map<String, String> whereNotFields;

    private int     rowLimit;
    private boolean isDistinct;

    /**
     * @param tableName
     */
    public MySqlSelectStatementBuilder(final String tableName) {
        this.selectFields = new ArrayList<String>();
        this.tableName = tableName;
        this.whereFields = new HashMap<String, String>();
        this.whereNotFields = new HashMap<String, String>();
        this.setRowLimit(0);
        this.isDistinct = false;
    }

    /**
     * @param fieldName
     */
    public void addSelectField(final String fieldName) {
        this.selectFields.add(fieldName);
    }

    /**
     * @param fieldNames
     */
    public void addSelectFields(final String[] fieldNames) {
        for (final String fieldName : fieldNames) {
            addSelectField(fieldName);
        }
    }

    /**
     * @param fieldName
     * @param fieldValue
     */
    public void addWhereField(final String fieldName, final String fieldValue) {
        if (fieldValue.equals(PLACEHOLDER)) {
            Log.error("Placeholder will automatically be used and cannot be added manually.");
        } else {
            final String value = fieldValue;
            this.whereFields.put(fieldName, value);
        }
    }

    /**
     * @param fieldName
     * @param fieldValue
     */
    public void addWhereNotField(final String fieldName, final String fieldValue) {
        if (fieldValue.equals(PLACEHOLDER)) {
            Log.error("Placeholder will automatically be used and cannot be added manually.");
        } else {
            final String value = fieldValue;
            this.whereNotFields.put(fieldName, value);
        }
    }

    /**
     * @return
     */
    public String getUnpreparedStatementString() {
        final StringBuilder statementStringBuilder = new StringBuilder(100);
        // select
        statementStringBuilder.append("SELECT ");
        if (isDistinct) {
            statementStringBuilder.append("DISTINCT ");
        }
        if (this.selectFields.size() > 0) {
            final Iterator<String> it = this.selectFields.iterator();
            while (it.hasNext()) {
                final String fieldName = it.next();
                statementStringBuilder.append(fieldName);
                if (it.hasNext()) {
                    statementStringBuilder.append(",");
                }
            }
        } else {
            statementStringBuilder.append("*");
        }
        // from
        statementStringBuilder.append(" FROM ");
        statementStringBuilder.append(this.tableName);
        // where
        if (this.whereFields.size() > 0) {
            statementStringBuilder.append(" WHERE ");
            final Iterator<Map.Entry<String, String>> it = this.whereFields.entrySet().iterator();
            while (it.hasNext()) {
                final Map.Entry<String, String> entry = it.next();
                statementStringBuilder.append(entry.getKey());
                statementStringBuilder.append("=");
                statementStringBuilder.append(PLACEHOLDER);
                if (it.hasNext()) {
                    statementStringBuilder.append(" AND ");
                }

            }
        }
        if (this.whereNotFields.size() > 0) {
            if (this.whereFields.size() > 0) {
                statementStringBuilder.append(" AND ");
            } else {
                statementStringBuilder.append(" WHERE ");
            }
            final Iterator<Map.Entry<String, String>> it = this.whereNotFields.entrySet().iterator();
            while (it.hasNext()) {
                final Map.Entry<String, String> entry = it.next();
                statementStringBuilder.append(entry.getKey());
                statementStringBuilder.append("!=");
                statementStringBuilder.append(PLACEHOLDER);
                if (it.hasNext()) {
                    statementStringBuilder.append(" AND ");
                }

            }
        }
        if (this.rowLimit > 0) {
            statementStringBuilder.append(" LIMIT ");
            statementStringBuilder.append(this.rowLimit);
        }
        return statementStringBuilder.toString();
    }

    /**
     * @param connection
     * @return
     * @throws SQLException
     */
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(getUnpreparedStatementString());
        int parameterIndex = 1;
        for (String value : whereFields.values()) {
            statement.setString(parameterIndex, value);
            parameterIndex = parameterIndex + 1;
        }
        for (String value : whereNotFields.values()) {
            statement.setString(parameterIndex, value);
            parameterIndex = parameterIndex + 1;
        }
        return statement;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getUnpreparedStatementString();
    }

    /**
     * @return the rowLimit
     */
    public int getRowLimit() {
        return rowLimit;
    }

    /**
     * @param rowLimit the rowLimit to set
     */
    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public boolean isDistinct() {
        return isDistinct;
    }

    public void setDistinct(final boolean isDistinct) {
        this.isDistinct = isDistinct;
    }
}
