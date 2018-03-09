package com.blank.common.domain;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.annotation.AnnotationUtils;

public class DomainMetaBuilder {
	public static DomainMetadata build(Class clazz) {
		DomainMetadata metadata = new DomainMetadata();
		DomainMeta domainMeta = AnnotationUtils.getAnnotation(clazz, DomainMeta.class);
		String domainName = StringUtils.uncapitalize(clazz.getSimpleName());
		metadata.setName(domainName);
		metadata.setPluralName(domainMeta.pluralName());
		List<FieldMeta> metas = new LinkedList<FieldMeta>();
		Field[] fields = FieldUtils.getAllFields(clazz);
		for (Field field : fields) {
			if (field.getName().equals("serialVersionUID")) {
				continue;
			}
			FieldMeta each = new FieldMeta(field.getName(), field.getType().getSimpleName().toLowerCase());
			each.setTranslationKey("#{" + domainName + "." + each.getName() + "}");
			metas.add(each);
		}
		metadata.setMetaFields(metas);
		return metadata;
	}
}
