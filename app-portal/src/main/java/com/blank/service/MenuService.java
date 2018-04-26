package com.blank.service;

import java.util.Set;

import com.blank.common.service.AbstractService;
import com.blank.domain.Menu;
import com.blank.domain.User;
import com.blank.model.MenuItem;

public interface MenuService extends AbstractService<Menu, Long> {

	Set<MenuItem> loadMenus(User user);

}
