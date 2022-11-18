package com.company.server.service;

import com.company.server.entity.Contact;
import com.company.server.payload.ContactDTO;
import com.company.server.payload.EditContactDTO;
import com.company.server.payload.Result;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public interface ContactService {
    File CONTACTS_FILE=new File("src/main/resources/contacts.json");
    Result CREATE(ContactDTO contactDTO);
    Result DELETE(UUID uuid);
    ArrayList<Contact>ALL_CONTACTS();
    Result EDIT(UUID uuid, EditContactDTO editContactDTO);
    ArrayList<Contact>SEARCH(String text);


}
