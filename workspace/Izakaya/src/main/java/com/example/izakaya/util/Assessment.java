package com.example.izakaya.util;

/**
 * 評価レベルenum.
 * @author 双江
 *
 */
public enum Assessment {
	ONE("★",1),
	TWO("★★",2),
	THREE("★★★",3),
	FOUR("★★★★",4),
	FIVE("★★★★★",5);
	
	//評価レベル名称
	private String name;
	//評価レベル
	private int level;

	/**
	 * 
	 * @param name
	 * @param level
	 */
	private Assessment(String name, int level) {
		this.name=name;
		this.level=level;
	}
	
	/**
	 * 評価レベル名称より、評価レベルenumを取得する.
	 * @param name 評価レベル名称
	 * @return 評価レベルenum
	 */
	public static Assessment getEnumFromName(String name) {
		if(name == null) {
			return null;
		}
		
		for(Assessment assessment :Assessment.values()) {
			if(assessment.getName().equals(name)) {
				return assessment;
			}
		}
		return null;
	}
	
	/**
	 * 評価レベルより、評価レベルenumを取得する.
	 * @param level 地域level
	 * @return 評価レベルenum
	 */
	public static Assessment getEnumFromLevel(int level) {
		
		for(Assessment assessment :Assessment.values()) {
			if(assessment.getLevel()== level) {
				return assessment;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	
	
	
}
