package com.example.nhom2_case.security.service.impl;


import com.example.nhom2_case.security.DTO.DataMailDTO;
import com.example.nhom2_case.security.DTO.sdi.ClientSdi;
import com.example.nhom2_case.security.service.ClientService;
import com.example.nhom2_case.security.service.MailService;
import com.example.nhom2_case.security.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private MailService mailService;

    @Override
    public Boolean create(ClientSdi sdi) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(sdi.getEmail());
                dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("name", sdi.getName());
            props.put("username", sdi.getUsername());
            props.put("password",sdi.getName());
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return true;
        } catch (MessagingException exp){
            exp.printStackTrace();
        }
        return false;
    }
}