package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Mihaela.Stoian on 7/19/2017.
 */
public class HeadersLogFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        LogFileWriter logFileWriter = new LogFileWriter();
        logFileWriter.logHeader("ceva", "altceva");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
