package com.bjzcyl.util;

import java.math.BigInteger;

/**
 * 权限计算帮助类
 * @author fh qq 3 1 3 5 96790[青苔]
 * 修改日期：2015/11/2
 */
public class RightsHelper {
	public static BigInteger sumRights(int[] rights){
		BigInteger num = new BigInteger("0");
		for(int i=0; i<rights.length; i++){
			num = num.setBit(rights[i]);
		}
		return num;
	}
	
	public static BigInteger sumRights(String[] rights){
		BigInteger num = new BigInteger("0");
		for(int i=0; i<rights.length; i++){
			num = num.setBit(Integer.parseInt(rights[i]));
		}
		return num;
	}
	
	public static boolean testRights(BigInteger sum,int targetRights){
		return sum.testBit(targetRights);
		//return true;
	}
	
	public static boolean testRights(String sum,int targetRights){
		if(Tools.isEmpty(sum))
			return false;
		return testRights(new BigInteger(sum),targetRights);
	}
	
	public static boolean testRightsMenu(String sum,String targetRights){
		if(sum == null || sum.isEmpty()) return false;
		if(sum.contains("^" + targetRights + "-") || sum.contains("-" + targetRights + "^") || sum.contains("^" + targetRights + "^"))
			return true;
		return false;
	}
	
	public static boolean testRightsPermission(String sum, String targetRights){
		if(sum == null || sum.isEmpty()) return false;
		if(sum.contains(targetRights))
			return true;
		return false;
	}
	
	public static boolean testRights(String sum,String targetRights){
		if(Tools.isEmpty(sum))
			return false;
		return testRights(new BigInteger(sum),targetRights);
	}
	
	
	public static boolean testRights(BigInteger sum,String targetRights){
		return testRights(sum,Integer.parseInt(targetRights));
	}
}
