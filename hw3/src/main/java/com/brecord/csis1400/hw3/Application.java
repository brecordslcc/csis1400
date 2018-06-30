package com.brecord.csis1400.hw3;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.brecord.csis1400.hw3.Application.FilingStatus.HeadOfHousehold;
import static com.brecord.csis1400.hw3.Application.FilingStatus.MarriedJoint;
import static com.brecord.csis1400.hw3.Application.FilingStatus.MarriedSeparate;
import static com.brecord.csis1400.hw3.Application.FilingStatus.Single;

public class Application {

    enum FilingStatus {
        Single("Single"),
        MarriedJoint("Married Filing Jointly or Qualified Widower"),
        MarriedSeparate("Married Filing Separately"),
        HeadOfHousehold("Head of Household");

        private final String name;

        FilingStatus(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private final static DecimalFormat decimalFormat;

    private final static Map<FilingStatus, TreeMap<Float, Float>> taxRates;

    static {
        decimalFormat = new DecimalFormat("'$'#,###.00");

        taxRates = new HashMap<>();

        for (final FilingStatus filingStatus : FilingStatus.values()) {
            taxRates.put(filingStatus, new TreeMap<>());
        }

        taxRates.get(Single).put(8350f, .1f);
        taxRates.get(Single).put(33950f, .15f);
        taxRates.get(Single).put(82250f, .25f);
        taxRates.get(Single).put(171550f, .28f);
        taxRates.get(Single).put(372950f, .33f);
        taxRates.get(Single).put(Float.MAX_VALUE, .35f);

        taxRates.get(MarriedJoint).put(16700f, .1f);
        taxRates.get(MarriedJoint).put(67900f, .15f);
        taxRates.get(MarriedJoint).put(137050f, .25f);
        taxRates.get(MarriedJoint).put(208850f, .28f);
        taxRates.get(MarriedJoint).put(372950f, .33f);
        taxRates.get(MarriedJoint).put(Float.MAX_VALUE, .35f);

        taxRates.get(MarriedSeparate).put(8350f, .1f);
        taxRates.get(MarriedSeparate).put(33950f, .15f);
        taxRates.get(MarriedSeparate).put(68525f, .25f);
        taxRates.get(MarriedSeparate).put(104425f, .28f);
        taxRates.get(MarriedSeparate).put(186475f, .33f);
        taxRates.get(MarriedSeparate).put(Float.MAX_VALUE, .35f);

        taxRates.get(HeadOfHousehold).put(11950f, .1f);
        taxRates.get(HeadOfHousehold).put(45500f, .15f);
        taxRates.get(HeadOfHousehold).put(117450f, .25f);
        taxRates.get(HeadOfHousehold).put(190200f, .28f);
        taxRates.get(HeadOfHousehold).put(372950f, .33f);
        taxRates.get(HeadOfHousehold).put(Float.MAX_VALUE, .35f);
    }

    public static void main(String[] args) {
        displayTotalTax(getUserFilingStatus(), getUserTaxableIncome());
    }

    private static void displayTotalTax(final FilingStatus filingStatus, float taxableIncome) {
        float lastMaxTaxed = 0f,
                tax = 0f;

        for (Float maxTaxableIncome : taxRates.get(filingStatus).keySet()) {
            float amountToTax = maxTaxableIncome - lastMaxTaxed,
                    taxRate = taxRates.get(filingStatus).get(maxTaxableIncome);

            tax += taxRate * (taxableIncome < amountToTax ? taxableIncome : amountToTax);

            if ((taxableIncome -= amountToTax) <= 0)
                break;

            lastMaxTaxed = maxTaxableIncome;
        }

        JOptionPane.showInputDialog(String.format("Tax is %s", decimalFormat.format(tax)));
    }

    private static float getUserTaxableIncome() {
        String message = "Enter your taxable income:",
                error = "Invalid input.\n\n";

        while (true) {
            try {
                final float input = Float.parseFloat(JOptionPane.showInputDialog(message));
                if (input < 0)
                    throw new NumberFormatException();
                return input;
            } catch (final NumberFormatException e) {
                if (!message.startsWith(error))
                    message = error.concat(message);
            }
        }
    }

    private static FilingStatus getUserFilingStatus() {
        StringBuilder message = new StringBuilder("Enter your filing status:\n\n");
        String error = "Invalid input.\n\n";

        for (int i = 0; i < FilingStatus.values().length; i++) {
            message.append(String.format("%s for %s\n", i, FilingStatus.values()[i]));
        }

        message.append("\n");

        while (true) {
            try {
                return FilingStatus.values()[Integer.valueOf(JOptionPane.showInputDialog(message.toString()).trim())];
            } catch (final NumberFormatException | ArrayIndexOutOfBoundsException e) {
                if (!message.toString().startsWith(error))
                    message = new StringBuilder(error.concat(message.toString()));
            }
        }
    }
}
