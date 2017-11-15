package com.yadu.himalayamtnew.xmlHandler;

import com.yadu.himalayamtnew.xmlGetterSetter.FailureGetterSetter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by yadavendras on 14-11-2017.
 */

public class XMLHandlers {


    // FAILURE XML HANDLER
    public static FailureGetterSetter failureXMLHandler(XmlPullParser xpp,
                                                        int eventType) {
        FailureGetterSetter failureGetterSetter = new FailureGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("STATUS")) {
                        failureGetterSetter.setStatus(xpp.nextText());
                    }
                    if (xpp.getName().equals("ERRORMSG")) {
                        failureGetterSetter.setErrorMsg(xpp.nextText());
                    }
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return failureGetterSetter;
    }


    // LOGIN XML HANDLER
    public static LoginGetterSetter loginXMLHandler(XmlPullParser xpp,
                                                    int eventType) {
        LoginGetterSetter lgs = new LoginGetterSetter();

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("RESULT")) {
                        lgs.setResult(xpp.nextText());
                    }
                    if (xpp.getName().equals("APP_VERSION")) {
                        lgs.setVERSION(xpp.nextText());
                    }
                    if (xpp.getName().equals("APP_PATH")) {
                        lgs.setPATH(xpp.nextText());
                    }
                    if (xpp.getName().equals("CURRENTDATE")) {
                        lgs.setDATE(xpp.nextText());
                    }

                    if (xpp.getName().equals("RIGHTNAME")) {
                        lgs.setRIGHTNAME(xpp.nextText());
                    }

                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lgs;
    }

}
