package br.com.vinicius.signo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {

	public static final String PATTERN_DATA_PADRAO = "dd/MM/yyyy";
	public static final String PATTERN_TEMPO_PADRAO = "hh:mm";

	private DateUtils() {
		throw new UnsupportedOperationException();
	}

	public static Date parseData(String data, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String formatData(Date data, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(data);
	}
	
	public static Date parseTempo(String tempo, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(tempo);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String formatTempo(Date tempo, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(tempo);
	}

}
