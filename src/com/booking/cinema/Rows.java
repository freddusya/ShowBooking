package com.booking.cinema;

import com.booking.util.PropertiesConstant;

public class Rows {

	private String[] rowArray = {};
	private int limit;
	
	public Rows() throws Exception {
		this(PropertiesConstant.ROWS_MAX);
	}
	
	public Rows(int size) throws Exception{
		setLimit(PropertiesConstant.ROWS_MAX);
		if (size > limit) {
			throw new Exception ("Row Size too large. The limit of Rows is :" + limit);
		}
		setRowArray(new String[size]);
		int characterAtoZ = 65; // letter 'A'
		for (int i = 0; i < size; i++) {
			rowArray[i] = Character.toString((char)characterAtoZ);
			characterAtoZ++;
		}
	}

	
	public String[] getRowArray() {
		return rowArray;
	}

	private void setRowArray(String[] rowArray) {
		this.rowArray = rowArray;
	}

	public int getLimit() {
		return limit;
	}

	private void setLimit(int limit) {
		this.limit = limit;
	}

	
	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(int i = 0; i < this.rowArray.length ; i++) {
			str.append(rowArray[i]);
			if(i+1 != this.rowArray.length) {
				str.append(",");
			}
		}
		str.append("]");
		
		return str.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(new Rows());
			System.out.println(new Rows(26));
			System.out.println(new Rows(10));
			System.out.println(new Rows(15));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
	}

}
