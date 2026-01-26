/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejo_fechas;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 *
 * @author Manuela
 */
public class ManejoFecha {

    public static void main(String[] args) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Locale locale = Locale.getDefault();

        //  Obtener fecha actual
        LocalDateTime hoy = LocalDateTime.now();

        // Crear fecha específica (Of permite pasar los parámetros directamente)
        // NOTA: Enero es 1 o la constante Month.JANUARY
        LocalDateTime fecha1 = LocalDateTime.of(2026, Month.JANUARY, 31, 16, 11, 44);

        // Construir fecha2 usando 'with' (como las fechas son inmutables, esto crea una nueva instancia)
        LocalDateTime fecha2 = LocalDateTime.of(2026, 1, 1, 22, 36, 22, 123_000_000); // 123ms a nanosegundos

        // ------------ VISUALIZAR  ------------
        System.out.println("---------- VISUALIZAR FECHAS -------");
        System.out.println("Fecha de hoy (sin formato): " + hoy);
        System.out.println("Fecha de hoy (con formato): " + hoy.format(formato));

        System.out.println("\nValor de fecha1 (con formato): " + fecha1.format(formato));
        System.out.println("Valor de fecha2 (con formato): " + fecha2.format(formato));

        // --- EXTRACCIÓN DE DATOS ---
        System.out.println("\n------- DETALLES DE FECHA 2 -------");
        System.out.println("Año : " + fecha2.getYear());
        System.out.println("Mes (Número 1-12): " + fecha2.getMonthValue());
        System.out.println("Mes (String): " + fecha2.getMonth().getDisplayName(TextStyle.SHORT, locale));
        System.out.println("Dia del mes: " + fecha2.getDayOfMonth());
        System.out.println("Dia de la semana (Número): " + fecha2.getDayOfWeek().getValue());
        System.out.println("Dia de la semana (String): " + fecha2.getDayOfWeek().getDisplayName(TextStyle.FULL, locale));

        // Semanas (requiere campos temporales específicos)
        System.out.println("Semana del año: " + fecha2.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
        System.out.println("Dia del año: " + fecha2.getDayOfYear());

        System.out.println("Reloj 24h: " + fecha2.getHour());
        System.out.println("Minutos  : " + fecha2.getMinute());
        System.out.println("Milisegundos: " + (fecha2.getNano() / 1_000_000));

        // --- ARITMÉTICA DE FECHAS ---
        System.out.println("\n------- ARITMÉTICA -------");
        LocalDateTime hoyMas10 = hoy.plusDays(10);
        System.out.println("Sumar 10 dias: " + hoyMas10.format(formato));

        LocalDateTime hoyMenos20 = hoy.minusDays(20);
        System.out.println("Restar 20 dias: " + hoyMenos20.format(formato));

        // --- COMPARAR FECHAS ---
        System.out.println("\n------- COMPARAR FECHAS -------");
        System.out.println("¿Hoy es después de fecha1?: " + hoy.isAfter(fecha1));
        System.out.println("¿Hoy es antes de fecha1?: " + hoy.isBefore(fecha1));

        // --- CÁLCULO DE DIFERENCIAS ---
        System.out.println("\n------- CÁCULO DE DIFERENCIA -------");
        LocalDateTime fecha3 = LocalDateTime.now().withMonth(Month.MARCH.getValue());
        LocalDateTime fecha4 = LocalDateTime.now().withMonth(Month.FEBRUARY.getValue());

        long diasEntre = ChronoUnit.DAYS.between(fecha4, fecha3);
        System.out.println("Días entre fecha3 y fecha4: " + diasEntre);
    }

}
