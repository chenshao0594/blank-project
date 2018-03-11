package com.blank.common.domain.frw;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.Embedded;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.annotations.common.reflection.java.generics.TypeUtils;
import org.springframework.beans.BeanUtils;

public class DomainMetaBuilder {
	private final static String META_PATH = "domain-meta/{name}_meta.xml";
	private final static String LIST_PAGE_DEFAULT = "domain/list";
	private final static String DIALOG_PAGE_DEFAULT = "domain/dialog";
	private final static String DETAIL_PAGE_DEFAULT = "domain/detail";

	public static DomainMetadata build(Class clazz) {
		DomainMetadata metadata = new DomainMetadata();
		DomainMetaConfig metaConfig = buildMetaConfig(clazz);
		BeanUtils.copyProperties(metaConfig, metadata);
		List<FieldMeta> rootFieldMeta = buildRootFieldMeta(clazz);
		Map<String, FieldMeta> fieldMap = new HashMap<String, FieldMeta>();
		rootFieldMeta.forEach((obj) -> {
			fieldMap.put(obj.getName(), obj);
		});

		if (!StringUtils.isBlank(metaConfig.getListFields())) {
			String[] listFields = metaConfig.getListFields().split(";");
			List<FieldMeta> listFieldMeta = new LinkedList<FieldMeta>();
			Stream.of(listFields).forEach((obj) -> {
				if (fieldMap.get(obj.trim()) != null) {
					listFieldMeta.add(fieldMap.get(obj.trim()));
				} else {
					System.out.println("$" + obj.trim() + "$   dialog field is null ");
				}

			});
			metadata.setListFields(listFieldMeta);
		}

		if (!StringUtils.isBlank(metaConfig.getDialogFields())) {
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

		if (!StringUtils.isBlank(metaConfig.getDetailFields())) {
			String[] detailFields = metaConfig.getDetailFields().split(";");
			List<FieldMeta> detailFieldMeta = new LinkedList<FieldMeta>();
			Stream.of(detailFields).forEach((obj) -> {
				if (fieldMap.get(obj.trim()) != null) {
					detailFieldMeta.add(fieldMap.get(obj.trim()));
				} else {
					System.out.println("$" + obj.trim() + "$   dialog field is null ");
				}

			});
			metadata.setDetailFields(detailFieldMeta);
		}

		metadata.setMetaFields(rootFieldMeta);
		return metadata;
	}

	private static List<FieldMeta> buildRootFieldMeta(Class clazz) {
		List<FieldMeta> result = new LinkedList<FieldMeta>();
		Field[] fields = FieldUtils.getAllFields(clazz);
		String domainName = StringUtils.uncapitalize(clazz.getSimpleName());
		Stream.of(fields).forEach((field) -> {
			Class type = field.getType();
			String name = field.getName();
			if (field.isAnnotationPresent(OneToMany.class)) {
				System.out.println(name + "is OneToMany");
			} else if (field.isAnnotationPresent(ManyToMany.class)) {
				System.out.println(name + "is ManyToMany  " + field.getGenericType() + TypeUtils.isSimple(type));
			} else if (field.isAnnotationPresent(ManyToOne.class)) {
				System.out.println(name + "is ManyToOne");
			} else if (field.isAnnotationPresent(OneToOne.class)) {
				List<FieldMeta> meta = buildFieldMeta(type, name);
				result.addAll(meta);
				System.out.println(name + "is OneToOne" + TypeUtils.isSimple(type));
			} else if (field.isAnnotationPresent(Embedded.class)) {
				System.out.println(name + "is Embedded");
			} else {
				// System.out.println("simple " + TypeUtils.isSimple(type));
				FieldMeta meta = new FieldMeta(name, type.getSimpleName().toLowerCase());
				meta.setTranslationKey(domainName + "." + name);
				result.add(meta);
			}
		});

		return result;
	}

	private static List<FieldMeta> buildFieldMeta(Class clazz, String keyPrefix) {
		String tmpKeyPrefix = StringUtils.isBlank(keyPrefix) ? "" : keyPrefix.trim() + ".";
		String domainName = StringUtils.uncapitalize(clazz.getSimpleName());
		List<FieldMeta> metas = new LinkedList<FieldMeta>();
		Field[] fields = FieldUtils.getAllFields(clazz);
		Stream.of(fields).forEach((obj) -> {
			Class type = obj.getType();
			String name = obj.getName();
			FieldMeta meta = new FieldMeta(tmpKeyPrefix + name, type.getSimpleName().toLowerCase());
			meta.setTranslationKey(domainName + "." + name);
			metas.add(meta);
		});
		return metas;
	}

	private static DomainMetaConfig buildMetaConfig(Class clazz) {
		String domainName = StringUtils.uncapitalize(clazz.getSimpleName());
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
			IOUtils.closeQuietly(configStream);
		}
		return config;
	}

}
