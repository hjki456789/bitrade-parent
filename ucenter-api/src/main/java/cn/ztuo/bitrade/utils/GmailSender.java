package cn.ztuo.bitrade.utils;

import java.security.*;

import com.sun.net.ssl.internal.ssl.Provider;
import org.springframework.mail.javamail.*;

import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;

public class GmailSender
{
    public static void main(final String[] args) throws AddressException, MessagingException {
        final String html = "<html>\n<head>\n    <meta charset=\"utf-8\">\n</head>\n<body>\n<div>\n    <h3>\u6b22\u8fce\u60a8\u52a0\u5165amex\uff01</h3><br/>\n    <br/>\u60a8\u7684\u8d26\u53f7\u5df2\u7ecf\u6210\u529f\u521b\u5efa\uff0c\u8bf7\u70b9\u51fb\u6b64\u6fc0\u6d3b\u8d26\u53f7\uff1a<br/>\n    <br/><a href=\"${host}/uc/register/active?key=133d32d3\">\u70b9\u51fb\u6fc0\u6d3b</a><br/>\n    <br/>\u5982\u679c\u4e0d\u80fd\u70b9\u51fb\uff0c\u6216\u8005\u7b49\u5f85\u65f6\u95f4\u8fc7\u957f\u8bf7\u590d\u5236\u5982\u4e0b\u5730\u5740\u5230\u6d4f\u89c8\u5668\u8bbf\u95ee<br/>\n$www.aemex.io/uc/register/active?key=133d32d3\n    <br/>\u8bf7\u59a5\u5584\u4fdd\u7559\u8fd9\u5c01\u7535\u5b50\u90ae&#x4EF6;.\n    \u60a8\u7684\u5e10\u53f7\u8d44\u6599\u5982\u4e0b:<br/><br/>----------------------------<br/>\u5e10\u53f7: hdecer<br/>----------------------------<br/><br/>&nbsp;\u5982\u679c\u60a8\u5fd8\u8bb0\u4e86\u5bc6\u7801\uff0c\u53ef\u4ee5\u5728\u7528\u6237\u767b\u5f55\u754c\u9762\u901a\u8fc7\u201c\u627e\u56de\u5bc6\u7801\u201d\u7684\u94fe\u63a5\uff0c\u91cd\u7f6e\u4f60\u7684\u5bc6\u7801\u3002<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n    <br/>\n    <br/>${name}\u548c\u60a8\u4e00\u540c\u6210\u957f\uff0c\u611f\u8c22\u60a8\u7684\u6ce8\u518c\uff01<br/>\n    <br/>\n</div>\n</body>\n</html>";
        Security.addProvider(new Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        final Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        final String username = "ctce2020727@163.com";
        final String password = "MSJHKKZZOYNLQRWN";
        final Session session = Session.getDefaultInstance(props, null);
        final MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("ctce2020727@163.com"));
        msg.setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse("1491270278@qq.com", false));
        msg.setSubject("Hello");
        final MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
        helper.setText(html, true);
        msg.setSentDate(new Date());
        Transport.send((Message)msg);
        System.out.println("Message sent.");
    }
}
