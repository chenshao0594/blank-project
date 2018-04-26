package com.blank.utils;

import com.blank.domain.Menu;
import com.blank.model.MenuItem;

public class MenuUtils {

	public static MenuItem convert(Menu menu) {
		MenuItem item = new MenuItem();
		item.setId(menu.getId());
		item.setName(menu.getName());
		item.setOrder(menu.getOrder());
		item.setType(menu.getType());
		item.setUrl(menu.getUrl());
		return item;
	}

}
