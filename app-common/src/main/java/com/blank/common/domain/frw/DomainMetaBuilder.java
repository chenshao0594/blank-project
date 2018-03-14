package com.blank.common.domain.frw;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.blank.common.domain.config.DetailPageConfig;
import com.blank.common.domain.config.DomainMetaConfig;
import com.blank.common.model.FieldInfo;

public class DomainMetaBuilder {
	private final static String META_PATH = "domain-meta/{name}_meta.xml";

	public static DomainMeta build(final Class<?> clazz) {

		// build field Map
		Map<String, FieldInfo> fieldMap = buildFieldMap(clazz);
		Map<String, FieldMeta> propertiesMap = buildRootFieldMeta(clazz);

		DomainMetaConfig metaConfig = buildMetaConfig(clazz);
		DomainMeta metadata = new DomainMeta(metaConfig.getName());
		if (!StringUtils.isBlank(metaConfig.getPluralName())) {
			metadata.setPluralName(metaConfig.getPluralName());
		}

		// Detail Page

		DetailPageConfig detailPageConfig = metaConfig.getDetailPage();
		PageMeta detailPageMeta = buildDetailPage(detailPageConfig, fieldMap, propertiesMap);
		metadata.setDetailPage(detailPageMeta);

		// Dialog Page
		DetailPageConfig dialogPageConfig = metaConfig.getDialogPage();
		PageMeta dialogPageMeta = buildDetailPage(dialogPageConfig, fieldMap, propertiesMap);
		metadata.setDialogPage(dialogPageMeta);

		// Dialog Page
		DetailPageConfig listPageConfig = metaConfig.getListPage();
		PageMeta listPageMeta = buildDetailPage(listPageConfig, fieldMap, propertiesMap);
		metadata.setListPage(listPageMeta);
		return metadata;
	}

	private static PageMeta buildDetailPage(DetailPageConfig config, Map<String, FieldInfo> fieldsMap,
			Map<String, FieldMeta> propertiesMap) {
		PageMeta page = new PageMeta();
		page.setTemplate(config.getTemplate());
		if (config.getSubItems() != null) {
			Stream.of(config.getSubItems()).forEach((obj) -> {
				Class<?> fieldClass = fieldsMap.get(obj.getName()).getActualType();
				String fieldsContent = obj.getFields();
				String[] fieldNames = fieldsContent.split(";");
				SubItemMeta subItem = new SubItemMeta(obj.getName());
				Stream.of(fieldNames).forEach((eachField) -> {
					FieldMeta source = propertiesMap.get(fieldClass.getName() + "..." + eachField.trim());
					subItem.getFields().add(source);
				});
				page.getSubItems().add(subItem);
			});
		}
		if (!StringUtils.isBlank(config.getBasicInfo())) {
			for (String each : config.getBasicInfo().split(";")) {
				page.getBasicFields().add(propertiesMap.get(each));
			}
		}
		return page;
	}

	private static Map<String, FieldInfo> buildFieldMap(final Class<?> clazz) {
		Map<String, FieldInfo> fieldMap = new HashMap<String, FieldInfo>();
		FieldUtils.getAllFieldsList(clazz).forEach((obj) -> {
			FieldInfo info = new FieldInfo(obj.getName(), obj.getType());
			if (obj.getGenericType() instanceof ParameterizedType) {
				ParameterizedType parameter = (ParameterizedType) obj.getGenericType();
				Class<?> cc = (Class<?>) parameter.getActualTypeArguments()[0];
				info.setActualType(cc);
			}
			fieldMap.put(info.getName(), info);
		});
		return fieldMap;
	}

	private static Map<String, FieldMeta> buildRootFieldMeta(Class clazz) {
		Map<String, FieldMeta> result = new HashMap<String, FieldMeta>();
		Field[] fields = FieldUtils.getAllFields(clazz);
		String domainName = StringUtils.uncapitalize(clazz.getSimpleName());
		Stream.of(fields).forEach((field) -> {
			Class type = field.getType();
			String name = field.getName();
			if (field.getGenericType() instanceof ParameterizedType) {
				ParameterizedType parameter = (ParameterizedType) field.getGenericType();
				Class<?> cc = (Class<?>) parameter.getActualTypeArguments()[0];
				Map<String, FieldMeta> map = buildFieldMetaSub(cc);
				result.putAll(map);
			} else if (field.isAnnotationPresent(OneToOne.class) || field.isAnnotationPresent(ManyToOne.class)
					|| field.isAnnotationPresent(Embedded.class)) {
				Map<String, FieldMeta> meta = buildFieldMeta(type, name);
				result.putAll(meta);
			} else {
				FieldMeta meta = new FieldMeta(name, type.getSimpleName().toLowerCase());
				meta.setTranslationKey(domainName + "." + name);
				result.put(name, meta);
			}
		});
		return result;
	}

	private static Map<String, FieldMeta> buildFieldMetaSub(Class<?> clazz) {
		Map<String, FieldMeta> map = new HashMap<String, FieldMeta>();
		String domainName = StringUtils.uncapitalize(clazz.getSimpleName());
		Field[] fields = FieldUtils.getAllFields(clazz);
		Stream.of(fields).forEach((field) -> {
			if (field.getGenericType() instanceof ParameterizedType) {

			} else {
				Class type = field.getType();
				String name = field.getName();
				FieldMeta meta = new FieldMeta(name, type.getSimpleName().toLowerCase());
				meta.setTranslationKey(domainName + "." + name);
				map.put(clazz.getName() + "..." + name, meta);
			}
		});
		return map;
	}

	private static Map<String, FieldMeta> buildFieldMeta(Class clazz, String keyPrefix) {
		String tmpKeyPrefix = StringUtils.isBlank(keyPrefix) ? "" : keyPrefix.trim() + ".";
		String domainName = StringUtils.uncapitalize(clazz.getSimpleName());
		Map<String, FieldMeta> metas = new HashMap<String, FieldMeta>();
		Field[] fields = FieldUtils.getAllFields(clazz);
		Stream.of(fields).forEach((obj) -> {
			Class type = obj.getType();
			String name = obj.getName();
			FieldMeta meta = new FieldMeta(tmpKeyPrefix + name, type.getSimpleName().toLowerCase());
			meta.setTranslationKey(domainName + "." + name);
			metas.put(tmpKeyPrefix + name, meta);
		});
		return metas;
	}

	private static DomainMetaConfig buildMetaConfig(final Class clazz) {
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
