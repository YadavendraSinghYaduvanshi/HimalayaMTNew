package com.yadu.himalayamtnew.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yadu.himalayamtnew.xmlGetterSetter.AssetChecklistGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.AssetMasterGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.Audit_QuestionGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.BrandGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.CategoryMasterGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.CompanyGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.JourneyPlanGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.MappingAssetChecklistGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.MappingAssetChecklistreasonGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.MappingAssetGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.MappingAvailabilityGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.MappingPromotionGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.NonComplianceChecklistGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.NonWorkingReasonGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.PayslipGetterSetter;
import com.yadu.himalayamtnew.xmlGetterSetter.SkuMasterGetterSetter;

/**
 * Created by yadavendras on 14-11-2017.
 */

public class HimalayaDb extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HIMALAYA_MT_DATABASE1";
    public static final int DATABASE_VERSION = 2;
    private SQLiteDatabase db;

    public HimalayaDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        try {
            db = this.getWritableDatabase();
        } catch (Exception e) {
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //JCP data
    @SuppressLint("LongLogTag")
    public void insertJCPData(JourneyPlanGetterSetter data) {

        db.delete("JOURNEY_PLAN", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getStore_cd().size(); i++) {

                values.put("STORE_CD", Integer.parseInt(data.getStore_cd().get(i)));
                values.put("EMP_CD", Integer.parseInt(data.getEmp_cd().get(i)));

                values.put("VISIT_DATE", data.getVISIT_DATE().get(i));
                //values.put("VISIT_DATE", "04/05/2017");
                values.put("KEYACCOUNT", data.getKey_account().get(i));

                values.put("STORENAME", data.getStore_name().get(i));
                values.put("CITY", data.getCity().get(i));
                values.put("STORETYPE", data.getStore_type().get(i));
                //values.put("CATEGORY_TYPE", data.getCategory_type().get(i));

                values.put("UPLOAD_STATUS", data.getUploadStatus().get(i));
                values.put("CHECKOUT_STATUS", data.getCheckOutStatus().get(i));

						/*values.put("UPLOAD_STATUS", "N");
                        values.put("CHECKOUT_STATUS","N");
*/
                db.insert("JOURNEY_PLAN", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert JCP Data ",
                    ex.toString());
        }

    }

    //Store data
    public void insertSkuMasterData(SkuMasterGetterSetter data) {

        db.delete("SKU_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {


            for (int i = 0; i < data.getSku_cd().size(); i++) {


                values.put("SKU_CD", Integer.parseInt(data.getSku_cd().get(i)));
                values.put("SKU", data.getSku().get(i));
                values.put("BRAND_CD", Integer.parseInt(data.getBrand_cd().get(i)));
                values.put("BRAND", data.getBrand().get(i));

                values.put("CATEGORY_CD", Integer.parseInt(data.getCategory_cd().get(i)));
                values.put("CATEGORY", data.getCategory().get(i));
                //values.put("MRP", data.getMrp().get(i));
                values.put("SKU_SEQUENCE", data.getSku_sequence().get(i));
                values.put("BRAND_SEQUENCE", data.getBrand_sequence().get(i));
                values.put("CATEGORY_SEQUENCE", data.getCategory_sequence().get(i));
                values.put("HIMALAYA_PHOTO", data.getHIMALAYA_PHOTO().get(i));
                values.put("CATEGORY_PHOTO", data.getCATEGORY_PHOTO().get(i));

                //values.put("CATEGORY_TYPE", data.getCategory_type().get(i));

                //values.put("PACKING", data.getPacking_size().get(i));

                db.insert("SKU_MASTER", null, values);


            }

        } catch (Exception ex) {
            Log.d("Exception ", ex.toString());
        }

    }

    //mapping available data
    public void insertMappingAvailData(MappingAvailabilityGetterSetter data) {

        db.delete("MAPPING_AVAILABILITY", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getStore_cd().size(); i++) {

                values.put("STORE_CD", Integer.parseInt(data.getStore_cd().get(i)));
                values.put("SKU_CD", Integer.parseInt(data.getSku_cd().get(i)));
                        /*values.put("CATEGORY_SEQUENCE", Integer.parseInt(data.getCategory_sequence().get(i)));
                        values.put("BRAND_SEQUENCE", Integer.parseInt(data.getBrand_sequence().get(i)));
						values.put("SKU_SEQUENCE", Integer.parseInt(data.getSku_sequence().get(i)));*/


                db.insert("MAPPING_AVAILABILITY", null, values);

            }

        } catch (Exception ex) {
            Log.d("Exception ",
                    ex.toString());
        }

    }

    //mapping promotion data
    public void insertMappingPromotionData(MappingPromotionGetterSetter data) {

        db.delete("MAPPING_PROMOTION", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getStore_cd().size(); i++) {

                values.put("STORE_CD", Integer.parseInt(data.getStore_cd().get(i)));
                values.put("PROMOTION", data.getPromotion().get(i));
                values.put("PID", data.getPid().get(i));
                values.put("BRAND_CD", data.getBrand_cd().get(i));
                db.insert("MAPPING_PROMOTION", null, values);

            }

        } catch (Exception ex) {
            Log.d("Exception",
                    ex.toString());
        }

    }

    //mapping asset data
    public void insertMappingAssetData(MappingAssetGetterSetter data) {

        db.delete("MAPPING_ASSET", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getStore_cd().size(); i++) {

                values.put("STORE_CD", Integer.parseInt(data.getStore_cd().get(i)));
                values.put("CATEGORY_CD", Integer.parseInt(data.getCategory_cd().get(i)));
                values.put("ASSET_CD", data.getAsset_cd().get(i));
                values.put("IMAGE_URL ", data.getIMAGE_URL().get(i));

                db.insert("MAPPING_ASSET", null, values);

            }

        } catch (Exception ex) {
            Log.d("Exception ",
                    ex.toString());
        }

    }

    //Asset master data
    public void insertAssetMasterData(AssetMasterGetterSetter data) {

        db.delete("ASSET_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getAsset_cd().size(); i++) {

                values.put("ASSET_CD", Integer.parseInt(data.getAsset_cd().get(i)));
                values.put("ASSET", data.getAsset().get(i));

                db.insert("ASSET_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Exception",
                    ex.toString());
        }

    }

    //Company master data
    public void insertCompanyMasterData(CompanyGetterSetter data) {

        db.delete("COMPANY_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getCompany_cd().size(); i++) {

                values.put("COMPANY_CD", Integer.parseInt(data.getCompany_cd().get(i)));
                values.put("COMPANY", data.getCompany().get(i));

                db.insert("COMPANY_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Exception  ",
                    ex.toString());
        }

    }

    //Brand master data
    public void insertBrandMasterData(BrandGetterSetter data) {

        db.delete("BRAND_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getBrand_cd().size(); i++) {

                values.put("BRAND_CD", Integer.parseInt(data.getBrand_cd().get(i)));
                values.put("BRAND", data.getBrand().get(i));
                values.put("BRAND_SEQUENCE", data.getBrand_sequence().get(i));

                values.put("COMPANY_CD", Integer.parseInt(data.getCompany_cd().get(i)));
                values.put("CATEGORY_CD", Integer.parseInt(data.getCategory_cd().get(i)));

                db.insert("BRAND_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Exception Data ",
                    ex.toString());
        }

    }

    //Insert Asset Checklist data
    public void insertAssetChecklistData(AssetChecklistGetterSetter data) {

        db.delete("ASSET_CHECKLIST", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getCHECKLIST_ID().size(); i++) {

                values.put("CHECKLIST_ID", Integer.parseInt(data.getCHECKLIST_ID().get(i)));
                values.put("CHECKLIST", data.getCHECKLIST().get(i));
                values.put("CHECKLIST_TYPE", data.getTYPE().get(i));

                db.insert("ASSET_CHECKLIST", null, values);

            }

        } catch (Exception ex) {
            Log.d("Exc Insert ASSET",
                    ex.toString());
        }

    }

    //Insert Mapping Asset Checklist data
    public void insertMappingAssetChecklistData(MappingAssetChecklistGetterSetter data) {

        db.delete("MAPPING_ASSET_CHECKLIST", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getAsset_cd().size(); i++) {

                values.put("ASSET_CD", Integer.parseInt(data.getAsset_cd().get(i)));
                values.put("CHECKLIST_ID", data.getCheck_list_id().get(i));

                db.insert("MAPPING_ASSET_CHECKLIST", null, values);

            }

        } catch (Exception ex) {
            Log.d("Exception Data ",
                    ex.toString());
        }

    }

    //Category Master data
    public void insertCategoryMasterData(CategoryMasterGetterSetter data) {

        db.delete("CATEGORY_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getCategory_cd().size(); i++) {

                values.put("CATEGORY_CD", Integer.parseInt(data.getCategory_cd().get(i)));
                values.put("CATEGORY", data.getCategory().get(i));
                values.put("HIMALAYA_PHOTO", data.getHIMALAYA_PHOTO().get(i));
                values.put("CATEGORY_PHOTO", data.getCATEGORY_PHOTO().get(i));

                db.insert("CATEGORY_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Data ",
                    ex.toString());
        }

    }

    //Non Working data
    public void insertNonWorkingReasonData(NonWorkingReasonGetterSetter data) {

        db.delete("NON_WORKING_REASON", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getReason_cd().size(); i++) {

                values.put("REASON_CD", Integer.parseInt(data.getReason_cd().get(i)));
                values.put("REASON", data.getReason().get(i));
                values.put("ENTRY_ALLOW", data.getEntry_allow().get(i));

                db.insert("NON_WORKING_REASON", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Non Working Data ",
                    ex.toString());
        }

    }

    //Salary Pay Slip data
    public void insertPaySlipdata(PayslipGetterSetter data) {
        db.delete("EMP_SALARY", null, null);
        ContentValues values = new ContentValues();

        try {
            values.put("SALARY_MONTH", data.getMONTH().get(0));
            values.put("SALARY_YEAR", data.getSALARY_YEAR().get(0));
            values.put("ECODE", data.getECODE().get(0));
            values.put("EMP_NAME", data.getEMP_NAME().get(0));
            values.put("PAYMENT_MODE", data.getPAYMENT_MODE().get(0));
            values.put("PRESENT_DAYS", data.getPRESENT_DAYS().get(0));
            values.put("INCENTIVE", data.getINCENTIVE().get(0));
            values.put("NATIONAL_H", data.getNATIONAL_H().get(0));
            values.put("TOTAL_EARNING", data.getTOTAL_EARNING().get(0));
            values.put("PF", data.getPF().get(0));
            values.put("ESI", data.getESI().get(0));
            values.put("PT", data.getPT().get(0));
            values.put("LWF", data.getLWF().get(0));
            values.put("MIS_DEDUCTION", data.getMIS_DEDUCTION().get(0));
            values.put("TDS", data.getTDS().get(0));
            values.put("TAKE_HOME", data.getTAKE_HOME().get(0));

            db.insert("EMP_SALARY", null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Non Working Data ", ex.toString());
        }
    }

    //Audit Question Data
    public void insertAuditQuestionData(Audit_QuestionGetterSetter data) {
        db.delete("AUDIT_QUESTION_CATEGORYWISE", null, null);
        ContentValues values = new ContentValues();

        try {
            for (int i = 0; i < data.getQUESTION_ID().size(); i++) {
                values.put("QUESTION_ID", data.getQUESTION_ID().get(i));
                values.put("QUESTION", data.getQUESTION().get(i));
                values.put("ANSWER_ID", data.getANSWER_ID().get(i));
                values.put("ANSWER", data.getANSWER().get(i));
                values.put("QUESTION_TYPE", data.getQUESTION_TYPE().get(i));
                values.put("CATEGORY_ID", data.getCATEGORY_ID().get(i));
                values.put("CATEGORY", data.getCATEGORY().get(i));

                db.insert("AUDIT_QUESTION_CATEGORYWISE", null, values);
            }
        } catch (Exception ex) {
            Log.d("Database Exception", " while Insert Audit Question Data " + ex.toString());
        }
    }

    //NON_COMPLIANCE_CHECKLIST
    public void insertNonComplianceChecklistData(NonComplianceChecklistGetterSetter data) {

        db.delete("NON_COMPLIANCE_CHECKLIST", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getCREASON_ID().size(); i++) {

                values.put("CREASON_ID", data.getCREASON_ID().get(i));
                values.put("CREASON", data.getCREASON().get(i));

                db.insert("NON_COMPLIANCE_CHECKLIST", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Non Comliance Data ",
                    ex.toString());
        }

    }

    //MAPPING_ASSET_CHECKLIST_REASON
    public void insertMappingAssetChecklistReasonData(MappingAssetChecklistreasonGetterSetter data) {

        db.delete("MAPPING_ASSET_CHECKLIST_REASON", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getCREASON_ID().size(); i++) {

                values.put("CREASON_ID", data.getCREASON_ID().get(i));
                values.put("CHECKLIST_ID", data.getCHECKLIST_ID().get(i));

                db.insert("MAPPING_ASSET_CHECKLIST_REASON", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Mapping Checklist Reason Data ",
                    ex.toString());
        }

    }

}
