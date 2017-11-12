package com.bodhi.llc.contactcont;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 456);
            }
        } else {
            doSomeWorkHere();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 456) {
            if (permissions != null && permissions.length > 0) {
                if (permissions[0].equals(PackageManager.PERMISSION_GRANTED) && permissions.equals(PackageManager.PERMISSION_GRANTED)) {
                    doSomeWorkHere();
                }
            }
        }
    }

    void doSomeWorkHere() {
        // Gets values from the UI
        String name ="demo";
        String phone ="+91 9196805954";
        String email = "uhgikhilh@jk.mok";

        String company = "jhkljh";
        String jobtitle = "jhkhhhk";

// Creates a new intent for sending to the device's contacts application
        Intent insertIntent = new Intent(ContactsContract.Intents.Insert.ACTION);

// Sets the MIME type to the one expected by the insertion activity
        insertIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

// Sets the new contact name
        insertIntent.putExtra(ContactsContract.Intents.Insert.NAME, "Mr. Hello World Middle, End");

// Sets the new company and job title
        insertIntent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
        insertIntent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobtitle);

/*
 * Demonstrates adding data rows as an array list associated with the DATA key
 */

// Defines an array list to contain the ContentValues objects for each row
        ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();


/*
 * Defines the raw contact row
 */

// Sets up the row as a ContentValues object
//        ContentValues rawContactRow = new ContentValues();
//
//// Adds the account type and name to the row
////        rawContactRow.put(ContactsContract.RawContacts.ACCOUNT_TYPE, mSelectedAccount.getType());
////        rawContactRow.put(ContactsContract.RawContacts.ACCOUNT_NAME, mSelectedAccount.getName());
//
//// Adds the row to the array
//        contactData.add(rawContactRow);

/*
 * Sets up the phone number data row
 */

// Sets up the row as a ContentValues object
        ContentValues phoneRow = new ContentValues();

// Specifies the MIME type for this data row (all data rows must be marked by their type)
        phoneRow.put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
        );

// Adds the phone number and its type to the row
        phoneRow.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
        phoneRow.put(ContactsContract.CommonDataKinds.Phone.TYPE, 1);

// Adds the row to the array
        contactData.add(phoneRow);

        ContentValues phoneRow2 = new ContentValues();

// Specifies the MIME type for this data row (all data rows must be marked by their type)
        phoneRow2.put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
        );

// Adds the phone number and its type to the row
        phoneRow2.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "+91 03561 221227");
        phoneRow2.put(ContactsContract.CommonDataKinds.Phone.TYPE,2);

// Adds the row to the array
        contactData.add(phoneRow2);

 ContentValues address1 = new ContentValues();

// Specifies the MIME type for this data row (all data rows must be marked by their type)
        address1.put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE
        );

// Adds the phone number and its type to the row
        address1.put(ContactsContract.CommonDataKinds.StructuredPostal.STREET, "Adarpara");
        address1.put(ContactsContract.CommonDataKinds.StructuredPostal.CITY,"Jalpaiguri");
        address1.put(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY,"India");
        address1.put(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE,"735101");
        address1.put(ContactsContract.CommonDataKinds.StructuredPostal.REGION,"WestBeng");
        address1.put(ContactsContract.CommonDataKinds.StructuredPostal.TYPE,1);

// Adds the row to the array
        contactData.add(address1);

        ContentValues org = new ContentValues();

// Specifies the MIME type for this data row (all data rows must be marked by their type)
        org.put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE
        );

// Adds the phone number and its type to the row
        org.put("Chapter Name", "YPO");


// Adds the row to the array
        contactData.add(org);
        ContentValues web = new ContentValues();

// Specifies the MIME type for this data row (all data rows must be marked by their type)
        web.put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE
        );

// Adds the phone number and its type to the row
        web.put(ContactsContract.CommonDataKinds.Website.DATA, "www.google.com/bodhidipta");


// Adds the row to the array
        contactData.add(web);

/*
 * Sets up the email data row
 */

// Sets up the row as a ContentValues object
        ContentValues emailRow = new ContentValues();

// Specifies the MIME type for this data row (all data rows must be marked by their type)
        emailRow.put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
        );

// Adds the email address and its type to the row
        emailRow.put(ContactsContract.CommonDataKinds.Email.ADDRESS, email);

// Adds the row to the array
        contactData.add(emailRow);

/*
 * Adds the array to the intent's extras. It must be a parcelable object in order to
 * travel between processes. The device's contacts app expects its key to be
 * Intents.Insert.DATA
 */
        insertIntent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);

// Send out the intent to start the device's contacts app in its add contact activity.
        startActivity(insertIntent);
    }

}
