package com.devopsbuddy.persistence;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDataTImeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
		// TODO Auto-generated method stub
		return (localDateTime == null ? null : Timestamp.valueOf(localDateTime));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp sqlTimeStamp) {
		// TODO Auto-generated method stub
		return (sqlTimeStamp == null ? null : sqlTimeStamp.toLocalDateTime());
	}

}
