package com.yadu.himalayamtnew.autoupdate;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yadu.himalayamtnew.R;
import com.yadu.himalayamtnew.constants.AlertandMessages;
import com.yadu.himalayamtnew.constants.CommonString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class AutoUpdateActivity extends AppCompatActivity {

    String path = "", p, s;

    ProgressBar progressBar;
    private boolean status;
    TextView tv_version;

    String app_ver,version_name;

    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    private Data data;

    String versionCode;
    int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();

        path = intent.getStringExtra(CommonString.KEY_PATH);
        status = intent.getBooleanExtra(CommonString.KEY_STATUS, false);

        tv_version = (TextView) findViewById(R.id.tv_version_code);

        try {
            app_ver = String.valueOf(getPackageManager().getPackageInfo(getPackageName(), 0).versionCode);
            version_name = String.valueOf(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);

            tv_version.setText("Version " + version_name);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        AlertandMessages alert = new AlertandMessages(AutoUpdateActivity.this);
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                SharedPreferences preferences = PreferenceManager
                        .getDefaultSharedPreferences(AutoUpdateActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

                new DownloadTask(AutoUpdateActivity.this).execute();
            }
        };

        alert.editorDeleteAlert(CommonString.KEY_NEW_UPDATE, runnable);
    }

    private class DownloadTask extends AsyncTask<Void, Data, String> {

        private Context context;

        DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("Download");
            dialog.setCancelable(false);
            dialog.show();

            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);

        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub

            boolean success_flag = true;
            String error_msg = "";

            try {
                data = new Data();
                data.name = "Downloading Application";
                publishProgress(data);

                versionCode = getPackageManager().getPackageInfo(
                        getPackageName(), 0).versionName;

                data.name = "Upgraditing Version : " + versionCode;
                publishProgress(data);

                // download application
                URL url = new URL(path);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                // c.setDoOutput(true);
                c.getResponseCode();
                c.connect();
                length = c.getContentLength();

                String size = new DecimalFormat("##.##")
                        .format((double) ((double) length / 1024) / 1024)
                        + " MB";

                String PATH = Environment.getExternalStorageDirectory()
                        + "/download/";
                File file = new File(PATH);
                file.mkdirs();
                File outputFile = new File(file, "app.apk");
                FileOutputStream fos = new FileOutputStream(outputFile);

                InputStream is = c.getInputStream();

                int bytes = 0;
                byte[] buffer = new byte[1024];
                int len1 = 0;

                while ((len1 = is.read(buffer)) != -1) {

                    bytes = (bytes + len1);

                    s = new DecimalFormat("##.##")
                            .format((double) ((double) (bytes / 1024)) / 1024);

                    p = s.length() == 3 ? s + "0" : s;

                    p = p + " MB";
                    data.value = (int) ((double) (((double) bytes) / length) * 100);

                    data.name = "Download " + p + "/" + size;
                    publishProgress(data);

                    fos.write(buffer, 0, len1);

                }
                fos.close();
                is.close();

                return CommonString.KEY_SUCCESS;

            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block

                success_flag = false;
                error_msg = CommonString.MESSAGE_EXCEPTION;

            } catch (MalformedURLException e) {

                success_flag = false;
                error_msg = CommonString.MESSAGE_EXCEPTION;

            } catch (IOException e) {

                success_flag = false;
                error_msg = CommonString.MESSAGE_SOCKETEXCEPTION;

            } catch (Exception e) {

                success_flag = false;
                error_msg = CommonString.MESSAGE_EXCEPTION;
            }

            if(success_flag){
                return CommonString.KEY_SUCCESS;
            }
            else {
                return error_msg;
            }

        }

        @Override
        protected void onProgressUpdate(Data... values) {
            // TODO Auto-generated method stub

            pb.setProgress(values[0].value);
            percentage.setText(values[0].value + "%");
            message.setText(values[0].name);

        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            dialog.dismiss();

            if (result.equals(CommonString.KEY_SUCCESS)) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory()
                                + "/download/"
                                + "app.apk")),
                        "application/vnd.android.package-archive");
                startActivity(i);

                AutoUpdateActivity.this.finish();
            }

        }

    }

    class Data {
        int value;
        String name;
    }
}
