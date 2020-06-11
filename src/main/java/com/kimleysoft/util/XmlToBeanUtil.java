package com.kimleysoft.util;


public class XmlToBeanUtil {

	public static <T> T toBean(Class<T> clazz, String xml) {
		try {
			XStreamEx xstream = new XStreamEx();
			//XStream xstream = new XStream();
			xstream.processAnnotations(clazz);
			xstream.autodetectAnnotations(true);
			xstream.setClassLoader(clazz.getClassLoader());
			return (T) xstream.fromXML(xml);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
