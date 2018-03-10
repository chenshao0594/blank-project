package com.blank.common.domain;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.annotation.AnnotationUtils;

public class DomainMetaBuilder {
	private final static String META_PATH = "domain-meta/{name}_meta.xml";

	public static DomainMetadata build(Class clazz) {
		DomainMetadata metadata = new DomainMetadata();

		DomainMeta domainMeta = AnnotationUtils.getAnnotation(clazz, DomainMeta.class);
		String domainName = StringUtils.uncapitalize(clazz.getSimpleName());
		DomainMetaConfig metaConfig = buildMetaConfig(domainName);
		metadata.setName(metaConfig.getName());
		metadata.setPluralName(metaConfig.getPluralName());
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
		if (!StringUtils.isEmpty(metaConfig.getListFields())) {
			String[] lists = metaConfig.getListFields().split(";");
			metadata.setListFields(Arrays.asList(lists));
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

	public static void main(String[] args) throws JAXBException {

	}
}
