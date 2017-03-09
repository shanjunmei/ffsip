package com.ffzx.common.utils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateEditor extends PropertyEditorSupport {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isNotBlank(text)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = format.parse(text);
			} catch (ParseException e) {
				format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date = format.parse(text);
				} catch (ParseException e1) {
					logger.info(text+" convert to date fail",e1);
				}
			}
			setValue(date);
		}
	}

}
