package dt;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import java.io.File;

import dt.Tuny;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------");

        // System.out.println(jrxml.exists() ? jrxml.getAbsolutePath() : "DUYTRUNG");

        // generateReport("nana-list", new JRBeanCollectionDataSource(Arrays.asList(new TunyTune[] { new TunyTune() })));

        List<Tuny> tunyList = Arrays.asList(TunyFactory.getTunyList());
        List<Tuny> olderThan400 = tunyList.stream().filter(tuny -> tuny.getAge().compareTo(new BigDecimal(4000)) > 0).collect(Collectors.toList());
        System.out.println(olderThan400.size());

        // System.out.println(convertToStringWithUnit("DUYTRUNG", UnitName.YEN_KILOWATT_HOUR));

        System.out.println("----------------------------------------------------");
    }

    public static void generateReport(String fileName, JRBeanCollectionDataSource beanDataSource) {
        File jrxml = new File(fileName + ".jrxml");

        System.out.println(jrxml.exists() ? jrxml.getAbsolutePath() : "DUYTRUNG");

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxml.getAbsolutePath());

            JasperPrint print = JasperFillManager.fillReport(jasperReport, null, beanDataSource);

            // JasperPrint print = JasperFillManager.fillReport(jasperReport, null,
            //         new JRTableModelDataSource(new TunyTableModel()));

            File pdf = new File(fileName + ".pdf");

            JasperExportManager.exportReportToPdfFile(print, pdf.getAbsolutePath());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static class TunyWrapper {
        private String title;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private Tuny[] tunies;

        public Tuny[] getTunies() {
            return this.tunies;
        }

        public void setTunies(Tuny[] tunies) {
            this.tunies = tunies;
        }

        public TunyWrapper() {
            // this.title = "DUYTRUNG";
            this.tunies = TunyFactory.getTunyList();
        }
    }

    private static class TunyTableModel extends AbstractTableModel {

        private String[] columnNames = { "id", "name", "bio", "bambi" };

        private Object[][] data = {
        };

        public TunyTableModel() {
            data = new Object[][] { { "田中-結菜", "Product Communications Liaison",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_8 rv:5.0; SL) AppleWebKit/533.2.1 (KHTML, like Gecko) Version/7.0.1 Safari/533.2.1",
                    "Metrics" },
                    { "高橋-陽菜", "Chief Web Designer",
                            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.0; Trident/3.0; .NET CLR 3.0.59231.0)",
                            "Configuration" },
                    { "斎藤-心愛", "Human Optimization Supervisor",
                            "Mozilla/5.0 (Windows; U; Windows NT 6.1) AppleWebKit/532.0.1 (KHTML, like Gecko) Chrome/32.0.806.0 Safari/532.0.1",
                            "Infrastructure" },
                    { "鈴木-陸斗", "National Factors Associate",
                            "Mozilla/5.0 (Windows; U; Windows NT 6.3) AppleWebKit/537.2.1 (KHTML, like Gecko) Chrome/27.0.819.0 Safari/537.2.1",
                            "Accounts" },
                    { "斎藤-蓮", "Product Solutions Strategist",
                            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_0)  AppleWebKit/538.1.1 (KHTML, like Gecko) Chrome/32.0.854.0 Safari/538.1.1",
                            "Response" },
                    { "山田-陸斗", "International Identity Coordinator",
                            "Mozilla/5.0 (Windows; U; Windows NT 6.1) AppleWebKit/532.2.2 (KHTML, like Gecko) Chrome/23.0.837.0 Safari/532.2.2",
                            "Mobility" },
                    { "山本-結愛", "Customer Accounts Planner",
                            "Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 6.3; Trident/3.0; .NET CLR 3.5.47347.9)",
                            "Mobility" },
                    { "山口-結菜", "Dynamic Response Engineer",
                            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_9_7 rv:3.0; PT) AppleWebKit/534.2.2 (KHTML, like Gecko) Version/6.1.9 Safari/534.2.2",
                            "Marketing" },
                    { "林-颯太", "National Identity Engineer",
                            "Mozilla/5.0 (Windows NT 6.0; rv:9.4) Gecko/20100101 Firefox/9.4.9", "Creative" },
                    { "松本-颯太", "Dynamic Accountability Orchestrator",
                            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_3 rv:6.0; BR) AppleWebKit/538.2.0 (KHTML, like Gecko) Version/5.0.10 Safari/538.2.0",
                            "Paradigm" },
                    { "伊藤-莉子", "Central Applications Supervisor",
                            "Mozilla/5.0 (Windows NT 5.2; Win64; x64; rv:9.0) Gecko/20100101 Firefox/9.0.0",
                            "Creative" },
                    { "小林-大翔", "Lead Intranet Orchestrator",
                            "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/6.0)", "Assurance" }, };
        }

        @Override
        public int getRowCount() {
            return this.data.length;
        }

        @Override
        public int getColumnCount() {
            return this.columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return this.columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return this.data[rowIndex][columnIndex];
        }

    }

    private class InvoicingDetailsModel extends AbstractTableModel {

        private static final long serialVersionUID = 1L;

        private String[] columnNames = {};

        private Object[][] data = {};

        public InvoicingDetailsModel() {
        }

        public InvoicingDetailsModel(InvoicingHtbeGetInvoiceHistoryResult invoicingHistory, T948EntityModel issuanceHistoryDetail, List<T949EntityModel> billingHistoryFeeBreakdown) {
            String serviceType = issuanceHistoryDetail.getT948101();

            if(serviceType.equalsIgnoreCase(HIGH_PRESSURE)) {
                columnNames = new String[] {
                    "dateOfIssuance",
                    "billingDate",
                    "billableNumberOfBases",
                    "billingAmount",
                    "contractPower",
                    "contractType",
                    "customerNumber",
                    "demandAddress",
                    "demandDestinationBillApartmentName",
                    "demandDestinationName",
                    "demandLocationPlantName",
                    "fuelCostAdjustmentAmountTheMonth_1",
                    "fuelCostAdjustmentAmountTheNextMonth_1",
                    "maximumDemandYears_1",
                    "maximumDemandPower_1",
                    "maximumDemandPower_10",
                    "maximumDemandPower_11",
                    "maximumDemandPower_12",
                    "maximumDemandPower_2",
                    "maximumDemandPower_3",
                    "maximumDemandPower_4",
                    "maximumDemandPower_5",
                    "maximumDemandPower_6",
                    "maximumDemandPower_7",
                    "maximumDemandPower_8",
                    "maximumDemandPower_9",
                    "maximumDemandYears_10",
                    "maximumDemandYears_11",
                    "maximumDemandYears_12",
                    "maximumDemandYears_2",
                    "maximumDemandYears_3",
                    "maximumDemandYears_4",
                    "maximumDemandYears_5",
                    "maximumDemandYears_6",
                    "maximumDemandYears_7",
                    "maximumDemandYears_8",
                    "maximumDemandYears_9",
                    "monthEffectiveAmountOfPower",
                    "monthEffectiveInstructionNumberMainLine",
                    "monthEffectiveInstructionNumberSpareLine",
                    "monthFullTimeInstructionNumberMainLine",
                    "monthFullTimeInstructionNumberSpareLine",
                    "monthInvalidInstructionNumberMainLine",
                    "monthInvalidInstructionNumberSpareLine",
                    "monthMaximumDemandPower",
                    "monthMultiplyingFactor",
                    "monthReactiveEnergy",
                    "periodOfUseOptimal",
                    "periodOfUseSelf",
                    "powerFactor",
                    "previousMonthFuelCostAdjustmentAmount",
                    "renewableEnergyPowerGenerationPromotionLevyTheMonth",
                    "supplyPointSpecificNumber",
                    "taxEquivalent",
                    "thePreviousMonthFullTimeInstructionNumberMainLine",
                    "thePreviousMonthFullTimeInstructionNumberSpareLine",
                    "thePreviousMonthInvalidInstructionNumberMainLine",
                    "thePreviousMonthInvalidInstructionNumberSpareLine",
                    "thePreviousMonthMultiplyingFactor",
                    "thePreviousMonthTheEffectiveInstructionNumberMainLine",
                    "thePreviousMonthTheEffectiveInstructionNumberSpareLine",
                    "theTotalAmountOfElectricPowerUsed",
                    "totalAmt",
                    "feeBreakdownList"
                };

                data = new Object[][] {
                    {
                        invoicingHistory.getDateOfIssuance(),
                        invoicingHistory.getBillingDate(),
                        convertBigDecimalToString(invoicingHistory.getBillableNumberOfBases()),   // NUMERIC
                        convertBigDecimalToString(issuanceHistoryDetail.getT948901()),   // NUMERIC | billingAmount
                        convertBigDecimalToString(issuanceHistoryDetail.getT948109()),   // NUMERIC | contractPower
                        issuanceHistoryDetail.getT948108(),   // contractType
                        issuanceHistoryDetail.getT948102(),   // customerNumber
                        issuanceHistoryDetail.getT948106(),   // demandAddress
                        issuanceHistoryDetail.getT948107(),   // demandDestinationBillApartmentName
                        issuanceHistoryDetail.getT948104(),   // demandDestinationName
                        issuanceHistoryDetail.getT948105(),   // demandLocationPlantName
                        convertToAdjustmentAmt(issuanceHistoryDetail.getT948602()),   // NUMERIC | fuelCostAdjustmentAmountTheMonth_1
                        convertToAdjustmentAmt(issuanceHistoryDetail.getT948604()),   // NUMERIC | fuelCostAdjustmentAmountTheNextMonth_1
                        issuanceHistoryDetail.getT948301(),   // maximumDemandYears_1
                        convertBigDecimalToString(issuanceHistoryDetail.getT948302()),   // NUMERIC | maximumDemandPower_1
                        convertBigDecimalToString(issuanceHistoryDetail.getT948320()),   // NUMERIC | maximumDemandPower_10
                        convertBigDecimalToString(issuanceHistoryDetail.getT948322()),   // NUMERIC | maximumDemandPower_11
                        convertBigDecimalToString(issuanceHistoryDetail.getT948324()),   // NUMERIC | maximumDemandPower_12
                        convertBigDecimalToString(issuanceHistoryDetail.getT948304()),   // NUMERIC | maximumDemandPower_2
                        convertBigDecimalToString(issuanceHistoryDetail.getT948306()),   // NUMERIC | maximumDemandPower_3
                        convertBigDecimalToString(issuanceHistoryDetail.getT948308()),   // NUMERIC | maximumDemandPower_4
                        convertBigDecimalToString(issuanceHistoryDetail.getT948310()),   // NUMERIC | maximumDemandPower_5
                        convertBigDecimalToString(issuanceHistoryDetail.getT948312()),   // NUMERIC | maximumDemandPower_6
                        convertBigDecimalToString(issuanceHistoryDetail.getT948314()),   // NUMERIC | maximumDemandPower_7
                        convertBigDecimalToString(issuanceHistoryDetail.getT948316()),   // NUMERIC | maximumDemandPower_8
                        convertBigDecimalToString(issuanceHistoryDetail.getT948318()),   // NUMERIC | maximumDemandPower_9
                        issuanceHistoryDetail.getT948319(),   // maximumDemandYears_10
                        issuanceHistoryDetail.getT948321(),   // maximumDemandYears_11
                        issuanceHistoryDetail.getT948323(),   // maximumDemandYears_12
                        issuanceHistoryDetail.getT948303(),   // maximumDemandYears_2
                        issuanceHistoryDetail.getT948305(),   // maximumDemandYears_3
                        issuanceHistoryDetail.getT948307(),   // maximumDemandYears_4
                        issuanceHistoryDetail.getT948309(),   // maximumDemandYears_5
                        issuanceHistoryDetail.getT948311(),   // maximumDemandYears_6
                        issuanceHistoryDetail.getT948313(),   // maximumDemandYears_7
                        issuanceHistoryDetail.getT948315(),   // maximumDemandYears_8
                        issuanceHistoryDetail.getT948317(),   // maximumDemandYears_9
                        convertBigDecimalToString(issuanceHistoryDetail.getT948415()),   // NUMERIC | monthEffectiveAmountOfPower
                        convertBigDecimalToString(issuanceHistoryDetail.getT948403()),   // NUMERIC | monthEffectiveInstructionNumberMainLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948404()),   // NUMERIC | monthEffectiveInstructionNumberSpareLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948401()),   // NUMERIC | monthFullTimeInstructionNumberMainLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948402()),   // NUMERIC | monthFullTimeInstructionNumberSpareLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948405()),   // NUMERIC | monthInvalidInstructionNumberMainLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948406()),   // NUMERIC | monthInvalidInstructionNumberSpareLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948205()),   // NUMERIC | monthMaximumDemandPower
                        convertBigDecimalToString(issuanceHistoryDetail.getT948407()),   // NUMERIC | monthMultiplyingFactor
                        convertBigDecimalToString(issuanceHistoryDetail.getT948416()),   // NUMERIC | monthReactiveEnergy
                        issuanceHistoryDetail.getT948202(),   // periodOfUseOptimal
                        issuanceHistoryDetail.getT948201(),   // periodOfUseSelf
                        convertBigDecimalToString(issuanceHistoryDetail.getT948204()),   // NUMERIC | powerFactor
                        convertToAdjustmentAmt(issuanceHistoryDetail.getT948601()),   // NUMERIC | previousMonthFuelCostAdjustmentAmount
                        convertToAdjustmentAmt(issuanceHistoryDetail.getT948701()),   // NUMERIC | renewableEnergyPowerGenerationPromotionLevyTheMonth
                        issuanceHistoryDetail.getT948103(),   // supplyPointSpecificNumber
                        convertBigDecimalToString(issuanceHistoryDetail.getT948902()),   // NUMERIC | taxEquivalent
                        convertBigDecimalToString(issuanceHistoryDetail.getT948408()),   // NUMERIC | thePreviousMonthFullTimeInstructionNumberMainLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948409()),   // NUMERIC | thePreviousMonthFullTimeInstructionNumberSpareLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948412()),   // NUMERIC | thePreviousMonthInvalidInstructionNumberMainLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948413()),   // NUMERIC | thePreviousMonthInvalidInstructionNumberSpareLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948414()),   // NUMERIC | thePreviousMonthMultiplyingFactor
                        convertBigDecimalToString(issuanceHistoryDetail.getT948410()),   // NUMERIC | thePreviousMonthTheEffectiveInstructionNumberMainLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948411()),   // NUMERIC | thePreviousMonthTheEffectiveInstructionNumberSpareLine
                        convertBigDecimalToString(issuanceHistoryDetail.getT948203()),   // NUMERIC | theTotalAmountOfElectricPowerUsed
                        convertBigDecimalToString(calcSum(issuanceHistoryDetail.getT948901(), issuanceHistoryDetail.getT948902())),
                        billingHistoryFeeBreakdown
                    }
                };
            } else if(serviceType.equalsIgnoreCase(LOW_PRESSURE)) {
                columnNames = new String[] {
                    "dateOfIssuance",
                    "billingDate",
                    "billableNumberOfBases",
                    "customerNumber",
                    "supplyPointSpecificNumber",
                    "demandDestinationName",
                    "demandLocationPlantName",
                    "demandAddress",
                    "demandDestinationBillApartmentName",
                    "contractType",
                    "contractPower",
                    "periodOfUseSelf",
                    "periodOfUseOptimal",
                    "theTotalAmountOfElectricPowerUsed",
                    "meterReadingDate",
                    "monthIndicationNumber",
                    "thePreviousMonthInstructionNumber",
                    "subtracted",
                    "instrumentMultiplyingFactor",
                    "instrumentNumber",
                    "theNextMeterReadingDate",
                    "fuelCostAdjustmentAmountTheMonth_1",
                    "fuelCostAdjustmentAmountTheCurrentMonth_2",
                    "fuelCostAdjustmentAmountTheNextMonth_1",
                    "fuelCostAdjustmentAmountTheNextMonth_2",
                    "renewableEnergyPowerGenerationPromotionLevyTheMonth",
                    "billingAmount",
                    "taxEquivalent",
                    "totalAmt",
                    "feeBreakdownList"
                };

                data = new Object[][] {
                    {
                        invoicingHistory.getDateOfIssuance(),
                        invoicingHistory.getBillingDate(),
                        convertBigDecimalToString(invoicingHistory.getBillableNumberOfBases()),   // NUMERIC
                        issuanceHistoryDetail.getT948102(),   // customerNumber
                        issuanceHistoryDetail.getT948103(),   // supplyPointSpecificNumber
                        issuanceHistoryDetail.getT948104(),   // demandDestinationName
                        issuanceHistoryDetail.getT948105(),   // demandLocationPlantName
                        issuanceHistoryDetail.getT948106(),   // demandAddress
                        issuanceHistoryDetail.getT948107(),   // demandDestinationBillApartmentName
                        issuanceHistoryDetail.getT948108(),   // contractType
                        convertBigDecimalToString(issuanceHistoryDetail.getT948109()),   // NUMERIC | contractPower
                        issuanceHistoryDetail.getT948201(),   // periodOfUseSelf
                        issuanceHistoryDetail.getT948202(),   // periodOfUseOptimal
                        convertBigDecimalToString(issuanceHistoryDetail.getT948203()),   // NUMERIC | theTotalAmountOfElectricPowerUsed
                        issuanceHistoryDetail.getT948206(),   // meterReadingDate
                        convertBigDecimalToString(issuanceHistoryDetail.getT948501()),   // NUMERIC | monthIndicationNumber
                        convertBigDecimalToString(issuanceHistoryDetail.getT948502()),   // NUMERIC | thePreviousMonthInstructionNumber
                        convertBigDecimalToString(issuanceHistoryDetail.getT948503()),   // NUMERIC | subtracted
                        convertBigDecimalToString(issuanceHistoryDetail.getT948504()),   // NUMERIC | instrumentMultiplyingFactor
                        issuanceHistoryDetail.getT948505(),   // instrumentNumber
                        issuanceHistoryDetail.getT948506(),   // theNextMeterReadingDate
                        convertBigDecimalToString(issuanceHistoryDetail.getT948602()),   // NUMERIC | fuelCostAdjustmentAmountTheMonth_1
                        convertBigDecimalToString(issuanceHistoryDetail.getT948603()),   // NUMERIC | fuelCostAdjustmentAmountTheCurrentMonth_2
                        convertBigDecimalToString(issuanceHistoryDetail.getT948604()),   // NUMERIC | fuelCostAdjustmentAmountTheNextMonth_1
                        convertBigDecimalToString(issuanceHistoryDetail.getT948605()),   // NUMERIC | fuelCostAdjustmentAmountTheNextMonth_2
                        convertBigDecimalToString(issuanceHistoryDetail.getT948701()),   // NUMERIC | renewableEnergyPowerGenerationPromotionLevyTheMonth
                        convertBigDecimalToString(issuanceHistoryDetail.getT948901()),   // NUMERIC | billingAmount
                        convertBigDecimalToString(issuanceHistoryDetail.getT948902()),   // NUMERIC | taxEquivalent
                        convertBigDecimalToString(calcSum(issuanceHistoryDetail.getT948901(), issuanceHistoryDetail.getT948902())),
                        billingHistoryFeeBreakdown
                    }
                };
            }
        }

        @Override
        public int getRowCount() {
        return this.data.length;
        }

        @Override
        public int getColumnCount() {
        return this.columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
        return this.columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data[rowIndex][columnIndex];
        }
    }

    private static String convertBigDecimalToString(BigDecimal dec) {
        return dec == null ? "null" : dec.toString();
    }

    private String convertToAdjustmentAmt(BigDecimal amt) {
        return (amt == null || BigDecimal.ZERO.compareTo(amt) == 0) ? "0" : ("▲" + amt.toString() + "円／kWh");
    }

    private BigDecimal calcSum(BigDecimal dec1, BigDecimal dec2) {
        if (dec1 != null && dec2 != null) {
            return dec1.add(dec2);
        }

        if (dec1 == null && dec2 == null) {
            return null;
        }

        return dec1 != null ? dec1 : dec2;
    }

    private static String convertToStringWithUnit(Object obj, String unitName) {
        if (obj instanceof String) {
            return String.format("%s %s", obj, unitName);
        } else if (obj instanceof BigDecimal) {
            return String.format("%s %s", convertBigDecimalToString((BigDecimal) obj), unitName);
        }
        return String.format("%s %s", obj == null ? "null" : obj.toString(), unitName);
    }

    private static interface UnitName {
        final static String KILOWATT = "kW";
        final static String KILOWATT_HOUR = "kWh";
        final static String YEN_KILOWATT_HOUR = "円/kWh";
        final static String YEN_KILOWATT = "円/kW";
        final static String JP_YEN = "円";
    }

}
