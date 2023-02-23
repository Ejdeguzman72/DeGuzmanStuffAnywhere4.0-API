package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateAutoShopException;
import com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop;

public interface AutoShopDao {

	public List<AutoRepairShop> findAllAutoRepairShopInfo();

	public AutoRepairShop findAutoRepairShopById(int auto_shop_id);

	public AutoRepairShop findAutoRepairShopByName(String autoShopName);

	public List<AutoRepairShop> findAutoRepairShopByZip(String zip);

	public long getCountOfAutoRepairShops();

	public int addAutoRepairShopInfo(AutoRepairShop autoShop) throws DuplicateAutoShopException;

	public int updateAutoShopInfo(int autoShopId, AutoRepairShop autoRepairShop);

	public int deleteAutoRepairShopInfo(int auto_shop_id);

	public int deleteAllAutoShop();
}
