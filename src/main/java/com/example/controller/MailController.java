package com.example.controller;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MailController {
	
	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public void sendMailTest() throws Exception {

		String subject = "test ����";
		String content = "���� �׽�Ʈ ����";
		String from = "�������� ���̵�@�������ּ�";
		String to = "�޴��� ���̵�@�������ּ�";

		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			// true�� ��Ƽ��Ʈ �޼����� ����ϰڴٴ� �ǹ�

			/*
			 * �ܼ��� �ؽ�Ʈ �޼����� ���ÿ� �Ʒ��� �ڵ嵵 ��� ���� MimeMessageHelper mailHelper = new
			 * MimeMessageHelper(mail,"UTF-8");
			 */

			mailHelper.setFrom(from);
			// �� ���̵� ������ ���� �ܼ��� smtp ������ �ޱ� ���� ��� ���� ��������(setFrom())�ݵ�� �ʿ�
			// �������̿� �����ּҸ� �����ϴ��̰� ���� ��� ǥ�� �ǰ� ���ϽŴٸ� �Ʒ��� �ڵ带 ����Ͻø� �˴ϴ�.
			// mailHelper.setFrom("�������� �̸� <�������� ���̵�@�������ּ�>");
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content, true);
			// true�� html�� ����ϰڴٴ� �ǹ��Դϴ�.

			/*
			 * �ܼ��� �ؽ�Ʈ�� ����ϽŴٸ� ������ �ڵ带 ����ϼŵ� �˴ϴ�. mailHelper.setText(content);
			 */

			mailSender.send(mail);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}