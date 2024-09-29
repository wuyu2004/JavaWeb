package top.soft.class03response.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 11448
 * @description: TODO
 * @date 2024/9/28 14:56
 */

@WebServlet("/res")
public class ResponseTypeDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        switch (type){
            case "img":
                getImage(req,resp);
                break;
            case "pdf":
                getPDF(req,resp);
                break;
            case "text":
                getText(req,resp);
                break;
            case "web":
                getWebPage(req,resp);
                break;
            default:
                break;
        }
    }

    private void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        String realPath = request.getServletContext().getRealPath("/images/image.png");
        File file = new File(realPath);
        InputStream in = new FileInputStream(file);
        int read = 0;
        ServletOutputStream out = response.getOutputStream();
        while ((read = in.read()) != -1){
            out.write(read);
        }
        out.flush();
        out.close();
    }
    private void getPDF(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String realPath = request.getServletContext().getRealPath("/pdf/pdf.pdf");
        File file = new File(realPath);
        InputStream in = new FileInputStream(file);
        int read = 0;
        OutputStream out = response.getOutputStream();
        while ((read = in.read())!= -1) {
            out.write(read);
        }
        out.flush();
        out.close();
    }
    private void getText(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        String realPath = request.getServletContext().getRealPath("/web/txt.txt");
        File file = new File(realPath);
        InputStream in = new FileInputStream(file);
        int read = 0;
        OutputStream out = response.getOutputStream();
        while ((read = in.read())!= -1) {
            out.write(read);
        }
        out.flush();
        out.close();
    }
    private void getWebPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try {
            URL url = new URL("https://www.baidu.com");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            OutputStream out = response.getOutputStream();
            while ((inputLine = in.readLine())!= null) {
                out.write(inputLine.getBytes());
            }
            in.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching web page.");
        }
    }
}
