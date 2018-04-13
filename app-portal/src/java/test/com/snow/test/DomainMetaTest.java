package com.snow.test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import com.blank.domain.Owner;

public class DomainMetaTest {
	private static Map<Class<?>, DomainMeta> map = new HashMap<Class<?>, DomainMeta>();

	private static DomainMeta buildRootFieldMeta(Class clazz, boolean loop) {
		DomainMeta domainMeta = new DomainMeta();
		Map<String, AbstractField> result = domainMeta.getFields();
		Field[] fields = FieldUtils.getAllFields(clazz);
		String domainName = StringUtils.uncapitalize(clazz.getSimpleName());
		Stream.of(fields).forEach((field) -> {
			Class<?> type = field.getType();
			String name = field.getName();
			if (field.isAnnotationPresent(ManyToOne.class)) {
				ManyToOneField meta = new ManyToOneField(name, type);
				meta.setTranslationKey(domainName + "." + name);
				result.put(name, meta);
			} else if (field.isAnnotationPresent(OneToMany.class)) {
				if (loop) {
					ParameterizedType parameter = (ParameterizedType) field.getGenericType();
					Class<?> cc = (Class<?>) parameter.getActualTypeArguments()[0];
					DomainMeta ccMeta = map.get(cc);
					if (ccMeta == null) {
						synchronized (cc) {
							ccMeta = buildRootFieldMeta(cc, false);
						}
					}
					OneToManyField meta = new OneToManyField(name, type);
					meta.setTranslationKey(domainName + "." + name);
					meta.setActuralType(cc);
					meta.setDomainMeta(ccMeta);
					result.put(name, meta);
				}

			} else if (field.isAnnotationPresent(OneToOne.class)) {
				OneToOneField meta = new OneToOneField(name, type);
				meta.setTranslationKey(domainName + "." + name);
				result.put(name, meta);
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

	@Test
	public void test() throws ClassNotFoundException {
		System.out.println(DomainMetaTest.buildRootFieldMeta(Owner.class, true));
	}

}
