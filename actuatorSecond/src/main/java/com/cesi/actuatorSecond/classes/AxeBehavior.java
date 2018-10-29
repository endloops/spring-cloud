package com.cesi.actuatorSecond.classes;

import com.cesi.actuatorSecond.test.interfaces.WeaponBehavior;

public class AxeBehavior implements WeaponBehavior{

	@Override
	public void useWeapon() {
		
		System.out.println("用斧头劈");
	}

}
