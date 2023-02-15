package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop;

public class AutoShopAddUpdateResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7514895451725801321L;
	
	AutoRepairShop autoShop;

	public AutoRepairShop getAutoShop() {
		return autoShop;
	}

	public void setAutoShop(AutoRepairShop autoShop) {
		this.autoShop = autoShop;
	}
}
