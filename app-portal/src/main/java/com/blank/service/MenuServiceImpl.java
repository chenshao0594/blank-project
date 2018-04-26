package com.blank.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.blank.common.service.AbstractServiceImpl;
import com.blank.domain.Menu;
import com.blank.domain.User;
import com.blank.model.MenuItem;
import com.blank.repository.MenuRepository;
import com.blank.utils.MenuUtils;

@Service("menuService")
@Transactional
public class MenuServiceImpl extends AbstractServiceImpl<Menu, Long> implements MenuService {

	@Autowired
	private MenuRepository menuRepository;

	public MenuServiceImpl(MenuRepository repository) {
		super(repository);
		this.menuRepository = repository;
	}

	@Override
	public Set<MenuItem> loadMenus(User user) {
		Map<Long, MenuItem> root = new HashMap<Long, MenuItem>();
		Set<MenuItem> result = new HashSet<MenuItem>();
		List<Menu> menuList = this.menuRepository.findAll();
		if (CollectionUtils.isEmpty(menuList)) {
			return null;
		}
		for (Menu each : menuList) {
			Long parentId = each.getParentId();
			MenuItem rootItem = MenuUtils.convert(each);
			if (parentId == 0) {
				root.put(each.getId(), rootItem);
			}
		}
		for (Menu each : menuList) {
			Long parentId = each.getParentId();
			MenuItem item = MenuUtils.convert(each);
			if (parentId != 0) {
				MenuItem rootItem = root.get(parentId);
				rootItem.getItems().add(item);
			}
		}
		root.values().forEach((each) -> {
			result.add(each);
		});
		return result;
	}

}
