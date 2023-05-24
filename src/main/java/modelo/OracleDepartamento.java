package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.sql.CHAR;
import oracle.sql.NUMBER;
import oracle.sql.STRUCT;
//https://docs.oracle.com/en/database/oracle/oracle-database/18/jjdbc/Oracle-object-types.html#GUID-E47B8141-3C4C-44D5-BB5C-2F8E16DF5140

public class OracleDepartamento implements OracleData, OracleDataFactory {

	

	private NUMBER deptno;
	private CHAR name;
	private CHAR location;



	public static OracleDataFactory getOracleDataFactory() {
		return new OracleDepartamento();
	}	




	public OracleDepartamento(NUMBER deptno, CHAR name, CHAR location) {
		super();
		this.deptno = deptno;
		this.name = name;
		this.location = location;
	}




	public OracleDepartamento() {
		
	}

	@Override
	public Object toJDBCObject(Connection c) throws SQLException {
		Object[] attributes = { deptno, name, location };
		Struct struct = c.createStruct("PEOPLE_USER.DEPT_TABLE", attributes);
		return struct;
	}

	@Override
	public OracleData create(Object jdbcValue, int sqlType) throws SQLException {

		if (jdbcValue == null)
			return null;
		Object[] attributes = ((STRUCT) jdbcValue).getOracleAttributes();
		return new OracleDepartamento((NUMBER) attributes[0], (CHAR) attributes[1], (CHAR) attributes[2]);

	}

//	@Override
//	public String toString() {
//		try {
//			return "JPersona [idno=" + idno.intValue() + ", first_name=" + first_name + ", last_name=" + last_name
//					+ ", email=" + email + ", phone=" + phone + "]";
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//			return "JPersona [ idno=" + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
//					+ ", phone=" + phone + "]";
//		}
//
//	}

	
	

	@Override
	public String toString() {
		try {
			return "JDepartamento [deptno=" + deptno.intValue() + ", name=" + name + ", location=" + location + "]";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "JDepartamento [deptno= , name=" + name + ", location=" + location + "]";
		}
	}




	
}
