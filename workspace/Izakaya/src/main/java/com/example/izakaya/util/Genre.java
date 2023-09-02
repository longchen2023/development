package com.example.izakaya.util;

/**
 * ジャンルenum.
 * @author 双江
 *
 */
public enum Genre {

    JAPANESES_STYLE("和",1),
	CHINA_STYLE("中",2),
	WESTERN_STYLE("洋",3);
	
	//ジャンル名称
	private String name;
	//ジャンル番号
	private int no;

	/**
	 * 
	 * @param name
	 * @param no
	 */
	private Genre(String name, int no) {
		this.name=name;
		this.no=no;
	}
	
	/**
	 * ジャンル名称より、ジャンルenumを取得する.
	 * @param name ジャンル名称
	 * @return ジャンルenum
	 */
	public static Genre getEnumFromName(String name) {
		if(name == null) {
			return null;
		}
		
		for(Genre genre :Genre.values()) {
			if(genre.getName().equals(name)) {
				return genre;
			}
		}
		return null;
	}
	
	/**
	 * ジャンルnoより、ジャンルenumを取得する.
	 * @param no ジャンルno
	 * @return ジャンルenum
	 */
	public static Genre getEnumFromNo(int no) {
		
		for(Genre genre :Genre.values()) {
			if(genre.getNo()== no) {
				return genre;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	
	
}
