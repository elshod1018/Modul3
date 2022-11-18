package com.company.client;

import com.company.client.util.ScannerUtil;
import com.company.server.entity.Contact;
import com.company.server.payload.ContactDTO;
import com.company.server.payload.Result;
import com.company.server.service.ContactService;
import com.company.server.service.ContactServiceImpl;

import java.util.ArrayList;
import java.util.UUID;

public class Application {
    static ContactService contactService=new ContactServiceImpl();
    public static void main(String[] args) {
        while (true){
            System.out.print("""
                    0 => Exit
                    1 => Add contact
                    2 => Get contact
                    3 => Search contact
                    4 => All contact
                    5 => Delete contact\s""");
            switch (new ScannerUtil().scannerNumber.nextInt()) {
                case 0:
                    return;
                case 1:
                    Result result=createContact();
                    System.out.println(result);
                    break;
                case 2:
                    boolean t=false;
                    System.out.print("Enter contact id: ");
                    UUID uuid= UUID.fromString(new ScannerUtil().scannerText.nextLine());
                    ArrayList<Contact>contacts=contactService.ALL_CONTACTS();
                    for (Contact contact : contacts) {
                        if (contact.getId().equals(uuid)){
                           t=true;
                            System.out.println(contact);
                            break;
                        }
                    }
                    if (!t){
                        System.out.println("Can't find contact with this id");
                    }
                    break;
                case 3:
                    System.out.print("Enter text: ");
                    String text=new ScannerUtil().scannerText.nextLine();
                    ArrayList<Contact>searchContact=contactService.SEARCH(text);
                    if (searchContact==null){
                        System.out.println("Not contacts here");
                    } else if (searchContact.size()==0) {
                        System.out.println("Can't find anything with text");
                    }
                    else{
                        for (Contact contact : searchContact) {
                            System.out.println(contact);
                        }
                    }
                    break;
                case 4:
                    for (Contact contact : contactService.ALL_CONTACTS()) {
                        System.out.println(contact);
                    }
                    break;
                case 5:
                    System.out.print("Enter contact id for delete: ");
                    uuid= UUID.fromString(new ScannerUtil().scannerText.nextLine());
                    System.out.println(contactService.DELETE(uuid));
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }
    }
    public static Result createContact(){
        ContactDTO contactDTO=new ContactDTO();
        System.out.print("Enter phone number: ");
        contactDTO.setPhoneNumber(new ScannerUtil().scannerText.nextLine());
        System.out.print("Enter full name: ");
        contactDTO.setFullName(new ScannerUtil().scannerText.nextLine());
        return contactService.CREATE(contactDTO);
    }
}
