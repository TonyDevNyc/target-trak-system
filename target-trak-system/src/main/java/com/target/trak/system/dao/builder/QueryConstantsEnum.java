package com.target.trak.system.dao.builder;

public enum QueryConstantsEnum {

	WHERE_CLAUSE(" WHERE 1 = 1"),
	AND (" AND "),
	EQUALS(" = "),
	LIKE(" LIKE "),
	WILDCARD("% "),
	ORDER_BY_CLAUSE(" ORDER BY "),
	ASCENDING_ORDER(" ASC "),
	DESCENDING_ORDER(" DESC "),
	LIMIT_CLAUSE(" LIMIT "),
	COMMA(","),
	EMPTY_SPACE(" ");

	public String value;

	public String getValue() {
		return value;
	}

	private QueryConstantsEnum(String value) {
		this.value = value;
	}
	
	
}
