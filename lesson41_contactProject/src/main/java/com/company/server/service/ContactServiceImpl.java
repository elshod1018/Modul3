package com.company.server.service;

import com.company.server.entity.Contact;
import com.company.server.payload.ContactDTO;
import com.company.server.payload.EditContactDTO;
import com.company.server.payload.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactServiceImpl implements ContactService {
    @Override
    public Result CREATE(ContactDTO contactDTO) {
        Result result = new Result();
        List<Contact> contacts;
        String check = checkParams(contactDTO);
        if (check.equals("ok")) {
            if (!isHere(contactDTO)) {
                Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
                contacts = ALL_CONTACTS();
                if (contacts == null) {
                    contacts = new ArrayList<>();
                }
                Contact contact = new Contact();
                contact.setId(UUID.randomUUID());
                contact.setFullName(contactDTO.getFullName());
                contact.setPhoneNumber(contactDTO.getPhoneNumber());
                CONTACTS_FILE.delete();
                contacts.add(contact);
                try (PrintWriter writer = new PrintWriter(CONTACTS_FILE)) {
                    writer.write(gson.toJson(contacts));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                result.setMessage("Created successfully");
                result.setSuccess(true);
            } else {
                result.setSuccess(false);
                result.setMessage("This contact exist already");
            }
        } else {
            result.setMessage(check);
            result.setSuccess(false);
        }
        return result;
    }

    @Override
    public Result DELETE(UUID uuid) {
        ArrayList<Contact>contacts=ALL_CONTACTS();
        Result result=new Result();
        if (contacts!=null){
            Gson gson=new GsonBuilder().serializeNulls().setPrettyPrinting().create();
            Contact contact=contacts.stream().filter(contact1 -> contact1.getId().equals(uuid))
                    .findFirst().orElse(null);
            if (contact==null){
                result.setMessage("Can't find this contact");
                result.setSuccess(false);
            }else{
                contacts.remove(contact);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTACTS_FILE))) {
                    writer.write(gson.toJson(contacts));
                    result.setSuccess(true);
                    result.setMessage("Deleted successfully");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    @Override
    public ArrayList<Contact> ALL_CONTACTS() {
        ArrayList<Contact> contacts;
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting().create();
        try (BufferedReader reader = new BufferedReader(new FileReader(CONTACTS_FILE))) {
            Type type = new TypeToken<List<Contact>>() {
            }.getType();
            contacts = gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    @Override
    public Result EDIT(UUID uuid, EditContactDTO editContactDTO) {
        return null;
    }

    @Override
    public ArrayList<Contact> SEARCH(String text) {
        ArrayList<Contact> contacts = ALL_CONTACTS();
        ArrayList<Contact>returnContacts=new ArrayList<>();
        if (contacts != null) {
            for (Contact contact : contacts) {
                if (contact.getFullName().contains(text)
                        || contact.getPhoneNumber().contains(text)){
                    returnContacts.add(contact);
                }
            }
        }
        return returnContacts;
    }

    private String checkParams(ContactDTO contactDTO) {
        if (contactDTO == null) {
            return "Dates not found";
        }
        if (contactDTO.getPhoneNumber() == null || contactDTO.getPhoneNumber().isBlank()) {
            return "Phone number is required";
        }

        if (!contactDTO.getPhoneNumber().matches("\\+\\d{12}")) {
            return "Wrong phone number format";
        }
        if (contactDTO.getFullName() == null) {
            return "FullName data not found";
        }
        if (contactDTO.getFullName().isBlank()) {
            contactDTO.setFullName(contactDTO.getPhoneNumber());
        }
        return "ok";
    }

    private boolean isHere(ContactDTO contactDTO) {
        ArrayList<Contact> contacts = ALL_CONTACTS();
        if (contacts != null) {
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getPhoneNumber().equals(contactDTO.getPhoneNumber())) {
                    return true;
                }
            }
        }
        return false;
    }
}
