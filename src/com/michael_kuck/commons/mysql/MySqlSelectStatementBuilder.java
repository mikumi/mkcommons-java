/**
 * 
 */
package com.michael_kuck.commons.mysql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author michaelkuck
 * 
 */
public class MySqlSelectStatementBuilder {

	final private static String PLACEHOLDER = "?";
	final private ArrayList<String> selectFields;
	final private String tableName;
	final private HashMap<String, String> whereFields;
	final private HashMap<String, String> whereNotFields;

	/**
	 * @param tableName
	 */
	public MySqlSelectStatementBuilder(final String tableName)
	{
		this.selectFields = new ArrayList<String>();
		this.tableName = tableName;
		this.whereFields = new HashMap<String, String>();
		this.whereNotFields = new HashMap<String, String>();
	}

	/**
	 * @param fieldName
	 */
	public void addSelectField(final String fieldName)
	{
		this.selectFields.add(fieldName);
	}

	/**
	 * @param fieldNames
	 */
	public void addSelectFields(final String[] fieldNames)
	{
		for (final String fieldName : fieldNames) {
			addSelectField(fieldName);
		}
	}

	/**
	 * @param fieldName
	 * @param fieldValue
	 */
	public void addWhereField(final String fieldName, final String fieldValue)
	{
		this.whereFields.put(fieldName, "'" + fieldValue + "'");
	}

	/**
	 * @param fieldName
	 */
	public void addWhereFieldWithPlaceholder(final String fieldName)
	{
		addWhereField(fieldName, PLACEHOLDER);
	}

	public void addWhereFieldsWithPlaceholder(final String[] fieldNames)
	{
		for (final String fieldname : fieldNames) {
			addWhereField(fieldname, PLACEHOLDER);
		}
	}
	
	/**
	 * @param fieldName
	 * @param fieldValue
	 */
	public void addWhereNotField(final String fieldName, final String fieldValue)
	{
		this.whereNotFields.put(fieldName, "'" + fieldValue + "'");
	}

	/**
	 * @param fieldName
	 */
	public void addWhereNotFieldWithPlaceholder(final String fieldName)
	{
		addWhereNotField(fieldName, PLACEHOLDER);
	}

	public void addWhereNotFieldsWithPlaceholder(final String[] fieldNames)
	{
		for (final String fieldname : fieldNames) {
			addWhereNotField(fieldname, PLACEHOLDER);
		}
	}

	/**
	 * @return
	 */
	public String getStatement()
	{
		final StringBuilder statementStringBuilder = new StringBuilder(100);
		// select
		statementStringBuilder.append("SELECT ");
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
				statementStringBuilder.append(entry.getValue());
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
				statementStringBuilder.append(entry.getValue());
				if (it.hasNext()) {
					statementStringBuilder.append(" AND ");
				}

			}
		}
		return statementStringBuilder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.getStatement();
	}

}
