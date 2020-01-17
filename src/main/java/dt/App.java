package dt;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------");

        // System.out.println(jrxml.exists() ? jrxml.getAbsolutePath() : "DUYTRUNG");
        generateReport("nana", new JRBeanArrayDataSource(TunyFactory.getTunyList()));

        System.out.println("----------------------------------------------------");
    }

    public static void generateReport(String fileName, JRBeanArrayDataSource beanDataSource) {
        File jrxml = new File(fileName + ".jrxml");

        System.out.println(jrxml.exists() ? jrxml.getAbsolutePath() : "DUYTRUNG");

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxml.getAbsolutePath());

            // JasperPrint print = JasperFillManager.fillReport(jasperReport, null, beanDataSource);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, null, new JRTableModelDataSource(new TunyTableModel()));

            File pdf = new File(fileName + ".pdf");

            JasperExportManager.exportReportToPdfFile(print, pdf.getAbsolutePath());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class TunyTableModel extends AbstractTableModel {

        private String[] columnNames = {"id", "name", "bio"};

        private Object[][] data = {

        };

        public TunyTableModel() {
            data = new Object[][] {
                {"田中-結菜", "Product Communications Liaison", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_8 rv:5.0; SL) AppleWebKit/533.2.1 (KHTML, like Gecko) Version/7.0.1 Safari/533.2.1"},
                {"高橋-陽菜", "Chief Web Designer", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.0; Trident/3.0; .NET CLR 3.0.59231.0)"},
                {"斎藤-心愛", "Human Optimization Supervisor", "Mozilla/5.0 (Windows; U; Windows NT 6.1) AppleWebKit/532.0.1 (KHTML, like Gecko) Chrome/32.0.806.0 Safari/532.0.1"},
                {"鈴木-陸斗", "National Factors Associate", "Mozilla/5.0 (Windows; U; Windows NT 6.3) AppleWebKit/537.2.1 (KHTML, like Gecko) Chrome/27.0.819.0 Safari/537.2.1"},
                {"斎藤-蓮", "Product Solutions Strategist", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_0)  AppleWebKit/538.1.1 (KHTML, like Gecko) Chrome/32.0.854.0 Safari/538.1.1"},
                {"山田-陸斗", "International Identity Coordinator", "Mozilla/5.0 (Windows; U; Windows NT 6.1) AppleWebKit/532.2.2 (KHTML, like Gecko) Chrome/23.0.837.0 Safari/532.2.2"},
                {"山本-結愛", "Customer Accounts Planner", "Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 6.3; Trident/3.0; .NET CLR 3.5.47347.9)"},
                {"山口-結菜", "Dynamic Response Engineer", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_9_7 rv:3.0; PT) AppleWebKit/534.2.2 (KHTML, like Gecko) Version/6.1.9 Safari/534.2.2"},
                {"林-颯太", "National Identity Engineer", "Mozilla/5.0 (Windows NT 6.0; rv:9.4) Gecko/20100101 Firefox/9.4.9"},
                {"松本-颯太", "Dynamic Accountability Orchestrator", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_3 rv:6.0; BR) AppleWebKit/538.2.0 (KHTML, like Gecko) Version/5.0.10 Safari/538.2.0"},
                {"伊藤-莉子", "Central Applications Supervisor", "Mozilla/5.0 (Windows NT 5.2; Win64; x64; rv:9.0) Gecko/20100101 Firefox/9.0.0"},
                {"小林-大翔", "Lead Intranet Orchestrator", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/6.0)"},
            };
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
        public String getColumnName(int columnIndex)
        {
            return this.columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return this.data[rowIndex][columnIndex];
        }

    }
}
