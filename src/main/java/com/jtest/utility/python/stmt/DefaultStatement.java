package com.jtest.utility.python.stmt;


import com.jtest.utility.python.checker.PyError;

public class DefaultStatement extends IStatement {

	public DefaultStatement(String line, int startline, int endline) {
		super(line, startline, endline);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int parseStmt(String[] lines, int ilineno) throws PyError {
		// TODO Auto-generated method stub
		return 0;
	}

}
