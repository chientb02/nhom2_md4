package com.example.nhom2_case.security.service;




import com.example.nhom2_case.security.DTO.DataMailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}