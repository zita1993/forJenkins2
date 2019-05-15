package com.czx.mvnbook.account.email;
import static org.junit.Assert.assertEquals;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Message;

public class AccountEmailServiceTest {
    private GreenMail greenMail;
    @Before
    public void startMailServer() throws Exception{
        greenMail=new GreenMail(ServerSetup.SMTP);
        greenMail.setUser("c_zhuoxuan@163.com","1013060560czx");
        greenMail.start();
    }
    @After
    public void stopMailServer() throws Exception{
        greenMail.stop();
    }
    @Test
    public void testSendEmail() throws Exception{
        ApplicationContext ctx=new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService=(AccountEmailService) ctx.getBean("accountEmailService");
        String subject="Test Subject";
        String htmlText="<h3>Test</h3>";
        accountEmailService.sendEmail("c_zhuoxuan@163.com","subject","htmlText");
        greenMail.waitForIncomingEmail(2000,1);
        Message[] msgs=greenMail.getReceivedMessages();
        assertEquals(1,msgs.length);
        assertEquals(subject,msgs[0].getSubject());
        assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
    }
}
