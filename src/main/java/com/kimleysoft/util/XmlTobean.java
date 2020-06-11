package com.kimleysoft.util;

public class XmlTobean {

	public static <T> T toBean(Class<T> clazz, String xml) {
		try {
			XStreamEx xstream = new XStreamEx();
			xstream.processAnnotations(clazz);
			xstream.autodetectAnnotations(true);
			xstream.setClassLoader(clazz.getClassLoader());
			return (T) xstream.fromXML(xml);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
