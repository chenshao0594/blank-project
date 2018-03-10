package com.blank.common.domain;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

public class DomainMetaBuilder {
	private final static String META_PATH = "domain-meta/{name}_meta.xml";

	public static DomainMetadata build(Class clazz) {
		DomainMetadata metadata = new DomainMetadata();
		// DomainMeta domainMeta = AnnotationUtils.getAnnotation(clazz,
		// DomainMeta.class);
		String domainName = StringUtils.uncapitalize(clazz.getSimpleName());
		DomainMetaConfig metaConfig = buildMetaConfig(domainName);
		metadata.setName(metaConfig.getName());
		metadata.setPluralName(metaConfig.getPluralName());
		List<FieldMeta> metas = new LinkedList<FieldMeta>();
		Field[] fields = FieldUtils.getAllFields(clazz);
		Map<String, FieldMeta> fieldMap = new HashMap<String, FieldMeta>();
		Stream.of(fields).forEach((obj) -> {
			FieldMeta each = new FieldMeta(obj.getName(), obj.getType().getSimpleName().toLowerCase());
			each.setTranslationKey("#{" + domainName + "." + each.getName() + "}");
			metas.add(each);
			fieldMap.put(obj.getName(), each);

		});
		if (!StringUtils.isEmpty(metaConfig.getListFields())) {
			String[] lists = metaConfig.getListFields().split(";");
			metadata.setListFields(Arrays.asList(lists));
		}

		if (!StringUtils.isEmpty(metaConfig.getDialogFields())) {
			String[] dialogFields = metaConfig.getDialogFields().split(";");
			List<FieldMeta> dialogFieldMeta = new LinkedList<FieldMeta>();
			Stream.of(dialogFields).forEach((obj) -> {
				if (fieldMap.get(obj.trim()) != null) {
					dialogFieldMeta.add(fieldMap.get(obj.trim()));
				} else {
					System.out.println("$" + obj.trim() + "$   dialog field is null ");
				}

			});
			metadata.setDialogFields(dialogFieldMeta);
		}

		metadata.setMetaFields(metas);
		return metadata;
	}

	private static DomainMetaConfig buildMetaConfig(String domainName) {
		String path = META_PATH.replace("{name}", domainName);
		DomainMetaConfig config = null;
		InputStream configStream = null;
		try {
			configStream = DomainMetaBuilder.class.getClassLoader().getResourceAsStream(path);
			JAXBContext jaxbContext = JAXBContext.newInstance(DomainMetaConfig.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			config = (DomainMetaConfig) jaxbUnmarshaller.unmarshal(configStream);
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {

		}
		return config;
	}

}
