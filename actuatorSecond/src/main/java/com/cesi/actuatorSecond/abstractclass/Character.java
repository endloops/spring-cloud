package com.cesi.actuatorSecond.abstractclass;

import com.cesi.actuatorSecond.test.interfaces.WeaponBehavior;

public abstract class Character{
	
	protected WeaponBehavior beahior;
	
	public abstract void fight();
	
	protected abstract void setWeapon(WeaponBehavior w);
}
