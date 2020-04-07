package br.com.cast.avaliacao.service.rules;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe auxiliar para controle de datas
 * @author jmveloso
 */
public class Datas {

	/**
	 * Remove hora, minuto, segundo e milisegundos da data informada como parametro
	 * @param data
	 * @return
	 */
	public static Date removeTempo(Date data) {
		
		Calendar calendar = getCalendar(data);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
	
	public static Date adicionaDias(Date data, Integer dias) {
		
		Calendar calendar = getCalendar(data);
		calendar.add(Calendar.DATE, dias);
	
		return calendar.getTime();
	}
	
	private static Calendar getCalendar(Date data) {
		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(data);
		return calendar;
	}
}