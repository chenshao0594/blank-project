package com.blank.common.domain.frw.page;

import java.io.InputStream;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.blank.common.domain.config.DetailPageConfig;
import com.blank.common.domain.config.DomainMetaConfig;
import com.blank.common.domain.frw.DomainMeta;
import com.blank.common.domain.frw.DomainMetaBuilder;
import com.blank.common.domain.frw.field.AbstractField;
import com.blank.common.domain.frw.field.ComplexField;
import com.blank.common.domain.frw.field.DomainPageMeta;

public class DomainPageBuilder {
	private final static String META_PATH = "domain-meta/{name}_meta.xml";

	public static DomainPageMeta build(final Class<?> clazz) {
		DomainPageMeta pageMeta = new DomainPageMeta(clazz);
		DomainMetaConfig metaConfig = buildMetaConfig(clazz);
		// build domain meta
		pageMeta.setName(metaConfig.getName());
		pageMeta.setPluralName(metaConfig.getPluralName());
		DomainMeta domainMeta = DomainMetaBuilder.build(clazz, true);
		if (!StringUtils.isBlank(metaConfig.getPluralName())) {
			domainMeta.setPlural(metaConfig.getPluralName());
		}
		// Detail Page
		DetailPageConfig detailPageConfig = metaConfig.getDetailPage();
		PageMeta detailPageMeta = buildDetailPage(detailPageConfig, domainMeta);
		pageMeta.setDetailPage(detailPageMeta);

		// Dialog Page
		DetailPageConfig dialogPageConfig = metaConfig.getDialogPage();
		PageMeta dialogPageMeta = buildDetailPage(dialogPageConfig, domainMeta);
		pageMeta.setDialogPage(dialogPageMeta);
		// List Page
		DetailPageConfig listPageConfig = metaConfig.getListPage();
		PageMeta listPageMeta = buildDetailPage(listPageConfig, domainMeta);
		pageMeta.setListPage(listPageMeta);
		return pageMeta;
	}

	private static PageMeta buildDetailPage(DetailPageConfig config, final DomainMeta domainMeta) {
		PageMeta page = new PageMeta();
		page.setTemplate(config.getTemplate());
		if (config.getSubItems() != null) {
			Stream.of(config.getSubItems()).forEach((obj) -> {
				String fieldsContent = obj.getFields();
				String[] fieldNames = fieldsContent.split(";");
				SubPageMeta subItem = new SubPageMeta(obj.getName());
				AbstractField subItemFiled = domainMeta.getFields().get(obj.getName());
				ComplexField complexField = (ComplexField) subItemFiled;
				DomainMeta tempMeta = complexField.getDomainMeta();
				Stream.of(fieldNames).forEach((eachField) -> {
					PageFieldMeta pageField = new PageFieldMeta();
					pageField.setPath(eachField);
					AbstractField field = tempMeta.getFields().get(eachField);
					pageField.setTranslationKey(field.getTranslationKey());
					subItem.getFields().add(pageField);
				});
				page.getSubItems().add(subItem);
			});
		}
		if (!StringUtils.isBlank(config.getBasicInfo())) {
			for (String each : config.getBasicInfo().split(";")) {
				if (StringUtils.isBlank(each)) {
					continue;
				}
				PageFieldMeta pageField = new PageFieldMeta();
				pageField.setPath(each);
				AbstractField fieldMeta = null;
				if (each.contains(".")) {
					String[] keys = each.split("\\.");
					ComplexField temp = (ComplexField) domainMeta.getFields().get(keys[0]);
					pageField.setTranslationKey(temp.getDomainMeta().getFields().get(keys[1]).getTranslationKey());
				} else {
					fieldMeta = domainMeta.getFields().get(each);
					pageField.setTranslationKey(fieldMeta.getTranslationKey());
				}

				page.getBasicFields().add(pageField);
			}
		}
		return page;
	}

	private static DomainMetaConfig buildMetaConfig(final Class<?> clazz) {
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
