package com.blank.common.domain.frw;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.blank.common.domain.frw.field.AbstractField;
import com.blank.common.domain.frw.field.ChoiceField;
import com.blank.common.domain.frw.field.ManyToManyField;
import com.blank.common.domain.frw.field.ManyToOneField;
import com.blank.common.domain.frw.field.OneToManyField;
import com.blank.common.domain.frw.field.OneToOneField;
import com.blank.common.domain.frw.field.SimpleFile;

public class DomainMetaBuilder {
	private static Map<Class<?>, DomainMeta> map = new ConcurrentHashMap<Class<?>, DomainMeta>();

	public static DomainMeta build(final Class<?> clazz, final boolean loop) {
		if (map.containsKey(clazz)) {
			return map.get(clazz);
		}
		String domainName = StringUtils.uncapitalize(clazz.getSimpleName());
		DomainMeta domainMeta = new DomainMeta(domainName);
		Map<String, AbstractField> result = domainMeta.getFields();
		Field[] fields = FieldUtils.getAllFields(clazz);
		Stream.of(fields).forEach((field) -> {
			Class<?> type = field.getType();
			String name = field.getName();
			if ("serialVersionUID".equals(name)) {
				return;
			}
			if (field.isAnnotationPresent(ManyToOne.class)) {
				DomainMeta tempMeta = map.get(type);
				if (tempMeta == null && loop) {
					tempMeta = build(type, false);
					map.put(type, tempMeta);
				}
				ManyToOneField meta = new ManyToOneField(name, type);
				meta.setTranslationKey(domainName + "." + name);
				meta.setDomainMeta(tempMeta);
				result.put(name, meta);
			} else if (field.isAnnotationPresent(OneToMany.class)) {
				if (loop) {
					ParameterizedType parameter = (ParameterizedType) field.getGenericType();
					Class<?> cc = (Class<?>) parameter.getActualTypeArguments()[0];
					DomainMeta ccMeta = map.get(cc);
					if (ccMeta == null) {
						synchronized (cc) {
							ccMeta = build(cc, false);
						}
					}
					OneToManyField meta = new OneToManyField(name, type);
					meta.setTranslationKey(domainName + "." + name);
					meta.setActuralType(cc);
					meta.setDomainMeta(ccMeta);
					result.put(name, meta);
				}

			} else if (field.isAnnotationPresent(OneToOne.class)) {
				DomainMeta tempMeta = map.get(type);
				if (tempMeta == null && loop) {
					tempMeta = build(type, false);
					map.put(type, tempMeta);
				}
				OneToOneField meta = new OneToOneField(name, type);
				meta.setTranslationKey(domainName + "." + name);
				meta.setDomainMeta(tempMeta);
				result.put(name, meta);
			} else if (field.isAnnotationPresent(ManyToMany.class)) {
				if (loop) {
					ParameterizedType parameter = (ParameterizedType) field.getGenericType();
					Class<?> cc = (Class<?>) parameter.getActualTypeArguments()[0];
					DomainMeta ccMeta = map.get(cc);
					if (ccMeta == null) {
						synchronized (cc) {
							ccMeta = build(cc, false);
						}
					}
					ManyToManyField meta = new ManyToManyField(name, type);
					meta.setTranslationKey(domainName + "." + name);
					meta.setActuralType(cc);
					meta.setDomainMeta(ccMeta);
					result.put(name, meta);
				}

			} else if (type.isEnum()) {
				ChoiceField meta = new ChoiceField(name, type);
				meta.setTranslationKey(domainName + "." + name);
				result.put(name, meta);
			} else {
				SimpleFile meta = new SimpleFile(name, type);
				meta.setTranslationKey(domainName + "." + name);
				result.put(name, meta);
			}
		});
		map.put(clazz, domainMeta);
		return domainMeta;
	}

}
