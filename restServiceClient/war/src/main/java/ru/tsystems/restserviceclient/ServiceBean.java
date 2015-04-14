/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.ru.tsystems.restserviceclient;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@ManagedBean(name = "serviceBean")
@SessionScoped
public class ServiceBean {

    @EJB (beanName = RestClientImpl.JNDI_NAME)
    RestClient restClient;
    
    private static final Logger logger = Logger.getLogger(ServiceBean.class);
    
    public Date dateFrom;

    public Date dateTo;

    public String request;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String askService() {
        String string = restClient.askService(dateToString(dateFrom), dateToString(dateTo));
        request = string;
        return "welcome";
    }

    private String dateToString(Date date) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        return s;
    }
    
     public void downloadFile() throws IOException, org.json.simple.parser.ParseException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            // Get the text that will be added to the PDF
            String text = request;
            if (text == null || text.trim().length() == 0) {
                text = "No data.";
            }
            // step 1
            Document document = new Document();
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            // step 3
            document.open();
            // step 4
            document.add(new Paragraph(
                    "Tickets from " + dateToString(dateFrom) + " to " + dateToString(dateTo)));

            PdfPTable table = new PdfPTable(7); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Id"));;
            PdfPCell cell2 = new PdfPCell(new Paragraph("Departure"));;
            PdfPCell cell3 = new PdfPCell(new Paragraph("Passenger"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Route"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("From"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("To"));
            PdfPCell cell7 = new PdfPCell(new Paragraph("Train"));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);

            //parse data  
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(text);
            for (Object obj : array) {
                JSONObject objTemp = (JSONObject) obj;
                long ticketId = (long) objTemp.get("ticketId");
                String t = Long.toString(ticketId);
                table.addCell(t);
                table.addCell((String)objTemp.get("depatureTime"));
                table.addCell((String)objTemp.get("passenger"));
                table.addCell((String)objTemp.get("route"));
                table.addCell((String)objTemp.get("stationFrom"));
                table.addCell((String)objTemp.get("stationTo"));
                table.addCell((String)objTemp.get("train"));
            }

            document.add(table);

            // step 5
            document.close();

            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
            FacesContext.getCurrentInstance().getResponseComplete();
        } catch (DocumentException e) {
            logger.error("Error!", e);
        }

    }
}
