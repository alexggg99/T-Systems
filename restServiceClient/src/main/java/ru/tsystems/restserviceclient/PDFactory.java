/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tsystems.restserviceclient;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@ManagedBean(name = "pdfBean")
@SessionScoped
public class PDFactory {

    public void createDocument() {
        Document document = new Document();
        try {
            File file = new File("/tmp/tickets.pdf");
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            document.add(new Paragraph("A Hello World PDF document."));
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public void downloadFile() {
//
//        File file = new File("/tmp/tickets.pdf");
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//
//        response.setHeader("Content-Disposition", "attachment;filename=tickets.pdf");
//        response.setContentLength((int) file.length());
//        ServletOutputStream out = null;
//        try {
//            FileInputStream input = new FileInputStream(file);
//            byte[] buffer = new byte[1024];
//            out = response.getOutputStream();
//            int i = 0;
//            while ((i = input.read(buffer)) != -1) {
//                out.write(buffer);
//                out.flush();
//            }
//            FacesContext.getCurrentInstance().getResponseComplete();
//        } catch (IOException err) {
//            err.printStackTrace();
//        } finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//            } catch (IOException err) {
//                err.printStackTrace();
//            }
//        }
//    }
    
    public void downloadFile() throws IOException{
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            // Get the text that will be added to the PDF
            String text = "You didn't enter any text.";
            if (text == null || text.trim().length() == 0) {
                 text = "You didn't enter any text.";
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
                "You have submitted the following text using the %s method:"));
            document.add(new Paragraph(text));
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
        }
        catch(DocumentException e) {
            throw new IOException(e.getMessage());
        }
    
    }
    
}
