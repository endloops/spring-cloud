package sxs.xas.bqq.hqz.yjgc.myw;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.hadoop.mapred.machines_jsp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WangSecondApiGatewayApplicationTests {

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private TemplateEngine template;
	
	/**
	 * 邮件服务测试
	 */
	@Test
	public void contextLoads() {
		System.out.println("aaaaaaaaaaa");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("13649283825@163.com");
		mailMessage.setTo("wangzhezhao@chinasofti.com");
		mailMessage.setSubject("第一个");
		mailMessage.setText("啊啊啊啊啊啊啊啊啊啊啊啊啊");
		
		sender.send(mailMessage);
	}
	@Test
	public void testHtmlMailcontextLoads() throws MessagingException {
		System.out.println("aaaaaaaaaaa");
		MimeMessage mailMessage = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
		helper.setTo("wangzhezhao@chinasofti.com");
		helper.setFrom("13649283825@163.com");
		helper.setSubject("这是一个HTML 邮件");
		helper.setText("<html>"
				+ "<h1>111111<h1>"
				+ "<h2>222222222<h2>"
				+ "</html>", true);
		
		sender.send(mailMessage);
	}
	@Test
	public void testMobanMailcontextLoads() throws MessagingException {
		System.out.println("aaaaaaaaaaa");
		MimeMessage mailMessage = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
		helper.setTo("wangzhezhao@chinasofti.com");
		helper.setFrom("13649283825@163.com");
		helper.setSubject("这是一个模板 邮件");
		Context context = new Context();
		context.setVariable("id", "006");
		String maString = template.process("template",context );
		helper.setText(maString,true);
		sender.send(mailMessage);
	}

}
