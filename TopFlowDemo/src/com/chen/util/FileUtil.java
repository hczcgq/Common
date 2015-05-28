package com.chen.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.MediaColumns;

public class FileUtil {

    /**
     * 判断SD是否可以
     * 
     * @return
     */
    public static boolean isSdcardExist() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * 判断文件是存在
     * 
     * @return
     */
    public static boolean isFileExist(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 创建根目录
     * 
     * @param path
     *            目录路径
     */
    public static void createDirFile(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 创建文件
     * 
     * @param path
     *            文件路径
     * @return 创建的文件
     */
    public static File createNewFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return null;
            }
        }
        return file;
    }

    /**
     * 删除文件夹
     * 
     * @param folderPath
     *            文件夹的路径
     */
    public static void delFolder(String folderPath) {
        delAllFile(folderPath);
        String filePath = folderPath;
        filePath = filePath.toString();
        java.io.File myFilePath = new java.io.File(filePath);
        myFilePath.delete();
    }

    /**
     * 删除所有文件
     * 
     * @param path
     *            文件的路径
     */
    public static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);
                delFolder(path + "/" + tempList[i]);
            }
        }
    }

    /**
     * 通过Uri获取图片路径
     * 
     * @param uri
     * @param context
     * @return
     */
    public static String getPath(Uri uri, Context context) {
        String[] projection = { MediaColumns.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = ((Activity) context).managedQuery(uri, projection,
                null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    /**
     * 根据路径判断文件的格式
     * 
     * @param filename
     * @return
     */
    public static String[] discussType(String filename) {

        if (filename.toLowerCase().endsWith(".jpg")) {
            return new String[] { "jpg", "jpeg" };
        } else if (filename.toLowerCase().endsWith(".png")) {
            return new String[] { "png", "png" };
        } else if (filename.toLowerCase().endsWith(".gif")) {
            return new String[] { "gif", "gif" };
        } else {
            return new String[] {};
        }
    }

    /**
     * 检测文件类型
     * 
     * @param param
     * @return
     */
    public static boolean checkFileIsSpecileFile(String param) {
        File file = new File(param);
        /* 取得扩展名 */
        String end = file
                .getName()
                .substring(file.getName().lastIndexOf(".") + 1,
                        file.getName().length()).toLowerCase();
        System.out.println("end:" + end);
        if (end.equals("txt") || end.equals("doc") || end.equals("docx")
                || end.equals("xls") || end.equals("xlsx") || end.equals("ppt")
                || end.equals("pptx") || end.equals("pdf") || end.equals("rar")
                || end.equals("zip") || end.equals("jpg") || end.equals("png")
                || end.equals("gif") || end.equals("mp3") || end.equals("mp4")
                || end.equals("avi")) {
            return true;
        }
        return false;
    }

    /**
     * 获取文件名字
     * 
     * @param filename
     * @return
     */
    public static String getFileName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('/');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(dot + 1, filename.length());
            }
        }
        return filename;
    }

    /**
     * 
     * 获取指定文件大小
     * 
     * @param f
     * @return
     * @throws Exception
     */
    @SuppressWarnings("resource")
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        }
        return size;
    }

    /**
     * 
     * Long型转大小
     * 
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 打开文件
     * 
     * @param context
     * @param filePath
     *            路径
     */
    public static void openFile(Context context, String filePath) {
        Intent intent;
        if (filePath != null) {
            File file = new File(filePath);
            if (file.exists()) {
                /* 取得扩展名 */
                String end = file
                        .getName()
                        .substring(file.getName().lastIndexOf(".") + 1,
                                file.getName().length()).toLowerCase();
                /* 依扩展名的类型决定MimeType */
                if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
                        || end.equals("xmf") || end.equals("ogg")
                        || end.equals("wav")) {
                    try {
                        intent = getAudioFileIntent(filePath);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                        e.printStackTrace();
                    }
                } else if (end.equals("3gp") || end.equals("mp4")
                        || end.equals("avi")) {
                    try {
                        intent = getAudioFileIntent(filePath);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                        e.printStackTrace();
                    }
                } else if (end.equals("jpg") || end.equals("gif")
                        || end.equals("png") || end.equals("jpeg")
                        || end.equals("bmp")) {
                    try {
                        intent = getImageFileIntent(filePath);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                        e.printStackTrace();
                    }
                } else if (end.equals("apk")) {
                    try {
                        intent = getApkFileIntent(filePath);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                        e.printStackTrace();
                    }
                } else if (end.equals("ppt") || end.equals("pptx")) {
                    try {
                        intent = getPptFileIntent(filePath);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                        e.printStackTrace();
                    }
                } else if (end.equals("xls") || end.equals("xlsx")) {
                    try {
                        intent = getExcelFileIntent(filePath);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                        e.printStackTrace();
                    }
                } else if (end.equals("doc") || end.equals("docx")) {
                    try {
                        intent = getWordFileIntent(filePath);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                        e.printStackTrace();
                    }
                } else if (end.equals("pdf")) {
                    try {
                        intent = getPdfFileIntent(filePath);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                        e.printStackTrace();
                    }
                } else if (end.equals("chm")) {
                    try {
                        intent = getChmFileIntent(filePath);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                        e.printStackTrace();
                    }
                } else if (end.equals("txt")) {
                    try {
                        intent = getTextFileIntent(filePath, false);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                        e.printStackTrace();
                    }
                } else if (end.equals("zip") || end.equals("rar")) {
                    try {
                        intent = getAllIntent(filePath);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                        e.printStackTrace();
                    }
                } else {
                    ToastUtil.showToastShort(context, "无法打开，请安装相应的软件！");
                }
            }
        }
    }

    // Android获取一个用于打开所有文件的intent
    public static Intent getAllIntent(String param) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    // Android获取一个用于打开APK文件的intent
    public static Intent getApkFileIntent(String param) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    // Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    // Android获取一个用于打开AUDIO文件的intent
    public static Intent getAudioFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    // Android获取一个用于打开Html文件的intent
    public static Intent getHtmlFileIntent(String param) {

        Uri uri = Uri.parse(param).buildUpon()
                .encodedAuthority("com.android.htmlfileprovider")
                .scheme("content").encodedPath(param).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    // Android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "image/*");
        return intent;
    }

    // Android获取一个用于打开PPT文件的intent
    public static Intent getPptFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    // Android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    // Android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }

    // Android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    // Android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(String param, boolean paramBoolean) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
            Uri uri1 = Uri.parse(param);
            intent.setDataAndType(uri1, "text/plain");
        } else {
            Uri uri2 = Uri.fromFile(new File(param));
            intent.setDataAndType(uri2, "text/plain");
        }
        return intent;
    }

    // Android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(String param) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }
}
