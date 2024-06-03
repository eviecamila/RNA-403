/**
 * Introduction to Neural Networks with Java, 2nd Edition
 * Copyright 2008 by Heaton Research, Inc.
 * http://www.heatonresearch.com/books/java-neural-2/
 *
 * ISBN13: 978-1-60439-008-7
 * ISBN:   1-60439-008-5
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
package com.heatonresearch.book.introneuralnet.ch10.sp500;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Random;

import com.heatonresearch.book.introneuralnet.common.ReadCSV;

/**
 * Chapter 10: Application to the Financial Markets
 *
 * FinancialSample: Holds a sample of financial data at the specified date. This
 * includes the close of the SP500 and the prime interest rate.
 *
 * @author Jeff Heaton
 * @version 2.1
 */
public class FinancialSample implements Comparable<FinancialSample> {

    private double amount;
    private double rate;
    private Date date;
    private double percent;

    public int compareTo(final FinancialSample other) {
        return getDate().compareTo(other.getDate());
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * @return the percent
     */
    public double getPercent() {
        return this.percent;
    }

    /**
     * @return the rate
     */
    public double getRate() {
        return this.rate;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(final double amount) {
        this.amount = amount;
    }

    /**
     * @param date the date to set
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(final double percent) {
        this.percent = percent;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(final double rate) {
        this.rate = rate;
    }

    public FinancialSample() {
        Random random = new Random();
        this.amount = 1000 + (5000 - 1000) * random.nextDouble(); // Monto aleatorio entre 1000 y 5000
        this.rate = 1 + (10 - 1) * random.nextDouble(); // Tasa de interés aleatoria entre 1% y 10%
        long currentTime = System.currentTimeMillis();
        long thirtyYearsAgo = 30L * 365 * 24 * 60 * 60 * 1000; // 30 años en milisegundos
        long randomDate = currentTime - (random.nextLong() % thirtyYearsAgo); // Fecha aleatoria en los últimos 30 años
        this.date = new Date(randomDate);
        this.percent = -50 + (100 - (-50)) * random.nextDouble(); // Porcentaje aleatorio entre -50% y 100%
    }

    @Override
public String toString() {
    final NumberFormat nf = NumberFormat.getPercentInstance();
    nf.setMinimumFractionDigits(2);
    nf.setMaximumFractionDigits(2);
    final StringBuilder result = new StringBuilder();
    if (this.date != null) { // Verificar si date no es null
        result.append("\nFecha: ").append(ReadCSV.displayDate(this.date));
    } else {
        result.append("\nFecha: N/A"); // O proporcionar un mensaje alternativo si date es null
    }
    result.append("\nMonto: $").append(this.amount);
    result.append("\nTasa Prime: ").append(this.rate).append("%");
    result.append("\nPorcentaje Anterior: ").append(nf.format(this.percent));
    return result.toString();
}

}
