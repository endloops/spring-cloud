package com.cesi.actuatorSecond.classes;

import com.cesi.actuatorSecond.abstractclass.Character;
import com.cesi.actuatorSecond.test.interfaces.WeaponBehavior;

/**
 * @author wang
 *
 */
public class Queen extends Character{
	
	@Override
	public void fight() {
		beahior.useWeapon();
	}
	
	public Queen(){
	}

	@Override
	protected void setWeapon(WeaponBehavior w) {
		this.beahior = w;
	}
	public static void main(String[] args) {
		Queen ss = new Queen();
		ss.setWeapon(new AxeBehavior());
		ss.fight();
	}
}