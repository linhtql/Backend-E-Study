package com.estudy.service;

import javax.mail.MessagingException;

import com.estudy.model.DataMail;

public interface IMailService {
	void sendHtmlMail(DataMail dataMail, String templateName) throws MessagingException;
}
